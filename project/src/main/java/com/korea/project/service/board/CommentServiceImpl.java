package com.korea.project.service.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.korea.project.dao.board.CommentDAO;
import com.korea.project.vo.board.CommentVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	private final CommentDAO commentDAO;
	
	//댓글저장
	@Override
	@Transactional
	public int save(CommentVO params) {
		commentDAO.save(params);
		return params.getCommentIdx();
	}
	
	//댓글 상세정보 조회
	@Override
	public CommentVO findById(int commentIdx) {
		return commentDAO.findById(commentIdx);
	}
	
	
	//댓글 수정
	@Override
	@Transactional
	public int update(CommentVO params) {
		commentDAO.update(params);
		return params.getCommentIdx();
	}
	
	//댓글 삭제
	@Transactional
	@Override
	public int deleteComment(int commentIdx) {
		commentDAO.deleteById(commentIdx);
		return commentIdx;
	}
	
	//댓글 리스트 조회
	@Override
	public List<CommentVO> findAllComment(int boardIdx) {
		return commentDAO.findAll(boardIdx);
	}
	
}
