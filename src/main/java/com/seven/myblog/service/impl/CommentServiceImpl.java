package com.seven.myblog.service.impl;

import com.seven.myblog.mapper.BlogExtMapper;
import com.seven.myblog.mapper.CommentMapper;
import com.seven.myblog.model.Comment;
import com.seven.myblog.model.CommentExample;
import com.seven.myblog.model.User;
import com.seven.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BlogExtMapper blogExtMapper;
    private List<Comment> temps =new ArrayList<>();

    /**
     * 获取一级评论
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> listByBlogId(Long blogId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andBlogIdEqualTo(blogId).andParentCommentIdIsNull();
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        List<Comment> commentList = comments.stream().map(comment -> {
            commentExample.clear();
            commentExample.createCriteria().andParentCommentIdEqualTo(comment.getId());
            comment.setReplyComments(commentMapper.selectByExample(commentExample));
            return comment;
        }).collect(Collectors.toList());

        return combineChildren(commentList);
    }

    private List<Comment> combineChildren(List<Comment> commentList) {
        List<Comment> comments = commentList.stream().map(comment -> {
            List<Comment> replyComments = comment.getReplyComments();
            if (!ObjectUtils.isEmpty(replyComments)) {
                for (Comment replyComment : replyComments) {
                    //收集子评论
                    recursively(replyComment);
                }
            }
            //在recursively()方法中会把每一个一级评论的所有子评论放在临时集合temps中
            //先设置一下temps中所有子评论的parentComment,前端展示需要用到
            if(temps.size()>0){
              temps=  temps.stream().map(comment1 -> {
                    comment1.setParentComment(commentMapper.selectByPrimaryKey(comment1.getParentCommentId()));
                    return comment1;
                }).collect(Collectors.toList());
            }
            comment.setReplyComments(temps);
            //取完数据，置空
            temps = new ArrayList<>();
            return comment;
        }).collect(Collectors.toList());
        return comments;
    }

    private void recursively(Comment replyComment) {
        temps.add(replyComment);
        //先查找一下子评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentCommentIdEqualTo(replyComment.getId());
        replyComment.setReplyComments(commentMapper.selectByExample(commentExample));
        //获取二级评论的子评论
        List<Comment> replyComments = replyComment.getReplyComments();
        if(!ObjectUtils.isEmpty(replyComments)){
            for (Comment comment : replyComments) {
                recursively(comment);
            }
        }

    }

    /**
     * 插入评论
     * @param comment
     */
    @Override
    public void insertComment(Comment comment,User user ) {
        comment.setCreateTime(new Date());
        comment.setBlogId(comment.getBlog().getId());
        comment.setAvatar("/images/avatar.png");
        if(user!=null && comment.getNickname().equals(user.getNickname()) && comment.getEmail().equals(user.getEmail())){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }
        if(comment.getParentComment().getId()!=-1){
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        //插入评论
        commentMapper.insertSelective(comment);
        //更新评论数
        blogExtMapper.incCommentCount(comment.getBlogId());

    }
}
