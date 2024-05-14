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
public class IssueViewDAO {
    private final DataSource dataSource;

    public IssueViewDAO(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public List<Issue> listIssue() throws SQLException {
        List<Issue> issuelist = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM issue ORDER BY issueid";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Issue issue = new Issue();
                issue.setIid(resultSet.getInt("issueid"));
                issue.setIname(resultSet.getString("issuename"));
                issue.setIdesc(resultSet.getString("issuedesc"));
                issue.setIdate(resultSet.getDate("issuedate"));
                issue.setIremark(resultSet.getString("issueremark"));

                issuelist.add(issue);
            }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
            return issuelist;
  
    }

    //getIssue by ID
    public Issue getIssueById(int issueid) throws SQLException {
        Issue issue = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM issue WHERE issueid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, issueid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                issue = new Issue();
                issue.setIid(resultSet.getInt("issueid"));
                issue.setIname(resultSet.getString("issuename"));
                issue.setIdesc(resultSet.getString("issuedesc"));
                issue.setIdate(resultSet.getDate("issuedate"));
                issue.setIremark(resultSet.getString("issueremark"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return issue;
    }



    // update issue
    public void updateIssue(Issue issue) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE issue SET issuename=?, issuedesc=?, issuedate=?, issueremark=? "
                    + "WHERE issueid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, issue.getIname());
            statement.setString(2, issue.getIdesc());
            statement.setDate(3, issue.getIdate());
            statement.setString(4, issue.getIremark());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    
    
    

}