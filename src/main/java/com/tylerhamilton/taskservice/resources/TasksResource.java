package com.tylerhamilton.taskservice.resources;

import com.tylerhamilton.taskservice.core.Task;
import com.tylerhamilton.taskservice.db.TaskDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TasksResource {
    private final TaskDAO tasksDAO;

    public TasksResource(TaskDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Task getTask(@PathParam("id") LongParam id) {
        long taskId = id.get();
        Task task = tasksDAO.findById(taskId);
        if (task == null) {
            throw new NotFoundException("No task found with id " + taskId);
        }
        return task;
    }

    @GET
    @UnitOfWork
    public Task[] getTasks(@QueryParam("user") String user) {
        List<Task> assignedTaskList = tasksDAO.findAllAssignedTo(user);
        if (assignedTaskList == null || assignedTaskList.isEmpty()) {
            throw new NotFoundException("No tasks found with assigned user " + user);
        }
        Task[] tasks = assignedTaskList.toArray(new Task[assignedTaskList.size()]);

        return tasks;
    }

    @POST
    @UnitOfWork
    public Task createTask(Task newTask) {
        // POST is for creation, not updating, so set ID to zero, if it has been set, to create a new Task
        newTask.setId(0);
        return tasksDAO.create(newTask);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response deleteTask(@PathParam("id") LongParam id) {
        Task taskToDelete = getTask(id);
        tasksDAO.delete(taskToDelete);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}")
    @UnitOfWork
    public Task updateTask(@PathParam("id") LongParam id, Task task) {
        long taskId = id.get();
        if (task.getId() != 0 && taskId != task.getId()) {
            throw new BadRequestException("Can not change the id of a task.");
        }
        Task updatedTask = tasksDAO.findById(taskId);

        // fragile if new properties are added
        // able to work around with reflection or an auto mapping library (which would use reflection internally)
        // or just a "reminder" by creating Unit Tests that check to make sure no new properties were added there that need to be added here too, and fail if they weren't added here
        if (task.getTaskName() != "") {
            updatedTask.setAssignedUser(task.getTaskName());
        }
        if (task.getAssignedUser() != "") {
            updatedTask.setAssignedUser(task.getAssignedUser());
        }
        updatedTask.setCompleted(task.isCompleted());

        return tasksDAO.saveOrUpdate(updatedTask);
    }
}
