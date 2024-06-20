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
import java.util.List;

@WebServlet(
        name = "todoListController",
        value = {"/todo/list"}
)
public class TodoListController extends HttpServlet {
  private static final Logger log = LogManager.getLogger(TodoListController.class);
  private TodoService todoService;

  public TodoListController() {
    this.todoService = TodoService.INSTANCE;
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("todo list....................");

    try {
      List<TodoDTO> dtoList = this.todoService.listALL();
      req.setAttribute("dtoList", dtoList);
      req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    } catch (Exception var4) {
      log.error(var4);
      throw new ServletException("list error");
    }
  }
}

