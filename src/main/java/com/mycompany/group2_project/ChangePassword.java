
package com.mycompany.group2_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class ChangePassword extends JFrame implements ActionListener {

    // UI Components
    private JLabel lblCurrentPass, lblNewPass, lblRetypePass;
    private JPasswordField txtCurrent, txtNew, txtRetype;
    private JButton btnCancel, btnConfirm;
    private JPanel mainPanel;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public String customerId;


    //Constructor
    ChangePassword(String id) throws SQLException { 
        customerId = id;
          
        try{
         //For connecting to the database

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
     
       // Set JFrame properties
        setTitle("Change Password");
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

        // Current Password label
        lblCurrentPass = new JLabel("Current Password");
        lblCurrentPass.setBounds(75, 30, 200, 30);
        lblCurrentPass.setFont(new Font("Arial", Font.BOLD, 15));
        mainPanel.add(lblCurrentPass);

        // Password field for current password input
        txtCurrent = new JPasswordField();
        txtCurrent.setBounds(75, 60, 300, 30);
        mainPanel.add(txtCurrent);

        // New Password label
        lblNewPass = new JLabel("New Password");
        lblNewPass.setBounds(75, 90, 200, 30);
        lblNewPass.setFont(new Font("Arial", Font.BOLD, 15));
        mainPanel.add(lblNewPass);

        // Password field for New Password input
        txtNew = new JPasswordField();
        txtNew.setBounds(75, 120, 300, 30);
        mainPanel.add(txtNew);

        // Re-type Password label
        lblRetypePass = new JLabel("Re-type Password");
        lblRetypePass.setBounds(75, 150, 200, 30);
        lblRetypePass.setFont(new Font("Arial", Font.BOLD, 15));
        mainPanel.add(lblRetypePass);

        // Password Field for Re-typing new password
        txtRetype = new JPasswordField();
        txtRetype.setBounds(75, 180, 300, 30);
        mainPanel.add(txtRetype);

        // Cancel button
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(100, 230, 100, 30);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 15));
        btnCancel.setBackground(new Color(113, 45, 59));
        btnCancel.setForeground(Color.WHITE);
        mainPanel.add(btnCancel);

        // Confirm button
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(250, 230, 100, 30);
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 15));
        btnConfirm.setBackground(new Color(113, 45, 59));
        btnConfirm.setForeground(Color.WHITE);
        mainPanel.add(btnConfirm);

        // Add action listeners
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
        
        // Display the JFrame
        setVisible(true);
    
    }   catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Handles button actions (Cancel, Confirm)
    @Override
    public void actionPerformed(ActionEvent e) {
    // Check if the "Cancel" button was clicked
    if (e.getSource() == btnCancel) {
        this.dispose(); 
    } 
    // Check if the "Confirm" button was clicked
    else if (e.getSource() == btnConfirm) {
        // Get text from password fields
        String currentPasswordInput = new String(txtCurrent.getPassword());
        String newPassword = new String(txtNew.getPassword());
        String retypePassword = new String(txtRetype.getPassword());

        // Check for empty fields
        if (currentPasswordInput.isEmpty() || newPassword.isEmpty() || retypePassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        // Validate current password
        
            try {
                pst = con.prepareStatement("select * from account_profile where customer_id=?");
                pst.setString(1, customerId);
                rs = pst.executeQuery();
             if(rs.next()){
            
        String pass = rs.getString("password");
        if (!currentPasswordInput.equals(pass)) {
            JOptionPane.showMessageDialog(this, "Current password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        }
            } catch (SQLException ex) {
                Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        // Validate that new passwords match
        if (!newPassword.equals(retypePassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        try {
            pst = con.prepareStatement("update account_profile set password=? where customer_id=?");
            pst.setString(1, newPassword);
            pst.setString(2, customerId);
              int rs1 = pst.executeUpdate();
              if(rs1==1){
                  
               // Show success message and close the frame
        JOptionPane.showMessageDialog(this, "Password Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
       
        this.dispose(); 
              }
        } catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    }
}