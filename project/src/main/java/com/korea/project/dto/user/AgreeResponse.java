package com.korea.project.dto.user;

import lombok.Data;

@Data
public class AgreeResponse {
	private String result;
	private String message;

    public AgreeResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

}
