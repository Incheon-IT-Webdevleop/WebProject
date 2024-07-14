package com.korea.project.mapper.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korea.project.vo.board.CommentVO;

@Mapper
public interface CommentMapper {
	//댓글 저장
	//@params - 댓글정보
	public void save(CommentVO params);
	
	//댓글 상세정보 조회
	public CommentVO findById(int commentIdx);
	
	//댓글 수정
	public void update(CommentVO params);
	
	//댓글 삭제
	public void deleteById(int userIdx);
	
	//댓글 리스트 조회
	List<CommentVO> findAll(int boardIdx);

	//댓글 수 카운팅
	public int count(int boardIdx);

}