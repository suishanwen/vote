/*
package com.vote.module;


import com.google.inject.AbstractModule;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

import javax.persistence.EntityManager;
import java.util.Properties;

public class DbModule extends AbstractModule {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE
            = new ThreadLocal<EntityManager>();

    public void configure() {
        bind(PersistFilter.class);

        install(new JpaPersistModule("domain").properties(toProperties()));
        install(new ServletModule() {
            @Override
            protected void configureServlets() {
                filter("/api*/
/*").through(PersistFilter.class);
            }
        });

    }

    public Properties toProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

        properties.put("javax.persistence.jdbc.driver", "oracle.jdbc.driver.OracleDriver");
        properties.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@localhost:1521:orcl");
        properties.put("javax.persistence.jdbc.user","vote");
        properties.put("javax.persistence.jdbc.password", "vote");

        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "true");

        return properties;
    }

}
*/
