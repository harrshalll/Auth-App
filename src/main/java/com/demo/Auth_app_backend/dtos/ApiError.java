package com.demo.Auth_app_backend.dtos;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record ApiError(
        int status,
        String error,
        String message,
        String path,
        OffsetDateTime timeStamp
) {

    public static ApiError of(int status, String error,String message,String path) {
        return new ApiError(status,error,path,message,OffsetDateTime.now(ZoneOffset.UTC));
    }
    public static ApiError of(int status, String error,String message,String path,boolean noDateTime) {
        return new ApiError(status,error,path,message,null);
    }
}
