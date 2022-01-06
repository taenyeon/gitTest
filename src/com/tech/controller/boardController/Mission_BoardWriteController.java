package com.tech.controller.boardController;

import com.tech.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Mission_BoardWriteController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        if (request.getMethod().equals("GET")) {
//            response.sendRedirect("/board/noticeReg.jsp");
//        } else if (request.getMethod().equals("POST")) {
//            Map<String,String> datas = new HashMap<>();
//            String path = request.getSession().getServletContext().getRealPath("/files/");
//            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//            diskFileItemFactory.setSizeThreshold(4096);
//            diskFileItemFactory.setRepository(new File(path + "/uploadTest"));
//            ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
//
//            int size = 15 * 1024 * 1024; // 15MB
//            upload.setFileSizeMax(size);
//            upload.setHeaderEncoding("utf-8");
//            List<FileItem> items = upload.parseRequest(request);
//            Iterator<FileItem> files = items.iterator();
//            List<bowlFile> fileList = new ArrayList<>();
//
//            while (files.hasNext()){
//                FileItem file = files.next();
//                file.getString("utf-8");
//                if (file.isFormField()){
//                    String name = file.getFieldName();
//                    String value = new String((file.getString()).getBytes("8859_1"), StandardCharsets.UTF_8);
//                    datas.put(name,value);
//                } else {
//                    bowlFile fileItem = new bowlFile();
//                    String fileName = new File(System.currentTimeMillis()+"_"+file.getName()).getName();
//                    File storeFile = new File(path+fileName);
//                    datas.put(file.getName(),fileName);
//                    file.write(storeFile);
//                    fileItem.setFilePath(path +fileName);
//                    fileItem.setFileName(file.getName());
//                    fileItem.setFileSize(file.getSize()/1024); // kb 단위
//                    fileList.add(fileItem);
//                }
//            }
//
//            BoardService boardService = new BoardService();
//            HttpSession session = request.getSession();
//            bowlBoard board = new bowlBoard();
//            board.setTitle(datas.get("title"));
////            board.setWriter((String) session.getAttribute("login"));
//            board.setWriter(datas.get("login"));
//            board.setContent(datas.get("content"));
//
//            int result = boardService.writeBoard(board, fileList);
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter w = response.getWriter();
//            if (result > 0) {
//                w.println("<script>alert('게시물이 업로드되었습니다.'); location.href='/board/notice.do'</script>");
//            } else {
//                w.println("<script>alert('게시물 업로드에 실패하였습니다.'); location.href='/board/notice.do'</script>");
//            }
//
//        }
    }
}
