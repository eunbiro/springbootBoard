package com.highgarden.springboot_board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.highgarden.springboot_board.dto.BoardCommentDTO;
import com.highgarden.springboot_board.dto.BoardDTO;
import com.highgarden.springboot_board.dto.BoardFileDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    public BoardDTO save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
        return boardDTO;
    }
    
    public void saveFile(BoardFileDTO boardFileDTO) {
    	sql.insert("Board.saveFile", boardFileDTO);
    }
    
    public void saveComment(BoardCommentDTO boardCommentDTO) {
    	sql.insert("Board.saveComment", boardCommentDTO);
    }
    
    public List<BoardFileDTO> findFile(Long id) {
		return sql.selectList("Board.findFile", id);
	}
    
    public List<BoardCommentDTO> findComment(Long id) {
    	return sql.selectList("Board.findComment", id);
    }
    
    public List<BoardDTO> findAll() {
    	System.out.println("findAll");
    	return sql.selectList("Board.findAll");
    }
    
    public void updateHits(Long id) {
    	sql.update("Board.updateHits", id);
    }
    
    public void updateComment(Long id) {
    	sql.update("Board.updateComment", id);
    }
    
    public BoardDTO findById(Long id) {
    	return sql.selectOne("Board.findById", id);
    }
    
    public void update(BoardDTO boardDTO) {
    	sql.update("Board.update", boardDTO);
    }
    
    public void delete(Long id) {
    	sql.delete("Board.delete", id);
    }
    
    public void deleteFile(Long id) {
    	sql.delete("Board.deleteFile", id);
    }
    
    public void deleteComment(Long id) {
    	sql.delete("Board.deleteComment", id);
    }
}

