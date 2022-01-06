package com.tech.controller.memberControllers;

import com.tech.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class Mission_MemberInfoUpdateController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        MemberService memberService = new MemberService();
//        bowlMember member = new bowlMember();
//        request.setCharacterEncoding("utf-8");
//        member.setId(request.getParameter("c"));
//        member.setName(request.getParameter("name"));
//        member.setTel(request.getParameter("tel"));
//        member.setBirth(request.getParameter("birth"));
//        member.setHabit(request.getParameter("habit"));
//        member.setEmail(request.getParameter("email"));
//        boolean is_lunar = !request.getParameter("is_lunar").equals("N");
//        boolean gender = !request.getParameter("gender").equals("F");
//
//        member.setIs_lunar(is_lunar);
//        member.setIs_Man(gender);
//        int info = memberService.updateInfo(member);
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter writer = response.getWriter();
//        if (info > 0){
//            writer.println("<script>alert('수정되었습니다.');</script>");
//        } else {
//            writer.println("<script>alert('수정에 실패하였습니다.');</script>");
//        }
//            response.sendRedirect("/member/index.jsp");
    }
}
