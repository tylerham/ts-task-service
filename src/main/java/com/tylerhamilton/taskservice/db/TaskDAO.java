package com.tylerhamilton.taskservice.db;

import com.google.common.base.Optional;
import com.tylerhamilton.taskservice.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Map;

import java.util.List;

public class TaskDAO extends AbstractDAO<Task> {
    public TaskDAO(SessionFactory factory) {
        super(factory);
        System.out.println("task dao ctor after super");
    }
    public Task create(Task task) {
        return persist(task);
    }

    public List<Task> findAllAssignedTo(String userName) {
        criteria().add(Restrictions.eq("assignedUser", userName));
        return list(criteria());
    }

}
