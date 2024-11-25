package com.mycompany.group2_project;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class EmailVerification extends JFrame implements ActionListener {
    
    // UI Components
    private JLabel lblEmail, lblEmail1;
    private JTextField txtUserEmail;
    private JButton btnBack, btnNext;
    
    EmailVerification() {  //Constructor
        
        // Setting up the JFrame properties
        setTitle("Email Verification");
        setSize(464, 368);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // Displaying the hidden email label
        lblEmail = new JLabel("ry**********ay@gmail.com");
        lblEmail.setBounds(130, 90, 250, 30); 
        lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblEmail);
        
        // Prompting the user to enter their email
        lblEmail1 = new JLabel("Enter your Email:");
        lblEmail1.setBounds(162, 115, 140, 30); 
        lblEmail1.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblEmail1);
        
        // Text field for user to input their email
        txtUserEmail = new JTextField();
        txtUserEmail.setBounds(107, 155, 250, 30);
        txtUserEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtUserEmail);
       
        // 'Back' button for navigation
        btnBack = new JButton("Cancel");
        btnBack.setBounds(106, 220, 100, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));    
        add(btnBack);
        
        // 'Next' button for submission
        btnNext = new JButton("Next");
        btnNext.setBounds(252, 220, 100, 30);
        btnNext.setFont(new Font("Arial", Font.BOLD, 15));    
        add(btnNext);
        
        // Registering the action listeners for buttons
        btnBack.addActionListener(this);
        btnNext.addActionListener(this);
        
        // Set frame visibility
        setVisible(true);
    }
         
    @Override
    public void actionPerformed(ActionEvent e) {  
        
        // Check if 'Back' button was clicked
        if (e.getSource() == btnBack) {
            this.dispose(); // Close the current frame
        } 
        // Check if 'Next' button was clicked
        else if (e.getSource() == btnNext) {
            String correctEmail = "ryzamaealicaway@gmail.com"; // Expected email for validation
            String enteredEmail = txtUserEmail.getText().trim(); // User input
            
            // Validate the entered email
            if (enteredEmail.equals(correctEmail)) {
                this.dispose(); // Close the current frame
                
                // Open the ChangePass frame for password change
                new ChangePassword().setLocationRelativeTo(null);
            } 
            
            else {
                // Show an error dialog if the email does not match
                JOptionPane.showMessageDialog(this, "The Email you entered do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmailVerification().setLocationRelativeTo(null); // Center the main frame
            }
        });
    }
}

