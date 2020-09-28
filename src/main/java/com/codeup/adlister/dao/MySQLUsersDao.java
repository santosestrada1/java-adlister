package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection = null;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        PreparedStatement stmt = null;
        User user = new User();
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
        return user;
    }

    @Override
    public Long insert(User user) {
        try {
            String insertQuery = "INSERT INTO users(username,email,password) VALUES (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }

    }


    public static void main(String[] args) {
        MySQLUsersDao dao = new MySQLUsersDao(new Config());
        User user = new User();
        user.setUsername("instructors");
        user.setEmail("instructors@codeup.com");
        user.setPassword("password");
        dao.insert(user);

        User found = dao.findByUsername("instructors");
        System.out.println(found.getEmail());
    }
}