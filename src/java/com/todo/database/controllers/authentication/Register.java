package com.todo.database.controllers.authentication;

/**
 *
 * @author kevin
 */
import com.todo.database.ConnectionProvider;
import com.todo.database.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {

    public static boolean registerUser(String username,String password) {
        try {
            if (Login.validateLogin(username,password)!=null) {
                return true;
            }
            Connection con = ConnectionProvider.CreateConnection();
            String query = "insert into USERS(USERNAME,PASSWORD) values(?,?);";
            PreparedStatement pstmt = con.prepareStatement(query);

//          Setting values of parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
