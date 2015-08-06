package com.tylerhamilton.taskservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "task")
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

    public Task(long id, String taskName, String assignedUser, boolean completed) {
        this.id = id;
        this.taskName = taskName;
        this.assignedUser = assignedUser;
        this.completed = completed;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getTaskName() {
        return taskName;
    }

    @JsonProperty
    public String getAssignedUser() {
        return assignedUser;
    }

    @JsonProperty
    public boolean isCompleted() {
        return completed;
    }


}
