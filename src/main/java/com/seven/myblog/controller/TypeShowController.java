package com.seven.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.mapper.BlogExtMapper;
import com.seven.myblog.model.Blog;
import com.seven.myblog.service.BlogService;
import com.seven.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String showType(@RequestParam(name = "page",defaultValue = "1") Integer page,
                           @RequestParam(name = "size",defaultValue = "5") Integer size,
                           @PathVariable("id") Long id, Model model)
    {
        List<TypeDTO> types = typeService.getTopType();
        model.addAttribute("types",types);
        if(id==-1l && types.size()>0){
            id=types.get(0).getId();
        }

        PageHelper.startPage(page,size);
        List<Blog> blogs = blogService.listUnionByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";

    }
}
