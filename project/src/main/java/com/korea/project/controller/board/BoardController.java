package com.korea.project.controller.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.korea.project.dto.board.BoardListRequest;
import com.korea.project.dto.board.BoardListResponse;
import com.korea.project.dto.board.PagingResponse;
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
	public String list(@ModelAttribute("params") final BoardListRequest params, Model model) {
		int nowPage = 1;
		System.out.println("con nowPage : " + params.getNowpage());
		if(params.getNowpage() != 0) {
			nowPage = params.getNowpage();
		}
		params.setNowpage(nowPage);
		PagingResponse<BoardListResponse> response = boardService.findBoardList(params);
		System.out.println("짜잔: " + response.getList().size());
		model.addAttribute("response",response);
		
		
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
	System.out.println(boardVO.getBoardSectors());
		boardService.register(boardVO);
		
		
		return new RedirectView("list");
	}
	
	//게시글 삭제하기
//	@PostMapping("del")
//	public String delPost(@RequestParam int boardIdx) {
//		boardService.delPost(boardIdx);
//		if(type == 0) {
//			
//		}
//		return "redirect:/board/list";
//	}
//	
	//게시글 상세 페이지
	@GetMapping("view")
	public String openBoardView(@RequestParam int boardIdx, Model model) {
		//id는  findBoardById 쿼리의 WHERER조건으로 사용되는 게시글 번호임
		BoardVO vo = boardService.findById(boardIdx);
		model.addAttribute("vo",vo);
		
		//조회수 올려주기
		boardService.viewCount(boardIdx);
		
		return "board/boardView";
	}
	
	
	//게시글 목록 필터링해서 검색하기
//	@GetMapping("list")
//	@ResponseBody
//	public List<BoardListResponse> filterList(  @RequestParam(required = false) int sector,
//									            @RequestParam(required = false) String bigArea,
//									            @RequestParam(required = false) String smallArea,
//									            @RequestParam(required = false) String keyword){
//		
//		BoardListRequest boardCR = new BoardListRequest();
//	    boardCR.setBoardSectors(sector);
//	    boardCR.setBoardBigArea(bigArea.trim());
//	    boardCR.setBoardSmallArea(smallArea.trim());
//	    boardCR.setSearchKeyword(keyword.trim());
//		System.out.println(boardCR);
//		System.out.println(boardService.filter(boardCR));
//		return boardService.filter(boardCR);
//	}
	
	
	

	
}
