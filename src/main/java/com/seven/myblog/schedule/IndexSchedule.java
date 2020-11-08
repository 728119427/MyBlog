package com.seven.myblog.schedule;

import com.seven.myblog.cache.IndexCache;
import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.model.Blog;
import com.seven.myblog.service.BlogService;
import com.seven.myblog.service.TagService;
import com.seven.myblog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class IndexSchedule {
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private IndexCache indexCache;

    //每天刷新一次
    @Scheduled(fixedRate = 86400000)
    public void updateCache(){
        //获取最新数据
        List<TypeDTO> topType = typeService.getTopType();
        List<TagDTO> topTag = tagService.getTopTag();
        List<Blog> blogs = blogService.recommendBlogs(10);
        Map<String, List<Blog>> archiveMap = blogService.archiveMap();
        //更新缓存
        indexCache.setTypes(topType);
        indexCache.setTags(topTag);
        indexCache.setRecommendBlogs(blogs);
        indexCache.setArchiveMap(archiveMap);
        log.info("TopTypes: {} ",topType.stream().map(typeDTO -> typeDTO.getName()).collect(Collectors.joining("|")));
        log.info("TopTags: {} ",topTag.stream().map(tagDTO -> tagDTO.getName()).collect(Collectors.joining("|")));
        log.info("recommendBlogs:{} "+blogs.stream().map(blog -> blog.getTitle()).collect(Collectors.joining("|")));
    }
}
