package com.tech.controller.boardController;

import com.tech.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Mission_BoardNoticeController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String search = request.getParameter("search");
//        BoardService boardService = new BoardService();
//        List<bowlBoard> boards;
//        if (search == null){
//               boards = boardService.findAll();
//        request.setAttribute("boards",boards);
//        }
//        else {
//            String select = request.getParameter("select");
//            boards = boardService.findByElement(select, search);
//            request.setAttribute("boards",boards);
//            request.setAttribute("select",select);
//            request.setAttribute("search",search);
//
//        }
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/board/notice.jsp");
//        dispatcher.forward(request, response);
    }
}
