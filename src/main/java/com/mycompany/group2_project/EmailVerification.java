
package com.mycompany.group2_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JLabel lblEmail;
    private JTextField txtUserEmail;
    private JButton btnBack, btnNext;
    private JPanel mainPanel;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public String customerId, enteredEmail, correctEmail;

    // Constructor
    EmailVerification(String id) {
        customerId = id;
        
         try{
         //For connecting to the database

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
                pst = con.prepareStatement("SELECT * FROM account_profile WHERE customer_id=?");
                pst.setString(1, customerId);
        
        // Set JFrame properties
        setTitle("Email Verification");
        setSize(464, 368);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon logoIcon = new ImageIcon("Icon.png");
        setIconImage(logoIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         // Main panel with border
        mainPanel = new JPanel();
        mainPanel.setBounds(5, 10, 440, 315);
        mainPanel.setBorder(BorderFactory.createMatteBorder(20, 10, 20, 10, new Color(113, 45, 59)));
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        // Instruction to prompt the user
        lblEmail = new JLabel("Enter your Email:");
        lblEmail.setBounds(160, 100, 200, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblEmail);

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
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailVerification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmailVerification.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Handles button actions (Back, Next)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            // Close the frame when "Cancel" was clicked
            this.dispose();
        }
        else if (e.getSource() == btnNext) {
            try {
                // Validate email input
                enteredEmail = txtUserEmail.getText(); // User input
                pst = con.prepareStatement("select * from account_profile where customer_id=?");
                pst.setString(1, customerId);
            }
            
            catch (SQLException ex) {
                Logger.getLogger(EmailVerification.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs = pst.executeQuery();
                    
                if(rs.next()) {
                    correctEmail = rs.getString("email");
                }

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
                
                try {
                    new ChangePassword(customerId).setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    Logger.getLogger(EmailVerification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             } catch (SQLException ex) {
                Logger.getLogger(EmailVerification.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}