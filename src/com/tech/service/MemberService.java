package com.tech.service;

import com.tech.crypt.work.BCrypt;
import com.tech.crypt.work.SHA256;
import com.tech.db.bowlMember;
import com.tech.repository.MemberRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class MemberService  {
    MemberRepository memberRepository;


    public String login(String id,String pwd) throws Exception {
        memberRepository = new MemberRepository();
            String session = null;
            bowlMember member = memberRepository.findByID(id);
            String bcpass = member.getPwd();
                SHA256 sha = SHA256.getInsatnce();
                String shapass = sha.getSha256(pwd.getBytes());
        if (bcpass != null) {
            BCrypt.checkpw(shapass, bcpass);
        session = id;
        }
        return session;
        }

    public List<bowlMember> list() throws SQLException, ClassNotFoundException, NamingException {
        memberRepository = new MemberRepository();
        return memberRepository.findAll();
    }

    public int join(bowlMember member) throws Exception {
        memberRepository = new MemberRepository();
        SHA256 sha = SHA256.getInsatnce();
        String shPwd = sha.getSha256(member.getPwd().getBytes());
        member.setPwd(BCrypt.hashpw(shPwd,BCrypt.gensalt()));
        return memberRepository.insertMember(member);
    }

    public int updateInfo(bowlMember member) throws SQLException, ClassNotFoundException, NamingException {
        memberRepository = new MemberRepository();
        return memberRepository.updateMember(member);
    }

    public bowlMember info(String id, String pwd) throws Exception {
        memberRepository = new MemberRepository();
        String login = login(id, pwd);
        return memberRepository.findByID(login);
    }
}
