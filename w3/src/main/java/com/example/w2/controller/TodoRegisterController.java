package com.example.w2.controller;

import lombok.extern.log4j.Log4j2;
import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(
        name = "todoRegisterController",
        value = {"/todo/register"}
)
public class TodoRegisterController extends HttpServlet {
  private static final Logger log = LogManager.getLogger(TodoRegisterController.class);
  private TodoService todoService;
  private final DateTimeFormatter DATEFORMAT;

  public TodoRegisterController() {
    this.todoService = TodoService.INSTANCE;
    this.DATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("/todo/register GET ........");
    HttpSession session = req.getSession();
    if (session.isNew()) {
      log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
      resp.sendRedirect("/login");
    } else if (session.getAttribute("loginInfo") == null) {
      log.info("로그인한 정보가 없는 사용자");
      resp.sendRedirect("/login");
    } else {
      req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    TodoDTO todoDTO = TodoDTO.builder().title(req.getParameter("title")).dueDate(LocalDate.parse(req.getParameter("dueDate"), this.DATEFORMAT)).build();
    log.info("/todo/register POST ........");
    log.info(todoDTO);

    try {
      this.todoService.register(todoDTO);
    } catch (Exception var5) {
      var5.printStackTrace();
    }

    resp.sendRedirect("/todo/list");
  }
}
