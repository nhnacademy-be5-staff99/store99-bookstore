package com.nhnacademy.store99.bookstore.like.controller;

import com.nhnacademy.store99.bookstore.like.service.LikeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

}
