package com.tech.controller.memberControllers;

import com.tech.controller.Controller;
import com.tech.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class Mission_MemberLoginController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")){
        System.out.println(request.getRequestURI());
        response.sendRedirect("/member/loginForm.jsp");
        }


        else if (request.getMethod().equals("POST")){
            MemberService memberService = new MemberService();

            request.setCharacterEncoding("utf-8");
            String id = request.getParameter("id");
            String pwd = request.getParameter("pwd");

            if (id == null || pwd == null) {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.println("<script>alert('아이디 또는 비밀번호를 입력하지 않았습니다.'); location.href='index.jsp'</script>");

            } else {
                String login = memberService.login(id, pwd);
                if (login != null){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login", id);
                    System.out.println(session.getAttribute("login"));

                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.println("<script>alert('로그인에 성공했습니다.');</script>");
                    response.sendRedirect("/member/index.jsp");
                } else { // 비밀번호 오류 출력
                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.println("<script>alert('비밀번호 오류입니다.'); location.href='index.jsp'</script>");
                }
            }
        }
    }
}
