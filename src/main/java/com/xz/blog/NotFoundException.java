package com.xz.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:24 2019/8/24
 * @Modified By:
 */
@ResponseStatus(HttpStatus.NOT_FOUND)//这样子才能自动找404页面
public class NotFoundException extends RuntimeException {

    public NotFoundException(){

    }
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
