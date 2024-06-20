package com.example.w2.controller;

import lombok.extern.log4j.Log4j2;
import com.example.w2.service.TodoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "todoRemoveController",
        value = {"/todo/remove"}
)
public class TodoRemoveController extends HttpServlet {
  private static final Logger log = LogManager.getLogger(TodoRemoveController.class);
  private TodoService todoService;

  public TodoRemoveController() {
    this.todoService = TodoService.INSTANCE;
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long tno = Long.parseLong(req.getParameter("tno"));
      log.info("tno: " + tno);
      this.todoService.remove(tno);
    } catch (Exception var4) {
      var4.printStackTrace();
    }

    resp.sendRedirect("/todo/list");
  }
}
