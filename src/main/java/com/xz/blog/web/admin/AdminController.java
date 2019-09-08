package com.xz.blog.web.admin;

import com.xz.blog.pojo.User;
import com.xz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 19:39 2019/9/3
 * @Modified By:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("path", "profile");
        return "admin/profile";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpSession session, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        User user = (User) session.getAttribute("user");
        if (user==null){
            return "修改失败";
        }
        if (userService.updatePassword(user, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            session.removeAttribute("user");
            return "success";
        } else {
            return "修改失败";
        }
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpSession session, @RequestParam("username") String username,
                             @RequestParam("nickname") String nickname) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(nickname)) {
            return "参数不能为空";
        }
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        if (userService.updateName(id, username, nickname)!=0) {
            User user1 =userService.findById(id);
            session.setAttribute("user",user1);
            return "success";
        } else {
            return "修改失败";
        }
    }
}
