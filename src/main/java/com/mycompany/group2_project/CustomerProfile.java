package com.mycompany.group2_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CustomerProfile extends JFrame implements ActionListener {

    // UI Components
    private JLabel lblName, lblUsername, lblEmail, lblPhoneNumber, lblPassword, lblAddress;
    private JButton btnHome, btnOrder, btnResetPass;
    private JPanel EmailBox, UsernameBox, PhoneNumberBox, PasswordBox, AddressBox, mainPanel;

    CustomerProfile() { // Constructor
        
        // Setting up the JFrame properties
        setTitle("Customer Profile"); // Title of the JFrame
        setSize(464, 737); // Size of the JFrame 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default close operation to exit the application
        setResizable(false); //
        setLayout(null); // Null layout for manual positioning

        // Main panel with a maroon border
        mainPanel = new JPanel(); // Create a JPanel
        mainPanel.setBounds(5, 10, 440, 685); // Position and size of the main panel
        mainPanel.setBorder(BorderFactory.createMatteBorder(20, 10, 20, 10, new Color(113, 45, 59))); // Set a maroon matte border
        mainPanel.setLayout(null); /// Null layout for manual positioning
        mainPanel.setBackground(Color.WHITE); // Background color of the main panel to white
        add(mainPanel); // Add the main panel to the JFrame

        // Home button with icon
        Icon ico = new ImageIcon("C:\\Users\\ryzam\\Documents\\NetBeansProjects\\ProjectRyza\\src\\main\\java\\com\\mycompany\\projectryza\\home.png"); // Home icon image
        btnHome = new JButton(ico); // JButton with the icon
        btnHome.setBounds(20, 40, 30, 30); // Position and size of the button
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        mainPanel.add(btnHome); // Add the button to the main panel

        // User Name label
        lblName = new JLabel("Ryza Alicaway"); // JLabel for displaying the User's Name
        lblName.setBounds(145, 40, 200, 30); // Position and size of the label
        lblName.setFont(new Font("Arial", Font.BOLD, 22)); // Font style and size
        lblName.setForeground(new Color(113, 45, 59)); // Font color to maroon
        mainPanel.add(lblName); // Add the label to the main panel

        // Order History button
        btnOrder = new JButton("Order History"); // JButton for Order History
        btnOrder.setBounds(168, 70, 115, 20); // Position and size of the button
        btnOrder.setFont(new Font("Arial", Font.BOLD, 12)); // Font style and size
        btnOrder.setBackground(new Color(113, 45, 59)); // Button background color to maroon
        btnOrder.setForeground(Color.WHITE); // Text color to white
        mainPanel.add(btnOrder); // Add the button to the main panel

        // Email label
        lblEmail = new JLabel("Email"); // JLabel for the Email label
        lblEmail.setBounds(50, 110, 80, 30); // Position and size of the label
        lblEmail.setFont(new Font("Arial", Font.BOLD, 18)); // Font style and size
        mainPanel.add(lblEmail); // Add the label to the main panel

        // Email text box
        EmailBox = createBoxWithLabel("ry***********ay@gmail.com", 50, 140, 340, 40); // Create a text box with the User's Email
        mainPanel.add(EmailBox); // Add the text box to the main panel

        // Username label
        lblUsername = new JLabel("Username"); // JLabel for the Username label
        lblUsername.setBounds(50, 195, 100, 30); // Position and size of the label
        lblUsername.setFont(new Font("Arial", Font.BOLD, 18)); // Font style and size
        mainPanel.add(lblUsername); // Add the label to the main panel

        // Username text box
        UsernameBox = createBoxWithLabel("ryzchi", 50, 225, 340, 40); // Create a text box with the User's Username
        mainPanel.add(UsernameBox); // Add the text box to the main panel

        // Phone Number label
        lblPhoneNumber = new JLabel("Phone Number"); // JLabel for the Phone Number label
        lblPhoneNumber.setBounds(50, 275, 150, 30); // Position and size of the label
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 18)); // Font style and size
        mainPanel.add(lblPhoneNumber); // Add the label to the main panel

        // Phone Number text box
        PhoneNumberBox = createBoxWithLabel("09086003668", 50, 305, 340, 40); // Create a text box with the User's Phone Number
        mainPanel.add(PhoneNumberBox); // Add the text box to the main panel

        // Password label
        lblPassword = new JLabel("Password"); // JLabel for the password label
        lblPassword.setBounds(50, 355, 100, 30); // Position and size of the label
        lblPassword.setFont(new Font("Arial", Font.BOLD, 18)); // Font style and size
        mainPanel.add(lblPassword); // Add the label to the main panel

        // Password text box
        PasswordBox = createBoxWithLabel("**********", 50, 385, 340, 40); // Create a text box with Masked Password
        mainPanel.add(PasswordBox); // Add the text box to the main panel

        
        // Address label
        lblAddress = new JLabel("Address"); // JLabel for the Address label
        lblAddress.setBounds(50, 435, 100, 30); // Position and size of the label
        lblAddress.setFont(new Font("Arial", Font.BOLD, 18)); // Font style and size
        mainPanel.add(lblAddress); // Add the label to the main panel

        // Addresss text box
        AddressBox = createBoxWithLabel("Brgy. Santo Niño, Biñan City, Laguna", 50, 465, 340, 40); // Create a text box with the User's Address
        mainPanel.add(AddressBox); // Add the text box to the main panel

        // Change Password button
        btnResetPass = new JButton("Change Password"); // JButton for Changing the Password
        btnResetPass.setBounds(134, 530, 180, 30); // Position and size of the button
        btnResetPass.setFont(new Font("Arial", Font.BOLD, 15)); // Font style and size
        btnResetPass.setBackground(new Color(113, 45, 59)); // Button background color to maroon
        btnResetPass.setForeground(Color.WHITE); // Text color to white
        mainPanel.add(btnResetPass); // Add the button to the main panel
        
        // Action Listener for Change Password
        btnResetPass.addActionListener(this);
        btnOrder.addActionListener(this);
        btnHome.addActionListener(this);

        // Set frame visibility
        setVisible(true);
    }

    // Method to create a boxed label
    private JPanel createBoxWithLabel(String text, int x, int y, int width, int height) {
        
        // Create a new JPanel to serve as the box
        JPanel box = new JPanel();
        box.setBounds(x, y, width, height); // Position and size of the box
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Black border with a thickness of 2 pixels 
        box.setLayout(null); // Manual positioning of label inside the box
        box.setBackground(Color.WHITE); // Background color of the box to whiter

        // Create a new JLabel to display the text inside the box
        JLabel label = new JLabel(text); 
        label.setBounds(10, 10, width - 20, height - 20); // Position and size of the label within the box
        label.setFont(new Font("Arial", Font.PLAIN, 15)); // Font style and size for the label text
        box.add(label); // Add the label to the box

        return box;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnResetPass) {
            // Create and display the Reset frame
            new EmailVerification().setLocationRelativeTo(null);
        }
        
     /*   
        else if (e.getSource() == btnOrder) {
        // Create and display the ProjectDSA frame
        
        this.dispose(); // Close the current frame
        new StatusMainx ();
    }
        
        else if (e.getSource() == btnHome) {
            //Create and display the FoodDel Frame
            
            this.dispose(); // Close the current frame
            new Trial_foodDelMain();
        }
*/
    }
    

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerProfile().setLocationRelativeTo(null); // Center the main frame
        });
    }
}






