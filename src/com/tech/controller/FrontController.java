package com.tech.controller;


import com.tech.controller.boardController.*;
import com.tech.controller.memberControllers.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!");
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        Controller controller = null;

        // member

        // 회원가입 관련 URI
        if (requestURI.equals("/member/join.do")){
            controller = new Mission_MemberJoinController();
        }
         // 로그인 관련 URI
        if (requestURI.equals("/member/login.do")){
            controller = new Mission_MemberLoginController();
        }
           // 로그아웃 관련 URI
        if (requestURI.equals("/member/logout.do")){
            controller = new Mission_MemberLogoutController();
        }   // 유저정보 관련 URI
        if (requestURI.equals("/member/userInfo.do")){
            controller = new Mission_MemberInfoController();
        }
        if (requestURI.equals("/member/userInfoUpdate.do")){
            controller = new Mission_MemberInfoUpdateController();
        }

        // board
        if (requestURI.equals("/board/notice.do")){
            controller = new Mission_BoardNoticeController();
        }
        if (requestURI.equals("/board/noticeDetail.do")){
            controller = new Mission_BoardDetailController();
        }
        if (requestURI.equals("/board/noticeDelete.do")){
            controller = new Mission_BoardDeleteController();
        }
        if (requestURI.equals("/board/noticeEdit.do")){
            controller = new Mission_BoardEditController();

        }
        if (requestURI.equals("/board/noticeFileDown.do")){
            controller = new Mission_BoardFileDownController();
        }
        if (requestURI.equals("/board/noticeWrite.do")){
            controller = new Mission_BoardWriteController();
        }
        try {
            controller.execute(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
