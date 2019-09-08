package com.xz.blog.service;

import com.xz.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 13:39 2019/8/25
 * @Modified By:
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

    List<Type> listTypeTop(Integer size);

    Type getTypeByName(String name);

    List<Type> listType();

    long getTotalTypes();
}
