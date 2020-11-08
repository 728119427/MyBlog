package com.seven.myblog.service.impl;

import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.dto.TagDTO;
import com.seven.myblog.mapper.BlogExtMapper;
import com.seven.myblog.mapper.TagMapper;
import com.seven.myblog.model.*;
import com.seven.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BlogExtMapper blogExtMapper;

    @Override
    public Tag getTag(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tag> list() {
        TagExample tagExample = new TagExample();
        tagExample.setOrderByClause("id DESC");
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        return tags;
      
    }

    @Override
    public void insertTag(Tag tag) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andNameEqualTo(tag.getName());
        List<Tag> selectByExample = tagMapper.selectByExample(tagExample);
        if(selectByExample.size()>0){
            throw new IllegalArgumentException("该标签已存在");
        }
        tagMapper.insertSelective(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        Tag byPrimaryKey = tagMapper.selectByPrimaryKey(tag.getId());
        if(ObjectUtils.isEmpty(byPrimaryKey)){
            throw new IllegalArgumentException("该标签不存在");
        }
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andNameEqualTo(tag.getName());
        List<Tag> selectByExample = tagMapper.selectByExample(tagExample);
        if(selectByExample.size()>0){
            throw new IllegalArgumentException("该标签已存在");
        }
        tagMapper.updateByPrimaryKey(tag);
    }

    @Override
    public int deleteTag(Long id) {
        Tag byPrimaryKey = tagMapper.selectByPrimaryKey(id);
        if(ObjectUtils.isEmpty(byPrimaryKey)){
            throw new IllegalArgumentException("该标签不存在");
        }
        int i = tagMapper.deleteByPrimaryKey(id);
        System.out.println("删除返回结果 i= "+i);
        return i;
    }



    @Override
    public List<TagDTO> getTopTag() {

        TreeSet<TagDTO> tagDTOS = new TreeSet<>();
        List<Tag> tags = tagMapper.selectByExample(new TagExample());
        for (Tag tag : tags) {
          
            //获取每一个类型的blog数量
            Integer tagCounts = blogExtMapper.countByTagId(tag.getId());
            TagDTO tagDTO = new TagDTO(tag.getId(),tag.getName(), tagCounts);
            tagDTOS.add(tagDTO);
        }
        List<TagDTO> list = new ArrayList<>();
        list.addAll(tagDTOS);
        return list;
        
    }
}
