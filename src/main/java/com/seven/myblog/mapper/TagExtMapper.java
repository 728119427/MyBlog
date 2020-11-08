package com.seven.myblog.mapper;

import com.seven.myblog.model.Tag;
import com.seven.myblog.model.TagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagExtMapper {

    /**
     * 关联查询blog和tag，然后获取指定blogId的所有tag
     * @param id
     * @return
     */
    List<Tag> listTagByBlogId(Long id);
}