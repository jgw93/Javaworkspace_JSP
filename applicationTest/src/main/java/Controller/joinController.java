package Controller;

import DAO.MemberDAO;
import DTO.MemberDTO;
import Service.MemberService;
import lombok.extern.log4j.Log4j2;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "joinController", value = "/join")
public class joinController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/join.jsp GET......");
        req.getRequestDispatcher("/join.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(req.getParameter("memberId"))
                .memberPw(req.getParameter("memberPw"))
                .name(req.getParameter("name"))
                .phone(req.getParameter("phone"))
                .email1(req.getParameter("memberId"))
                .email2(req.getParameter("email2"))
                .gender(req.getParameter("gender"))
                .build();
        if(req.getParameter("agree").equals("on")){
            memberDTO.setAgree(true);
        }else{
            memberDTO.setAgree(false);
        }


        log.info("/join.jsp");
        log.info(memberDTO);
        try {
            memberService.register(memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/join");

    }
}


