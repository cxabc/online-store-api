package com.maidao.edu.store.api.coupon.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.coupon.model.Coupon;
import com.maidao.edu.store.api.coupon.qo.CouponQo;
import com.maidao.edu.store.api.coupon.service.CouponServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author: chen.star
 * @date: 2024/3/12 10:36
 * @description: null
 **/
@Controller
@RequestMapping("/adm/coupon")
public class CouponController extends BaseController {
    @Resource
    private CouponServiceImp couponServiceImp;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView save(String coupon) throws Exception {
        couponServiceImp.save(parseModel(coupon, new Coupon()));
        return feedback(true);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        couponServiceImp.remove(id);
        return feedback(true);
    }

    @RequestMapping(value = "/coupon")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView coupon(Integer id) throws Exception {
        return feedback(couponServiceImp.coupon(id));
    }

    @RequestMapping(value = "/coupons")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView coupons(String couponQo) throws Exception {
        return feedback(couponServiceImp.coupons(parseModel(couponQo, new CouponQo())));
    }

}
