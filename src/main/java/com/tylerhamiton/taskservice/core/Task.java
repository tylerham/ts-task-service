package com.tylerhamiton.taskservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    private long id;

    private String taskName;
    private String assignedUser;

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
