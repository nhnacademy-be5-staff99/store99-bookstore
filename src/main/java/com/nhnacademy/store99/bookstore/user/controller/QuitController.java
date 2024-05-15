package com.nhnacademy.store99.bookstore.user.controller;


import com.nhnacademy.store99.bookstore.user.service.QuitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")

public class QuitController {
    private final QuitService quitService;

    public QuitController(QuitService quitService) {
        this.quitService = quitService;
    }

    @PostMapping("/quit")
    public Long quit(@RequestHeader("X-USER-ID") Long xUserId) {
        quitService.quit(xUserId);
        return xUserId;
    }
}
