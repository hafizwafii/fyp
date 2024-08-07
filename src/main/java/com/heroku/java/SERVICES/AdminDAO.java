package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList;
import java.sql.Date;
import java.util.List; 
import javax.sql.DataSource; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;

import com.heroku.java.MODEL.Admin;
import com.heroku.java.MODEL.Issue;
import com.heroku.java.MODEL.Volunteer;

@Repository
public class AdminDAO {

    private final DataSource dataSource;

    public AdminDAO(DataSource dataSource){
        this.dataSource=dataSource;
    }

     public void addAdmin(@ModelAttribute("AdminDetail") Admin admin) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertIssueSql = "INSERT INTO admin ( adminname, adminemail, adminusername, adminpassword, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertIssueSql); 
            insertStatement.setString(1, admin.getAdminname());
            insertStatement.setString(2, admin.getAdminemail());
            insertStatement.setString(3, admin.getAdminusername());
            insertStatement.setString(4, admin.getAdminpassword());
            insertStatement.setString(5, admin.getRole());


            insertStatement.execute(); 
            connection.close();
        } catch (SQLException e) {

            // Handle any exceptions or errors that occurred during the database operation
            e.printStackTrace();
            throw e;
        }
    }

    public List<Admin> listAdmin(String role) throws SQLException {
        List<Admin> adminlist = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM admin WHERE role = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminid(resultSet.getInt("adminid"));
                admin.setAdminname(resultSet.getString("adminname"));
                admin.setAdminemail(resultSet.getString("adminemail"));
                admin.setAdminusername(resultSet.getString("adminusername"));
                admin.setAdminpassword(resultSet.getString("adminpassword"));
                admin.setRole(resultSet.getString("role"));
    
                adminlist.add(admin);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return adminlist;
    }
    
    
    public List<Admin> listAdminsByRole(String role) throws SQLException {
        List<Admin> adminlist = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM admin WHERE role = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminid(resultSet.getInt("adminid"));
                admin.setAdminname(resultSet.getString("adminname"));
                admin.setAdminemail(resultSet.getString("adminemail"));
                admin.setAdminusername(resultSet.getString("adminusername"));
                admin.setAdminpassword(resultSet.getString("adminpassword"));
                admin.setRole(resultSet.getString("role"));
    
                adminlist.add(admin);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return adminlist;
    }
    
    public List<Admin> listNormalAdmins() throws SQLException {
        List<Admin> adminlist = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM admin WHERE role = 'Normal Admin'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminid(resultSet.getInt("adminid"));
                admin.setAdminname(resultSet.getString("adminname"));
                admin.setAdminemail(resultSet.getString("adminemail"));
                admin.setAdminusername(resultSet.getString("adminusername"));
                admin.setAdminpassword(resultSet.getString("adminpassword"));
                admin.setRole(resultSet.getString("role"));
    
                adminlist.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return adminlist;
    }
    

    // find admin by username
    public Admin findAdminByUsername(String adminusername) throws SQLException {
        Admin admin = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM admin WHERE adminusername = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, adminusername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                admin = new Admin();
                admin.setAdminid(resultSet.getInt("adminid"));
                admin.setAdminname(resultSet.getString("adminname"));
                admin.setAdminemail(resultSet.getString("adminemail"));
                admin.setAdminusername(resultSet.getString("adminusername"));
                admin.setAdminpassword(resultSet.getString("adminpassword"));
                admin.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return admin;
    }

    //get Admin by ID
        public Admin getAdminById(int adminid) throws SQLException {
            Admin admin = null;
            try (Connection connection = dataSource.getConnection()) {
                String sql = "SELECT * FROM admin WHERE adminid=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, adminid);
                ResultSet resultSet = statement.executeQuery();
    
                if (resultSet.next()) {
                    admin = new Admin();
                    admin.setAdminid(resultSet.getInt("adminid"));
                    admin.setAdminname(resultSet.getString("adminname"));
                    admin.setAdminemail(resultSet.getString("adminemail"));
                    admin.setAdminusername(resultSet.getString("adminusername"));
                    admin.setAdminpassword(resultSet.getString("adminpassword"));
                    admin.setRole(resultSet.getString("role"));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
    
            return admin;
        }

        public List<Volunteer> getAllVolunteers() throws SQLException {
            List<Volunteer> volunteers = new ArrayList<>();
        
            try (Connection connection = dataSource.getConnection()) {
                String sql = "SELECT v.*, EXISTS (SELECT 1 FROM registration WHERE vid = v.vid) AS is_registered FROM volunteer v ORDER BY v.vid";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
        
                while (resultSet.next()) {
                    Volunteer volunteer = new Volunteer();
                    volunteer.setId(resultSet.getInt("vid"));
                    volunteer.setName(resultSet.getString("vfullname"));
                    volunteer.setEmail(resultSet.getString("vemail"));
                    volunteer.setPhonenum(resultSet.getString("vphonenum"));
                    volunteer.setIcnum(resultSet.getString("vicnum"));
                    volunteer.setUsername(resultSet.getString("vusername"));
                    volunteer.setRegistered(resultSet.getBoolean("is_registered"));  // Assuming the Volunteer model has this attribute
        
                    volunteers.add(volunteer);
                }
            } catch (SQLException e) {
                throw new SQLException("Error retrieving volunteers: " + e.getMessage());
            }
        
            return volunteers;
        }

         // update account
        public void updateAccount(Admin admin) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE admin SET adminname=?, adminemail=?, adminusername=?, adminpassword=?, role=? "
                    + "WHERE adminid=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, admin.getAdminname());
            statement.setString(2, admin.getAdminemail());
            statement.setString(3, admin.getAdminusername());
            statement.setString(4, admin.getAdminpassword());
            statement.setString(5, admin.getRole());
            statement.setInt(6, admin.getAdminid());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

        //delete account
    public void deleteAccount(int adminid) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM admin WHERE adminid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, adminid);

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}