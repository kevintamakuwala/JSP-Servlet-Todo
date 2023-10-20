package com.todo.database.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author kevin
 */
public class Done extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hp = request.getSession(false);
        if (hp != null) {
            boolean toggleTaskParam = Boolean.parseBoolean(request.getParameter("markDone"));
            int id = Integer.parseInt(request.getParameter("task_id"));

            if (toggleTaskParam) {
                if (TasksManage.toggleCompleted(id)) {
                    response.sendRedirect("home");
                }
            }
        } else {
            response.sendRedirect("login");
        }
    }

}
