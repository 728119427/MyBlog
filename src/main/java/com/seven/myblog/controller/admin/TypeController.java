package com.seven.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.myblog.model.Type;
import com.seven.myblog.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * 跳转到分类页面
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        Model model
    ){
        PageHelper.startPage(page,size);
        List<Type> typeList = typeService.list();
        PageInfo<Type> pageInfo = new PageInfo<>(typeList,5);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,Model model){

        try{
            int res = typeService.deleteType(id);
            if(res==1){
                //删除成功
                return "redirect:/admin/types";
            }else {
                model.addAttribute("message","该分类下有博客，不能删除！");
                return "admin/types";
            }
        }catch (IllegalArgumentException e){
            model.addAttribute("message",e.getMessage());
            return "admin/types";
        }


    }

    /**
     * 跳转到新增页面
     * @return
     */
    @GetMapping("/types/input")
    public String inputPage(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 新增type
     * @param type
     * @return
     */
    @PostMapping("/types")
    public String input(Type type,Model model){
        if(StringUtils.isEmpty(type.getName())){
            model.addAttribute("message","分类名称不能为空");
            return "admin/types-input";
        }
        try{
            //添加成功
            typeService.insertType(type);
            return "redirect:/admin/types";
        }catch (IllegalArgumentException e){
            //添加失败
            model.addAttribute("message",e.getMessage());
            return "admin/types-input";
        }


    }

    /**
     * 跳转编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editPage(@PathVariable("id") Long id,Model model){
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    /**
     * 编辑type
     * @param type
     * @return
     */
    @PostMapping("/types/{id}")
    public String edit(Type type, Model model){
        if(StringUtils.isEmpty(type.getName())){
            model.addAttribute("message","分类名称不能为空");
            return "admin/types-input";
        }

        try{
            //编辑成功
            typeService.updateType(type);
            return "redirect:/admin/types";
        }catch (IllegalArgumentException e){
            //编辑失败
            model.addAttribute("message",e.getMessage());
            return "admin/types-input";
        }

    }
}
