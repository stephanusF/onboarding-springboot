package com.ecomindo.onboarding.testinghat.dto;

public class ResultMsgDTO {
    private String message;

    public ResultMsgDTO(){

    }

    public ResultMsgDTO(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

}
