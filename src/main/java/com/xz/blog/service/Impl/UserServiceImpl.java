package com.xz.blog.service.Impl;

import com.xz.blog.dao.UserRepository;
import com.xz.blog.pojo.User;
import com.xz.blog.service.UserService;
import com.xz.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:03 2019/8/25
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user= userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
