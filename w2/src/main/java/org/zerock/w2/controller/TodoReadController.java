package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.DTO.TodoDTO;
import org.zerock.w2.Service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);
            // 데이터 담기
            req.setAttribute("dto", todoDTO);

            // 쿠키 찾기
            Cookie viewTodoCookie = findCoolie(req.getCookies(), "viewTodos");
            String todolistStr = viewTodoCookie.getValue();
            boolean exist = false;

            if (todolistStr != null && todolistStr.indexOf(tno + "-") >= 0) {
                exist = true;
            }
            log.info("exist: " + exist);

            if (!exist) {
                todolistStr += tno + "-";
                viewTodoCookie.setValue(todolistStr);
                viewTodoCookie.setMaxAge(60 * 60 * 24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    private Cookie findCoolie(Cookie[] cookies, String cookieName) {
        Cookie targetCookie = null;

        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals(cookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }
        if (targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60 * 60 * 24);
        }
        return targetCookie;
    }

}
