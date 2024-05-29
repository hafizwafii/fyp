// package com.heroku.java.CONTROLLER;

// import org.springframework.beans.factory.annotation.Autowired; 
// import org.springframework.stereotype.Controller; 
// import org.springframework.web.bind.annotation.GetMapping; 
// import org.springframework.web.bind.annotation.ModelAttribute; 
// import org.springframework.web.bind.annotation.PostMapping; 
// import org.springframework.web.bind.annotation.RequestParam; 
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.ui.Model; 
// import jakarta.servlet.http.HttpSession; 
// import java.sql.SQLException; 
// import javax.sql.DataSource;

// import java.io.IOException;
// import java.sql.*;
// import java.util.List;

// import com.heroku.java.SERVICES.RegistrationDAO; //nama file dao

// import com.heroku.java.MODEL.Registration;


// @Controller
// public class RegistrationController {
//     private final DataSource dataSource;

//     public RegistrationController(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//     @GetMapping("/registrationform")
//     public String showRegistrationDetail(@RequestParam("pid") int pId, Model model) {
//         try {
//             Registration registrations = registrationDAO.getRegistrationDetails(pId);
//             model.addAttribute("registrations", registrations);
//             return "registrationform";
//         } catch (SQLException e) {
//             System.out.println("message : " + e.getMessage());
//             return "homevolunteer";
//         }
//     }


// }
