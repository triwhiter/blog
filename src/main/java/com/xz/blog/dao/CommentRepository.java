package com.xz.blog.dao;

import com.xz.blog.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:48 2019/8/30
 * @Modified By:
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    Comment findCommentById(Long parentCommentId);
}
