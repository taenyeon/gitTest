package com.tech.repository;

import com.tech.db.bowlMember;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

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

    // member 찾기
    public bowlMember findByID(String id) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = getConnection();
     PreparedStatement pstmt = con.prepareStatement("select"+
             " id," +
             " pwd," +
             " name," +
             " IS_MAN," +
             " to_char(BIRTH,'YYYY-MM-DD') as birth," +
             " is_lunar," +
             " tel," +
             " email," +
             " habit," +
             " to_char(regdate,'YYYY-MM-DD') as regdate" +
             " from MEMBERS" +
             " where ID = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        bowlMember result = new bowlMember();
        if (rs.next()){
            result = getResult(rs);
        }
        close(con,pstmt,rs);
        return result;

    }

    private bowlMember getResult(ResultSet rs) throws SQLException {
        bowlMember result = new bowlMember();
        result.setId(rs.getString("id"));
        result.setPwd(rs.getString("pwd"));
        result.setName(rs.getString("name"));
        result.setTel(rs.getString("tel"));
        result.setBirth(rs.getString("birth"));
        result.setEmail(rs.getString("email"));
        result.setIs_Man(rs.getBoolean("is_man"));
        result.setIs_lunar(rs.getBoolean("is_lunar"));
        result.setHabit(rs.getString("habit"));
        result.setRegDate(rs.getString("regdate"));
        return result;
    }

    // 모든 member 찾기
    public List<bowlMember> findAll() throws SQLException, ClassNotFoundException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("select " +
                "id," +
                " pwd," +
                " name," +
                " IS_MAN," +
                " to_char(BIRTH,'YYYY-MM-DD') as birth," +
                " is_lunar," +
                " tel," +
                " email," +
                " habit," +
                " to_char(regdate,'YYYY-MM-DD') as regdate" +
                " from MEMBERS");
        ResultSet rs = pstmt.executeQuery();
        List<bowlMember> members = new ArrayList<>();
        while(rs.next()){
            bowlMember result = getResult(rs);
            members.add(result);
        }
        close(con,pstmt,rs);
        return members;
    }
    // member 업데이트 하기
    public int updateMember(bowlMember member) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("update MEMBERS set " +
                "NAME=?," +
                "IS_MAN=?," +
                "BIRTH=to_date(?,'YYYY-MM-DD')," +
                "IS_LUNAR=?," +
                "TEL=?," +
                "EMAIL=?," +
                "HABIT=?," +
                "REGDATE=to_date(?,'YYYY-MM-DD')" +
                " where ID=?");
        ResultSet rs = null;
        pstmt.setString(1, member.getName());
        pstmt.setBoolean(2,member.isIs_Man());
        pstmt.setString(3,member.getBirth());
        pstmt.setBoolean(4,member.isIs_lunar());
        pstmt.setString(5,member.getTel());
        pstmt.setString(6,member.getEmail());
        pstmt.setString(7, member.getHabit());
        pstmt.setString(8, member.getRegDate());
        pstmt.setString(9,member.getId());
        int result = pstmt.executeUpdate();
        close(con,pstmt,rs);
        return result;
    }
    // member 추가하기
    public int insertMember(bowlMember member) throws SQLException, NamingException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("insert into MEMBERS values (?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?,?,to_date(?,'YYYY-DD-MM'))");
        ResultSet rs = null;
        pstmt.setString(1,member.getId());
        pstmt.setString(2, member.getPwd());
        pstmt.setString(3, member.getName());
        pstmt.setBoolean(4,member.isIs_Man());
        pstmt.setString(5,member.getBirth());
        pstmt.setBoolean(6,member.isIs_lunar());
        pstmt.setString(7,member.getTel());
        pstmt.setString(8,member.getEmail());
        pstmt.setString(9, member.getHabit());
        pstmt.setString(10, member.getRegDate());
        int result = pstmt.executeUpdate();
        close(con,pstmt,rs);
        return result;
    }

}
