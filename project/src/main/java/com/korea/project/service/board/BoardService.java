package com.korea.project.service.board;

import java.util.List;
import java.util.stream.Collectors;

import com.korea.project.dto.board.BoardListRequest;
import com.korea.project.dto.board.BoardListResponse;
import com.korea.project.dto.board.PagingResponse;
import com.korea.project.vo.board.BoardVO;


public interface BoardService {
	
	
	
	//게시글 조회 
	public PagingResponse<BoardVO> findBoardList(final BoardListRequest params);
	//public List<BoardVO> getList();
	
	
	
	//게시글 추가
	public void register(BoardVO boardVO);
	
//	//게시글 카테고리, 검색어에 관한 필터링 조회 
//	public List<BoardListResponse> filter(BoardListRequest boardListRequest);
	
	

}
