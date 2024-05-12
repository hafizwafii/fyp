package com.heroku.java.SERVICES;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList; 
import java.util.List; 
import javax.sql.DataSource; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service; 
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;

import com.heroku.java.MODEL.Volunteer;

@Repository
public class LoginDAO {
    private DataSource dataSource;

    public LoginDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //model V
    public Volunteer checkVolunteer(String username, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("password : "+ password);
            System.out.println("username : "+ username);
            String sql = "SELECT vid, vfullname FROM volunteer WHERE vusername = ? AND vpassword = ?";// check attribute database
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            //selagi db tu baca dia amik benda tu yang declare 
            if (resultSet.next()) {
                int id = resultSet.getInt("vid");
                String name = resultSet.getString("vfullname");
                // attribute dia amik dari db pass ke controller
                return new Volunteer(id, name, username, password);
            }
            connection.close();
        } 
        return null;
    }


    
}