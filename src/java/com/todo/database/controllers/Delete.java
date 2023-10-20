package com.todo.database.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdk.jshell.execution.Util;

public class Delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hp = request.getSession(false);
        if (hp != null) {

            boolean deleteTaskParam = Boolean.parseBoolean(request.getParameter("deleteTask"));
            int id = Integer.parseInt(request.getParameter("task_id"));

            if (deleteTaskParam) {
                if (TasksManage.deleteTask(id)) {
                    response.sendRedirect("home");
                }
            }
        } else {
            response.sendRedirect("login");
        }
    }

}
