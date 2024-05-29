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

// import com.heroku.java.MODEL.Registration;
// import com.heroku.java.MODEL.Program;

// @Repository
// public class RegistrationDAO {
//     private final DataSource dataSource;

//     public RegistrationDAO(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }   
    
//     // Update Registration
//     public Registration getRegistrationDetails(int pid) throws SQLException {
//         try (Connection connection = dataSource.getConnection()) {
//             String sql = "SELECT * FROM registration WHERE programid = ?";
//             PreparedStatement statement = connection.prepareStatement(sql);
//             statement.setInt(1, pid);
//             ResultSet resultSet = statement.executeQuery();

//             if (resultSet.next()) {
//                 String pname = resultSet.getString("pname");
//                 String pdesc = resultSet.getString("pdesc");
//                 String pvenue = resultSet.getString("pvenue");
//                 String ptime = resultSet.getString("ptime");
//                 String pdate = resultSet.getString("pdate");

//                 byte[] pimageBytes = resultSet.getBytes("pimage");
//                 String base64Image = Base64.getEncoder().encodeToString(pimageBytes);
//                 String imageSrc = "data:image/jpeg;base64," + base64Image;

//                 System.out.println(pid);
//                 return new Registration(pid, pname, pdesc, pvenue , ptime, pdate, null, null,imageSrc);
//             }
//         } catch (SQLException e) {
//             throw e;
//         }
//         return null;
//     }
    
    
// }
