package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.Date;
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

import com.heroku.java.MODEL.Volunteer;

@Repository
public class VolunteerProfileDAO {
    private final DataSource dataSource;

    public VolunteerProfileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    // warna kuning volunteer tu model ; biru tu function dao ; vid tu data yang nak pass dalam db
    public Volunteer VolunteerProfile(int vid) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM volunteer WHERE vid = ?";

            // masuk and keluar dari db ke system
            final var statement = connection.prepareStatement(sql);
            //setiap kali tanda soal kena set balik
            statement.setInt(1, vid);
            //execute db
            final var resultSet = statement.executeQuery();

            // dia amik dari db so naming kena betul ; selagi dia baca dia akan amik data
            while (resultSet.next()) {
                Volunteer volunteer=new Volunteer();
                volunteer.setId(resultSet.getInt("vid"));
                volunteer.setName(resultSet.getString("vfullname"));
                volunteer.setEmail(resultSet.getString("vemail"));
                volunteer.setPhonenum(resultSet.getString("vphonenum"));
                volunteer.setIcnum(resultSet.getString("vicnum"));
                volunteer.setBirthdate(resultSet.getDate("vbirthdate"));
                volunteer.setAge(resultSet.getInt("vage"));
                volunteer.setUsername(resultSet.getString("vusername"));
                volunteer.setPassword(resultSet.getString("vpassword"));

                return volunteer;
            }
        } catch (SQLException sqe) {
            System.out.println("Error Code = " + sqe.getErrorCode());
            System.out.println("SQL state = " + sqe.getSQLState());
            System.out.println("Message = " + sqe.getMessage());
            System.out.println("printTrace /n");
            sqe.printStackTrace();
        }
        return null;
    }

    // update profile
    // public Volunteer UpdateProfile (Volunteer volunteer) throws SQLException {
    //     try (Connection connection = dataSource.getConnection()) {
    //         String sql = "UPDATE volunteer SET vfullname=?, vemail=?, vphonenum=?, vicnum=?, vbirthdate=? , vusername=? , vpassword=? WHERE vid=?";
    //         final var statement = connection.prepareStatement(sql);

    //         String vpassword = volunteer.getPassword();
    //         System.out.println("password: " + vpassword);
            

    //         statement.setString(1, volunteer.getName());
    //         statement.setString(2, volunteer.getEmail());
    //         statement.setInt(3, volunteer.getPhonenum());
    //         statement.setString(4, volunteer.getIcnum());
    //         statement.setDate(5, volunteer.getBirthdate());
    //         statement.setString(6, volunteer.getUsername());
    //         statement.setString(7, volunteer.getPassword());
    //         statement.setInt(8, volunteer.getId());

    //         statement.executeUpdate();
    //     }
    //     return volunteer;
    // }

    public boolean updateProfile(Volunteer volunteer) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE volunteer SET vfullname=?, vemail=?, vphonenum=?, vicnum=?, vbirthdate=? , vusername=? , vpassword=? WHERE vid=?";
            final var statement = connection.prepareStatement(sql);
    
            String vpassword = volunteer.getPassword();
            System.out.println("password: " + vpassword);
    
            statement.setString(1, volunteer.getName());
            statement.setString(2, volunteer.getEmail());
            statement.setString(3, volunteer.getPhonenum());
            statement.setString(4, volunteer.getIcnum());
            statement.setDate(5, volunteer.getBirthdate());
            statement.setString(6, volunteer.getUsername());
            statement.setString(7, volunteer.getPassword());
            statement.setInt(8, volunteer.getId());
    
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Return true if at least one row was updated, false otherwise
        }
    }
    

    private String volunteerpassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'volunteerpassword'");
    }





}