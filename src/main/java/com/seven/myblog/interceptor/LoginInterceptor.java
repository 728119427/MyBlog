package com.seven.myblog.interceptor;

import com.seven.myblog.model.User;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if(ObjectUtils.isEmpty(user)){
            //用户未登录
            response.sendRedirect(request.getContextPath()+"/admin/");
            return false;
        }
        return true;
    }
}
