package com.olaniyi.DAO;

import com.olaniyi.Model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class JdbcDapImpl {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Autowired
    private DataSource dataSource;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCircleCount(){
        String queryToGetCount = "SELECT COUNT(*) FROM circle";
        jdbcTemplate.getDataSource();
        return jdbcTemplate.queryForObject(queryToGetCount, Integer.class);
    }

    public  String getCirclName(int circleId){
        String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
        return  jdbcTemplate.queryForObject(sql, new Object[]{circleId}, String.class);
    }

    public void insertCircle(Circle circle){
        String sql = "INSERT INTO circle (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, new Object[]{circle.getId(), circle.getName()});

    }









}
