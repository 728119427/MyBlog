package com.seven.myblog.service.impl;

import com.seven.myblog.dto.BlogDTO;
import com.seven.myblog.mapper.BlogExtMapper;
import com.seven.myblog.mapper.BlogMapper;
import com.seven.myblog.model.Blog;
import com.seven.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogExtMapper blogExtMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertBlog(Blog blog) {
        String tagIds = blog.getTagIds();
        //先插入blog返回id
        blogMapper.insertSelective(blog);
        //再插入blog_tag
        if(!StringUtils.isEmpty(tagIds)){
            List<Long> tags = convertToList(tagIds);
            BlogDTO blogDTO=new BlogDTO();
            for (int i = 0; i <tags.size() ; i++) {
                blogDTO.setBlogId(blog.getId());
                blogDTO.setTagId(tags.get(i));
                blogExtMapper.insertBlogTag(blogDTO);
            }
        }


    }

    /**
     * 匠strIds转换为List
     * @param ids
     * @return
     */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public void updateBlog(Blog blog) {
        Blog byPrimaryKey = blogMapper.selectByPrimaryKey(blog.getId());
        if(ObjectUtils.isEmpty(byPrimaryKey)){
            throw new IllegalArgumentException("该博客不存在或已删除！");
        }
        //先更新blog其他信息
        blogMapper.updateByPrimaryKeySelective(blog);

        //更新blog_tag,先查询出blog_tag原来的标签
        List<Long> old_tagIds = blogExtMapper.get_bt_tgId(blog.getId());
        String strTagIds = blog.getTagIds();
        List<Long> new_tagIds = convertToList(strTagIds);
        //特殊情况，现有为空，原来不为空，全删除
        if(ObjectUtils.isEmpty(new_tagIds) && !ObjectUtils.isEmpty(old_tagIds)){
            blogExtMapper.deleteBlogTag(blog.getId());
        }
        //原来为空，现有不为空，全部直接插入
        BlogDTO blogDTO=new BlogDTO();
        if(!ObjectUtils.isEmpty(new_tagIds) && ObjectUtils.isEmpty(old_tagIds)){
            for (int i = 0; i <new_tagIds.size() ; i++) {
                blogDTO.setBlogId(blog.getId());
                blogDTO.setTagId(new_tagIds.get(i));
                blogExtMapper.insertBlogTag(blogDTO);
            }
        }

        if(!ObjectUtils.isEmpty(new_tagIds) && !ObjectUtils.isEmpty(old_tagIds)){
            //两者都不为空，先删除原来有现在没有的
            List<Long> delete_ids = old_tagIds.stream().filter(tagId -> !new_tagIds.contains(tagId)).collect(Collectors.toList());
            if(delete_ids.size()>0){
                for (int i = 0; i < delete_ids.size(); i++) {
                    blogDTO.setBlogId(blog.getId());
                    blogDTO.setTagId(delete_ids.get(i));
                    blogExtMapper.delete_some_BlogTag(blogDTO);
                }
            }
            //最后添加现在有的，原来没有的
            List<Long> insertIds = new_tagIds.stream().filter(tagId -> !old_tagIds.contains(tagId)).collect(Collectors.toList());
            if(insertIds.size()>0){
                for (int i = 0; i < insertIds.size(); i++) {
                    blogDTO.setBlogId(blog.getId());
                    blogDTO.setTagId(insertIds.get(i));
                    blogExtMapper.insertBlogTag(blogDTO);
                }
            }
        }


    }


    @Override
    public int deleteBlog(Long id) {
        Blog byPrimaryKey = blogMapper.selectByPrimaryKey(id);
        if(ObjectUtils.isEmpty(byPrimaryKey)){
            throw new IllegalArgumentException("该博客不存在或已删除！");
        }else {
            return blogMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<Blog> listUnion() {
        return blogExtMapper.listUnion();
    }

    @Override
    public List<Blog> listUnion_search(BlogDTO blogDTO) {
        return blogExtMapper.listUnion_search(blogDTO);
    }

    @Override
    public Blog getUnionBlogById(Long id) {
        //注意这里可能根据id查不到东西，要抛出异常！！！！！！！！
         return blogExtMapper.getUnionBlogById(id);
    }
}