package com.seven.myblog.controller;

import com.seven.myblog.model.Blog;
import com.seven.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        Map<String, List<Blog>> archiveMap = blogService.archiveMap();
        model.addAttribute("archiveMap",archiveMap);
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
