package com.korea.project.dao.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.korea.project.mapper.board.CommentMapper;
import com.korea.project.vo.board.CommentVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentDAO {
	private final CommentMapper commentMapper;
	
	//댓글 저장
	public void save(CommentVO params) {
		commentMapper.save(params);
	}
	
	//댓글 상세정보 조회
	public CommentVO findById(int userIdx) {
		return commentMapper.findById(userIdx);
	}
	
	//댓글 수정
	public void update(CommentVO params) {
		commentMapper.update(params);
	}
	
	//댓글 삭제
	public void deleteById(int userIdx) {
		commentMapper.deleteById(userIdx);
	}
	
	//댓글 리스트 조회
	public List<CommentVO> findAll(int boardIdx){
		return commentMapper.findAll(boardIdx);
	}
	
	//댓글 수 카운팅
	public int count(int boardIdx) {
		return commentMapper.count(boardIdx);
	}
	
	
	
}
