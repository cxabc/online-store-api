package com.maidao.edu.store.api.coupon.controller;

import com.maidao.edu.store.api.coupon.model.UserCoupon;
import com.maidao.edu.store.api.coupon.qo.UserCouponQo;
import com.maidao.edu.store.api.coupon.service.UserCouponServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author: chen.star
 * @date: 2024/3/12 11:31
 * @description: null
 **/
@Controller
@RequestMapping("/usr/coupon")
public class UserCouponController extends BaseController {

    @Resource
    private UserCouponServiceImp userCouponServiceImp;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String userCoupon) throws Exception {
        userCouponServiceImp.save(parseModel(userCoupon, new UserCoupon()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        userCouponServiceImp.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/used")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView used(Integer id) throws Exception {
        userCouponServiceImp.used(id);
        return feedback(null);
    }

    @RequestMapping(value = "/coupon")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView coupon(Integer id) throws Exception {
        return feedback(userCouponServiceImp.coupon(id));
    }

    @RequestMapping(value = "/usercoupons")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView usercoupons(String userCouponQo) throws Exception {
        return feedback(userCouponServiceImp.userCoupons(parseModel(userCouponQo, new UserCouponQo())));
    }
}
