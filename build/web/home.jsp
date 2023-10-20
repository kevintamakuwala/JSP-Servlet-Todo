<%-- 
    Document   : home
    Created on : 19-Oct-2023, 11:35:08 pm
    Author     : kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.todo.database.models.Task" %>
<%@page import="com.todo.database.controllers.TasksManage" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>To-Do List</title>

        <!-- Include Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <style>
            .home__container{
                display: flex;
                align-items: center;
                justify-content: start;
                margin-top: 10rem;
                flex-direction: column;
                height: 100%;
            }
            .button__container{
                display:flex;
                gap:1rem;
            }
            .heading__container{
                display:flex;
                align-items: center;
                justify-content:  center;
            }
            .logout{
                /*float:right;*/
                right:3rem;
                position: absolute;
            }
        </style>
    </head>
    <body>
        <div class="container" style="min-height:100vh">
            <%                    
                    HttpSession hp = request.getSession(false);
                    if (hp == null) {
                        // Redirect to the login page if the session is not available
                        response.sendRedirect("login");
                    } else {
                        // Check if user_id attribute exists
                        Object user_idObj = hp.getAttribute("user_id");
                        if (user_idObj != null) {
                            int user_id = (int) user_idObj;
                            String username = (String) hp.getAttribute("username");
                            String password = (String) hp.getAttribute("password");
            %>
            <div class="heading__container">
                <h1 style="text-align:center">My Todo App</h1>
                <div class="logout">

                    <form action="Logout" method="post">
                        <button type="submit" class="btn btn-danger">
                            Logout
                        </button>
                    </form>
                </div>
            </div>

            <div class="home__container">

                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary mb-5" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                    Add Task
                </button>

                <!-- Modal -->
                <form action="Add" method="post">
                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Add a Task</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="formGroupExampleInput" class="form-label">Title</label>
                                        <input type="text" class="form-control" name="title" placeholder="Homework" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="formGroupExampleInput2" class="form-label">Description</label>
                                        <input type="text" class="form-control" name="description" placeholder="at 10pm after Dinner.">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="hidden" name="user_id"  value="<%= user_id %>"/>
                                    <button class="btn btn-primary" type="submit" id="addTaskButton">Add Task</button>
                                    <button class="btn btn-secondary" data-bs-dismiss="modal" id="addCancelButton" type="button"> Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <div class="accordion" style="width: 80vw">
                    <%         
                        List<Task> taskList=TasksManage.getTasks(user_id);
                        
                        for (int i=0;i<taskList.size();i++) {
                            
                            String buttonId = "accordionButton" + i;
                            String collapseId = "accordionCollapse" + i;
                            
                            Task t = taskList.get(i);
                            int task_id = t.getTaskId();
                            String title = t.getTitle();
                            String description = t.getDescription();
                            boolean status=t.isCompleted();
                    %>
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#<%= collapseId %>" aria-expanded="false" aria-controls="<%= collapseId %>">
                                <%
                                    if(t.isCompleted()){
                                        out.println("<del>"+title+"</del>");
                                    }else{
                                       out.println("<span>"+title+"</span>");                                   }
                                %>
                            </button>
                        </h2>
                        <div id="<%= collapseId %>" class="accordion-collapse collapse">
                            <div class="accordion-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <%
                                                if(!description.equals("")){
                                                    out.println("<th scope='col'>Description</th>");
                                                }
                                            %>
                                            <th scope="col">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>

                                            <%
                                                if(!description.equals("")){
                                                out.println("<td><span>"+description+"</span></td>");
                                            }
                                            %>

                                            <td>
                                                <%
                                                    if(t.isCompleted()){
                                                        out.println("Completed");
                                                    }else{
                                                        out.println("Pending");
                                                    }
                                                %>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>

                                <div class="button__container">
                                    <form action="Done" method="post">
                                        <input type="hidden" name="task_id" value="<%= task_id %>"/>
                                        <input type="hidden" name="markDone" value="true"/>

                                        <%
                                            if(!status){
                                                out.println("<button class='btn btn-success' type='submit'>Mark as Done</button>");
                                            }else{
                                                out.println("<button class='btn btn-success' type='submit'>Mark as Pending</button>");
                                            }
                                        %>
                                    </form>
                                    <form action="Delete" method="post">
                                        <input type="hidden" name="task_id" value="<%= task_id %>"/>
                                        <input type="hidden" name="deleteTask" value="true"/>
                                        <button class="btn btn-danger" type="submit">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>


                <%
                        }
                    }   
                %>

            </div>

        </div>


        <script>
            // Get references to the input fields and the Add Task button
            var titleInput = document.querySelector("input[name='title']");
            var descriptionInput = document.querySelector("input[name='description']");
            var addTaskButton = document.getElementById("addTaskButton");

            // Add an event listener to check input fields when they change
            titleInput.addEventListener("input", toggleAddButton);
            descriptionInput.addEventListener("input", toggleAddButton);

            // Function to toggle the Add Task button based on input fields
            function toggleAddButton() {
                if (titleInput.value.trim() !== "") {
                    addTaskButton.dataset.bsDismiss = "modal";
                } else {
                    delete addTaskButton.dataset.bsDismiss;
                }
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
