package com.maidao.edu.store.api.comment.service;

import com.maidao.edu.store.api.comment.model.Comment;
import com.maidao.edu.store.api.comment.qo.CommentQo;
import com.maidao.edu.store.api.comment.repository.CommentRepository;
import com.maidao.edu.store.api.orders.model.Orders;
import com.maidao.edu.store.api.orders.repository.OrdersRepository;
import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.repository.UserRepository;
import com.maidao.edu.store.common.context.Contexts;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: chen.star
 * @date: 2024/3/11 18:55
 * @description: null
 **/
@Service
public class CommentServiceImp implements CommentService {

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private OrdersRepository orderRepository;

    @Resource
    private UserRepository userRepository;

    @Override
    public void remove(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void save(Comment comment) {
        Integer userId = Contexts.requestUserId();
        comment.setUserId(userId);
        orderRepository.findById(comment.getOrdersId()).ifPresent(orders -> orders.setStatus(4));
        comment.setCreateAt(System.currentTimeMillis());
        commentRepository.save(comment);
    }

    @Override
    public Comment findComment(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Comment> comments(CommentQo qo) {
        Page<Comment> comments = commentRepository.findAll(qo);
        Optional.ofNullable(comments).orElse(Page.empty())
                .stream()
                .filter(Objects::nonNull)
                .filter(comment -> comment.getOrdersId() != null)
                .filter(comment -> comment.getUserId() != null)
                .forEach(comment -> {
                    Orders order = orderRepository.findById(comment.getOrdersId()).orElse(null);
                    User user = userRepository.findById(comment.getUserId()).orElse(null);
                    comment.setOrders(order);
                    comment.setUser(user);
                });
        return comments;
    }
}
