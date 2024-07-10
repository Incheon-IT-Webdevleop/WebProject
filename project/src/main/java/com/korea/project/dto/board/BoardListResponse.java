package com.korea.project.dto.board;

import lombok.Data;

@Data
public class BoardListResponse {

		private int boardView;
		private String boardIdx,
					   boardTitle,
					   userNickname,
					   boardWriteDate;
						
					   
					
}
