package com.tylerhamilton.taskservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "taskName", nullable = false)
    private String taskName;

    @Column(name = "assignedUser", nullable = false)
    private String assignedUser;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    public Task() {
    }

    public Task(String taskName, String assignedUser, boolean completed) {
        this.taskName = taskName;
        this.assignedUser = assignedUser;
        this.completed = completed;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @JsonProperty
    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    @JsonProperty
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
