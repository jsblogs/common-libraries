package com.jsblogs.ratelimiter.dto;

public class LimitExceededError {

    private String error;

    public LimitExceededError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
