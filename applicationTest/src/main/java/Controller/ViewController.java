package Controller;

import DTO.NoticeDTO;
import Service.NoticeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "viewController", value = "/notice_view")
@Log4j2
public class ViewController extends HttpServlet {
    private NoticeService noticeService = NoticeService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            NoticeDTO noticeDTO = noticeService.get(tno);
            // 데이터 담기
            req.setAttribute("dto", noticeDTO);

            req.getRequestDispatcher("/notice_view.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        Long tno = Long.parseLong(req.getParameter("tno"));
        log.info("tno: " + tno);
            noticeService.remove(tno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/notice_list");
    }

}
