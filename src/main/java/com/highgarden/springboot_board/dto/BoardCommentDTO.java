package com.highgarden.springboot_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCommentDTO {
	private Long id;
	private Long boardId;
	private String commentWriter;
	private String commentCont;
	private String createdAt;
}
