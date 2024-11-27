package com.mycompany.group2_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class EmailVerification extends JFrame implements ActionListener {

    // UI Components
    private JLabel lblEmail, lblEmail1;
    private JTextField txtUserEmail;
    private JButton btnBack, btnNext;
    private JPanel mainPanel;


    // Constructor
    EmailVerification() {
        // Set JFrame properties
        setTitle("Email Verification");
        setSize(464, 368); 
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon logoIcon = new ImageIcon("fordaFood.png");
        setIconImage(logoIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         // Main panel with border
        mainPanel = new JPanel();
        mainPanel.setBounds(5, 10, 440, 315);
        mainPanel.setBorder(BorderFactory.createMatteBorder(20, 10, 20, 10, new Color(113, 45, 59)));
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        // Masked email display for reference
        lblEmail = new JLabel("alf*********rto@gmail.com");
        lblEmail.setBounds(115, 70, 250, 30); 
        lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblEmail);

        // Instruction to prompt the user
        lblEmail1 = new JLabel("Enter your Email:");
        lblEmail1.setBounds(162, 100, 140, 30); 
        lblEmail1.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(lblEmail1);

        // Text field for user input
        txtUserEmail = new JTextField();
        txtUserEmail.setBounds(100, 140, 250, 30);
        txtUserEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        mainPanel.add(txtUserEmail);

        // Cancel button
        btnBack = new JButton("Cancel");
        btnBack.setBounds(100, 200, 100, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setBackground(new Color(113, 45, 59)); 
        btnBack.setForeground(Color.WHITE);
        mainPanel.add(btnBack);

        // Next button
        btnNext = new JButton("Next");
        btnNext.setBounds(252, 200, 100, 30);
        btnNext.setFont(new Font("Arial", Font.BOLD, 15));
        btnNext.setBackground(new Color(113, 45, 59)); 
        btnNext.setForeground(Color.WHITE);
        mainPanel.add(btnNext);

        // Add action listeners
        btnBack.addActionListener(this);
        btnNext.addActionListener(this);

        // Display the JFrame
        setVisible(true); 
    }

    // Handles button actions (Back, Next)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            // Close the frame when "Cancel" was clicked
            this.dispose();
        } 
        else if (e.getSource() == btnNext) {
            // Validate email input
            String correctEmail = "alfredgualberto@gmail.com"; // The correct email for validation
            String enteredEmail = txtUserEmail.getText().trim(); // User input

            if (enteredEmail.isEmpty()) {
                // Warn if no email is entered
                JOptionPane.showMessageDialog(this, "Please enter a valid Email Address", "Warning", JOptionPane.WARNING_MESSAGE);
            } 
            else if (!enteredEmail.equals(correctEmail)) {
                // Show error if the email doesn't match
                JOptionPane.showMessageDialog(this, "Invalid Email Address!", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else {
                // Proceed if the email matches
                this.dispose(); 
                new ChangePassword().setLocationRelativeTo(null); 
            }
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmailVerification().setLocationRelativeTo(null));
    }
}

