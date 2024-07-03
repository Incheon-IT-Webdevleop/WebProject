package com.korea.project.dto.board;

import lombok.Data;

@Data
public class BoardCatogoryRequest {
	private String 	boardBigArea, // 카테고리-시,도
					boardSmallArea;// 카테고리 - 시, 군, 구
}
