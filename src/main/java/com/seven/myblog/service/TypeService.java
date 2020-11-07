package com.seven.myblog.service;

import com.seven.myblog.dto.TypeDTO;
import com.seven.myblog.model.Type;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TypeService {

    /**
     * 根据id查询type
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 查询所有type
     * @return
     */
    List<Type> list();

    /**
     * 添加type
     * @param type
     */
    void insertType(Type type);

    /**
     * 更新type
     * @param type
     */
    void updateType(Type type);

    /**
     * 根据id删除type
     * @param id
     */
    int deleteType(Long id);

    /**
     * 获取博客最多的类型
     * @return
     */
    List<TypeDTO> getTopType();
}
