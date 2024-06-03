    // package com.heroku.java.SERVICES;

    // import java.sql.Connection; 
    // import java.sql.PreparedStatement; 
    // import java.sql.ResultSet; 
    // import java.sql.SQLException; 
    // import java.sql.Statement; 
    // import java.util.ArrayList;
    // import java.util.Base64;
    // import java.util.List; 
    // import javax.sql.DataSource; 
    // import org.springframework.beans.factory.annotation.Autowired;
    // import org.springframework.stereotype.Repository;
    // import org.springframework.stereotype.Service; 
    // import jakarta.servlet.http.HttpSession;

    // import javax.sql.DataSource;
    // import java.sql.Date;
    // import org.springframework.web.multipart.MultipartFile;

    // import com.heroku.java.MODEL.Registration;
    // import com.heroku.java.MODEL.Program;
    // import com.heroku.java.MODEL.Volunteer;

    // @Repository
    // public class RegistrationDAO {
    //     private final DataSource dataSource;

    //     public RegistrationDAO(DataSource dataSource) {
    //         this.dataSource = dataSource;
    //     }   
        
    //     // Update Registration
    //     public Registration getRegistrationDetails(int programid) throws SQLException {
    //         try (Connection connection = dataSource.getConnection()) {
    //             String sql = "SELECT * FROM registration WHERE programid = ?";
    //             PreparedStatement statement = connection.prepareStatement(sql);
    //             statement.setInt(1, programid);
    //             ResultSet resultSet = statement.executeQuery();

    //             if (resultSet.next()) {

    //                 byte[] pvimageBytes = resultSet.getBytes("pvimage");
    //                 String base64Image = Base64.getEncoder().encodeToString(pvimageBytes);
    //                 String imageSrc = "data:image/jpeg;base64," + base64Image;


    //                 int rid = resultSet.getInt(columnLabel:"rid");
                    
                    
    //                 // int programid = resultSet.getInt(columnLabel:"programid")
    //                 String pvname = resultSet.getString("pvname");
    //                 String pvdesc = resultSet.getString("pvdesc");
    //                 String pvvenue = resultSet.getString("pvvenue");
    //                 String pvtime = resultSet.getString("pvtime");
    //                 Date pvdate = resultSet.getDate("pvdate");
    //                 Date rdate = resultSet.getDate("rdate");


                    

    //                 System.out.println(programid);
    //                 return new Registration(rid, vid, programid , pvname, pvdesc, pvvenue , pvtime, pvdate, null, null,imageSrc,rdate);
    //             }
    //         } catch (SQLException e) {
    //             throw e;
    //         }
    //         return null;
    //     }
        
        
    // }
