package com.example.w2.controller;


import com.example.w2.dto.MemberDTO;
import com.example.w2.service.MemberService;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(
        name = "loginController",
        value = {"/login"}
)
public class LoginController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LoginController.class);

    public LoginController() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login Get ...........");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login post........");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String auto = req.getParameter("auto");
        boolean rememberMe = auto != null && auto.equals("on");



        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);
        if(rememberMe) {
            String uuid = UUID.randomUUID().toString();
            MemberService.INSTANCE.updateUuid(mid, uuid);
            memberDTO.setUuid(uuid);
            Cookie rememberCookie = new Cookie("remember-me", uuid);
            rememberCookie.setMaxAge(60*60*24*7); // 쿠키 유효기한은 1주일
            rememberCookie.setPath("/");

            resp.addCookie(rememberCookie);
        }
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo",memberDTO);
            resp.sendRedirect("/todo/list");
        }
        catch (Exception e){
            resp.sendRedirect("login?result=error");
        }
    }

    }
