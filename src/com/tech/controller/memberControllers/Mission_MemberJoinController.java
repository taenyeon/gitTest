package com.tech.controller.memberControllers;

import com.tech.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mission_MemberJoinController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        if (request.getMethod().equals("GET")){
//        response.sendRedirect("/member/joinForm.jsp");
//        }
//        if (request.getMethod().equals("POST")){
//            MemberService memberService = new MemberService();
//            bowlMember member = new bowlMember();
//            request.setCharacterEncoding("utf-8");
//            Date date = new Date();
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String regDate = format.format(date);
//            member.setId(request.getParameter("id"));
//            member.setPwd(request.getParameter("pwd"));
//            member.setName(request.getParameter("name"));
//            member.setTel(request.getParameter("tel"));
//            member.setBirth(request.getParameter("birth"));
//            member.setHabit(request.getParameter("habit"));
//            member.setRegDate(regDate);
//            member.setEmail(request.getParameter("email"));
//            boolean is_lunar = !request.getParameter("is_lunar").equals("N");
//            boolean gender = !request.getParameter("gender").equals("F");
//
//            member.setIs_lunar(is_lunar);
//            member.setIs_Man(gender);
//
//
//            int join = memberService.join(member);
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter writer = response.getWriter();
//            if (join >0){
//                writer.println("<script>alert('회원가입에 성공하였습니다.');</script>");
//                response.sendRedirect("/member/index.jsp");
//            } else {
//                writer.println("<script>alert('회원가입에 실패하였습니다.'); location.href='index.jsp'</script>");
//            }
//        }
    }
}
