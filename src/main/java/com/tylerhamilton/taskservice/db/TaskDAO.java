package com.tylerhamilton.taskservice.db;

import com.tylerhamilton.taskservice.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TaskDAO extends AbstractDAO<Task> {
    public TaskDAO(SessionFactory factory) {
        super(factory);
    }

    public Task create(Task task) {
        return persist(task);
    }

    public List<Task> findAllAssignedTo(String assignedUser) {
        Criteria criteria = criteria().add(Restrictions.ilike("assignedUser", assignedUser, MatchMode.EXACT));
        return list(criteria);
    }

    public Task findById(long id) {
        return get(id);
    }

    public Task saveOrUpdate(Task existingTask) {
        return super.persist(existingTask);
    }

    public void delete(Task savedTask) {
        super.currentSession().delete(savedTask);
    }
}
