package com.seven.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.myblog.model.Tag;
import com.seven.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 跳转到标签页面
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String tags(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        Model model
    ){
        PageHelper.startPage(page,size);
        List<Tag> tagList = tagService.list();
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList,5);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,Model model){
        try{
            int res = tagService.deleteTag(id);
            if(res==1){
                //删除成功
                return "redirect:/admin/tags";
            }else {
                model.addAttribute("message","该标签下有博客，不能删除！");
                return "admin/tags";
            }
        }catch (IllegalArgumentException e){
            model.addAttribute("message",e.getMessage());
            return "admin/tags";
        }



    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/tags/input")
    public String inputPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * 新增tag
     * @param tag
     * @return
     */
    @PostMapping("/tags")
    public String input(Tag tag,Model model){
        if(StringUtils.isEmpty(tag.getName())){
            model.addAttribute("message","标签名称不能为空");
            return "admin/tags-input";
        }
        try{
            //添加成功
            tagService.insertTag(tag);
            return "redirect:/admin/tags";
        }catch (IllegalArgumentException e){
            //添加失败
            model.addAttribute("message",e.getMessage());
            return "admin/tags-input";
        }


    }

    /**
     * 跳转编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editPage(@PathVariable("id") Long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    /**
     * 编辑type
     * @param tag
     * @return
     */
    @PostMapping("/tags/{id}")
    public String edit(Tag tag, Model model){
        if(StringUtils.isEmpty(tag.getName())){
            model.addAttribute("message","标签名称不能为空");
            return "admin/tags-input";
        }
        
        try{
            //编辑成功
            tagService.updateTag(tag);
            return "redirect:/admin/tags";
        }catch (IllegalArgumentException e){
            //编辑失败
            model.addAttribute("message",e.getMessage());
            return "admin/tags-input";
        }

    }
}
