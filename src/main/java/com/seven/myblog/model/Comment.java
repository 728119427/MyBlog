package com.seven.myblog.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Component
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Long blogId;
    private Long parentCommentId;
    private Boolean adminComment;
    //表关系
    private Blog blog;
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
}