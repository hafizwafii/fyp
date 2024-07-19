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
import com.heroku.java.SERVICES.LoginDAO;
import com.heroku.java.MODEL.Admin;
import com.heroku.java.MODEL.Volunteer;

@Controller
public class LoginController {
    private LoginDAO loginDAO;

    @Autowired
    public LoginController(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("username") != null) {
            // Redirect to the appropriate home page based on the user type
            if (session.getAttribute("volunteerid") != null) {
                return "redirect:/homevolunteer";
            } else if (session.getAttribute("adminid") != null) {
                return "redirect:/homepageadmin";
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("username") String username,
                        @RequestParam("password") String password, Model model) {
        try {
            Volunteer isVolunteer = loginDAO.checkVolunteer(username, password);
            Admin isAdmin = loginDAO.checkAdmin(username, password);

            if (isVolunteer != null) {
                session.setAttribute("username", username);
                session.setAttribute("volunteerid", isVolunteer.getId());

                 //try debug
            System.out.println("Volunteer username who login: "+ username);
            System.out.println("Volunteer Id who login: "+ isVolunteer.getId());
           
            return "redirect:/homevolunteer";

            } else if (isAdmin != null) {
                session.setAttribute("username", isAdmin.getAdminusername());
                session.setAttribute("adminid", isAdmin.getAdminid());
                session.setAttribute("adminname", isAdmin.getAdminname());
                session.setAttribute("role", isAdmin.getRole());

            System.out.println("Admin username who login: " + isAdmin.getAdminusername());
            System.out.println("Admin id who login: " + isAdmin.getAdminid());
            System.out.println("Admin name who login: " + isAdmin.getAdminname());
            System.out.println("Admin role who login: " + isAdmin.getRole());

            return "redirect:/homepageadmin";

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
