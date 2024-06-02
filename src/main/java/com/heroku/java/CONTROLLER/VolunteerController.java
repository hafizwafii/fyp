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

import javax.sql.DataSource;

import com.heroku.java.SERVICES.VolunteerDAO;
import com.heroku.java.MODEL.Volunteer;



@Controller
public class VolunteerController  {   
    private VolunteerDAO volunteerDAO;
    private DataSource dataSource;

    @Autowired
    public VolunteerController (VolunteerDAO volunteerDAO){
        this.volunteerDAO=volunteerDAO;
    }

    // private final DataSource dataSource;

    // public volunteerController(DataSource dataSource) {
    //     this.dataSource = dataSource;
    // }
    
    
    //CREATE ACCOUNT
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String addVolunteer(HttpSession session, @ModelAttribute("signup")Volunteer volunteer) {
        try {
            volunteerDAO.addVolunteer(volunteer);
        // VolunteerDAO volunteerDAO = new VolunteerDAO(volunteerDAO);
        // volunteerDAO.addVolunteer(volunteer);

        return "redirect:/login";
    } catch (SQLException e) {
        e.printStackTrace();
        return "error";
    }
    }

    
    @GetMapping("/searchVolunteer")
    public String searchVolunteer(@RequestParam("vname") String volunteerName, Model model) {
        try{
            List<Volunteer> volunteers = volunteerDAO.searchVolunteersByName(volunteerName);
            model.addAttribute("volunteers", volunteers); 
        }catch (SQLException e) {
                    // Handle the SQLException, log it, or rethrow it as a RuntimeException if needed
                    e.printStackTrace(); // You may want to log the exception instead
                    // You can also redirect to an error page or handle it in a way that makes sense for your application
                    model.addAttribute("error", "An error occurred during the search: " + e.getMessage());
                }
        return "viewVolunteer"; // Replace with the name of your results view
    }
    // public String searchVolunteer(@RequestParam(name = "searchValue", required = false) String searchValue, Model model) {
    //     try {
    //         // Perform the search based on the searchValue
    //         VolunteerDAO volunteerDAO = new VolunteerDAO(dataSource);
    //         List<Volunteer> searchResults = volunteerDAO.searchVolunteersByName(searchValue);
    
    //         // Add the search results and the searchValue to the model
    //         model.addAttribute("volunteers", searchResults);
    //         model.addAttribute("searchValue", searchValue);
    //     } catch (SQLException e) {
    //         // Handle the SQLException, log it, or rethrow it as a RuntimeException if needed
    //         e.printStackTrace(); // You may want to log the exception instead
    //         // You can also redirect to an error page or handle it in a way that makes sense for your application
    //         model.addAttribute("error", "An error occurred during the search: " + e.getMessage());
    //     }
    // // Return the view name to display the search results
    // return "viewVolunteer";
    // }


    
}
