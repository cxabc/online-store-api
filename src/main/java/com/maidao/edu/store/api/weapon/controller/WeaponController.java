package com.maidao.edu.store.api.weapon.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.weapon.model.Weapon;
import com.maidao.edu.store.api.weapon.qo.WeaponQo;
import com.maidao.edu.store.api.weapon.service.WeaponService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
@RequestMapping(value = "/weapon")
public class WeaponController extends BaseController{

    @Resource
    private WeaponService weaponService;

    @GetMapping(value = "/weapon")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView weapon(Integer id) {
        return feedback(weaponService.getById(id));
    }

    @GetMapping(value = "/all")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView weaponAll() {
        return feedback(weaponService.getAll());
    }

    @PostMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView weaponNew(Weapon weapon) {
        weaponService.saveWeapon(weapon);
        return feedback(true);
    }

    @PostMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView weaponDel(Integer id) {
        weaponService.deleteWeapon(id);
        return feedback(true);
    }

    @PostMapping(value = "/weapons")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView weaponQo(String weaponQo) {
        WeaponQo qo = parseModel(weaponQo, new WeaponQo());
        return feedback(weaponService.weapons(qo, true));
    }

}
