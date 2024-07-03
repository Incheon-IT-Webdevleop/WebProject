package com.korea.project.controller.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.korea.project.mapper.board.BoardMapper;
import com.korea.project.service.board.BoardService;
import com.korea.project.vo.board.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	final BoardService boardService;
	
	//게시글 목록 보여주기
	@GetMapping("list")
	public String list(Model model) {
		List<BoardVO> list = boardService.getList();
		model.addAttribute("boardForm",new BoardVO());
		model.addAttribute("list",list);
		
		return "board/boardList";
	}
	
	//게시글 추가하기
	@GetMapping("register")
	public String insert(Model model) {
		model.addAttribute("vo", new BoardVO());
		
		return "board/boardInsert";
	}
	@PostMapping("register")
	public RedirectView insert(BoardVO boardVO) {
		boardService.register(boardVO);
		return new RedirectView("list");
	}
	
//	//카테고리
//	@GetMapping("bordSectors")
//	public List<BoardVO> boardSectors(){
//		List<BoardVO> boardSectors = new ArrayList<>();
//		boardSectors.add(new BoardVO());
//		
//		return boardSectors;
//	}
//	
	
	

	
}
