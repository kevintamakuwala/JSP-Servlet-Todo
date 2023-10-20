<%-- 
    Document   : register
    Created on : 19-Oct-2023, 11:06:43 pm
    Author     : kevin
--%>
<%@ page import="com.todo.database.controllers.authentication.Register" %>
<%@ page import="com.todo.database.models.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <style>
            .home__container{
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: column;
                min-height: 100vh;
                width:auto;
            }
        </style>
    </head>
    <body>
        <div class="container home__container" >
            <form method="post">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Username</label>
                    <input type="text" class="form-control" name="username">
                    <div class="form-text">We'll never share your details with anyone else.</div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>

                <div class="mt-3">
                    Already an User? <a href="login">Login Here</a>
                </div>           
            </form>
        </div>
        <% 
            HttpSession httpSession = request.getSession(true);
            if(httpSession!=null && httpSession.getAttribute("user_id")!=null)
            {
                // Redirect to home.jsp
                response.sendRedirect("home");
            }else{
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (request.getMethod().equals("POST")) {
                    boolean status = Register.registerUser(username, password);

                    if (status) {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        out.println("<h1>Something went Wrong</h1>");
                    }
                }
            }
        %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </body>
</html>
