package com.heroku.java.CONTROLLER;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.ui.Model; 
import jakarta.servlet.http.HttpSession; 
import java.sql.SQLException; 
import java.util.List;

import com.heroku.java.SERVICES.LoginDAO;
import com.heroku.java.SERVICES.VolunteerDAO;
import com.heroku.java.MODEL.Volunteer;

@Controller
public class LoginController {
    private LoginDAO loginDAO;

    @Autowired
    public LoginController (LoginDAO loginDAO){
        this.loginDAO=loginDAO;
    }    

    @GetMapping("/login") 
    public String login(HttpSession session) { 
            return "login"; 
    }
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("username") String username,
                    @RequestParam("password") String password, Model model,Volunteer volunteer) {
    try {
        // loginDAO = new LoginDAO();
        
        boolean isVolunteer = loginDAO.checkVolunteer(username, password);
        
        if (isVolunteer) {
            session.setAttribute("username", username);
            System.out.println("Player who login: "+ username);
            return "redirect:/homepage"; // Replace with the appropriate customer home page URL
        } else {
            System.out.println("Invalid username or password");
            model.addAttribute("error", true);
            return "login";
        }
        // } else if (isEmployee) {
            
        //     session.setAttribute("username", username);
        //     return "redirect:/";
        // } else {
        //     System.out.println("Invalid username or password");
        //     model.addAttribute("error", true); 
        //     return "login"; 
        // }
    } catch (SQLException e) {
        e.printStackTrace();
        model.addAttribute("error", true); 
        return "login";
    }
}



}
