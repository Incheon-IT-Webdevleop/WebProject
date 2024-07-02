package com.korea.project.controller.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.korea.project.service.board.BoardService;
import com.korea.project.vo.board.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class BoardController {
	final BoardService boardService;
	
	@GetMapping("list")
	public String list(Model model) {
		List<BoardVO> list = boardService.getList();
		model.addAttribute("boardForm",new BoardVO());
		model.addAttribute("list",list);
		
		return "board/boardList";
	}
	
	@GetMapping("register")
	public String insert(Model model) {
		model.addAttribute("vo", new BoardVO());
		return "board/boardInsert";
	}
	
	
}
