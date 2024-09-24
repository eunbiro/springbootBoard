package com.highgarden.springboot_board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.highgarden.springboot_board.dto.BoardCommentDTO;
import com.highgarden.springboot_board.dto.BoardDTO;
import com.highgarden.springboot_board.dto.BoardFileDTO;
import com.highgarden.springboot_board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	// 게시글 저장
	public void save(BoardDTO boardDTO) throws IOException {
		if (boardDTO.getBoardFile().get(0).isEmpty()) {	// 저장 시 첨부 된 파일 유무 조회
			// 없으면 File을 0으로
			boardDTO.setFileAttached(0);
			boardRepository.save(boardDTO);
		} else {
			// 있으면 File을 1로
			boardDTO.setFileAttached(1);
			// board를 먼저 insert
			BoardDTO saveBoard = boardRepository.save(boardDTO);
			// 파일 처리 후 boardfile insert
			for (MultipartFile boardFile : boardDTO.getBoardFile()) {
				String originalFileName = boardFile.getOriginalFilename();
				String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
				
				BoardFileDTO boardFileDTO = new BoardFileDTO();
				boardFileDTO.setOriginalFileName(originalFileName);
				boardFileDTO.setStoredFileName(storedFileName);
				boardFileDTO.setBoardId(saveBoard.getId());
				
				String savePath = "D:/board_tistory/springboot-board/src/main/resources/upload_files/" + storedFileName;
				// 실질적으로 파일이 저장되는 코드
				boardFile.transferTo(new File(savePath));
				boardRepository.saveFile(boardFileDTO);
			}
		}
	}
	
	// 댓글 저장
	public void saveComment(BoardCommentDTO boardCommentDTO) {
		boardRepository.saveComment(boardCommentDTO);
	}
	
	// 게시글 사진 조회
	public List<BoardFileDTO> findFile(Long id) {
		return boardRepository.findFile(id);
	}
	
	// 댓글 조회
	public List<BoardCommentDTO> findComment(Long id) {
		return boardRepository.findComment(id);
	}
	
	// 목록 조회
	public List<BoardDTO> findAll() {
		return boardRepository.findAll();
	}
	
	// 조회수 증가
	public void updateHits(Long id) {
		boardRepository.updateHits(id);
	}
	
	// 댓글 수 카운터
	public void updateComment(Long id) {
		boardRepository.updateComment(id);
	}
	
	// 게시글 상세 조회
	public BoardDTO findById(Long id) {
		return boardRepository.findById(id);
	}
	
	// 게시글 수정
	public void update(BoardDTO boardDTO) {
		boardRepository.update(boardDTO);
	}
	
	// 게시글 삭제
	public void delete(Long id) {
		boardRepository.delete(id);
	}
}
