package com.korea.project.dao.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.korea.project.dto.board.BoardListRequest;
import com.korea.project.dto.board.BoardListResponse;
import com.korea.project.mapper.board.BoardMapper;
import com.korea.project.vo.board.BoardVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
	
	private final BoardMapper boardMapper;
	
	//게시판 리스트 목록 
	public List<BoardVO> findBoardList(BoardListRequest params){
		return boardMapper.findAll(params);
	}
	
	//게시글 페이징
	public int count(BoardListRequest params) {
		return boardMapper.count(params);
	}
	
	// 게시글 수 카운팅
//	public int boardCount(BoardListRequest params) {
//		return boardMapper.boardCount(params);
//	}
	
	//게시글 목록 추가
	public void save(BoardVO boardVO) {
		boardMapper.insert(boardVO);
	}
	
//	//게시판 카테고리 검색에 따라 게시판 조회 
//	public List<BoardListResponse> filter(BoardListRequest boardCR){
//		return boardMapper.filter(boardCR);
//	}
	
	//게시글 조회하기
	public BoardVO findById(int boardIdx) {
		return boardMapper.findById(boardIdx);
	}
	
	public int boardCount(BoardListRequest params) {
		return boardMapper.boardCount(params);
	}
}
