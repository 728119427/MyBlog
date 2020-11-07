package com.seven.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.model.Blog;
import com.seven.myblog.model.Type;
import com.seven.myblog.service.BlogService;
import com.seven.myblog.service.TagService;
import com.seven.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String blogs(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        Model model
    ){
        PageHelper.startPage(page,size);
        List<Blog> blogList = blogService.listUnion();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList, 5);
        model.addAttribute("pageInfo",pageInfo);
        List<TypeDTO> types = typeService.getTopType();
        List<TagDTO> tags = tagService.getTopTag();
        List<Blog> recommendBlogs = blogService.recommendBlogs();
        model.addAttribute("recommendBlogs",recommendBlogs);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blogCounts",blogService.blogCounts());
        return "index";
    }
}
