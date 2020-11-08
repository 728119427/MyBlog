package com.seven.myblog.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
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

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }


    private String tagsToIds(List<Tag> tags) {
      /*  if (!ObjectUtils.isEmpty(tags)) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }*/
      if(!ObjectUtils.isEmpty(tags)){
          //要把long先转换为string才行
          String strTagIds = tags.stream().map(s -> s.getId()).map(String::valueOf).collect(Collectors.joining(","));
          return strTagIds;
      }
      return "";

    }


}