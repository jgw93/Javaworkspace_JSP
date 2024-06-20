package Controller;

import DTO.MemberDTO;
import Service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Log4j2
@WebServlet(name= "joinController", value = "join.jsp")
public class joinController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/join.jsp GET......");
        req.getRequestDispatcher("join.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(req.getParameter("email_lbl"))
                .memberPw(req.getParameter("pwd_lbl"))
                .name(req.getParameter("name_lbl"))
                .phone(req.getParameter("phone_lbl"))
                .createDate(new Date(req.getParameter("createDate")))
                .build();

        log.info("/join.jsp POST......");
        log.info(memberDTO);
        try {
            memberService.register(memberDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/join.jsp");
    }
}


