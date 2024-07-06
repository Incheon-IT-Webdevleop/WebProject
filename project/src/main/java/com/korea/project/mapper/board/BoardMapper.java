package com.korea.project.mapper.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korea.project.dto.board.BoardListRequest;
import com.korea.project.vo.board.BoardVO;

@Mapper
public interface BoardMapper {

	//게시판 조회
	public List<BoardListRequest> findBoardList(BoardListRequest params);
//    List<BoardVO> selectAll();
	
	//게시글 수 카운팅
	int boardCount(BoardListRequest params);
	

	//게시글 목록 추가
    void insert(BoardVO boardVO);
	
//	//카테고리 필터링 된 게시글 목록 가져오기
//	List<BoardListResponse> filter(BoardListRequest boardListRequest);
	
	//게시글 페이징
	public int count(BoardListRequest boardListRequest);
}
