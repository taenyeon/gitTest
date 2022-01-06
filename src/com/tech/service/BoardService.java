package com.tech.service;

import com.tech.db.bowlBoard;
import com.tech.db.bowlFile;
import com.tech.repository.BoardRepository;

import javax.naming.NamingException;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardService {
    BoardRepository boardRepository = new BoardRepository();

    public int writeBoard(bowlBoard board, List<bowlFile> files) throws SQLException, NamingException {
       return boardRepository.insertBoard(board,files);
    }

    public int updateBoard(bowlBoard board, List<bowlFile> files) throws SQLException, NamingException {
        deleteFile(board.getNo());
        return boardRepository.updateBoard(board,files);
    }

    public List<Object> findById(String no) throws SQLException, NamingException {
        boardRepository.hitUp(no);
        List<Object> list = new ArrayList<>();
        bowlBoard board = boardRepository.findById(no);
        List<bowlFile> file = boardRepository.findFile(no);
        list.add(board);
        list.add(file);
        return list;
    }

    public List<bowlBoard> findAll() throws SQLException, NamingException {
        return boardRepository.findAll();
    }


    public List<bowlBoard> findByElement(String element, String value) throws SQLException, NamingException {
        element ="notice_"+element;
        return boardRepository.findByElement(element,value);
    }

    public void deleteFile(String no) throws SQLException, NamingException {
        List<bowlFile> files = boardRepository.findFile(no);
        boardRepository.deleteFile(no);
        for (bowlFile file : files){
            String filePath = file.getFilePath();
            System.out.println("filePath = " + filePath);
            File oldFile = new File(filePath);
            oldFile.delete();
        }

    }

    public int deleteBoard(String no) throws SQLException, NamingException {
        deleteFile(no);
        return boardRepository.deleteBoard(no);
    }

}
