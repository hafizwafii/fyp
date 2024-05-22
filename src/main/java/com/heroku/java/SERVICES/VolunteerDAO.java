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
public class VolunteerDAO {
    private final DataSource dataSource;

    public VolunteerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVolunteer(Volunteer volunteer) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertVolunteerSql = "INSERT INTO volunteer ( vfullname, vemail, vphonenum, vicnum, vbirthdate, vage, vusername, vpassword) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertVolunteerSql); 
            insertStatement.setString(1, volunteer.getName());
            insertStatement.setString(2, volunteer.getEmail());
            insertStatement.setInt(3, volunteer.getPhonenum());
            insertStatement.setString(4, volunteer.getIcnum());
            insertStatement.setDate(5, volunteer.getBirthdate());
            insertStatement.setInt(6, volunteer.getAge());
            insertStatement.setString(7, volunteer.getUsername());
            insertStatement.setString(8, volunteer.getPassword());

            insertStatement.execute(); 
        } catch (SQLException e) {
            // Handle any exceptions or errors that occurred during the database operation
            throw e;
        }
    }

    public List<Volunteer> getAllvolunteers() throws SQLException {
        List<Volunteer> volunteers = new ArrayList<>();
    
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM volunteer order by vid";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Volunteer volunteer = new Volunteer();
                volunteer.setName(resultSet.getString("vfullname"));
                volunteer.setId(resultSet.getInt("vid"));
                volunteer.setEmail(resultSet.getString("vemail"));
                volunteer.setPhonenum(resultSet.getInt("vphonenum"));
                volunteer.setIcnum(resultSet.getString("vicnum"));
                volunteer.setUsername(resultSet.getString("vusername"));
                
    
                volunteers.add(volunteer);
            }
            connection.close();
        } catch (SQLException e) {
            throw new SQLException("Error retrieving volunteer: " + e.getMessage());
        }
    
        return volunteers;
    }

        // searchvolunteer
        public List<Volunteer> searchVolunteersByName(String name) throws SQLException{
            List<Volunteer> volunteers = new ArrayList<>();
        
            try (Connection connection = dataSource.getConnection()) {
                String sql = "SELECT * FROM volunteer WHERE vfullname LIKE ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, "%" + name + "%");
                    
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            Volunteer volunteer = new Volunteer();
                           volunteer.setName(resultSet.getString("vfullname"));
                           volunteer.setId(resultSet.getInt("vid"));
                           volunteer.setEmail(resultSet.getString("vemail"));
                           volunteer.setPhonenum(resultSet.getInt("vphonenum"));
                           volunteer.setIcnum(resultSet.getString("vicnum"));
                           volunteer.setUsername(resultSet.getString("vusername"));
        
                           volunteers.add(volunteer);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new SQLException("Error retrievingvolunteer: " + e.getMessage());
            }
            return volunteers;
            }


}
