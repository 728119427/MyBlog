package com.seven.myblog.service;

import com.seven.myblog.dto.BlogDTO;
import com.seven.myblog.model.Blog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface BlogService {

    /**
     * 查询博客总数
     * @return
     */
    Long countBlog();

    /**
     * 根据id查询blog
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 根据id查询关联的blog
     * @param id
     * @return
     */
    Blog getUnionBlogById(Long id);

    /**
     * 添加blog
     * @param blog
     */
    void insertBlog(Blog blog);

    /**
     * 更新blog
     * @param blog
     */
    void updateBlog(Blog blog);

    /**
     * 根据id删除blog
     * @param id
     */
    int deleteBlog(Long id);



    /**
     * 关联查询所有blog
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
     * 查询推荐的blogs
     * @return
     */
    List<Blog> recommendBlogs(Integer size);

    /**
     * 查询总的博客数量
     * @return
     */
    Integer blogCounts();

    /**
     * 根据搜索条件查询总的博客数量
     * @return
     */
    Integer blogCounts_search(BlogDTO blogDTO);

    /**
     * 将文本转换为html
     * @param id
     * @return
     */
     Blog getAndConvert(Long id);

    /**
     * 根据前端typeId查询分类页面显示的blog
     * @param id
     * @return
     */
    List<Blog> listUnionByTypeId(Long id);

    /**
     * 根据前端tagId查询分类页面显示的blog
     * @param id
     * @return
     */
    List<Blog> listUnionByTagId(Long id);

    /**
     * 获取归档数据
     * @return
     */
    Map<String,List<Blog>> archiveMap();
}
