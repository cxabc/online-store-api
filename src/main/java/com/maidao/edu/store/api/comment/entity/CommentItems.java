package com.maidao.edu.store.api.comment.entity;

import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 17:44
 * @description: null
 **/
public class CommentItems {

    private List<String> img;
    private String commentTxt;

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getCommentTxt() {
        return commentTxt;
    }

    public void setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
    }
}
