package com.korea.project.service.board;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.project.dao.board.BoardDAO;
import com.korea.project.vo.board.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardDAO boardDAO;
	
	//게시글 조회
	@Override
	public List<BoardVO> getList() {
		return boardDAO.findAll();
	}
	
	//게시글 등록
	@Override
	public void register(BoardVO boardVO) {
		boardDAO.save(boardVO);
	}
	
//	//카테고리
//	@Override
//	public List<BoardVO> sectors(int code, String sectorsDisplay) {
//		return boardDAO.sectorsCodes;
//	}
}
