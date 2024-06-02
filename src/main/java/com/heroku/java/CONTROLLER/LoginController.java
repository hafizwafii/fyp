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
import com.heroku.java.MODEL.Admin;
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
                    @RequestParam("password") String password, Model model) {
    try {
        
        // panggil logindao tadi (checkvolunteer = function kat logindao)
        Volunteer isVolunteer = loginDAO.checkVolunteer(username, password);
        Admin isAdmin = loginDAO.checkAdmin(username, password);

        //setup session
        if (isVolunteer !=null) {
            session.setAttribute("username", username);
            session.setAttribute("volunteerid", isVolunteer.getId());

            //try debug
            System.out.println("Volunteer who login: "+ username);
            System.out.println("Volunteer id who login: "+ isVolunteer.getId());
            return "redirect:/homevolunteer"; // Replace with the appropriate customer home page URL


        // admin login
        } else if (isAdmin != null) { 

            session.setAttribute("username", isAdmin.getAdminusername());
            session.setAttribute("adminid", isAdmin.getAdminid());

            System.out.println("Admin who login: " + isAdmin.getAdminusername());
            System.out.println("Admin id who login: " + isAdmin.getAdminid());
            return "redirect:/homepageadmin"; // Replace with the appropriate admin home page URL
            

        } else {
            System.out.println("Invalid username or password");
            model.addAttribute("error", true);
            return "login";
        }
        
        
    } catch (SQLException e) {
        e.printStackTrace();
        model.addAttribute("error", true); 
        return "login";
    }
}

@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/homepage";
    }



}