package com.nhnacademy.store99.bookstore.user.controller;

import com.nhnacademy.store99.bookstore.user.dto.EmailDto;
import com.nhnacademy.store99.bookstore.user.dto.PasswordDto;
import com.nhnacademy.store99.bookstore.user.dto.SignUpDto;
import com.nhnacademy.store99.bookstore.user.service.MailService;
import com.nhnacademy.store99.bookstore.user.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/sign-up")
public class SignUpController {
    private final SignUpService signUpService;
    private final MailService mailService;

    @Autowired
    public SignUpController(SignUpService signUpService, MailService mailService) {
        this.signUpService = signUpService;
        this.mailService = mailService;
    }
    
    @PostMapping("/mailConfirm")
    public ResponseEntity<String> mailConfirm(@RequestBody EmailDto emailDto) {
        try {
            String code = mailService.sendSimpleMessage(emailDto.getEmail());
            System.out.println("인증코드 : " + code);
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/duplicateCheck")
    public ResponseEntity<Boolean> duplicateCheck(@RequestBody PasswordDto passwordDto) {
        boolean isDuplicate = signUpService.duplicateCheck(passwordDto.getPassword());
        return ResponseEntity.ok(isDuplicate);
    }

    @PostMapping("/access")
    public String signUp(@RequestBody SignUpDto signUpDto) {
        signUpService.signUp(signUpDto);
        return "Sign up successful";
    }

}
