package com.xz.blog.web;

import com.xz.blog.service.TagService;
import com.xz.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 16:08 2019/8/30
 * @Modified By:
 */
@Controller
public class AboutShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("types", typeService.listTypeTop(4));
        model.addAttribute("tags", tagService.listTagTop(6));
        return "about";
    }
}
