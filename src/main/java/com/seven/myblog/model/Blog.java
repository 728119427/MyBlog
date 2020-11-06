package com.seven.myblog.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Blog {
    private Long id;
    private Boolean appreciation;
    private Boolean commentabled;
    private Date createTime;
    private String description;
    private String firstPicture;
    private String flag;
    private Boolean published;
    private Boolean recommend;
    private Boolean shareStatement;
    private String title;
    private Date updateTime;
    private Integer views;
    private Long typeId;
    private Long userId;
    private Integer commentCount;
    private String content;
    //表关系
    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private String tagIds;

}