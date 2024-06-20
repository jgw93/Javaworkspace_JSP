package Controller;

import DTO.MemberDTO;
import DTO.NoticeDTO;
import Service.NoticeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "addController", value = "/notice_add")
@Log4j2
public class AddController extends HttpServlet {
    private NoticeService noticeService = NoticeService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/notice_add.jsp").forward(req, resp);
//        try {
//            Long tno = Long.parseLong(req.getParameter("tno"));
//            NoticeDTO noticeDTO = noticeService.get(tno);
//
//            req.setAttribute("noticeDTO", noticeDTO);
//            req.getRequestDispatcher("notice_add.jsp").forward(req, resp);
//        }catch (Exception e){
//            log.error(e.getMessage());
//            throw new ServletException("add get error...");
//        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/notice_add.jsp").forward(req, resp);
//        NoticeDTO noticeDTO = NoticeDTO.builder()
//                .tno(Long.parseLong(req.getParameter("tno")))
//                .title(req.getParameter("title"))
//                .build();
//        log.info("add POST...");
//        log.info(noticeDTO);
//        try {
//            noticeService.modify(noticeDTO);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .title(req.getParameter("title"))
                .content(req.getParameter("content"))
                .build();


        try {
            NoticeService.INSTANCE.add(noticeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/notice_list");
    }
}
