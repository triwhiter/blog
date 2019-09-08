package com.xz.blog.web.admin;

import com.xz.blog.pojo.Comment;
import com.xz.blog.pojo.User;
import com.xz.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:09 2019/8/25
 * @Modified By:
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/login")
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,@RequestParam("verifyCode") String verifyCode, HttpSession session, RedirectAttributes attributes){
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "redirect:/admin/login";
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "redirect:/admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "redirect:/admin/login";
        }
        User user = userService.checkUser(username,password);
        if (user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/index";
        }else {
            session.setAttribute("errorMsg","用户名或密码错误");
            return "redirect:/admin/login";
        }
    }

    @GetMapping({"/index","", "/"})
    public String index(Model model){
        model.addAttribute("path", "index");
        model.addAttribute("categoryCount", typeService.getTotalTypes());
        model.addAttribute("blogCount", blogService.getTotalBlogs());
        model.addAttribute("linkCount", 0);
        model.addAttribute("tagCount", tagService.getTotalTags());
        model.addAttribute("commentCount", commentService.getTotalComments());
        return "admin/index";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/login";
    }
}
