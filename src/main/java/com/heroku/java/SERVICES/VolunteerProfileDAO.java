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
                String vfullname = resultSet.getString("vfullname");
                String vemail = resultSet.getString("vemail");
                int vphonenum = resultSet.getInt("vphonenum");
                String vicnum = resultSet.getString("vicnum");
                Date vbirthdate = resultSet.getDate("vbirthdate");
                int vage = resultSet.getInt("vage");
                String vusername = resultSet.getString("vusername");
                String vpassword = resultSet.getString("vpassword");

                // debug
                System.out.println("Volunteer name from db = " + vfullname);

                // hantar dekat bean untuk dia baca pastu pass data kat controller
                return new Volunteer(vid, vfullname, vemail, vphonenum, vicnum , vbirthdate ,vage , vusername , vpassword);
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

    //update profile
    public Volunteer UpdateProfile (@ModelAttribute("VolunteerProfile") Volunteer volunteer) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE volunteer SET vfullname=?, vemail=?, vphonenum=?, vicnum=?, vbirthdate=? , vusername=? , vpassword=? WHERE vid=?";
            final var statement = connection.prepareStatement(sql);

            String vpassword = volunteer.getVolunteerpassword();
            System.out.println("password: " + vpassword);

            statement.setString(1, volunteer.getVolunteername());
            statement.setString(2, volunteer.getVolunteeremail());
            statement.setInt(3, volunteer.getVolunteerphonenum());
            statement.setString(4, volunteer.getVolunteericnum());
            statement.setDate(5, volunteer.getVolunteerbirthdate());
            statement.setString(6, volunteer.getVolunteerusername());
            statement.setString(7, volunteerpassword());

            statement.executeUpdate();
        }
        return volunteer;
    }

    private String volunteerpassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'volunteerpassword'");
    }





}