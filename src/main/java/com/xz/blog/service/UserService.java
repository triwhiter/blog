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
}
