package com.hackathon.hcldemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebResponse<T> {
    private T data;
    private String errorMessage;
    private List<String> validationMessage;
    private String httpStatus;

    public WebResponse() {

    }
    public WebResponse(T data) {
        this.data = data;
    }

    public WebResponse(List<String> validationMessage) {
        this.validationMessage = validationMessage;
    }

    public WebResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
