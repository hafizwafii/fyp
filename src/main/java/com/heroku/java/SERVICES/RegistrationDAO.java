package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import com.heroku.java.MODEL.Registration;

@Repository
public class RegistrationDAO {

    private final DataSource dataSource;

    public RegistrationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isAlreadyRegistered(int volunteerId, int programId) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String checkSql = "SELECT COUNT(*) FROM registration WHERE vid = ? AND programid = ?";
            PreparedStatement statement = connection.prepareStatement(checkSql);
            statement.setInt(1, volunteerId);
            statement.setInt(2, programId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        }
    }

    // before push
    // public void showRegistration(Registration registration) throws SQLException {
    //     if (!isAlreadyRegistered(registration.getVolunteerId(), registration.getProgramId())) {
    //         try (Connection connection = dataSource.getConnection()){
    //             String insertIssueSql = "INSERT INTO registration (rdate, vid, programid) VALUES (?, ?, ?)";
    //             PreparedStatement statement = connection.prepareStatement(insertIssueSql); 

    //             statement.setDate(1, registration.getRdate());
    //             statement.setInt(2, registration.getVolunteerId());
    //             statement.setInt(3, registration.getProgramId());
                
    //             statement.executeUpdate();
    //         }
    //     } else {
    //         throw new SQLException("Volunteer is already registered for this program.");
    //     }
    // }

    public void showRegistration(Registration registration) throws SQLException {
        if (!isAlreadyRegistered(registration.getVolunteerId(), registration.getProgramId())) {
            try (Connection connection = dataSource.getConnection()){
                String insertSql = "INSERT INTO registration (rdate, vid, programid) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(insertSql); 

                statement.setDate(1, registration.getRdate());
                statement.setInt(2, registration.getVolunteerId());
                statement.setInt(3, registration.getProgramId());

                statement.executeUpdate();
            }
        } else {
            throw new SQLException("Volunteer is already registered for this program.");
        }
    }
}
