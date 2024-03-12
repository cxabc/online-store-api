package com.maidao.edu.store.api.address.controller;

import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.api.address.qo.AddressQo;
import com.maidao.edu.store.api.address.service.AddressServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 **/
@Controller
@RequestMapping(value = "/user/address")
public class AddressController extends BaseController {
    //1. extends BaseController 使用feedback(null);数据转换json 使用parseModel(addressQo, new AddressQo());数据转成类
    //2. ModelAndView 是 Spring MVC 中的一个类，用于封装处理器（Controller）处理请求后的视图名称和模型数据。它可以用于控制器方法处理完请求后，将数据和视图信息一起返回给客户端

    @Resource
    private AddressServiceImp addressServiceImp;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String address) throws Exception {
        addressServiceImp.save(parseModel(address, new Address()));
        return feedback(true);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        addressServiceImp.remove(id);
        return feedback(true);
    }

    @RequestMapping(value = "/address")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView address(Integer id) throws Exception {
        return feedback(addressServiceImp.address(id));
    }

    @RequestMapping(value = "/addresss")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView addresss(String addressQo) {
        AddressQo qo = parseModel(addressQo, new AddressQo());
        List<Address> addresss = addressServiceImp.addresss(qo, true);
        return feedback(addresss);
    }

    @RequestMapping(value = "/def")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView def(Integer id) throws Exception {
        addressServiceImp.def(id);
        return feedback(true);
    }
}
