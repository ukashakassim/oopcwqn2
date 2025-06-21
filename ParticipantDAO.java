package com.vu.exhibition.dao;

import com.vu.exhibition.model.Participant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ParticipantDAO {
    private static final String DB_URL = "jdbc:ucanaccess://VUE_Exhibition.accdb";
    private static final String TABLE_NAME = "Participants";

    // Database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Create (Register)
    public boolean registerParticipant(Participant participant) {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, participant.getRegistrationId());
            pstmt.setString(2, participant.getStudentName());
            pstmt.setString(3, participant.getFaculty());
            pstmt.setString(4, participant.getProjectTitle());
            pstmt.setString(5, participant.getContactNumber());
            pstmt.setString(6, participant.getEmail());
            pstmt.setString(7, participant.getImagePath());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error registering participant: " + e.getMessage());
            return false;
        }
    }

    // Read (Search)
    public Participant searchParticipant(String registrationId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE RegistrationID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, registrationId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Participant(
                    rs.getString("RegistrationID"),
                    rs.getString("StudentName"),
                    rs.getString("Faculty"),
                    rs.getString("ProjectTitle"),
                    rs.getString("ContactNumber"),
                    rs.getString("Email"),
                    rs.getString("ImagePath")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error searching participant: " + e.getMessage());
        }
        return null;
    }

    // Update
    public boolean updateParticipant(Participant participant) {
        String sql = "UPDATE " + TABLE_NAME + " SET StudentName=?, Faculty=?, ProjectTitle=?, " +
                    "ContactNumber=?, Email=?, ImagePath=? WHERE RegistrationID=?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, participant.getStudentName());
            pstmt.setString(2, participant.getFaculty());
            pstmt.setString(3, participant.getProjectTitle());
            pstmt.setString(4, participant.getContactNumber());
            pstmt.setString(5, participant.getEmail());
            pstmt.setString(6, participant.getImagePath());
            pstmt.setString(7, participant.getRegistrationId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating participant: " + e.getMessage());
            return false;
        }
    }

    // Delete
    public boolean deleteParticipant(String registrationId) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE RegistrationID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, registrationId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting participant: " + e.getMessage());
            return false;
        }
    }

    // Get all participants (for verification)
    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                participants.add(new Participant(
                    rs.getString("RegistrationID"),
                    rs.getString("StudentName"),
                    rs.getString("Faculty"),
                    rs.getString("ProjectTitle"),
                    rs.getString("ContactNumber"),
                    rs.getString("Email"),
                    rs.getString("ImagePath")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching participants: " + e.getMessage());
        }
        return participants;
    }
}