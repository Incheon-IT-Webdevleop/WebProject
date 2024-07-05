package com.korea.project.service.board;

import java.util.List;

import com.korea.project.vo.board.BoardVO;


public interface BoardService {
	
	
	
	//게시글 조회 
    List<BoardVO> getList();
	
	//게시글 추가
    void register(BoardVO boardVO);
	
//	//카테고리 추가 
//	public List<BoardVO> sectors();
}
