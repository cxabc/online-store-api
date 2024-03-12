package com.maidao.edu.store.api.product.controller;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.product.model.Product;
import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.product.service.ProductServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author: chen.star
 * @date: 2024/3/11 15:34
 * @description: null
 **/
@Controller
@RequestMapping(value = "/adm/product")
public class ProductController extends BaseController {

    @Resource
    private ProductServiceImp productServiceImp;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView save(String product) throws Exception {
        productServiceImp.save(parseModel(product, new Product()));
        return feedback(true);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView remove(Integer id) throws Exception {
        productServiceImp.remove(id);
        return feedback(true);
    }

    @RequestMapping(value = "/product")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView product(Integer id) throws Exception {
        return feedback(productServiceImp.product(id));
    }

    @RequestMapping(value = "/products")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView products(String productQo) throws Exception {
        return feedback(productServiceImp.products(parseModel(productQo, new ProductQo()), true));
    }

    @RequestMapping(value = "/outsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView outSome(String ids) throws Exception {
        productServiceImp.outSome(JSON.parseArray(ids, Integer.class));
        return feedback(true);
    }

    @RequestMapping(value = "/putsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView putSome(String ids) throws Exception {
        productServiceImp.putSome(JSON.parseArray(ids, Integer.class));
        return feedback(true);
    }

    @RequestMapping(value = "/findbyids")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView findByIds(String ids) throws Exception {
        return feedback(productServiceImp.findByIds(JSON.parseArray(ids, Integer.class)));
    }

    @RequestMapping(value = "/findbycodes")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView findByCodes(String codes) throws Exception {
        return feedback(productServiceImp.findByIds(JSON.parseArray(codes, Integer.class)));
    }

    @RequestMapping(value = "/products_user")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView productsUser(String productQo) throws Exception {
        return feedback(productServiceImp.products(parseModel(productQo, new ProductQo()), true));
    }
}
