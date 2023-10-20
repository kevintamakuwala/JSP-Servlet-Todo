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
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hp = request.getSession(false);
        if (hp != null) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            if (TasksManage.addTask(user_id, title, description)) {
               response.sendRedirect("home");
            }
        } else {
            response.sendRedirect("login");
        }
    }
}
