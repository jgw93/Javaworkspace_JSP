package com.example.w2.controller;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet({"/logout"})
public class LogoutController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LogoutController.class);

    public LogoutController() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("log out..............");
        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();
        resp.sendRedirect("/");
    }
}
