package com.maidao.edu.store.api.vip.service;

import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.repository.UserRepository;
import com.maidao.edu.store.api.user.service.UserServiceImp;
import com.maidao.edu.store.api.vip.entity.Duration;
import com.maidao.edu.store.api.vip.entity.PriceRule;
import com.maidao.edu.store.api.vip.model.Vip;
import com.maidao.edu.store.api.vip.model.VipUser;
import com.maidao.edu.store.api.vip.qo.VipQo;
import com.maidao.edu.store.api.vip.qo.VipUserQo;
import com.maidao.edu.store.api.vip.repository.VipRepository;
import com.maidao.edu.store.api.vip.repository.VipUserRepository;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.mail.MailHelper;
import com.maidao.edu.store.common.mail.MailService;
import com.maidao.edu.store.common.task.ApiTask;
import com.maidao.edu.store.common.task.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.maidao.edu.store.common.exception.ErrorCode.*;

/**
 **/
@Service
public class VipServiceImp implements VipService{

    @Resource
    private VipRepository vipRepository;

    @Resource
    private VipUserRepository vipUserRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserServiceImp userServiceImp;

    @Resource
    private TaskService taskService;

    @Resource
    private MailService mailService;

    @Override
    public void save(Vip vip) throws ServiceException {
        if (vipRepository.findByName(vip.getName()) != null) {
            throw new ServiceException(ERR_VIP_NAME_EXIST);
        }
        if (vipRepository.findByGrade(vip.getGrade()) != null) {
            throw new ServiceException(ERR_VIP_NAME_EXIST);
        }
        vipRepository.save(vip);
    }

    @Override
    public void remove(Integer id) throws Exception {
        vipRepository.deleteById(id);
    }

    @Override
    public Vip vip(Integer id) {
        /*
         * findById(id)与getOne(id)这两种方法有啥异同
         * findById(id)它返回的是一个 Optional<T> 对象，表示可能存在或者不存在的实体对象。是一个立即加载的方法
         * getOne(id)它返回的是实体对象本身，而不是 Optional 对象。它是一个延迟加载的方法
         */
        if (vipRepository.findById(id).isPresent()) {
            return vipRepository.findById(id).get();
        }
        return null;
        // return vipRepository.getOne(id);
    }

    @Override
    public List<Vip> vips(VipQo qo) {
        return vipRepository.findAll(qo);
    }

    @Override
    public Vip search(Integer grade) {
        return vipRepository.findByGrade(grade);
    }

    @Override
    public void modStatus(Integer id, Integer status) {
        vipRepository.modStatusById(id, status);
    }

    @Override
    public Page<VipUser> vipUsers(VipUserQo qo) {
        Page<VipUser> vipUsers = vipUserRepository.findAll(qo);
        Optional.ofNullable(vipUsers).orElse(Page.empty())
                .stream()
                .filter(VipUser -> VipUser.getUserId() != null)
                .filter(VipUser -> VipUser.getVipId() != null)
                .forEach(v -> {
                    boolean exist = userRepository.findById(v.getUserId()).isPresent();
                    if (exist) {
                        v.setUser(userRepository.findById(v.getUserId()).get());
                    }
                    exist = vipRepository.findById(v.getVipId()).isPresent();
                    if (exist) {
                        v.setVip(vipRepository.findById(v.getVipId()).get());
                    }
                });
        return vipUsers;
    }

    @Override
    public void renew(Integer id, String duration, String remark) {
        if (vipUserRepository.findById(id).isPresent()) {
            VipUser vipUser = vipUserRepository.findById(id).get();
            vipUser.setExpireAt(parseDuration(vipUser, duration));
            vipUserRepository.save(vipUser);
        }
    }

    private long parseDuration(VipUser vipUser, String duration) throws ServiceException {
        Duration dur = Duration.parse(duration);
        if (dur == null || dur.forever()) {
            throw new ServiceException(ERR_BAD_DURATION);// err.300 = 续费周期填写错误
        }
        long now = System.currentTimeMillis();// 到期Expire
        return dur.addDate(new Date(vipUser.getExpireAt() > now ? vipUser.getExpireAt() : now)).getTime();
    }

