package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.heroku.java.MODEL.Registration;
import java.sql.Date;

@Repository
public class RegistrationDAO {

    private final DataSource dataSource;

    public RegistrationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

     // Update Registration
    //  public Registration getRegistrationDetails(int programid) throws SQLException {
    //     try (Connection connection = dataSource.getConnection()) {
    //         String sql = "SELECT * FROM program WHERE programid = ?";
    //         PreparedStatement statement = connection.prepareStatement(sql);
    //         statement.setInt(1, programid);
    //         ResultSet resultSet = statement.executeQuery();

    //         if (resultSet.next()) {
    //             int rid = resultSet.getInt("registrationid");
    //             int vid = resultSet.getInt("vid");
    //             int programid = resultSet.getInt("programid");
    //             String programname = resultSet.getString("pvname");
    //             String programdesc = resultSet.getString("pvdesc");
    //             String programvenue = resultSet.getString("pvvenue");
    //             String programtime = resultSet.getString("pvtime");
    //             Date programdate = resultSet.getDate("pvdate");
    //             Date rdate = resultSet.getDate("registrationdate");

    //             // byte[] programimageBytes = resultSet.getBytes("pvimage");
    //             // String base64Image = Base64.getEncoder().encodeToString(programimageBytes);
    //             // String imageSrc = "data:image/jpeg;base64," + base64Image;

    //         //     public Registration(int rid, int vid, int programid, byte[] programimagebyte, MultipartFile programimage,
    //         // String imageSrc, String programname, String programdesc, String programvenue, String programtime,
    //         // String programdate, Date rdate) {

    //         String progdate = programdate.toString();

    //             return new Registration(rid,vid, programid, null,null,null, programname,programdesc, programvenue,programtime,progdate,rdate);
    //         }
    //     } catch (SQLException e) {
    //         throw e;
    //     }
    //     return null;
    // }
}
