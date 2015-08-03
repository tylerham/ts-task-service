package com.tylerhamiton.taskservice.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    private long id;

    private String name;
    private String assignedUser;
    private boolean completed;

    public Task() {
    }

    public Task(long id, String name, String assignedUser, boolean completed) {
        this.id = id;
        this.name = name;
        this.assignedUser = assignedUser;
        this.completed = completed;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
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
