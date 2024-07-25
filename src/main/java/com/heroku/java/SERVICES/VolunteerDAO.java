package com.heroku.java.SERVICES;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList; 
import java.util.List; 
import java.sql.Date;
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

    @Autowired
    public VolunteerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVolunteer(Volunteer volunteer) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertVolunteerSql = "INSERT INTO volunteer (vfullname, vemail, vphonenum, vicnum, vbirthdate, vage, vusername, vpassword) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertVolunteerSql, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, volunteer.getName());
            insertStatement.setString(2, volunteer.getEmail());
            
            String formattedPhoneNum = String.format("%010d", volunteer.getPhonenum());
            insertStatement.setString(3, formattedPhoneNum);
    
            insertStatement.setString(4, volunteer.getIcnum());
            insertStatement.setDate(5, new java.sql.Date(volunteer.getBirthdate().getTime()));
            insertStatement.setInt(6, volunteer.getAge());
            insertStatement.setString(7, volunteer.getUsername());
            insertStatement.setString(8, volunteer.getPassword());
    
            insertStatement.execute();
        } catch (SQLException e) {
            throw e;
        }
    }
    

    public List<Volunteer> getAllvolunteers() throws SQLException {
        List<Volunteer> volunteers = new ArrayList<>();
    
        try (Connection connection = dataSource.getConnection()) {
            // Modified SQL to include a check for registrations
            String sql = "SELECT v.*, (SELECT COUNT(*) FROM registration WHERE vid = r.vid) > 0 AS is_registered FROM volunteer v ORDER BY vid";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Volunteer volunteer = new Volunteer();
                volunteer.setId(resultSet.getInt("vid"));
                volunteer.setName(resultSet.getString("vfullname"));
                volunteer.setEmail(resultSet.getString("vemail"));
                volunteer.setPhonenum(resultSet.getString("vphonenum"));
                volunteer.setIcnum(resultSet.getString("vicnum"));
                volunteer.setUsername(resultSet.getString("vusername"));
                volunteer.setRegistered(resultSet.getBoolean("is_registered")); // Assuming you have this setter in your Volunteer model

                System.out.println("Volunteer ID: " + volunteer.getId() + ", Registered: " + volunteer.isRegistered());
                
                volunteers.add(volunteer);
            }
            connection.close();
        } catch (SQLException e) {
            throw new SQLException("Error retrieving volunteers: " + e.getMessage());
        }
    
        return volunteers;
    }
    

        // searchvolunteer
        public List<Volunteer> searchVolunteersByName(String name) throws SQLException {
            List<Volunteer> volunteers = new ArrayList<>();
        
            try (Connection connection = dataSource.getConnection()) {
                // Updated SQL to include a check for registrations
                String sql = "SELECT v.*, EXISTS (SELECT 1 FROM registration WHERE vid = v.vid) AS is_registered FROM volunteer v WHERE v.vfullname LIKE ? ";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + name + "%");
        
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Volunteer volunteer = new Volunteer();
                    volunteer.setId(resultSet.getInt("vid"));
                    volunteer.setName(resultSet.getString("vfullname"));
                    volunteer.setEmail(resultSet.getString("vemail"));
                    volunteer.setPhonenum(resultSet.getString("vphonenum"));
                    volunteer.setIcnum(resultSet.getString("vicnum"));
                    volunteer.setUsername(resultSet.getString("vusername"));
                    volunteer.setRegistered(resultSet.getBoolean("is_registered")); // Set registration status
        
                    volunteers.add(volunteer);
                }
            } catch (SQLException e) {
                throw new SQLException("Error retrieving volunteers by name: " + e.getMessage());
            }
            return volunteers;
            
        }
        
        // ni after push
        public List<Integer> getProgramIdsByVolunteerId(int volunteerId) throws SQLException {
            List<Integer> programIds = new ArrayList<>();
            try (Connection connection = dataSource.getConnection()) {
                String sql = "SELECT programid FROM registration WHERE vid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, volunteerId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    programIds.add(resultSet.getInt("programid"));
                }
            } catch (SQLException e) {
                throw new SQLException("Error retrieving program IDs for volunteer: " + e.getMessage());
            }
            return programIds;
        }

        // ni after push
        public Volunteer getVolunteerById(int vid) throws SQLException {
            Volunteer volunteer = null;
            try (Connection connection = dataSource.getConnection()) {
                String sql = "SELECT * FROM volunteer WHERE vid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, vid);
    
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    volunteer = new Volunteer();
                    volunteer.setId(resultSet.getInt("vid"));
                    volunteer.setName(resultSet.getString("vfullname"));
                    volunteer.setEmail(resultSet.getString("vemail"));
                    volunteer.setPhonenum(resultSet.getString("vphonenum"));
                    volunteer.setIcnum(resultSet.getString("vicnum"));
                    volunteer.setBirthdate(resultSet.getDate("vbirthdate"));
                    volunteer.setAge(resultSet.getInt("vage"));
                    volunteer.setUsername(resultSet.getString("vusername"));
                    volunteer.setPassword(resultSet.getString("vpassword"));
                }
            } catch (SQLException e) {
                throw new SQLException("Error retrieving volunteer by ID: " + e.getMessage());
            }
            return volunteer;
        }

           

}
