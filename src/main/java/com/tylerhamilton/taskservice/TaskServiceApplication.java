package com.tylerhamilton.taskservice;

import com.tylerhamilton.taskservice.core.Task;
import com.tylerhamilton.taskservice.db.TaskDAO;
import com.tylerhamilton.taskservice.resources.TasksResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;

public class TaskServiceApplication extends Application<TaskServiceConfiguration> {
    private final HibernateBundle<TaskServiceConfiguration> hibernate = new HibernateBundle<TaskServiceConfiguration>(Task.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TaskServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new TaskServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "task-service";
    }

    @Override
    public void initialize(Bootstrap<TaskServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TaskServiceConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TaskServiceConfiguration c) {
                return c.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(TaskServiceConfiguration configuration,
                    Environment environment) {
        System.out.println("started run");
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        System.out.println("hibernate bundle get session factory is null " + sessionFactory == null);
        final TaskDAO taskDAO = new TaskDAO(sessionFactory);
        final TasksResource tasksResource = new TasksResource(
                configuration.getDefaultName(),
                taskDAO
        );
        environment.jersey().register(tasksResource);
    }

}
