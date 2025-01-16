
package com.mycompany.group2_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CustomerProfile extends JFrame implements ActionListener {

    // UI Components
    private JLabel lblName, lblUsername, lblEmail, lblPhoneNumber, lblPassword, lblAddress;
    private JButton btnHome, btnOrder, btnResetPass;
    private JPanel mainPanel;
    private JTextField Address, Username, PhoneNumber, Email;
    private JPasswordField  Password;
    String add,email,fn,ln, phnumber, username, pass;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    // Constructor
    CustomerProfile() {
          
        try{
         //For connecting to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
                pst = con.prepareStatement("SELECT * FROM account_profile WHERE customer_id='3AG'");
       
                rs = pst.executeQuery();
                if(rs.next()){
                    fn = rs.getString("first_name");
                    ln = rs.getString("last_name");
                    add = rs.getString("address");
                    email = rs.getString("email");
                    phnumber = rs.getString("phone_number");
                    username = rs.getString("username");
                    pass = rs.getString("password");
                      
        // Set JFrame properties
        setTitle("Customer Profile");
        setSize(464, 737);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon logoIcon = new ImageIcon("Icon.png");
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
        lblName = new JLabel(fn + " " + ln);
        lblName.setBounds(130, 40, 200, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 25));
        lblName.setForeground(new Color(113, 45, 59)); 
        mainPanel.add(lblName);

        // Order History Button
        btnOrder = new JButton("Order History");
        btnOrder.setBounds(168, 80, 115, 20);
        btnOrder.setFont(new Font("Arial", Font.BOLD, 12));
        btnOrder.setBackground(new Color(113, 45, 59)); // Maroon
        btnOrder.setForeground(Color.WHITE);
        mainPanel.add(btnOrder);

        // Add Profile Details (Email, Username, Phone Number, Password, Address)
        lblAddress = new JLabel("Address");
        lblAddress.setBounds(50, 120, 80, 30);
        lblAddress.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblAddress);

        Address = new JTextField(add);
        Address.setBounds(50, 150, 350, 30);
        Address.setFont(new Font("Arial", Font.BOLD, 13));
        mainPanel.add(Address);

        lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 210, 100, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblEmail);

        Email = new JTextField(email);
        Email.setBounds(50, 240, 350, 30);
        Email.setFont(new Font("Arial", Font.BOLD, 13));
        mainPanel.add(Email);

        lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setBounds(50, 300, 150, 30);
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblPhoneNumber);

        PhoneNumber= new JTextField(phnumber);
        PhoneNumber.setBounds(50, 330, 350, 30);
        PhoneNumber.setFont(new Font("Arial", Font.BOLD, 13));
        mainPanel.add(PhoneNumber);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 390, 100, 30);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblUsername);

        Username= new JTextField(username);
        Username.setBounds(50, 420, 350, 30);
        Username.setFont(new Font("Arial", Font.BOLD, 13));
        mainPanel.add(Username);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 480, 100, 30);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblPassword);

        Password= new JPasswordField(pass);
        Password.setBounds(50, 510, 350, 30);
        Password.setFont(new Font("Arial", Font.BOLD, 13));
        mainPanel.add(Password);

        // Add Change Password Button
        btnResetPass = new JButton("Change Password");
        btnResetPass.setBounds(134, 580, 180, 30);
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
         } catch (SQLException ex) {
            Logger.getLogger(CustomerProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
//        new DeliveryStatus();
    }
         // Check if the "Home" button was clicked
        else if (e.getSource() == btnHome) {
            this.dispose();
            // Open the MenuSelectionMain frame (Back to the main menu)
//            new MenuSelection();
        } 
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerProfile().setLocationRelativeTo(null);
        });
    }
}







