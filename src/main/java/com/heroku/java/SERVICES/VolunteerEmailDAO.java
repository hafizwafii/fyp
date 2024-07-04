package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class VolunteerEmailDAO {
    private final DataSource dataSource;

    public VolunteerEmailDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<String> getVolunteerEmail() throws SQLException {
        ArrayList<String> volunteers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT vemail From volunteer";
            final var statement = connection.prepareStatement(sql);
            final var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String EmailToVolunteer = resultSet.getString("vemail");
                volunteers.add(EmailToVolunteer);
            }
        }
        return volunteers;
    }

    //  public ArrayList<String> getMemberEmail(int programId) throws SQLException {
    //     ArrayList<String> volunteers = new ArrayList<>();
        
    //     try (Connection connection = dataSource.getConnection()) {
    //         String sql = "SELECT player.playeremail " +
    //                      "FROM player " +
    //                      "JOIN member ON player.playerid = member.playerid " +
    //                      "JOIN team ON member.teamid = team.teamid " +
    //                      "WHERE team.eventdetailid = ?";
            
    //         PreparedStatement statement = connection.prepareStatement(sql);
    //         statement.setInt(1, programId);
            
    //         ResultSet resultSet = statement.executeQuery();

    //         while (resultSet.next()) {
    //             String emailToMember = resultSet.getString("vemail");
    //             volunteers.add(emailToMember);
    //         }
    //     }

    //     return volunteers;
    // }

    public ArrayList<String> getRegisterEmail(int programId) throws SQLException {
        ArrayList<String> volunteers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT vemail FROM volunteer JOIN registration ON volunteer.vid = registration.vid WHERE registration.programid = ?";
            final var statement = connection.prepareStatement(sql);
           
            statement.setInt(1, programId);
            
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String EmailToVolunteer = resultSet.getString("vemail");
                volunteers.add(EmailToVolunteer);
            }
        }
        return volunteers;
    }

}
