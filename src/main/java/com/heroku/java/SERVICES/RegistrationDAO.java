package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import com.heroku.java.MODEL.Program;
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

    public List<Program> getProgramsByVolunteerId(int volunteerId) throws SQLException {
    List<Program> programs = new ArrayList<>();
    try (Connection connection = dataSource.getConnection()) {
        String sql = "SELECT p.pname, p.pdesc, p.pvenue, p.ptime, p.pdate " +
                     "FROM registration r " +
                     "JOIN program p ON r.programid = p.programid " +
                     "WHERE r.vid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, volunteerId);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Program program = new Program();
            program.setPname(resultSet.getString("pname"));
            program.setPdesc(resultSet.getString("pdesc"));
            program.setPvenue(resultSet.getString("pvenue"));
            program.setPtime(resultSet.getString("ptime"));
            program.setPdate(resultSet.getDate("pdate"));
            // program.setRdate(resultSet.getDate("rdate"));
            programs.add(program);
        }
    }
    return programs;
}



}
