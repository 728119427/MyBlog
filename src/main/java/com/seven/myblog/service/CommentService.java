package com.seven.myblog.service;

import com.seven.myblog.model.Comment;
import com.seven.myblog.model.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Transactional
public interface CommentService {

    /**
     * 查找所有blog对应的评论
     * @return
     */
    List<Comment> listByBlogId(Long blogId);

    /**
     * 插入commnet
     * @param comment
     */
    void insertComment(Comment comment, User user);
}
