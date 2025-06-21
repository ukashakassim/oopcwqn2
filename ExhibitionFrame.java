package ExhibitionFrame;

import com.vu.exhibition.controller.ExhibitionController;
import com.vu.exhibition.model.Participant;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ExhibitionFrame extends JFrame {
    private JTextField regIdField, nameField, facultyField, projectField, contactField, emailField;
    private JLabel imageLabel;
    private JButton registerBtn, searchBtn, updateBtn, deleteBtn, clearBtn, exitBtn, uploadBtn;
    private final ExhibitionController controller;
    private String imagePath;

    public ExhibitionFrame() {
        setTitle("Victoria University Exhibition Registration System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new ExhibitionController(this);
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        // Initialize fields
        regIdField = new JTextField(20);
        nameField = new JTextField(20);
        facultyField = new JTextField(20);
        projectField = new JTextField(20);
        contactField = new JTextField(20);
        emailField = new JTextField(20);
        
        // Initialize buttons
        registerBtn = new JButton("Register");
        searchBtn = new JButton("Search");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        exitBtn = new JButton("Exit");
        uploadBtn = new JButton("Upload Image");
        
        // Initialize image label
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(300, 200));
        imageLabel.setBorder(BorderFactory.createEtchedBorder());
        
        // Add action listeners
        registerBtn.addActionListener(e -> registerParticipant());
        searchBtn.addActionListener(e -> searchParticipant());
        updateBtn.addActionListener(e -> updateParticipant());
        deleteBtn.addActionListener(e -> deleteParticipant());
        clearBtn.addActionListener(e -> clearForm());
        exitBtn.addActionListener(e -> System.exit(0));
        uploadBtn.addActionListener(e -> uploadImage());
    }

    private void layoutComponents() {
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.add(new JLabel("Registration ID:"));
        formPanel.add(regIdField);
        formPanel.add(new JLabel("Student Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Faculty:"));
        formPanel.add(facultyField);
        formPanel.add(new JLabel("Project Title:"));
        formPanel.add(projectField);
        formPanel.add(new JLabel("Contact Number:"));
        formPanel.add(contactField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(registerBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(exitBtn);
        
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(new JLabel("Project Image:"), BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(uploadBtn, BorderLayout.SOUTH);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    private void registerParticipant() {
        if (validateInputs()) {
            Participant participant = createParticipantFromForm();
            controller.registerParticipant(participant);
        }
    }

    private void searchParticipant() {
        String regId = regIdField.getText().trim();
        if (regId.isEmpty()) {
            showMessage("Please enter Registration ID to search");
            return;
        }
        
        Participant participant = controller.searchParticipant(regId);
        if (participant != null) {
            populateForm(participant);
            displayImage(participant.getImagePath());
        } else {
            showMessage("Participant not found");
        }
    }

    private void updateParticipant() {
        if (validateInputs()) {
            Participant participant = createParticipantFromForm();
            controller.updateParticipant(participant);
        }
    }

    private void deleteParticipant() {
        String regId = regIdField.getText().trim();
        if (regId.isEmpty()) {
            showMessage("Please enter Registration ID to delete");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this participant?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteParticipant(regId);
        }
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Project Image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "gif"));
        
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();
            displayImage(imagePath);
        }
    }

    private void displayImage(String path) {
        if (path != null && !path.isEmpty()) {
            try {
                BufferedImage img = ImageIO.read(new File(path));
                Image scaledImage = img.getScaledInstance(
                    imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } catch (IOException ex) {
                showMessage("Error loading image: " + ex.getMessage());
            }
        } else {
            imageLabel.setIcon(null);
        }
    }

    private Participant createParticipantFromForm() {
        return new Participant(
            regIdField.getText().trim(),
            nameField.getText().trim(),
            facultyField.getText().trim(),
            projectField.getText().trim(),
            contactField.getText().trim(),
            emailField.getText().trim(),
            imagePath
        );
    }

    private void populateForm(Participant participant) {
        regIdField.setText(participant.getRegistrationId());
        nameField.setText(participant.getStudentName());
        facultyField.setText(participant.getFaculty());
        projectField.setText(participant.getProjectTitle());
        contactField.setText(participant.getContactNumber());
        emailField.setText(participant.getEmail());
        imagePath = participant.getImagePath();
    }

    public void clearForm() {
        regIdField.setText("");
        nameField.setText("");
        facultyField.setText("");
        projectField.setText("");
        contactField.setText("");
        emailField.setText("");
        imagePath = null;
        imageLabel.setIcon(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private boolean validateInputs() {
        if (regIdField.getText().trim().isEmpty() ||
            nameField.getText().trim().isEmpty() ||
            facultyField.getText().trim().isEmpty() ||
            projectField.getText().trim().isEmpty() ||
            contactField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty()) {
            
            showMessage("All fields are required!");
            return false;
        }
        
        if (!emailField.getText().trim().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showMessage("Please enter a valid email address!");
            return false;
        }
        
        if (!contactField.getText().trim().matches("^[0-9]{10,15}$")) {
            showMessage("Please enter a valid contact number (10-15 digits)!");
            return false;
        }
        
        return true;
    }
}