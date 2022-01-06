package com.tech.repository;

import com.tech.db.bowlBoard;
import com.tech.db.bowlFile;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BoardRepository {
    private Connection getConnection() throws NamingException, SQLException {
        Connection con;
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/noticeXe");
        con = ds.getConnection();
        return con;
    }
    public void close(Connection con, PreparedStatement pstmt,ResultSet rs) throws SQLException {
        if (rs !=null) {
            rs.close();
        }
        if (pstmt != null){
            pstmt.close();
        }
        if (con != null){
            con.close();
        }
    }

    public List<bowlBoard> findAll() throws SQLException, NamingException {
        Connection con = getConnection();
      PreparedStatement pstmt = con.prepareStatement("select * from BOWL_NOTICE_BOARD order by NOTICE_NO desc");
       ResultSet rs = pstmt.executeQuery();
        List<bowlBoard> bowlBoards = new LinkedList<>();
        while(rs.next()){
            bowlBoard bowlBoard = getGuroBoard(rs);
            bowlBoards.add(bowlBoard);
        }
    close(con,pstmt,rs);
        return bowlBoards;
    }


    public bowlBoard findById(String no) throws SQLException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from BOWL_NOTICE_BOARD where NOTICE_NO=?");
        pstmt.setString(1,no);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        bowlBoard board = getGuroBoard(rs);
        close(con,pstmt,rs);
        return board;

    }

    public List<bowlFile> findFile(String no) throws SQLException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from FILES where NOTICE_NO=?");
        System.out.println("no = " + no);
        pstmt.setString(1, no);
        ResultSet rs = pstmt.executeQuery();
        List<bowlFile> files = new ArrayList<>();
        while (rs.next()){
            bowlFile file = new bowlFile();
         file.setFilePath(rs.getString("file_path"));
         file.setFileName(rs.getString("file_name"));
         file.setFileSize(rs.getInt("file_size"));
         file.setNotice_no(rs.getInt("notice_no"));
         files.add(file);
        }
        close(con,pstmt,rs);
        return files;
    }
    public void deleteFile(String no) throws SQLException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("delete from FILES where NOTICE_NO=?");
        pstmt.setString(1,no);
        pstmt.executeUpdate();
        close(con,pstmt, null);
    }

    public int updateBoard(bowlBoard board,List<bowlFile> files) throws SQLException, NamingException {
        Connection con = getConnection();
        int resultSize = files.size()+1;
        con.setAutoCommit(false);
        PreparedStatement pstmt = con.prepareStatement("update BOWL_NOTICE_BOARD set NOTICE_TITLE=?,NOTICE_CONTENT=?,NOTICE_DATE=sysdate where NOTICE_NO=?");
        pstmt.setString(1,board.getTitle());
        pstmt.setString(2,board.getContent());
        pstmt.setString(3,board.getNo());
        int result = pstmt.executeUpdate();
        for (bowlFile file : files) {
            pstmt = con.prepareStatement("insert into FILES VALUES(FILE_PATH=?,FILE_NAME=?,FILE_SIZE=?,NOTICE_NO=?) ");
            pstmt.setString(1, file.getFilePath());
            pstmt.setString(2, file.getFileName());
            pstmt.setLong(3, file.getFileSize());
            pstmt.setString(4, board.getNo());
            result += pstmt.executeUpdate();
            if (result == resultSize) {
                con.commit();
            } else {
                con.rollback();
            }
        }
        close(con,pstmt, null);
        return result;
    }

    public int insertBoard(bowlBoard board,List<bowlFile> files) throws SQLException, NamingException {
        Connection con = getConnection();
        con.setAutoCommit(false);
        int resultSize = files.size()+1;
        PreparedStatement pstmt = con.prepareStatement("select BOWL_NOTICE_BOARD_NO_CNT.nextval as no from DUAL");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        String no = rs.getString("no");
        pstmt = con.prepareStatement("insert into BOWL_NOTICE_BOARD values (?,?,?,?,sysdate,0)");
        pstmt.setString(1,no);
        pstmt.setString(2,board.getTitle());
        pstmt.setString(3,board.getWriter());
        pstmt.setString(4,board.getContent());
        int result = pstmt.executeUpdate();
        if (files.size() != 0){

        for (bowlFile file : files){
        pstmt = con.prepareStatement("insert into FILES VALUES(?,?,?,?)");
        pstmt.setString(1,file.getFilePath());
        pstmt.setString(2,file.getFileName());
        pstmt.setLong(3,file.getFileSize());
        pstmt.setString(4,no);
        result += pstmt.executeUpdate();
        }
        if (result == resultSize){
            con.commit();
        } else {
            con.rollback();
        }
        } else {
            if (result == 1){
                con.commit();
            } else {
                con.rollback();
            }
        }
        close(con,pstmt,rs);
        return result;
    }

    public int deleteBoard(String no) throws SQLException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("select MAX(NOTICE_NO)as max from BOWL_NOTICE_BOARD");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int max = rs.getInt("max");
        pstmt = con.prepareStatement("delete from BOWL_NOTICE_BOARD where NOTICE_NO=?");
        pstmt.setString(1, no);
        int del = pstmt.executeUpdate();

        if (max == 1){
            pstmt = con.prepareStatement("drop sequence BOWL_NOTICE_BOARD_NO_CNT");
            pstmt.executeUpdate();
            pstmt = con.prepareStatement("create sequence BOWL_NOTICE_BOARD_NO_CNT start with 1 increment by 1 maxvalue 9999 nocache nocycle");
            pstmt.executeUpdate();

        }else if (max >=Integer.parseInt(no)) {
            if (max >Integer.parseInt(no)){
            pstmt = con.prepareStatement("update BOWL_NOTICE_BOARD set NOTICE_NO = NOTICE_NO-1 where NOTICE_NO <?");
            pstmt.setString(1, no);
            pstmt.executeUpdate();
            }
            pstmt = con.prepareStatement("ALTER SEQUENCE BOWL_NOTICE_BOARD_NO_CNT INCREMENT BY -1");
            pstmt.executeUpdate();
            pstmt = con.prepareStatement("select BOWL_NOTICE_BOARD_NO_CNT.nextval from dual");
            pstmt.executeUpdate();
            pstmt = con.prepareStatement("ALTER SEQUENCE BOWL_NOTICE_BOARD_NO_CNT INCREMENT BY 1");
            pstmt.executeUpdate();
        }
        close(con,pstmt,rs);
        return del;
    }

    public void hitUp(String no) throws SQLException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("update BOWL_NOTICE_BOARD set NOTICE_HIT= NOTICE_HIT+1 where NOTICE_NO = ?");
        pstmt.setString(1, no);
        pstmt.executeUpdate();
        close(con,pstmt, null);
    }
    public List<bowlBoard> findByElement(String element, String value) throws SQLException, NamingException {
        List<bowlBoard> boards = new LinkedList<>();
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from BOWL_NOTICE_BOARD where " + element+ " like ? order by NOTICE_NO desc ");
        pstmt.setString(1,"%"+value+"%");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            bowlBoard board = getGuroBoard(rs);
            boards.add(board);
        }
        close(con,pstmt,rs);
        return boards;
    }

    private bowlBoard getGuroBoard(ResultSet rs) throws SQLException {
        Date date = rs.getDate("notice_date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        bowlBoard bowlBoard = new bowlBoard();
        bowlBoard.setNo(rs.getString("notice_no"));
        bowlBoard.setTitle(rs.getString("notice_title"));
        bowlBoard.setWriter(rs.getString("notice_writer"));
        bowlBoard.setContent(rs.getString("notice_content"));
        bowlBoard.setHit(rs.getInt("notice_hit"));
        bowlBoard.setDate(format.format(date));

        return bowlBoard;
    }

}
