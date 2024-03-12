package com.maidao.edu.store.api.banner.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.banner.model.Banner;
import com.maidao.edu.store.api.banner.qo.BannerQo;
import com.maidao.edu.store.api.banner.service.BannerServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 **/
@Controller
@RequestMapping("/adm/banner")
public class BannerController extends BaseController {

    @Resource
    private BannerServiceImp bannerServiceImp;

    @RequestMapping("/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView save(String banner) throws Exception {
        bannerServiceImp.save(parseModel(banner, new Banner()));
        return feedback(true);
    }

    @RequestMapping("/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        bannerServiceImp.remove(id);
        return feedback(true);
    }

    @RequestMapping("/banner")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView banner(Integer id) throws Exception {
        return feedback(bannerServiceImp.banner(id));
    }

    @RequestMapping("/banners")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView banners(String bannerQo) throws Exception {
        return feedback(bannerServiceImp.banners(parseModel(bannerQo, new BannerQo()), true));
    }

    @RequestMapping("/outSome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView outSome(String ids) throws Exception {
        // JSON.parseArray(ids, Integer.class)将一个 JSON 格式的字符串数组解析为一个 Integer 类型的数组对象，
        // 这个方法通常用于处理 JSON 格式的字符串，将其转换为 Java 中的数据结构
        bannerServiceImp.outSome(parseArray(ids, Integer.class));
        return feedback(true);
    }

    @RequestMapping("/putSome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView putSome(String ids) throws Exception {
        bannerServiceImp.putSome(parseArray(ids, Integer.class));
        return feedback(true);
    }
}
