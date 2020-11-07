package com.seven.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.myblog.dto.BlogDTO;
import com.seven.myblog.model.Blog;
import com.seven.myblog.model.Type;
import com.seven.myblog.model.User;
import com.seven.myblog.service.BlogService;
import com.seven.myblog.service.TagService;
import com.seven.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 跳转blogs页面
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        Model model
    ){
        PageHelper.startPage(page,size);
        List<Blog> blogList = blogService.listUnion();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList, 5);
        List<Type> types = typeService.list();
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    /**
     * 搜索
     * @param page
     * @param size
     * @param blogDTO
     * @param model
     * @return
     */
    @RequestMapping("/blogs/search")
    public String search(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        BlogDTO blogDTO,
                        Model model
    ){
        if(!ObjectUtils.isEmpty(blogDTO.getTitle())){
            blogDTO.setTitle("%"+blogDTO.getTitle()+"%");
        }
        PageHelper.startPage(page,size);
        List<Blog> blogList = blogService.listUnion_search(blogDTO);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList, 5);
        List<Type> types = typeService.list();
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs::blogList";
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.list());
        model.addAttribute("tags", tagService.list());
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
       setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("blogs/{id}/input")
    public String editPage(@PathVariable("id") Long id, Model model){
        Blog blog = blogService.getUnionBlogById(id);
        blog.init();
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /**
     * 新增或编辑博客
     * @param blog
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String inputBlog(Blog blog, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(blog.getId()==null){
            //新增博客
            blog.setCommentCount(0);
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blog.setUser(user);
            blog.setUserId(user.getId());
            blog.setTypeId(blog.getType().getId());
            blogService.insertBlog(blog);
        }else {
            //更新博客
            blog.setUpdateTime(new Date());
            blog.setTypeId(blog.getType().getId());
            try{
                blogService.updateBlog(blog);
            }catch (IllegalArgumentException e){

            }
        }

        return "redirect:/admin/blogs";

    }

    /**
     * 删除博客
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable("id") Long id,Model model){
        try{
            blogService.deleteBlog(id);
            return "redirect:/admin/blogs";
        }catch (IllegalArgumentException e){
            model.addAttribute("message",e.getMessage());
            return "admin/blogs";
        }


    }
}
