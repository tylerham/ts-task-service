package com.tylerhamilton.taskservice.resources;

import com.tylerhamilton.taskservice.core.Task;
import com.tylerhamilton.taskservice.db.TaskDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TasksResource {
    private final String defaultName;
    private final AtomicLong counter;

    private final TaskDAO tasksDAO;

    public TasksResource(String defaultName, TaskDAO tasksDAO) {
        this.defaultName = defaultName;
        this.tasksDAO = tasksDAO;
        counter = new AtomicLong();
    }

    @GET
    @UnitOfWork
    public Task getTask(@QueryParam("id") long id) {
        return new Task(defaultName, defaultName, false);
    }

    @POST
    @UnitOfWork
    public Task createTask(Task task) {
        // POST is for creation, not updating, so set ID to zero, if it has been set, to create a new Task
        task.setId(0);
        System.out.println("############### TasksResource " + task.getId() + task.getTaskName() + task.getAssignedUser() + task.isCompleted());
        return tasksDAO.create(task);
    }

}
