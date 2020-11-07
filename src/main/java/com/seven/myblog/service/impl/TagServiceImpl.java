package com.seven.myblog.service.impl;

import com.seven.myblog.mapper.TagMapper;
import com.seven.myblog.model.Tag;
import com.seven.myblog.model.TagExample;
import com.seven.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

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
    public List<Tag> listByTagIds(String ids) {
        List<Long> tagIds = convertToList(ids);
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andIdIn(tagIds);
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        return tags;
    }
}
