package com.highgarden.springboot_board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.highgarden.springboot_board.dto.BoardCommentDTO;
import com.highgarden.springboot_board.dto.BoardDTO;
import com.highgarden.springboot_board.dto.BoardFileDTO;
import com.highgarden.springboot_board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor		// 초기화되지 않은 final 필드를 자동으로 생성자 주입
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/save")
	public String save() {
		return "save";
	}
	
	@PostMapping("/save")
	public String save(BoardDTO boardDTO) throws IOException { // IOException이 발생할 수 있으므로 예외를 던진다.
		BoardDTO dto = boardService.save(boardDTO);
		
		return "redirect:/" + dto.getId();
	}
	
	@GetMapping("/list")
	public String findAll(Model model) {
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		
		return "list";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		// 조회수 처리
		boardService.updateHits(id);
		// 상세내용 가져오기
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		
		if (boardDTO.getFileAttached() == 1) {
			List<BoardFileDTO> boardFileDTOList = boardService.findFile(id);
			model.addAttribute("boardFileDTOList", boardFileDTOList);
		}
		
		if (boardDTO.getCommentAttached() >= 1) {
			List<BoardCommentDTO> boardCommentDTOList = boardService.findComment(id);
			model.addAttribute("boardCommentDTOList", boardCommentDTOList);
		}
		
		return "detail";
	}
	
	@PostMapping("/savecomm/{id}")
	public String saveComment(@PathVariable("id") Long id, BoardCommentDTO boardCommentDTO, Model model) {
		boardService.updateComment(id);
		boardService.saveComment(boardCommentDTO);
		
		return "redirect:/" + id;
	}
	
	// id로 수정 할 게시글 내용을 불러와 수정 페이지에 노출
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		
		return "update";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, BoardDTO boardDTO, Model model) throws IOException {
		boardService.update(boardDTO, id);
		BoardDTO dto = boardService.findById(boardDTO.getId());
		
		model.addAttribute("board", dto);
		
		return "redirect:/" + id;
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		boardService.delete(id);
		
		return "redirect:/list";
	}

}
