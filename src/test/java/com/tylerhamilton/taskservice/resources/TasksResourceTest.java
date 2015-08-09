package com.tylerhamilton.taskservice.resources;

import com.tylerhamilton.taskservice.core.Task;
import com.tylerhamilton.taskservice.db.TaskDAO;
import com.tylerhamilton.taskservice.resources.TasksResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TasksResourceTest {
    private static final TaskDAO tasksDAO = mock(TaskDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TasksResource(tasksDAO))
            .build();

    @Captor
    private ArgumentCaptor<Task> taskCaptor;
    private Task newTask;
    private Task savedTask;

    @Before
    public void setup() {
        newTask = new Task("New Test Task", "User One", false);
        savedTask = new Task("Previously Saved Task", "Second User", true);
        savedTask.setId(5);
    }

    @After
    public void tearDown() {
        reset(tasksDAO);
    }

    @Test
    public void testPostTask() {
        when(tasksDAO.create(any(Task.class))).thenReturn(newTask);
        final Response response = resources.client().target("/tasks")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(newTask, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(tasksDAO).create(taskCaptor.capture());
        // could use reflection here to iterate through properties, or implement .equals in Task
        Assertions.assertThat(taskCaptor.getValue().getAssignedUser()).isEqualTo(newTask.getAssignedUser());
        Assertions.assertThat(taskCaptor.getValue().getTaskName()).isEqualTo(newTask.getTaskName());
    }

    @Test
    public void listPeople() throws Exception {
        final String savedUserName = savedTask.getAssignedUser();
        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(savedTask);
        when(tasksDAO.findAllAssignedTo(savedUserName)).thenReturn(tasks);

        final List<Task> response = resources.client().target("/tasks").queryParam("user", savedUserName)
                .request().get(new GenericType<List<Task>>() {
                });

        verify(tasksDAO).findAllAssignedTo(savedUserName);
        Task returnedTask = (Task) response.toArray()[0];
        Assertions.assertThat(returnedTask.getAssignedUser()).isEqualToIgnoringCase(savedUserName);
        Assertions.assertThat(returnedTask.getTaskName()).isEqualToIgnoringCase(savedTask.getTaskName());
    }
}
