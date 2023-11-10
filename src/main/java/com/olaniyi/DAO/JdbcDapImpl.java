package com.olaniyi.DAO;

import com.olaniyi.Model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class JdbcDapImpl {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Autowired
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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

//    public void insertCircle(Circle circle){
//        String sql = "INSERT INTO circle (id, name) VALUES (?, ?)";
//        jdbcTemplate.update(sql, new Object[]{circle.getId(), circle.getName()});
//
//    }

    public void insertCircle(Circle circle) {
        String sql = "INSERT INTO circle (id, name) VALUES (:id, :name)";
        SqlParameterSource nameParameter = new MapSqlParameterSource("id", circle.getId()).addValue("name", circle.getName());
        namedParameterJdbcTemplate.update(sql, nameParameter);
    }



    public void createTriangle () {
        String sql = "CREATE TABLE TRIANGLE (id int, name varchar (25) )";
        jdbcTemplate.execute(sql);
    }









}
