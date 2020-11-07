package com.seven.myblog.service;

import com.seven.myblog.dto.BlogDTO;
import com.seven.myblog.model.Blog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BlogService {

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
}
