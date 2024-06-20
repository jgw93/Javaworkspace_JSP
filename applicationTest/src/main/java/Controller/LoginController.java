package Controller;

import DTO.MemberDTO;
import Service.MemberService;
import lombok.extern.log4j.Log4j2;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "loginController", value = "/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login Get ...........");

        //HttpSession session = req.getSession();
//        if(session.isNew()){
//            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
//            resp.sendRedirect("/login");
//            return;
//        }
//        if(session.getAttribute("loginInfo") == null){
//            log.info("로그인한 정보가 없는 사용자");
//            resp.sendRedirect("/login");
//            return;
//        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login Post ...........");
        String memberId = req.getParameter("memberId");
        String memberPw = req.getParameter("memberPw");

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(memberId,memberPw);
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/");
        }catch(Exception e){
            resp.sendRedirect("/login?result=error");
        }
    }
}

