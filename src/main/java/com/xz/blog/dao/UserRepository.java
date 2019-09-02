package com.xz.blog.dao;

import com.xz.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:05 2019/8/25
 * @Modified By:
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username, String password);
}
