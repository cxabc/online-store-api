package com.maidao.edu.store.api.user.service;

import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.model.UserConfig;
import com.maidao.edu.store.api.user.model.UserSession;
import com.maidao.edu.store.api.user.model.UserSessionWrap;
import com.maidao.edu.store.api.user.qo.UserQo;
import com.maidao.edu.store.api.user.qo.UserSessionQo;
import com.maidao.edu.store.api.user.repository.UserRepository;
import com.maidao.edu.store.api.user.repository.UserSessionRepository;
import com.maidao.edu.store.api.vip.repository.VipUserRepository;
import com.maidao.edu.store.common.code.model.CodeCache;
import com.maidao.edu.store.common.code.model.VCode;
import com.maidao.edu.store.common.code.service.VCodeService;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.CollectionUtil;
import com.maidao.edu.store.common.util.DateUtils;
import com.maidao.edu.store.common.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.maidao.edu.store.common.exception.ErrorCode.*;

/**
 * @author: jc.cp
 * @date: 2024/2/25 18:17
 * @description: TODO
 **/
@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserSessionRepository userSessionRepository;
    @Resource
    private UserConfig userConfig;
    @Resource
    private CodeCache codeCache;
    @Resource
    private VCodeService vCodeService;
    @Resource
    private VipUserRepository vipUserRepository;

    /*
     * 由于sms(短信服务)服务过期, 注册验证码功能失效, 无法注册
     */
    @Override
    public void signUp(User user, String key, String smsCode) throws Exception {
        if (!(codeCache.getMobile(key).equals(user.getMobile()))) {
            throw new ServiceException(ERR_USER_MOBILE_DIFFER);
        }
        if (!(smsCode.equals(codeCache.getCode(key)))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.isChinaMobile(user.getMobile())) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);// err.205 = 请输入正确的手机号
        }
        if (!StringUtils.isEmail(user.getEmail())) {
            throw new ServiceException(ERR_USER_EMAIE_INVALID);
        }
        if (!StringUtils.validateNick(user.getNick())) {
            throw new ServiceException(ERR_USER_NICK_FORMAT);
        }
        if (!StringUtils.validatePassword(user.getPassword())) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        if (userRepository.findByMobile(user.getMobile()) != null) {
            throw new ServiceException(ERR_USER_MOBILE_USED);
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ServiceException(ERR_USER_EMAIE_USED);
        }
        user.setPassword(StringUtils.encryptPassword(user.getPassword(), userConfig.getSalt()));
        user.setCreatedAt(System.currentTimeMillis());
        userRepository.save(user);
    }

    @Override
    public UserSessionWrap signIn(String unknown, String password, VCode vCode, String ip) throws Exception {
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);// err.201 = 请输入正确的验证码
        }
        if (!StringUtils.validatePassword(password)) {// 验证 validate
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);// err.208 = 密码必须是6-18位
        }
        if (!StringUtils.isChinaMobile(unknown) && !StringUtils.isEmail(unknown) && !StringUtils.validateNick(unknown)) {// 验证 validate
            throw new ServiceException(ERR_USER_NOTEXIST);// err.211 = 账号不存在
        }
        User user = null;
        if (StringUtils.isChinaMobile(unknown)) {
            user = userRepository.findByMobile(unknown);
        } else if (StringUtils.isEmail(unknown)) {
            user = userRepository.findByEmail(unknown);
        } else if (StringUtils.validateNick(unknown)) {
            user = userRepository.findByNick(unknown);
        }
        if (user == null) {
            throw new ServiceException(ERR_USER_NOTEXIST);// err.211 = 账号不存在
        }// 加密 encrypt
        if (!((StringUtils.encryptPassword(password, userConfig.getSalt())).equals(user.getPassword()))) {
            throw new ServiceException(ERR_USER_PASSWORD_ERROR);// err.212 = 密码不正确
        }
        UserSession userSession = new UserSession();
        userSession.setUserId(user.getId());
        userSession.setToken(StringUtils.getRandStr(64));
        // System.currentTimeMillis(): 这个静态方法返回时间差，以毫秒为单位。通常用于计算时间间隔或记录时间点
        Long now = System.currentTimeMillis();
        userSession.setSignInAt(now);
        userSession.setExpireAt(DateUtils.addDays(now, userConfig.getSessionDays()));
        userSession.setIp(ip);
        userSessionRepository.save(userSession);
        user.setVipUser(vipUserRepository.findByUserId(user.getId()));
        return new UserSessionWrap(user, userSession);
    }

    @Override
    public UserSession findByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return userSessionRepository.findByToken(token);
    }

    @Override
    public String forgetPassword(String mobile, VCode vCode) throws Exception {
        if (!StringUtils.isChinaMobile(mobile)) {// 验证 validate
            throw new ServiceException(ERR_USER_NOTEXIST);// err.211 = 账号不存在
        }
        if (userRepository.findByMobile(mobile) == null) {
            throw new ServiceException(ERR_USER_NOTEXIST);// err.211 = 账号不存在
        }
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);// err.201 = 请输入正确的验证码
        }
        return mobile;
    }

    @Override
    public void resetPassword(String mobile, String password, String key, String smsCode) throws Exception {
        if (!(smsCode.equals(codeCache.getCode(key)))) {
            throw new ServiceException(ERR_VCODE_INVALID);// err.201 = 请输入正确的验证码
        }
        if (!StringUtils.validatePassword(password)) {// 验证 validate
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);// err.208 = 密码必须是6-18位
        }
        User user = userRepository.findByMobile(mobile);
        user.setPassword(StringUtils.encryptPassword(password, userConfig.getSalt()));
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String mobile, String password, String newpassword, VCode vCode) throws Exception {
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);// err.201 = 请输入正确的验证码
        }
        if (!StringUtils.validatePassword(password) || !StringUtils.validatePassword(newpassword)) {// 验证 validate
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);// err.208 = 密码必须是6-18位}
        }
        User user = userRepository.findByMobile(mobile);
        if (!((StringUtils.encryptPassword(password, userConfig.getSalt())).equals(user.getPassword()))) {
            throw new ServiceException(ERR_USER_PASSWORD_ERROR);// err.212 = 密码不正确
        }// 加密 encrypt
        user.setPassword(StringUtils.encryptPassword(newpassword, userConfig.getSalt()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Integer id) throws Exception {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<User> findAllUser(UserQo userQo) throws Exception {
        return userRepository.findAll(userQo);
    }

    @Override
    public Map profile() throws Exception {
        Integer userId = Contexts.requestUserId();
        User user = user(userId, true);
        return CollectionUtil.arrayAsMap("user", user);
    }

    @Override
    public User user(int id, boolean init) {
        User user = user(id);
        if (init) {
        }
        user.setVipUser(vipUserRepository.findByUserId(id));
        return user;
    }

    private User user(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public Page<UserSession> userSession(UserSessionQo qo) throws Exception {
        return userSessionRepository.findAll(qo);
    }

    @Override
    public void save(User user) throws ServiceException {
        if (!StringUtils.isChinaMobile(user.getMobile())) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);// err.205 = 请输入正确的手机号
        }
        User exist = userRepository.getOne(user.getId());
        exist.setNick(user.getNick());
        exist.setMobile(user.getMobile());
        exist.setEmail(user.getEmail());
        exist.setImg(user.getImg());
        userRepository.save(exist);
    }

    @Override
    public void modMibile(String mobile, VCode vCode) throws Exception {
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);// err.201 = 请输入正确的验证码
        }
        if (!StringUtils.isChinaMobile(mobile)) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);// err.205 = 请输入正确的手机号
        }
        if (userRepository.findByMobile(mobile) != null) {
            throw new ServiceException(ERR_USER_MOBILE_USED);// err.209 = 该手机号已被注册
        }
        User user = userRepository.findById(Contexts.requestUserId()).get();
        user.setMobile(mobile);
        userRepository.save(user);
    }
}
