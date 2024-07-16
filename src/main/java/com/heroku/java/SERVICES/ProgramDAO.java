package com.heroku.java.SERVICES;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List; 
import javax.sql.DataSource; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import org.springframework.web.multipart.MultipartFile;

// import com.heroku.java.MODEL.Issue;
import com.heroku.java.MODEL.Program;

public class ProgramDAO {
    private DataSource dataSource;

    public ProgramDAO(DataSource dataSource){
        this.dataSource=dataSource;
    }

     public void addProgram(@ModelAttribute("ProgramDetail") Program program) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            // String insertIssueSql = "INSERT INTO program ( pname, pdesc, pvenue, ptime, pdate, pimage) VALUES (?, ?, ?, ?, ?, ?)";
            String insertIssueSql = "INSERT INTO program ( pname, pdesc, pvenue, ptime, pdate, pimage, adminid) VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertIssueSql); 
            insertStatement.setString(1, program.getPname());
            insertStatement.setString(2, program.getPdesc());
            insertStatement.setString(3, program.getPvenue());
            insertStatement.setString(4, program.getPtime());
            insertStatement.setDate(5, program.getPdate());
            insertStatement.setBytes(6, program.getPimagebyte());
            insertStatement.setInt(7, program.getAdminId());
        
            insertStatement.execute(); 
            connection.close();
        } catch (SQLException e) {

            // Handle any exceptions or errors that occurred during the database operation
            e.printStackTrace();
            throw e;
        }
    }

    public List<Program> listProgram() throws SQLException {
        List<Program> programlist = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Modify the SQL query to filter out past programs
            String sql = "SELECT * FROM program WHERE pdate >= ? ORDER BY programid DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(currentDate));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int programid = resultSet.getInt("programid");
                String pname = resultSet.getString("pname");
                String pdesc = resultSet.getString("pdesc");
                String pvenue = resultSet.getString("pvenue");
                String ptime = resultSet.getString("ptime");
                Date pdate = resultSet.getDate("pdate");

                byte[] pimageBytes = resultSet.getBytes("pimage");
                String base64Image = Base64.getEncoder().encodeToString(pimageBytes);
                String imageSrc = "data:image/jpeg;base64," + base64Image;

                int adminId = resultSet.getInt("adminid");

                Program program = new Program(programid, pname, pdesc, pvenue, ptime, pdate, null, null, imageSrc, adminId);

                programlist.add(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return programlist;
    }

    

    // get Program by ID
    public Program getProgramById(int programid) throws SQLException {
        Program program = null;
        try (Connection connection = dataSource.getConnection()) {
            // String sql = "SELECT pname, pdesc, pvenue, ptime, pdate, pimage FROM program WHERE programid = ?";
            String sql = "SELECT pname, pdesc, pvenue, ptime, pdate, pimage FROM program WHERE programid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, programid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String pname = resultSet.getString("pname");
                String pdesc = resultSet.getString("pdesc");
                String pvenue = resultSet.getString("pvenue");
                String ptime = resultSet.getString("ptime");
                Date pdate = resultSet.getDate("pdate");

                System.out.println("Program Name in registration : " + pname);

                byte[] pimageBytes = resultSet.getBytes("pimage");
                String base64Image = Base64.getEncoder().encodeToString(pimageBytes);
                String imageSrc = "data:image/jpeg;base64," + base64Image;

                // int adminId  = resultSet.getInt("adminid");

                // program = new Program(programid, pname, pdesc, pvenue, ptime, pdate, null, null,imageSrc);
                program = new Program(programid, pname, pdesc, pvenue, ptime, pdate, null, null,imageSrc);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return program;
        
    }

    // update program
    public boolean updateProgram(Program program, MultipartFile pImage) {
        try (Connection connection = dataSource.getConnection()) {
            String sql;
            PreparedStatement statement;

            // Check if a new image is provided
            if (!pImage.isEmpty()) {
                sql = "UPDATE program SET pname=?, pdesc=?, pvenue=?, ptime=?, pdate=?, pimage=? WHERE programid=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, program.getPname());
                statement.setString(2, program.getPdesc());
                statement.setString(3, program.getPvenue());
                statement.setString(4, program.getPtime());
                statement.setDate(5, program.getPdate());

                statement.setBytes(6, pImage.getBytes());
                statement.setInt(7, program.getPid());
            } else {
                sql = "UPDATE program SET pname=?, pdesc=?, pvenue=?, ptime=? , pdate=?  WHERE programid=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, program.getPname());
                statement.setString(2, program.getPdesc());
                statement.setString(3, program.getPvenue());
                statement.setString(4, program.getPtime());
                statement.setDate(5, program.getPdate());
                statement.setInt(6, program.getPid());
            }

            // execute update
            int rowsAffected = statement.executeUpdate();
            connection.close();
            return rowsAffected > 0;

        } catch (SQLException sqe) {
            System.out.println("Error Code = " + sqe.getErrorCode());
            System.out.println("SQL state = " + sqe.getSQLState());
            System.out.println("Message = " + sqe.getMessage());
            sqe.printStackTrace();
        } catch (Exception e) {
            System.out.println("E message: " + e.getMessage());
        }
        return false;
    }

    //delete program
    // public void deleteProgram(int programid) throws SQLException {
    //     try (Connection connection = dataSource.getConnection()) {
    //         String sql = "DELETE FROM program WHERE programid=?";
    //         PreparedStatement statement = connection.prepareStatement(sql);
    //         statement.setInt(1, programid);

    //         statement.executeUpdate();
    //         connection.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         throw e;
    //     }
    // }

    public boolean hasRegistrations(int programid) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT COUNT(*) FROM registration WHERE programid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, programid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteProgram(int programid) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM program WHERE programid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, programid);

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void updateRegistration(int programid, String pname, String pdesc, String pvenue, String ptime, Date pdate) throws SQLException {
        String sql = "UPDATE program SET pname = ?, pdesc = ?, pvenue = ?, ptime = ?, pdate = ? WHERE programid = ?";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pname);
            statement.setString(2, pdesc);
            statement.setString(3, pvenue);
            statement.setString(4, ptime);
            statement.setDate(5, pdate);
            // statement.setString(6, imageSrc);
            statement.setInt(6, programid);
            
            statement.executeUpdate();
        }
    }

    

    

    





    
}
