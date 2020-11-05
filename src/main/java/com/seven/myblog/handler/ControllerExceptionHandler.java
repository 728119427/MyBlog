package com.seven.myblog.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(HttpServletRequest request,Exception ex){
        log.error("requestUrl: {}, Exception : {}",request.getRequestURL(), ex.getMessage());
        ModelAndView mv = new ModelAndView();
//        mv.addObject("msg", ex.getMessage());
//        mv.addObject("url",request.getRequestURL());
        mv.setViewName("error/error");
        return mv;
    }
}
