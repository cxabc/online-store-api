package com.maidao.edu.store.api.home;

import com.maidao.edu.store.api.banner.qo.BannerQo;
import com.maidao.edu.store.api.banner.service.BannerServiceImp;
import com.maidao.edu.store.api.comment.model.Comment;
import com.maidao.edu.store.api.comment.service.CommentServiceImp;
import com.maidao.edu.store.api.coupon.qo.CouponQo;
import com.maidao.edu.store.api.coupon.service.CouponServiceImp;
import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.product.service.ProductServiceImp;
import com.maidao.edu.store.api.sort.service.SortServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author: chen.star
 * @date: 2024/3/12 11:10
 * @description: null
 **/
@Controller
@RequestMapping(value = "/usr/home")
public class HomeController extends BaseController {

    @Resource
    private BannerServiceImp bannerServiceImp;

    @Resource
    private ProductServiceImp productServiceImp;

    @Resource
    private CouponServiceImp couponServiceImp;

    @Resource
    private SortServiceImp sortServiceImp;

    @Resource
    private CommentServiceImp commentServiceImp;


    @RequestMapping(value = "/banners")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView banners(String bannerQo) throws Exception {
        return feedback(bannerServiceImp.banners(parseModel(bannerQo, new BannerQo()), true));
    }

    @RequestMapping(value = "/products")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView products(String productQo) throws Exception {
        return feedback(productServiceImp.products(parseModel(productQo, new ProductQo()), true));
    }

    @RequestMapping(value = "/coupons")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView coupons(String couponQo) throws Exception {
        return feedback(couponServiceImp.coupons(parseModel(couponQo, new CouponQo())));
    }

    @RequestMapping(value = "/sorts")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView sorts() throws Exception {
        return feedback(sortServiceImp.sorts(true));
    }

    @RequestMapping(value = "/saveComment")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String comment) throws Exception {
        commentServiceImp.save(parseModel(comment, new Comment()));
        return feedback(true);
    }

    @RequestMapping(value = "/removeComment")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        commentServiceImp.remove(id);
        return feedback(true);
    }

}
