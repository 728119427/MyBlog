package com.seven.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.myblog.cache.IndexCache;
import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.model.Blog;
import com.seven.myblog.service.BlogService;
import com.seven.myblog.service.TagService;
import com.seven.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private IndexCache indexCache;

    @GetMapping("/tags/{id}")
    public String showType(@RequestParam(name = "page",defaultValue = "1") Integer page,
                           @RequestParam(name = "size",defaultValue = "5") Integer size,
                           @PathVariable("id") Long id, Model model)
    {
        List<TagDTO> tags = indexCache.getTags();
        model.addAttribute("tags",tags);
        if(id==-1l && tags.size()>0){
            id=tags.get(0).getId();
        }

        PageHelper.startPage(page,size);
        List<Blog> blogs = blogService.listUnionByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId",id);
        return "tags";

    }
}
