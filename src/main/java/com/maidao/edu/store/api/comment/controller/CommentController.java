package com.maidao.edu.store.api.comment.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.comment.model.Comment;
import com.maidao.edu.store.api.comment.qo.CommentQo;
import com.maidao.edu.store.api.comment.service.CommentServiceImp;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author: chen.star
 * @date: 2024/3/11 19:35
 * @description: null
 **/
@Controller
@RequestMapping("/adm/comment")
public class CommentController extends BaseController {

    @Resource
    private CommentServiceImp commentServiceImp;

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    @RequestMapping(value = "/save")
    public ModelAndView save(String comment) throws Exception {
        commentServiceImp.save(parseModel(comment, new Comment()));
        return feedback(true);
    }

    @RequiredPermission(adminType = AdminType.NONE, adminPermission = AdminPermission.COMMENT_EDIT)
    @RequestMapping(value = "/remove")
    public ModelAndView remove(Integer id) throws Exception {
        commentServiceImp.remove(id);
        return feedback(true);
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    @RequestMapping(value = "/comment")
    public ModelAndView comment(Integer id) throws Exception {
        return feedback(commentServiceImp.findComment(id));
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COMMENT_EDIT)
    @RequestMapping(value = "/comments")
    public ModelAndView comments(String commentQo) throws Exception {
        return feedback(commentServiceImp.comments(parseModel(commentQo, new CommentQo())));
    }
}
