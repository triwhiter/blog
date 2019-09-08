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

    @Override
    public int updateName(Long id, String username, String nickname) {
        return userRepository.updateById(id,username,nickname);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public boolean updatePassword(User user, String originalPassword, String newPassword) {
            String originalPasswordMd5 = MD5Utils.code(originalPassword);
            String newPasswordMd5 = MD5Utils.code(newPassword);
        System.out.println(newPasswordMd5);
            //比较原密码是否正确
            User user1=userRepository.findUserById(user.getId());
            if (originalPasswordMd5.equals(user1.getPassword())) {
                //设置新密码并修改
                user.setPassword(null);
                if (userRepository.updatePasswordById(newPasswordMd5,user1.getId()) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        return false;
    }
}
