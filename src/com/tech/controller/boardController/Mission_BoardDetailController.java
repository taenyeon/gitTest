package com.tech.controller.boardController;

import com.tech.controller.Controller;
import com.tech.db.bowlBoard;
import com.tech.db.bowlFile;
import com.tech.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Mission_BoardDetailController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String no = request.getParameter("no");
        HttpSession session = request.getSession(true);
        String id = (String) session.getAttribute("login");

        BoardService boardService = new BoardService();
        List<Object> list = boardService.findById(no);
        bowlBoard board = (bowlBoard) list.get(0);
        List<bowlFile> files = (List<bowlFile>) list.get(1);
        System.out.println(board.getWriter());
        boolean writer = false;
        if (id != null){
                if (board.getWriter().equals(id)){
                    writer = true;
                }
        }
        request.setAttribute("board",board);
        request.setAttribute("writer",writer);
        request.setAttribute("files",files);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/board/noticeDetail.jsp");
        dispatcher.forward(request, response);
    }
}
