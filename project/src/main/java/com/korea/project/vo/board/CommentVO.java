package com.korea.project.vo.board;

import lombok.Data;

@Data
public class CommentVO {
	private int commentIdx,//댓글 번호
				userIdx,
				boardIdx,//게시글 번호
				commentDepth,
				commentDel;
	private String commentContent,
				   userNickname,
				   commentWriteDate;
}
