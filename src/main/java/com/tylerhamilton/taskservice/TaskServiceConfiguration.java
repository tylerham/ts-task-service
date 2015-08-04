package com.tylerhamilton.taskservice;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class TaskServiceConfiguration extends Configuration {
    @NotEmpty
    private String defaultName = "New User";

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
