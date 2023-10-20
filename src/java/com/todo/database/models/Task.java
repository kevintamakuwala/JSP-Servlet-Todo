package com.todo.database.models;

/**
 *
 * @author kevin
 */
public class Task {

    private User user;
    private int taskId;
    private String title, description;
    private boolean completed = false;

    public Task(int taskId, User user, String title, String description, boolean completed) {
        this.taskId = taskId;
        this.user = user;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" + "user=" + user + ", taskId=" + taskId + ", title=" + title + ", description=" + description + ", completed=" + completed + '}';
    }

    public Task(int taskId, String title, String description, boolean completed) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Task(User user, String title, String description, boolean completed) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Task(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public Task(User user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
