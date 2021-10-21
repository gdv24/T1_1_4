package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
//        UserServiceImpl b = new UserServiceImpl();
//        b.dropUsersTable();
//        b.createUsersTable();
//        b.saveUser("N", "B", (byte) 56);
//        b.saveUser("A", "A", (byte) 56);
//        b.removeUserById(0);
//        System.out.println(b.getAllUsers());
//        b.cleanUsersTable();

        UserService v = new UserServiceImpl();
        v.dropUsersTable();
        v.createUsersTable();
        v.saveUser("First", "lastFirst", (byte) 56);
        v.saveUser("Second", "lastSecond", (byte) 57);
        v.removeUserById(1);
        System.out.println(v.getAllUsers());
        v.cleanUsersTable();
    }
}
