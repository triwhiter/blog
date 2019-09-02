package com.xz.blog.dao;

import com.xz.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 13:43 2019/8/25
 * @Modified By:
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
        Type findByName(String name);
        Type findTypeById(Long id);

        @Query("select t from Type t")
        List<Type> findTop(Pageable pageable);
}
