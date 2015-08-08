package com.tylerhamilton.taskservice.db;

import com.tylerhamilton.taskservice.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TaskDAO extends AbstractDAO<Task> {
    public TaskDAO(SessionFactory factory) {
        super(factory);
        System.out.println("task dao ctor after super");
    }

    public Task create(Task task) {
        System.out.println("#$$$$$$$$$$$$$$$ TaskDAO " +task.getId() + task.getTaskName() + task.getAssignedUser() + task.isCompleted());
        return persist(task);
    }

    public List<Task> findAllAssignedTo(String userName) {
        criteria().add(Restrictions.eq("assignedUser", userName));
        return list(criteria());
    }

}
