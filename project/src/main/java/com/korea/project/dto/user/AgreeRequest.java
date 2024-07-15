package com.korea.project.dto.user;

import lombok.Data;

@Data
public class AgreeRequest {
	private boolean agreeTerms;
	private boolean agreePrivacy;
}
