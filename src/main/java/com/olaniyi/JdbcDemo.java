package com.olaniyi;

import com.olaniyi.DAO.JdbcDapImpl;
import com.olaniyi.Model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class JdbcDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JdbcDapImpl dao = context.getBean("JdbcDapImpl", JdbcDapImpl.class);
        Circle circle = dao.getCircle(3);
        System.out.println(circle.getName());

    }
}
