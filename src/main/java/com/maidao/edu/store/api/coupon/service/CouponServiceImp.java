package com.maidao.edu.store.api.coupon.service;

import com.maidao.edu.store.api.coupon.model.Coupon;
import com.maidao.edu.store.api.coupon.qo.CouponQo;
import com.maidao.edu.store.api.coupon.repository.CouponRepository;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 20:45
 * @description: null
 **/
@Service
public class CouponServiceImp implements CouponService{

    @Resource
    private CouponRepository couponRepository;

    @Override
    public void save(Coupon coupon) throws ServiceException {
        couponRepository.save(coupon);
    }

    @Override
    public void remove(Integer id) throws Exception {
        couponRepository.deleteById(id);
    }

    @Override
    public Coupon coupon(Integer id) {
        return couponRepository.findById(id).orElse(null);
    }

    @Override
    public List<Coupon> coupons(CouponQo qo) {
        return couponRepository.findAll(qo);
    }
}
