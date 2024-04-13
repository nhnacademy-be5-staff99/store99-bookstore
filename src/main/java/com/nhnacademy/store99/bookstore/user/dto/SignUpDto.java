package com.nhnacademy.store99.bookstore.user.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class SignUpDto {

    private LocalDate userBirthDate;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

}
