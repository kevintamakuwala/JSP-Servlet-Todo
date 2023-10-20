/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.database.controllers;

import com.todo.database.ConnectionProvider;
import com.todo.database.models.Task;
import com.todo.database.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class TasksManage {

    public static boolean addTask(int user_id, String title, String description) {
        try {
            Connection con = ConnectionProvider.CreateConnection();
            String query = "insert into TASKS(user_id,title,description,completed) values(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);

//          Setting values of parameters
            pstmt.setInt(1, user_id);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setBoolean(4, false);

            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Task> getTasks(int userId) {
        List<Task> taskList = new ArrayList<>();
        try {
            Connection con = ConnectionProvider.CreateConnection();
            String query = "select * from tasks where user_id=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int task_id = resultSet.getInt("task_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                boolean completed = resultSet.getBoolean("completed");
                taskList.add(new Task(task_id, title, description, completed));
            }

            resultSet.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
        }

        return taskList;
    }

    public static boolean deleteTask(int taskId) {
        try {
            Connection con = ConnectionProvider.CreateConnection();
            String query = "delete from TASKS where task_id=?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, taskId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean toggleCompleted(int taskId) {
        try {
            Connection con = ConnectionProvider.CreateConnection();

            // First, retrieve the current status
            String q1 = "SELECT completed FROM TASKS WHERE task_id = ?";
            PreparedStatement pstmt = con.prepareStatement(q1);
            pstmt.setInt(1, taskId); // Set the task ID
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                boolean currentStatus = rs.getBoolean("completed");

                // Toggle the status
                boolean newStatus = !currentStatus;

                // Update the status
                String query = "UPDATE TASKS SET completed = ? WHERE task_id = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setBoolean(1, newStatus);
                pstmt.setInt(2, taskId);

                int updatedRows = pstmt.executeUpdate();

                return updatedRows > 0; // Return true if at least one row was updated
            } else {
                // Task with the given ID doesn't exist
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
