package com.example.w2.controller;

import com.example.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;
import com.example.w2.dto.TodoDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(
        name = "todoModifyController",
        value = {"/todo/modify"}
)
public class TodoModifyController extends HttpServlet {
  private static final Logger log = LogManager.getLogger(TodoModifyController.class);
  private TodoService todoService;
  private final DateTimeFormatter DATEFORMATTER;

  public TodoModifyController() {
    this.todoService = TodoService.INSTANCE;
    this.DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long tno = Long.parseLong(req.getParameter("tno"));
      TodoDTO todoDTO = this.todoService.get(tno);
      req.setAttribute("dto", todoDTO);
      req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
    } catch (Exception var5) {
      log.error(var5.getMessage());
      throw new ServletException("modify get.....error");
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String finishedStr = req.getParameter("finished");
    TodoDTO todoDTO = TodoDTO.builder().tno(Long.parseLong(req.getParameter("tno"))).title(req.getParameter("title")).dueDate(LocalDate.parse(req.getParameter("dueDate"), this.DATEFORMATTER)).finished(finishedStr != null && finishedStr.equals("on")).build();

    try {
      this.todoService.modify(todoDTO);
    } catch (Exception var6) {
      var6.printStackTrace();
    }

    resp.sendRedirect("/todo/list");
  }
}
