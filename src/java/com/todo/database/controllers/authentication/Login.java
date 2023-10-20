package com.todo.database.controllers.authentication;

/**
 *
 * @author kevin
 */
import com.todo.database.ConnectionProvider;
import com.todo.database.models.User;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public static User validateLogin(String user, String pwd) {
        try {
            Connection con = ConnectionProvider.CreateConnection();
            String query = "select * from USERS;";
            Statement stmt = con.createStatement();

            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                
                int user_id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                if (user.equals(username) && pwd.equals(password)) {
                    return new User(user_id,username, password);
                }
            }
            con.close();
            stmt.close();
        } catch (SQLException e) {
            return null;
        }
        return null;
    }
}
