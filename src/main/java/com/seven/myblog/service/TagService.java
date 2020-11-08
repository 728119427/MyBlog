package com.seven.myblog.service;

import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.model.Blog;
import com.seven.myblog.model.Tag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TagService {

    /**
     * 根据id查询tag
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 查询所有tag
     * @return
     */
    List<Tag> list();

    /**
     * 添加tag
     * @param tag
     */
    void insertTag(Tag tag);

    /**
     * 更新tag
     * @param tag
     */
    void updateTag(Tag tag);

    /**
     * 根据id删除tag
     * @param id
     */
    int deleteTag(Long id);



    /**
     * 获取博客最多的标签
     * @return
     */
    List<TagDTO> getTopTag();


    /**
     * 根据前端tagId查询分类页面显示的blog
     * @param id
     * @return
     */
    List<Blog> listUnionByTagId(Long id);


}
