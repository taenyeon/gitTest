package com.tech.controller.boardController;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.controller.Controller;
import com.tech.db.bowlBoard;
import com.tech.db.bowlFile;
import com.tech.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Mission_BoardEditController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")){
        BoardService boardService = new BoardService();
        String no = request.getParameter("no");
            List<Object> list = boardService.findById(no);
            bowlBoard board = (bowlBoard) list.get(0);
            List<bowlFile> files = (List<bowlFile>) list.get(1);
            request.setAttribute("board",board);
            request.setAttribute("files",files);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/board/noticeEdit.jsp?no="+no);
        dispatcher.forward(request, response);
        }
        if (request.getMethod().equals("POST")){
            BoardService boardService = new BoardService();

            String path = request.getSession().getServletContext().getRealPath("/files/");

            int size = 15 * 1024 * 1024; // 15MB
            MultipartRequest multipartRequest = new MultipartRequest(request, path, size,"utf-8", new DefaultFileRenamePolicy());
            Enumeration fileList = multipartRequest.getFileNames();
            List<bowlFile> files = new ArrayList<>();
            while (fileList.hasMoreElements()){
                bowlFile file = new bowlFile();
                String fileString = (String) fileList.nextElement();
                file.setFileName(multipartRequest.getFilesystemName(fileString));
                file.setFileName(multipartRequest.getOriginalFileName(fileString));
                file.setFileSize(multipartRequest.getFile(fileString).length()/1024); // kb 단위
                files.add(file);
            }

            bowlBoard board = new bowlBoard();
            board.setTitle(multipartRequest.getParameter("title"));
            board.setContent(multipartRequest.getParameter("content"));
            board.setNo(multipartRequest.getParameter("no"));

            int result = boardService.updateBoard(board,files);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter w = response.getWriter();
            if (result >0){
                w.println("<script>alert('게시판이 수정되었습니다.'); location.href='/board/noticePro.do'</script>");
            } else {
                w.println("<script>alert('게시판 수정에 실패하였습니다.'); location.href='/board/noticePro.do'</script>");
            }
        }
    }
}
