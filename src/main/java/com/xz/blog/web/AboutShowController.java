package com.xz.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 16:08 2019/8/30
 * @Modified By:
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
