package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
// import org.springframework.web.multipart.MultipartFile;

import com.heroku.java.MODEL.Registration;
import com.heroku.java.MODEL.Program;
import com.heroku.java.MODEL.Volunteer;

import java.sql.Date;

@Repository
public class RegistrationDAO {

    private final DataSource dataSource;

    public RegistrationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void showRegistration(Registration registration) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            String insertIssueSql = "INSERT INTO registration (rdate, vid, programid) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertIssueSql); 
    
            statement.setDate(1, registration.getRdate());
            statement.setInt(2, registration.getVolunteerId());
            statement.setInt(3, registration.getProgramId());
            
            statement.executeUpdate();
        }
    }
}