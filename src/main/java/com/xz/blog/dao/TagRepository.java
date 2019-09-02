package com.xz.blog.dao;

import com.xz.blog.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 16:25 2019/8/25
 * @Modified By:
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
    Tag findTagById(Long id);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

//    List<Tag> findAllById(Long id);
}
