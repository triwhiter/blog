package com.xz.blog.service;

import com.xz.blog.pojo.Blog;
import com.xz.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 16:20 2019/8/25
 * @Modified By:
 */
public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog getAndConvert(Long id);

    List<Blog> listRecommendBlogTop(Integer size);

    Page<Blog> listBlog(String query,Pageable pageable);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    long getTotalBlogs();
}
