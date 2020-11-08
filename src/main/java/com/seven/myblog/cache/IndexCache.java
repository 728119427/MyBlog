package com.seven.myblog.cache;

import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.model.Blog;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class IndexCache {

    List<TypeDTO> types ;
    List<TagDTO> tags ;
    List<Blog> recommendBlogs;
    Map<String,List<Blog>> archiveMap;

}
