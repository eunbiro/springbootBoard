package com.highgarden.springboot_board.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString			// ToString 메서드를 통해 DTO 내부정보를 printout 할 수 있게 해줌
public class BoardDTO {
	private long id;
	private String boardWriter;
	private String boardPass;
	private String boardTitle;
	private String boardContents;
	private int boardHits;
	private String createdAt;
	
	// 첨부파일 필드
	private int fileAttached;
	private List<MultipartFile> boardFile;
	
	// 댓글 필드
	private int commentAttached;
}
