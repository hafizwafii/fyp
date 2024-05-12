package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model; 
import jakarta.servlet.http.HttpSession; 
import java.sql.SQLException; 
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import com.heroku.java.SERVICES.IssueViewDAO;
import com.heroku.java.SERVICES.ProgramDAO; //nama file dao
import com.heroku.java.MODEL.Issue;
import com.heroku.java.MODEL.Program; // nama model/bean

@Controller
public class ProgramController {
    private final DataSource dataSource;

    public ProgramController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/addProgram")
    public String addProgram(HttpSession session) {
        return "addProgram";
    }

    @PostMapping("/addProgram")
    public String addProgram(HttpSession session, @ModelAttribute("ProgramDetail")Program program,  @RequestParam("pimage") MultipartFile pimage) throws IOException {
        try {
            program.setPimagebyte(pimage.getBytes());
            ProgramDAO programDAO = new ProgramDAO(dataSource);
            programDAO.addProgram(program);
            return "redirect:/viewProgram"; 
            
        

        
    } catch (SQLException e) {
        e.printStackTrace();
        return "/homepage";
    }
    }

    @GetMapping("/viewProgram")
    public String viewProgram(Model model, Program program) {
        ProgramDAO programDAO = new ProgramDAO(dataSource);
        try{
            List<Program> programlist = programDAO.listProgram();
            model.addAttribute("programs", programlist);
        }  catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
        return "viewProgram";    
      
        }
    
}
