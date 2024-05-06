package com.nhnacademy.store99.bookstore.user.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.user.dto.EmailDto;
import com.nhnacademy.store99.bookstore.user.dto.SignUpDto;
import com.nhnacademy.store99.bookstore.user.service.MailService;
import com.nhnacademy.store99.bookstore.user.service.SignUpService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinhyogyeom
 */
@RestController
@CrossOrigin
@RequestMapping("open/v1/users")
public class SignUpController {
    private final SignUpService signUpService;
    private final MailService mailService;

    @Autowired
    public SignUpController(SignUpService signUpService, MailService mailService) {
        this.signUpService = signUpService;
        this.mailService = mailService;
    }

    /**
     * 회원가입 메소드
     *
     * @param signUpDto
     * @return signUpDto(email, name, phoneNumber, userBirthDate)
     */
    @PostMapping("/sign-up")
    public ResponseEntity<CommonResponse<SignUpDto>> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        SignUpDto response = signUpService.signUp(signUpDto);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .resultMessage("Success")
                .build();
        CommonResponse<SignUpDto> commonResponse = CommonResponse.<SignUpDto>builder()
                .header(commonHeader)
                .result(response)
                .build();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(commonResponse);
    }

    /**
     * 이메일 인증 메소드
     *
     * @param emailDto
     * @return String
     */
    @PostMapping("/mailConfirm")
    public ResponseEntity<CommonResponse<String>> mailConfirm(@Valid @RequestBody EmailDto emailDto) {
        if (signUpService.duplicateCheck(emailDto.getEmail()).equals("true")) {
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.OK)
                    .resultMessage("Email Duplicate")
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .result("duplicateEmail")
                    .build();
            return ResponseEntity.ok(response);
        }
        try {
            String code = mailService.sendSimpleMessage(emailDto.getEmail());
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.OK)
                    .resultMessage("Mail confirmation request sent successfully.")
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .result(code)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .resultMessage("Error occurred: " + e.getMessage())
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}

