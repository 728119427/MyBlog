package com.seven.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.myblog.cache.IndexCache;
import com.seven.myblog.dto.BlogDTO;
import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.model.Blog;
import com.seven.myblog.model.Type;
import com.seven.myblog.service.BlogService;
import com.seven.myblog.service.TagService;
import com.seven.myblog.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private IndexCache indexCache;

    @GetMapping("/")
    public String blogs(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        Model model
    ){
        PageHelper.startPage(page,size);
        List<Blog> blogList = blogService.listUnion();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList, 5);
        model.addAttribute("pageInfo",pageInfo);
        List<TypeDTO> types = indexCache.getTypes();
        List<TagDTO> tags = indexCache.getTags();
        List<Blog> recommendBlogs = indexCache.getRecommendBlogs();
        model.addAttribute("recommendBlogs",recommendBlogs);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blogCounts",blogService.blogCounts());
        return "index";
    }


    @GetMapping("/search")
    public String search(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                         @RequestParam(name = "query",required = false) String query,
                        Model model
    ){
        if(!StringUtils.isEmpty(query)){
            String[] split = query.split(" ");
            query = Arrays.stream(split).collect(Collectors.joining("|"));
        }
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setTitle(query);
        PageHelper.startPage(page,size);
        List<Blog> blogList = blogService.listUnion_search(blogDTO);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList, 5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("blogCounts",blogService.blogCounts_search(blogDTO));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model){
        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newBlog(Model model){
        List<Blog> newblogs = indexCache.getRecommendBlogs().stream().limit(3).collect(Collectors.toList());
        model.addAttribute("newblogs",newblogs);
        return "_fragments::newblogList";

    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
