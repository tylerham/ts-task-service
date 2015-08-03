package com.tylerhamiton.taskservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TaskServiceApplication extends Application<TaskServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new TaskServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "task-service";
    }

    @Override
    public void initialize(Bootstrap<TaskServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TaskServiceConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
    }

}