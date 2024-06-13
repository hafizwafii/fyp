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

import com.heroku.java.MODEL.Issue;

@Repository
public class IssueCreateDAO {

    private final DataSource dataSource;

    public IssueCreateDAO(DataSource dataSource){
        this.dataSource=dataSource;
    }
    
    public void addIssue(@ModelAttribute("IssueDetail") Issue issue) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertIssueSql = "INSERT INTO issue ( issuename, issuedesc, issuedate, issueremark,adminid) VALUES (?, ?, ?, ?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertIssueSql); 
            insertStatement.setString(1, issue.getIname());
            insertStatement.setString(2, issue.getIdesc());
            insertStatement.setDate(3, issue.getIdate());
            insertStatement.setString(4, issue.getIremark());
            insertStatement.setInt(5, issue.getAdminId());



            insertStatement.execute(); 
            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
    }


}