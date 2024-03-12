package com.maidao.edu.store.api.coupon.repository;

import com.maidao.edu.store.api.coupon.model.UserCoupon;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 20:38
 * @description: null
 **/
public interface UserCouponRepository extends BaseRepository<UserCoupon, Integer> {
    UserCoupon findByCouponId(Integer couponId);

    List<UserCoupon> findByStatusAndExpirAtBefore(Integer status, Long expiredAt);
}
