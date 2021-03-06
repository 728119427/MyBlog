package com.seven.myblog.controller.admin;

import com.seven.myblog.cache.IndexCache;
import com.seven.myblog.model.Blog;
import com.seven.myblog.model.User;
import com.seven.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private IndexCache indexCache;

    @GetMapping("/")
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpServletRequest request
                        ) {
        if(StringUtils.isEmpty(username)){
            model.addAttribute("message","用户名不能为空");
            return "admin/login";
        }

        if(StringUtils.isEmpty(password)){
            model.addAttribute("message","密码不能为空");
            return "admin/login";
        }

        User user = userService.login(username, password);
        if(!ObjectUtils.isEmpty(user)){
            //登录成功
            user.setPassword(null);
            request.getSession().setAttribute("user",user);
            return "admin/index";
        }else {
            //登录失败
            model.addAttribute("message","用户名或密码错误！");
            return "admin/login";
        }

    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/admin/";
    }

    @GetMapping("/footer/newblog")
    public String newBlog(Model model){
        List<Blog> newblogs = indexCache.getRecommendBlogs().stream().limit(3).collect(Collectors.toList());
        model.addAttribute("newblogs",newblogs);
        return "admin/_fragments::newblogList";

    }
}
