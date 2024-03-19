package com.nhnacademy.store99.bookstore.response;

import lombok.*;
import org.springframework.http.HttpEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseData<T> extends HttpEntity<T> {
    private int statusCode;
    private String responseMessage;
    private T data;
    public ResponseData(final int statusCode, final String responseMessage){
        this.statusCode = statusCode;
        this.responseMessage=responseMessage;
        this.data=null;
    }
    public static<T> ResponseData<T> resp(final int statusCode, final String responseMessage){
        return resp(statusCode, responseMessage, null);
    }

    public static<T> ResponseData<T> resp(final int statusCode, final String responseMessage, final T t) {
        return ResponseData.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }
}
