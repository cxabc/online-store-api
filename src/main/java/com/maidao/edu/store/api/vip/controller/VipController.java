package com.maidao.edu.store.api.vip.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.vip.model.Vip;
import com.maidao.edu.store.api.vip.model.VipUser;
import com.maidao.edu.store.api.vip.qo.VipQo;
import com.maidao.edu.store.api.vip.qo.VipUserQo;
import com.maidao.edu.store.api.vip.service.VipServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 *
 **/
@Controller
@RequestMapping(value = "/adm/vip")
public class VipController extends BaseController {

    @Resource
    private VipServiceImp vipServiceImp;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView save(String vip) throws Exception {
        Vip exist = (parseModel(vip, new Vip()));
        vipServiceImp.save(exist);
        return feedback(true);
    }

    @RequestMapping(value = "/vip")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView vip(Integer id) throws Exception {
        return feedback(vipServiceImp.vip(id));
    }

    @RequestMapping(value = "/vips")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView vips(String vipQo) throws Exception {
        return feedback(vipServiceImp.vips(parseModel(vipQo, new VipQo())));
    }

    @RequestMapping(value = "/search")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView search(Integer grade) throws Exception {
        return feedback(vipServiceImp.search(grade));
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        vipServiceImp.remove(id);
        return feedback(true);
    }

    @RequestMapping(value = "/mod_status")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView modStatus(Integer id, Integer status) throws Exception {
        vipServiceImp.modStatus(id, status);
        return feedback(true);
    }

    @RequestMapping(value = "/vipUsers")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView vipUsers(String vipUserQo) throws Exception {
        return feedback(vipServiceImp.vipUsers(parseModel(vipUserQo, new VipUserQo())));
    }

    @RequestMapping(value = "/renew")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView renew(Integer id, String duration, String remark) throws Exception {
        vipServiceImp.renew(id, duration, remark);
        return feedback(true);
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    @RequestMapping(value = "/admNewVipUser")
    public ModelAndView admNewVipUser(String vipUser, String phone) throws Exception {
        vipServiceImp.admNewVipUser(parseModel(vipUser, new VipUser()), phone);
        return feedback();
    }

    @RequestMapping(value = "/expire")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView expire(Integer id) throws Exception {
        vipServiceImp.expire(id);
        return feedback(null);
    }

}
