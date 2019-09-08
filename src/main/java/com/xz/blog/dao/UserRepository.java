package com.xz.blog.dao;

import com.xz.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:05 2019/8/25
 * @Modified By:
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username, String password);

    User findUserById(Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.username = ?2 ,u.nickname = ?3 where  u.id = ?1")
    int updateById(Long id,String username, String nickname);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where  u.id = ?2")
    int updatePasswordById(String newPassword,Long id);
}
