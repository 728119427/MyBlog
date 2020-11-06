package com.seven.myblog.service.impl;

import com.seven.myblog.mapper.TypeMapper;
import com.seven.myblog.model.Tag;
import com.seven.myblog.model.Type;
import com.seven.myblog.model.TypeExample;
import com.seven.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;


    @Override
    public Type getType(Long id) {
        Type type = typeMapper.selectByPrimaryKey(id);
        return type;
    }

    @Override
    public List<Type> list() {
        TypeExample typeExample = new TypeExample();
        typeExample.setOrderByClause("id DESC");
        List<Type> types = typeMapper.selectByExample(typeExample);
        return types;
    }

    @Override
    public void insertType(Type type) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andNameEqualTo(type.getName());
        List<Type> selectByExample = typeMapper.selectByExample(typeExample);
        if(selectByExample.size()>0){
            throw new IllegalArgumentException("该分类已存在");
        }
        typeMapper.insertSelective(type);
    }

    @Override
    public void updateType(Type type) {
        Type byPrimaryKey = typeMapper.selectByPrimaryKey(type.getId());
        if(ObjectUtils.isEmpty(byPrimaryKey)){
            throw new IllegalArgumentException("该分类不存在");
        }
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andNameEqualTo(type.getName());
        List<Type> selectByExample = typeMapper.selectByExample(typeExample);
        if(selectByExample.size()>0){
            throw new IllegalArgumentException("该分类已存在");
        }
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public int deleteType(Long id) {

        Type byPrimaryKey = typeMapper.selectByPrimaryKey(id);
        if(ObjectUtils.isEmpty(byPrimaryKey)){
            throw new IllegalArgumentException("该分类不存在");
        }
        int i = typeMapper.deleteByPrimaryKey(id);
        System.out.println("删除返回结果 i= "+i);
        return i;
    }
}
