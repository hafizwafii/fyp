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

import com.heroku.java.MODEL.Admin;
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

    public Admin checkAdmin(String adminusername, String adminpassword) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("username : "+ adminusername);
            System.out.println("password : "+ adminpassword);

            String sql = "SELECT adminid, adminname, role FROM admin WHERE adminusername = ? AND adminpassword = ? ";// check attribute database
            PreparedStatement statement = connection.prepareStatement(sql);
            // select where
            statement.setString(1, adminusername);
            statement.setString(2, adminpassword);
            // statement.setString(3, role);
            ResultSet resultSet = statement.executeQuery();
            
            //selagi db tu baca dia amik benda tu yang declare 
            if (resultSet.next()) {
                // kena declare mana yang amik
                int adminid = resultSet.getInt("adminid");
                String adminname = resultSet.getString("adminname");
                String role = resultSet.getString("role");
                // attribute dia amik dari db pass ke controller
                // kena declare kat model
                return new Admin(adminid, adminname, adminusername, adminpassword,role);
            }
            connection.close();
        } 
        return null;
    }
    
}