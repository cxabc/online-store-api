package com.maidao.edu.store.api.sort.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.sort.model.Sort;
import com.maidao.edu.store.api.sort.service.SortService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/adm/sort")
public class SortController extends BaseController {
    @Autowired
    private SortService sortService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView save(String sort) throws Exception {
        sortService.save(parseModel(sort, new Sort()));
        return feedback(null);
    }

    @RequestMapping(value = "/sorts")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView sorts() {
        return feedback(sortService.sorts(true));
    }

    @RequestMapping(value = "/status")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView statusExamQuestionType(Integer id, Byte status) throws ServiceException {
        sortService.status(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView removeExamQuestionType(Integer id) throws ServiceException {
        sortService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/sort")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.SORT_EDIT)
    public ModelAndView sort(Integer id) throws Exception {
        return feedback(sortService.sort(id));
    }

}
