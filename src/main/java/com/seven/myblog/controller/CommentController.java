package com.seven.myblog.controller;

import com.seven.myblog.model.Comment;
import com.seven.myblog.model.User;
import com.seven.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{id}")
    public String list(@PathVariable("id") Long id, Model model){
        List<Comment> comments = commentService.listByBlogId(id);
        model.addAttribute("comments",comments);
        return "blog::commentList";
    }

    @PostMapping("/comments")
    public String saveComment(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        commentService.insertComment(comment,user);
        return "redirect:/comments/"+comment.getBlog().getId();
    }
}
