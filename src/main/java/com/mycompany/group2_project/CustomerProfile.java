package com.mycompany.group2_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomerProfile extends JFrame implements ActionListener {

    // UI Components
    private JLabel lblName, lblUsername, lblEmail, lblPhoneNumber, lblPassword, lblAddress;
    private JButton btnHome, btnOrder, btnResetPass;
    private JPanel EmailBox, UsernameBox, PhoneNumberBox, PasswordBox, AddressBox, mainPanel;

    // Constructor
    CustomerProfile() {
        // Set JFrame properties
        setTitle("Customer Profile");
        setSize(464, 737);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon logoIcon = new ImageIcon("fordaFood.png");
        setIconImage(logoIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main panel with border
        mainPanel = new JPanel();
        mainPanel.setBounds(5, 10, 440, 685);
        mainPanel.setBorder(BorderFactory.createMatteBorder(20, 10, 20, 10, new Color(113, 45, 59))); // Maroon border
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
        
        // Add Home Button
        Icon ico = new ImageIcon("home.png");
        btnHome = new JButton(ico);
        btnHome.setBounds(20, 40, 30, 30);
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        mainPanel.add(btnHome);

        // User's Name Label
        lblName = new JLabel("Ryza Alicaway");
        lblName.setBounds(145, 40, 200, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 22));
        lblName.setForeground(new Color(113, 45, 59)); 
        mainPanel.add(lblName);

        // Order History Button
        btnOrder = new JButton("Order History");
        btnOrder.setBounds(168, 70, 115, 20);
        btnOrder.setFont(new Font("Arial", Font.BOLD, 12));
        btnOrder.setBackground(new Color(113, 45, 59)); // Maroon
        btnOrder.setForeground(Color.WHITE);
        mainPanel.add(btnOrder);

        // Add Profile Details (Email, Username, Phone Number, Password, Address)
        lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 110, 80, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblEmail);

        EmailBox = createBoxWithLabel("ry***********ay@gmail.com", 50, 140, 340, 40);
        mainPanel.add(EmailBox);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 195, 100, 30);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblUsername);

        UsernameBox = createBoxWithLabel("ryzchi", 50, 225, 340, 40);
        mainPanel.add(UsernameBox);

        lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setBounds(50, 275, 150, 30);
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblPhoneNumber);

        PhoneNumberBox = createBoxWithLabel("09086003668", 50, 305, 340, 40);
        mainPanel.add(PhoneNumberBox);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 355, 100, 30);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblPassword);

        PasswordBox = createBoxWithLabel("**********", 50, 385, 340, 40);
        mainPanel.add(PasswordBox);

        lblAddress = new JLabel("Address");
        lblAddress.setBounds(50, 435, 100, 30);
        lblAddress.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblAddress);

        AddressBox = createBoxWithLabel("Brgy. Santo Niño, Biñan City, Laguna", 50, 465, 340, 40);
        mainPanel.add(AddressBox);

        // Add Change Password Button
        btnResetPass = new JButton("Change Password");
        btnResetPass.setBounds(134, 530, 180, 30);
        btnResetPass.setFont(new Font("Arial", Font.BOLD, 15));
        btnResetPass.setBackground(new Color(113, 45, 59));
        btnResetPass.setForeground(Color.WHITE);
        mainPanel.add(btnResetPass);

        // Add action listeners
        btnResetPass.addActionListener(this);
        btnOrder.addActionListener(this);
        btnHome.addActionListener(this);

        // Display the JFrame
        setVisible(true);
    }

    // Creates a JPanel with a label inside (used for profile details)
    private JPanel createBoxWithLabel(String text, int x, int y, int width, int height) {
        JPanel box = new JPanel();
        box.setBounds(x, y, width, height);
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        box.setLayout(null);
        box.setBackground(Color.WHITE);

        JLabel label = new JLabel(text);
        label.setBounds(10, 10, width - 20, height - 20);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        box.add(label);

        return box;
    }

    // Handles button actions (Change Password, Order History, Home)
    @Override
    public void actionPerformed(ActionEvent e) {
        // Open the EmailVerification frame when "Change Password" is clicked
        if (e.getSource() == btnResetPass) {
            new EmailVerification().setLocationRelativeTo(null); 
        }
        
        // Check if the "Order History" button was clicked
        else if (e.getSource() == btnOrder) {
        this.dispose();
        // Open the DeliveryStatusMain frame (Show order history)
        new DeliveryStatusMain();
    }
         // Check if the "Home" button was clicked
        else if (e.getSource() == btnHome) {
            this.dispose();
            // Open the MenuSelectionMain frame (Back to the main menu)
            new MenuSelectionMain();
        } 
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerProfile().setLocationRelativeTo(null);
        });
    }
}

