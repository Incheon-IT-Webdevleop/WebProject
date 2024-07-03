package com.korea.project.mapper.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korea.project.vo.board.BoardVO;

@Mapper
public interface BoardMapper {

	//게시판 조회
	public List<BoardVO> selectAll();
	
	//게시글 목록 추가
	public void insert(BoardVO boardVO);
	
	
//	//카테고리
//	public List<BoardVO> 
}
