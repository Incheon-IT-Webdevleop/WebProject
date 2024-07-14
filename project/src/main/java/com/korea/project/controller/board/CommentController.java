package com.korea.project.controller.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.service.board.CommentService;
import com.korea.project.vo.board.CommentVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
	final CommentService commentService;
	final HttpSession session;
	
	//신규 댓글 생성
	//saveComment의 객체를 CommentVO로 보내려고 했으나 서버에서 받아오지 못해서(gradle 추가가 필요해보임) ResponseEntity로 보내줌
	@PostMapping("/board/{boardIdx}/comments")
	public ResponseEntity<?> saveComment(@PathVariable final int boardIdx, @RequestBody CommentVO params) {
		//@PathVariable : 파라미터를 전달 받으려고 할 때 사용 - saveComment()는 URI에서 boardIdx를 수집해서 매핑함
		//@RequestBody : 데이터를 POST또는 PUT or PATCH하는 경우에 사용. 			 
		try {
			SessionUserDTO user = (SessionUserDTO)session.getAttribute("user");
			if(user == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
			}
			params.setUserIdx(user.getUserIdx());
			int commentIdx = commentService.save(params);
			CommentVO savedComment = commentService.findById(commentIdx);
			return ResponseEntity.ok(savedComment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR" + e.getMessage());
		}
	}
	
	//댓글 리스트 조회
	@GetMapping("/board/{boardIdx}/comments")
	public ResponseEntity<?>  findAllComment(@PathVariable final int boardIdx){
		
		List<CommentVO> viewComment = commentService.findAllComment(boardIdx);
		System.out.println(viewComment);
		try {
			//List<CommentVO> viewComment = commentService.findAllComment(boardIdx);
			System.out.println(viewComment);
			return ResponseEntity.ok(viewComment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR" + e.getMessage());
		}
		
	}
	
	//수정하려는 댓글의 정보 조회
	@PatchMapping("board/{boardIdx}/comments/{commentIdx}")
	public ResponseEntity<?> updateComment(@PathVariable final int boardIdx, @PathVariable final int commentIdx, @RequestBody final CommentVO params){
		commentService.update(params);
		try {
			System.out.println(params);
			commentService.update(params);
			//int updateComment =  commentService.findById(commentIdx);
			return ResponseEntity.ok("댓글 수정 성공");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR" + e.getMessage());
		}
	}
	
	//댓글 삭제하기
	@DeleteMapping("board/{boardIdx}/comments/{commentIdx}")
	public ResponseEntity<?> deleteComment(@PathVariable final int boardIdx, @PathVariable final int commentIdx){
		try {
			commentService.deleteComment(commentIdx);
			return ResponseEntity.ok("댓글 삭제 성공");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR" + e.getMessage());
		}
	}
	
}
