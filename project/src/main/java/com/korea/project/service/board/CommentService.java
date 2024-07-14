package com.korea.project.service.board;

import java.util.List;

import com.korea.project.vo.board.CommentVO;

public interface CommentService {
	
	//댓글 저장
	public int save(final CommentVO params);
	
	//댓글 상세정보 조회
	public CommentVO findById(final int commentIdx);
	
	//댓글 수정
	public int update(final CommentVO params);
	
	//댓글 삭제
	public int deleteComment(final int commentIdx);
	
	//댓글 리스트 조회
	public List<CommentVO> findAllComment(final int boardIdx);
	
}
