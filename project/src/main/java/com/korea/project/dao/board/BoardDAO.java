package com.korea.project.dao.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.korea.project.mapper.board.BoardMapper;
import com.korea.project.vo.board.BoardVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
	
	private final BoardMapper boardMapper;
	
	//게시판 조회 
	public List<BoardVO> findAll(){
		return boardMapper.selectAll();
	}
}
