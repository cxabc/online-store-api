package com.maidao.edu.store.common.file.controller;


import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import com.maidao.edu.store.common.file.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/adm/file")
public class AdmFileController extends BaseController {

    @Resource
    private FileService fileService;

    @RequestMapping(value = "/upload_token")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.NONE)
    public ModelAndView upload_token(String fileName, int fileSize) throws Exception {
        return feedback(fileService.uploadToken("f", fileName, fileSize, true));
    }

    @RequestMapping(value = "usr/upload_token")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView usrupload_token(String fileName, int fileSize) throws Exception {
        return feedback(fileService.uploadToken("f", fileName, fileSize, true));
    }

}
