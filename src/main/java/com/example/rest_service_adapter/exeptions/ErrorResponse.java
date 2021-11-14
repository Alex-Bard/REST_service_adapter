package com.example.rest_service_adapter.exeptions;

public class ErrorResponse {
     private final String title;
     private final String datail;

    public ErrorResponse(String title, String datail) {
        this.title = title;
        this.datail = datail;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return datail;
    }
}
