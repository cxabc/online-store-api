package com.maidao.edu.store.api.coupon.service;

import com.maidao.edu.store.api.coupon.model.UserCoupon;
import com.maidao.edu.store.api.coupon.qo.UserCouponQo;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 20:43
 * @description: null
 **/
public interface UserCouponService {

    void save(UserCoupon userCoupon) throws ServiceException;

    void remove(Integer id) throws Exception;

    void used(Integer id) throws Exception;

    UserCoupon coupon(Integer id);

    List<UserCoupon> userCoupons(UserCouponQo qo);

    void checkCoupon() throws Exception;

}
