package com.olaniyi.DAO;

import com.olaniyi.Model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class JdbcDapImpl {

    @Autowired
    private DataSource dataSource;
    public Circle getCircle(int circleId) {
        //set connection variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Circle circle = null;
        ResultSet resultSet = null;
        String userName = "app";
        String password = "app";


        try {
            // Establish the database connection
            connection = dataSource.getConnection(userName, password);

            // Define a SQL query to retrieve the Circle by its ID
            String query = "SELECT * FROM circle WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, circleId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                circle = new Circle(circleId, resultSet.getString("name"));
            }
        } catch ( SQLException e) {
            e.printStackTrace();
            // Handle exceptions here
        }
        finally {

            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return circle;
    }
}}
