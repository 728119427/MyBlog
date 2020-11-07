package com.seven.myblog.service.impl;

import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.mapper.BlogMapper;
import com.seven.myblog.mapper.TypeMapper;
import com.seven.myblog.model.BlogExample;
import com.seven.myblog.model.Tag;
import com.seven.myblog.model.Type;
import com.seven.myblog.model.TypeExample;
import com.seven.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private BlogMapper blogMapper;


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
        if(i<1){
            throw new IllegalArgumentException("该分类下有博客，请先删除博客！");
        }
        return i;
    }

    @Override
    public List<TypeDTO> getTopType() {
        TreeSet<TypeDTO> typeDTOS = new TreeSet<>();
        List<Type> types = typeMapper.selectByExample(new TypeExample());
        for (Type type : types) {
            BlogExample blogExample = new BlogExample();
            blogExample.createCriteria().andTypeIdEqualTo(type.getId());
            //获取每一个类型的blog数量
            Integer typeCounts = (int)blogMapper.countByExample(blogExample);
            TypeDTO typeDTO = new TypeDTO(type.getId(),type.getName(), typeCounts);
            typeDTOS.add(typeDTO);
        }
        List<TypeDTO> list = new ArrayList<>();
        list.addAll(typeDTOS);
        return list;
    }
}
