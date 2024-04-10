package com.nhnacademy.store99.bookstore.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
    private String email;

    public EmailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
