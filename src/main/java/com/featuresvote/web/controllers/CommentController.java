package com.featuresvote.web.controllers;

import com.featuresvote.domain.Comment;
import com.featuresvote.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/products/{productId}/feature/{featureId}/comments")
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    @ResponseBody()
    public List<Comment> getComments(@PathVariable Long productId, @PathVariable Long featureId) {

        return commentRepository.findByFeatureId(featureId);
    }
}