    @Override
    public void admNewVipUser(VipUser vipUser, String phone) throws Exception {
        if (phone == null) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);// err.205 = 请输入正确的手机号
        }
        User user = userRepository.findByMobile(phone);
        if (user == null) {
            throw new ServiceException(ERR_USER_NOTEXIST);// err.211 = 账号不存在
        }
        if (vipUserRepository.findByUserId(user.getId()) != null) {
            throw new ServiceException(ERR_VIP_USER_EXIST);// err.301 = VIP用户已存在
        }
        if (vipUser.getGrade() == null) {
            throw new ServiceException(ERR_VIP_TYPE_NONE);// err.302 = VIP等级错误
        }
        Vip vip = vipRepository.findByGrade(vipUser.getGrade());
        if (vipUser.getDuration() == null) {
            throw new ServiceException(ERR_VIP_DURATION_NONE);// err.303 = 缺少开通周期
        }
        vipUser.setVipId(vip.getId());
        vipUser.setUserId(user.getId());
        vipUser.setCreateAt(System.currentTimeMillis());
        vipUser.setExpireAt(System.currentTimeMillis());
        vipUser.setExpireAt(System.currentTimeMillis());
        vipUser.setExpireAt(parseDuration(vipUser, vipUser.getDuration()));
        vipUser.setRemark("管理员开通");
        vipUserRepository.save(vipUser);
    }

    @Override
    public void save(VipUser vipUser) throws ServiceException {
        vipUser.setUserId(Contexts.requestUserId());
        vipUser.setCreateAt(System.currentTimeMillis());
        vipUserRepository.save(vipUser);
    }

    @Override
    public void expire(Integer id) throws Exception {
        /*
         * findById(id)与getOne(id)这两种方法有啥异同
         * findById(id)它返回的是一个 Optional<T> 对象，表示可能存在或者不存在的实体对象。是一个立即加载的方法
         * getOne(id)它返回的是实体对象本身，而不是 Optional 对象。它是一个延迟加载的方法
         *
         * get() 方法用于获取 Optional 对象中包含的实际对象,如果 Optional 对象为空，则会抛出 NoSuchElementException 异常
         * 在调用 get() 方法之前最好先使用 isPresent() 方法检查 Optional 对象是否包含了非空的实际对象避免抛出 NoSuchElementException 异常
         */
        if (vipUserRepository.findById(id).isPresent()) {
            VipUser vipUser = vipUserRepository.findById(id).get();
            vipUser.setExpireAt(System.currentTimeMillis());
            vipUserRepository.save(vipUser);
        }
    }

    @Override
    public VipUser vipUser(Integer id) {
        return vipUserRepository.findByUserId(id);
    }

    @Override
    public VipUser userVip() {
        Integer userId = Contexts.requestUserId();
        VipUser vipUser = vipUserRepository.findByUserId(userId);
        if (vipUser == null || vipUser.getExpireAt() < System.currentTimeMillis()) {
            return null;
        } else {
            vipUser.setGrade(vip(vipUser.getVipId()).getGrade());
            vipUser.setName(vip(vipUser.getVipId()).getName());
            return vipUser;
        }
    }

    @Override
    public VipUser profile() {
        VipUser vipUser = vipUserRepository.findByUserId(Contexts.requestUserId());
        if (vipUser == null) {
            return null;
        } else {
            vipUser.setGrade(vip(vipUser.getVipId()).getGrade());
            vipUser.setName(vip(vipUser.getVipId()).getName());
            return vipUser;
        }
    }

    @Transient
    @Override
    public void newVip(Integer id, Integer sno) {
        vipCharge(id, sno, false);
    }

    @Transient
    @Override
    public void userRenew(Integer id, Integer sno) {
        vipCharge(id, sno, true);
    }

    private VipUser vipCharge(Integer id, Integer sno, boolean isRenew) {
        User user = userRepository.findById(Contexts.requestUserId()).get();
        Vip vip = vipRepository.findById(id).orElse(null);
        List<PriceRule> priceRule = vip.getPriceRule();
        PriceRule priceRule1 = priceRule.stream().filter(p -> p.getSno() == sno).findFirst().orElse(null);
        String duration = priceRule1.getDuration();
        VipUser vipUser = vipUserRepository.findByUserId(Contexts.requestUserId());
        if (isRenew) {
            vipUser.setExpireAt(parseDuration(vipUser, duration));
            vipUserRepository.save(vipUser);
        } else {
            if (vipUser != null) {
                vipUserRepository.deleteById(vipUser.getId());
            }
            vipUser = new VipUser();
            vipUser.setUserId(Contexts.requestUserId());
            vipUser.setVipId(id);
            vipUser.setCreateAt(System.currentTimeMillis());
            vipUser.setExpireAt(System.currentTimeMillis());
            vipUser.setExpireAt(parseDuration(vipUser, duration));
            vipUser.setRemark("用户开通");
            vipUser.setSno(sno);
            vipUserRepository.save(vipUser);
        }
        BigDecimal price = priceRule1.getPrice();
        if (price.compareTo(user.getBalance()) == 1) {
            throw new ServiceException(ERR_MONEY_LESS);
        } else {
            user.setBalance(user.getBalance().subtract(price));
            userRepository.save(user);
        }
        return vipUser;
    }

    private class vipEmail extends ApiTask {
        private User user;

        public vipEmail(User user) {
            super();
            this.user = user;
        }

        @Override
        protected void doApiWork() {
            MailHelper.MailInfo mail = new MailHelper.MailInfo();
            mail.setToAddress(user.getEmail());
            mail.setSubject("剑陈商城");
            mail.setContent("尊敬的用户：您的会员即将过期，请尽快续费哦!");
            mailService.send(mail);
        }
    }

}
