package com.tech.controller.memberControllers;


import com.tech.controller.Controller;
import com.tech.db.bowlMember;
import com.tech.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class Mission_MemberInfoController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")){
        response.sendRedirect("/member/infoForm.jsp");
        }
        if (request.getMethod().equals("POST")){
            MemberService memberService = new MemberService();
            request.setCharacterEncoding("utf-8");
            String pwd = request.getParameter("pwd");
            String id = request.getParameter("login");
            if (id != null && pwd != null){
            bowlMember member = memberService.info(id, pwd);
            if (member.getId() != null){
                request.setAttribute("member", member);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/member/info.jsp");
                dispatcher.forward(request, response);

            } else {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.println("<script>alert('비밀번호 확인에 실패했습니다.'); location.href='/member/index.jsp'</script>");
            }

            } else {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.println("<script>alert('아이디나 비밀번호가 빈칸입니다.'); location.href='/member/index.jsp'</script>");

            }
        }
    }
}
