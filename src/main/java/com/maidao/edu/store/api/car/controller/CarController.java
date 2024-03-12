package com.maidao.edu.store.api.car.controller;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.api.car.service.CarServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 10:33
 * @description: null
 **/
@Controller
@RequestMapping(value = "/user/car")
public class CarController extends BaseController {

    @Resource
    private CarServiceImp carServiceImp;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String car) throws Exception {
        carServiceImp.save(parseModel(car, new Car()));
        return feedback(true);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        carServiceImp.remove(id);
        return feedback(true);
    }

    @RequestMapping(value = "/car")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView car(Integer id) throws Exception {
        carServiceImp.car(id);
        return feedback(true);
    }

    @RequestMapping(value = "/cars")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView cars() throws Exception {
        return feedback(carServiceImp.cars());
    }

    @RequestMapping(value = "/findbyids")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView findByIds(String ids) throws Exception {
        List<Car> cars = carServiceImp.findByIds(JSON.parseArray(ids, Integer.class));
        return feedback(cars);
    }
}
