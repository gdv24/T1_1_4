package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";

    private static SessionFactory sessionFactory;


    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER); // регистрация JDBC драйвера
            connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD); // Передача информации о БД
            System.out.println("Connection Ok");
        } catch (ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DB_DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER_NAME);
                settings.put(Environment.PASS,USER_PASSWORD);
                settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL,"true");
                settings.put(Environment.FORMAT_SQL,"true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static void shutdownHibernate(){
        getSessionFactory().close();
    }


}
