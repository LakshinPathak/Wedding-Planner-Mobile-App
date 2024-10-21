package com.example.mos_innovative;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable throwable);
}