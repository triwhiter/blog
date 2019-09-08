package com.xz.blog.web.admin;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 13:51 2019/9/7
 * @Modified By:
 */
@Controller
public class KaptchaController {

    /**
     * 1、验证码工具
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    private DefaultKaptcha captchaProducer;

    /**
     * 2、生成验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */

    @GetMapping("/common/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String verifyCode = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 3、校对验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/imgvrifyControllerDefaultKaptcha")
    public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest,
                                                         HttpServletResponse httpServletResponse) {
        ModelAndView andView = new ModelAndView();
        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
        String tryCode = httpServletRequest.getParameter("tryCode");
        System.out.println("rightCode:"+rightCode+" ———— tryCode:"+tryCode);
        if (!rightCode.equals(tryCode)) {
            andView.addObject("info", "错误的验证码");
            andView.setViewName("/admin/login");
        } else {
            andView.addObject("info", "登录成功");
            andView.setViewName("/admin/index");
        }
        return andView;
    }

}
