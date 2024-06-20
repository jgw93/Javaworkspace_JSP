package Controller;

import DTO.NoticeDTO;
import Service.NoticeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="listController", value="/notice_list")
@Log4j2

public class ListController extends HttpServlet {
    private NoticeService noticeService = NoticeService.INSTANCE;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try{
            List<NoticeDTO> dtoList = noticeService.listALL();
            req.setAttribute("dtoList", dtoList);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("list error");
        }
        req.getRequestDispatcher("/notice_list.jsp").forward(req, resp);
    }

}
