package com.seven.myblog.mapper;

import com.seven.myblog.dto.BlogDTO;
import com.seven.myblog.model.Blog;
import com.seven.myblog.model.BlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogExtMapper {
    /**
     * 关联查询blog
     * @return
     */
    List<Blog> listUnion();

    /**
     * 根据条件关联查询
     * @param blogDTO
     * @return
     */
    List<Blog> listUnion_search(BlogDTO blogDTO);

    /**
     * 插入blog_tag表
     */
    void insertBlogTag(BlogDTO blogDTO);


    /**
     * 根据id查询关联的blog
     * @param id
     * @return
     */
    Blog getUnionBlogById(Long id);

    /**
     * 根据blogId查询原来的对应的tagids
     * @param blogId
     * @return
     */
    List<Long> get_bt_tgId(Long blogId);

    /**
     * 根据blog_id删除blog_tag中的数据
     * @param blogId
     */
    void deleteBlogTag(Long blogId);

    /**
     * 根据blogDto删除部分tagId
     * @param blogDTO
     */
    void delete_some_BlogTag(BlogDTO blogDTO);

    /**
     * 在blog_tag根据tagId统计对应的blog数量
     * @param tagId
     * @return
     */
    Integer countByTagId(Long tagId);
}