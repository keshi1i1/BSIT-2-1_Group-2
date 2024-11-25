package com.mycompany.group2_project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class ChangePassword extends JFrame implements ActionListener {

    // UI Components
    private JLabel lblCurrentPass, lblNewPass, lblRetypePass;
    private JPasswordField txtCurrent, txtNew, txtRetype;
    private JButton btnCancel, btnConfirm;

    // Simulated stored password 
    private final String currentPasswordStored = "Ryalicaway0720";

    ChangePassword() { //Constructor
        
        // Setting up the JFrame properties
        setTitle("Change Password");
        setSize(464, 368);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Current Password label
        lblCurrentPass = new JLabel("Current Password");
        lblCurrentPass.setBounds(20, 20, 200, 30);
        lblCurrentPass.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblCurrentPass);

        // Password field for current password input
        txtCurrent = new JPasswordField();
        txtCurrent.setBounds(20, 50, 200, 30);
        add(txtCurrent);

        // New Password label
        lblNewPass = new JLabel("New Password");
        lblNewPass.setBounds(20, 90, 200, 30);
        lblNewPass.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblNewPass);

        // Password field for New Password input
        txtNew = new JPasswordField();
        txtNew.setBounds(20, 120, 200, 30);
        add(txtNew);

        // Re-type Password label
        lblRetypePass = new JLabel("Re-type Password");
        lblRetypePass.setBounds(20, 160, 200, 30);
        lblRetypePass.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblRetypePass);

        // Password Field for Re-typing new password
        txtRetype = new JPasswordField();
        txtRetype.setBounds(20, 190, 200, 30);
        add(txtRetype);

        // Cancel button
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(106, 240, 100, 30);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 15));
        add(btnCancel);

        // Confirm button
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(252, 240, 100, 30);
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 15));
        add(btnConfirm);

        // Add action listeners for button clicks
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
        
        // Set frame visibility
        setVisible(true);
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new ChangePassword().setLocationRelativeTo(null); // Center the main frame
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Check if the "Cancel" button was clicked
        if (e.getSource() == btnCancel) {
            this.dispose(); // Close the current frame
        } 
        // Check if the "Confirm" button was clicked
        else if (e.getSource() == btnConfirm) {
            // Get text from password fields
            String currentPasswordInput = new String(txtCurrent.getPassword());
            String newPassword = new String(txtNew.getPassword());
            String retypePassword = new String(txtRetype.getPassword());

            // Validate current password
            if (!currentPasswordInput.equals(currentPasswordStored)) {
                JOptionPane.showMessageDialog(this, "Current password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop further execution if the password is incorrect
            }

            // Validate that new passwords match
            if (!newPassword.equals(retypePassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop further execution if passwords do not match
            }

            // Show success message and close the frame
            JOptionPane.showMessageDialog(this, "Password Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Close the current frame
            new CustomerProfile(); // Create a new ProjectRyza frame
        }
    }
}

