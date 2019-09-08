package com.xz.blog.service;

import com.xz.blog.pojo.User;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:00 2019/8/25
 * @Modified By:
 */
public interface UserService {
    User checkUser(String username,String password);

    int updateName(Long id, String username, String nickname);

    User findById(Long id);

    boolean updatePassword(User user, String originalPassword, String newPassword);
}
