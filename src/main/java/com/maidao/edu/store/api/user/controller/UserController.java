package com.maidao.edu.store.api.user.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.model.UserSessionWrap;
import com.maidao.edu.store.api.user.qo.UserQo;
import com.maidao.edu.store.api.user.qo.UserSessionQo;
import com.maidao.edu.store.api.user.service.UserServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.code.model.VCode;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private UserServiceImp userServiceImp;

    @RequestMapping(value ="/sign_in")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView sign_in(String unknown, String password, String vCode) throws Exception {
        UserSessionWrap userSessionWrap = userServiceImp.signIn(unknown, password, parseModel(vCode, new VCode()), getRemoteAddress());
        return feedback(userSessionWrap);
    }

    @RequestMapping(value ="/update_password")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView update_password(String mobile, String password, String newpassword, String vCode) throws Exception {
        userServiceImp.updatePassword(mobile, password, newpassword, parseModel(vCode, new VCode()));
        return feedback("success");
    }

    @RequestMapping(value ="/sing_up")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView sign_up(String user, String key, String smsCode) throws Exception {
        User model = parseModel(user, new User());
        userServiceImp.signUp(model, key, smsCode);
        return feedback("success");
    }

    @RequestMapping(value ="/forget_password")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView forget_password(String mobile, String vCode) throws Exception {
        return feedback(userServiceImp.forgetPassword(mobile, parseModel(vCode, new VCode())));
    }

    @RequestMapping(value ="/reset_password")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView reset_password(String mobile, String password, String key, String smsCode) throws Exception {
        userServiceImp.resetPassword(mobile, password, key, smsCode);
        return feedback("success");
    }

    @RequestMapping(value ="/find_alluser")
    @RequiredPermission(adminType = AdminType.NONE)
//    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView find_AllUser(String userQo) throws Exception {
        Page<User> users = userServiceImp.findAllUser(parseModel(userQo, new UserQo()));
        return feedback(users);
    }

    @RequestMapping(value ="/delete_byid")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView delete_ById(Integer id) throws Exception {
        System.out.println(id);
        userServiceImp.deleteById(id);
        return feedback("success");
    }

    @RequestMapping(value ="/find_byid")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView find_ById(Integer id) throws Exception {
        return feedback(userServiceImp.findById(id));
    }

    @RequestMapping(value ="/save")
    @RequiredPermission(adminType = AdminType.USER)
//    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView save(String user) throws Exception {
        userServiceImp.save(parseModel(user, new User()));
        return feedback(true);
    }

    @RequestMapping(value ="/profile")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView Profile() throws Exception {
        return feedback(userServiceImp.profile());
    }

    @RequestMapping(value ="/user_session")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_LIST)
    public ModelAndView userSession(String userSessionQo) throws Exception {
        return feedback(userServiceImp.userSession(parseModel(userSessionQo, new UserSessionQo())));
    }

    @RequestMapping("/modMobile")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView saveMobile(String mobile, String vCode) throws Exception {
        userServiceImp.modMibile(mobile, parseModel(vCode, new VCode()));
        return feedback(true);
    }


}
