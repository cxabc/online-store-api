package com.maidao.edu.store.api.comment.service;

import com.maidao.edu.store.api.comment.model.Comment;
import com.maidao.edu.store.api.comment.qo.CommentQo;
import org.springframework.data.domain.Page;

/**
 * @author: chen.star
 * @date: 2024/3/11 18:55
 * @description: null
 **/
public interface CommentService {
    void remove(Integer id);

    void save(Comment comment);

    Comment findComment(Integer id);

    Page<Comment> comments(CommentQo qo);
}
