package com.saurav.cms.dto.person;

import lombok.Getter;

@Getter
public class InternalResponse<T> {
    private final String errorMessage;
    private final T data;

    public InternalResponse(String errorMessage, T data) {
        this.errorMessage = errorMessage;
        this.data = data;
    }


    public InternalResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.data = null;
    }
}
