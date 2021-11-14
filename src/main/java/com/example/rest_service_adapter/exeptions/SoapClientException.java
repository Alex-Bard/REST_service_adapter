package com.example.rest_service_adapter.exeptions;

import org.springframework.http.HttpStatus;

public class SoapClientException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String title;
    private final String detail;

    public SoapClientException(HttpStatus httpStatus, String message,String title,String detail) {
        super(message);
        this.httpStatus = httpStatus;
        this.detail = detail;
        this.title = title;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
