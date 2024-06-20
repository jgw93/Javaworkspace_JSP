package com.example.w2.controller;

import lombok.extern.log4j.Log4j2;
import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "todoReadController",
        value = {"/todo/read"}
)
public class TodoReadController extends HttpServlet {
  private static final Logger log = LogManager.getLogger(TodoReadController.class);
  private TodoService todoService;

  public TodoReadController() {
    this.todoService = TodoService.INSTANCE;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long tno = Long.parseLong(req.getParameter("tno"));
      TodoDTO todoDTO = this.todoService.get(tno);
      req.setAttribute("dto", todoDTO);
      // 쿠키 찾기
      Cookie viewTodoCookie = findCookie(req.getCookies(),"viewTodos");
      String todoListStr = viewTodoCookie.getValue();
      boolean exist = false;
      if(todoListStr != null && todoListStr.indexOf(tno + "-") >= 0){
        exist = true;
      }
      if(!exist){
        todoListStr += tno + "-";
        viewTodoCookie.setValue(todoListStr);
        viewTodoCookie.setMaxAge(60*60*24);
        viewTodoCookie.setPath("/");
        resp.addCookie(viewTodoCookie);
      }





      req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
    } catch (Exception var5) {
      log.error(var5.getMessage());
      throw new ServletException(var5);
    }
  }

  private Cookie findCookie(Cookie[] cookies, String cookieName){
    Cookie targetCookie = null;


    if(cookies != null && cookies.length > 0){
      for (Cookie ck:cookies){
        if(ck.getName().equals(cookieName)){
          targetCookie = ck;
          break;
        }
      }
    }
    if(targetCookie == null){
      targetCookie = new Cookie(cookieName,"");
      targetCookie.setPath("/");
      targetCookie.setMaxAge(60*60*24);
    }
    return targetCookie;
  }

}

