package com.tylerhamilton.taskservice.resources;

import com.tylerhamilton.taskservice.core.Task;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final String defaultName;
    private final AtomicLong counter;

    public TaskResource(String defaultName) {
        this.defaultName = defaultName;
        counter = new AtomicLong();
    }

    @GET
    @Timed
    public Task getTask(@QueryParam("id") long id){
        return new Task(counter.incrementAndGet(), defaultName, defaultName,false);
    }
}
