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
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;

import com.heroku.java.MODEL.Admin;
import com.heroku.java.MODEL.Issue;

@Repository
public class AdminDAO {

    private final DataSource dataSource;

    public AdminDAO(DataSource dataSource){
        this.dataSource=dataSource;
    }

     public void addAdmin(@ModelAttribute("AdminDetail") Admin admin) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertIssueSql = "INSERT INTO admin ( adminname, adminemail, admincontactnum, adminusername, adminpassword) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertIssueSql); 
            insertStatement.setString(1, admin.getAdminname());
            insertStatement.setString(2, admin.getAdminemail());
            insertStatement.setInt(3, admin.getAdmincontactnum());
            insertStatement.setString(4, admin.getAdminusername());
            insertStatement.setString(5, admin.getAdminpassword());


            insertStatement.execute(); 
            connection.close();
        } catch (SQLException e) {

            // Handle any exceptions or errors that occurred during the database operation
            e.printStackTrace();
            throw e;
        }
    }

    

    
}
