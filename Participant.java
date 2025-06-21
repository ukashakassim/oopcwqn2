package com.vu.exhibition.model;

import java.awt.Image;

public class Participant {
    private final String registrationId;
    private final String studentName;
    private final String faculty;
    private final String projectTitle;
    private final String contactNumber;
    private final String email;
    private final String imagePath;
    private transient Image image; // Not persisted to database

    // Constructor
    public Participant(String registrationId, String studentName, String faculty, 
                      String projectTitle, String contactNumber, String email, 
                      String imagePath) {
        this.registrationId = registrationId;
        this.studentName = studentName;
        this.faculty = faculty;
        this.projectTitle = projectTitle;
        this.contactNumber = contactNumber;
        this.email = email;
        this.imagePath = imagePath;
    }

    // Getters and setters
    public String getRegistrationId() { return registrationId; }
    public String getStudentName() { return studentName; }
    public String getFaculty() { return faculty; }
    public String getProjectTitle() { return projectTitle; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getImagePath() { return imagePath; }
    public Image getImage() { return image; }
    
    public void setImage(Image image) { this.image = image; }
    // ... other setters if needed
}