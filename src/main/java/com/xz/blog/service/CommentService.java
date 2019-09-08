package com.xz.blog.service;

import com.xz.blog.pojo.Comment;

import java.util.List;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:45 2019/8/30
 * @Modified By:
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long id);

    Comment saveComment(Comment comment);

    long getTotalComments();
}
