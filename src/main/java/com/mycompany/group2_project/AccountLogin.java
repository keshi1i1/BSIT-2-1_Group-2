/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group2_project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createCompoundBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author jeste
 */
public class AccountLogin extends JFrame implements ActionListener, KeyListener {
    
    public AccountLogin() {
        //All the separated methods for efficiency
        PanelShadow();
        PictureIcons();
        HomePage();
        LogInPage();
        RegisterPage();
        Components();
        setLayout(null);
        setSize(464, 737);
        setVisible(true);
        setTitle("Account Login / Register");
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(logoIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Java swing components
    public JPanel loginP, loginSecondP, registerP, registerSecondP, loginShadowP, registerShadowP;
    public JLabel userLoginLbl, passLoginLbl, invalidUserPass, backgroundLbl, checkPassIconLbl1, firstNameLbl, lastNameLbl, addressLbl, emailLbl, phoneNumberLbl, userRegisterLbl, passRegisterLbl, confirmPassLbl, checkPassIconLbl2, checkPassIconLbl3;
    public JButton accountBtn, exitBtn, loginBtn, createBtn, closeLoginBtn, closeRegisterBtn, registerAccountBtn;
    public JTextField userLoginTF, firstNameTF, lastNameTF, addressTF, emailTF, phoneNumberTF, userRegisterTF;
    public JPasswordField passLoginTF, passRegisterTF, confirmPassTF;
    public JCheckBox checkPassLoginCB, checkPassRegisterCB, checkConfirmPassCB;
    public Font arial = new Font("Arial", Font.PLAIN, 14);
    public Font tahoma = new Font("Tahoma", Font.BOLD, 15);
    public Color maroon = new Color(113, 45, 59);
    public ImageIcon logoIcon, homePageIcon, openEyeIcon, closeEyeIcon, scaledOpenEye, scaledCloseEye;
    public Image imgScale1, imgScale2;
    public CompoundBorder tfBorder = createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1, true), BorderFactory.createEmptyBorder(0, 14, 0, 14));
    public CompoundBorder tfErrorBorder = createCompoundBorder(BorderFactory.createLineBorder(Color.red, 2, true), BorderFactory.createEmptyBorder(0, 14, 0, 14));
    public boolean enterPressed, enabled = true;
    public String username, password;
    
    //Added components in specified panels
    public void Components() {
        add(registerP);
        add(registerShadowP);
        add(loginP);
        add(loginShadowP);
        add(accountBtn);
        add(exitBtn);
        add(backgroundLbl);
        loginP.add(loginSecondP);
        loginP.add(userLoginLbl);
        loginP.add(userLoginTF);
        loginP.add(checkPassIconLbl1);
        loginP.add(checkPassLoginCB);
        loginP.add(passLoginLbl);
        loginP.add(passLoginTF);
        loginP.add(loginBtn);
        loginP.add(createBtn);
        loginP.add(invalidUserPass);
        loginSecondP.add(closeLoginBtn);
        registerP.add(registerSecondP);
        registerP.add(firstNameLbl);
        registerP.add(firstNameTF);
        registerP.add(lastNameLbl);
        registerP.add(lastNameTF);
        registerP.add(addressLbl);
        registerP.add(addressTF);
        registerP.add(emailLbl);
        registerP.add(emailTF);
        registerP.add(phoneNumberLbl);
        registerP.add(phoneNumberTF);
        registerP.add(userRegisterLbl);
        registerP.add(userRegisterTF);
        registerP.add(checkPassIconLbl2);
        registerP.add(checkPassRegisterCB);
        registerP.add(passRegisterLbl);
        registerP.add(passRegisterTF);
        registerP.add(checkPassIconLbl3);
        registerP.add(checkConfirmPassCB);
        registerP.add(confirmPassLbl);
        registerP.add(confirmPassTF);
        registerP.add(registerAccountBtn);
        registerSecondP.add(closeRegisterBtn);
    }
    
    //Method for putting a 'shadow' behind panels
    public void PanelShadow() {
        loginShadowP = new JPanel();
        loginShadowP.setVisible(false);
        loginShadowP.setLayout(null);
        loginShadowP.setBackground(new Color(0,0,0, 30));
        loginShadowP.setBounds(46,196,358,308);
        
        registerShadowP = new JPanel();
        registerShadowP.setVisible(false);
        registerShadowP.setLayout(null);
        registerShadowP.setBackground(new Color(0,0,0, 30));
        registerShadowP.setBounds(46,61,358,578);
    }
    
    //Method for adding an image to the program
    public void PictureIcons() {    
        homePageIcon = new ImageIcon("home_page.png");
        
        logoIcon = new ImageIcon("fordaFood.png");
        
        openEyeIcon = new ImageIcon("open_eye.png");
        imgScale1 = openEyeIcon.getImage().getScaledInstance(20, 19, Image.SCALE_SMOOTH);
        scaledOpenEye = new ImageIcon(imgScale1);
        
        closeEyeIcon = new ImageIcon("close_eye.png");
        imgScale2 = closeEyeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        scaledCloseEye = new ImageIcon(imgScale2);
    }
    
    //First panel for logging in or exitting the program
    public void HomePage() {
        backgroundLbl = new JLabel();
        backgroundLbl.setIcon(homePageIcon);
        backgroundLbl.setBounds(0, 0, 450, 700);
        
        accountBtn = new JButton("Account");
        accountBtn.setBackground(Color.white);
        accountBtn.setForeground(maroon);
        accountBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        accountBtn.setFocusable(false);
        accountBtn.setContentAreaFilled(false);
        accountBtn.setBounds(125,440, 200, 50);
        accountBtn.setFont(tahoma);
        accountBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if(!enabled) {
                    return;
                }
                accountBtn.setFocusable(true);
                accountBtn.setContentAreaFilled(true);
                accountBtn.setBackground(maroon);
                accountBtn.setForeground(Color.white);
            }
            public void mouseExited(MouseEvent e) {
                if(!enabled) {
                    return;
                }
                accountBtn.setFocusable(false);
                accountBtn.setContentAreaFilled(false);
                accountBtn.setBackground(Color.white);
                accountBtn.setForeground(maroon);
            }
        });
        accountBtn.addActionListener(this);
        
        exitBtn = new JButton("Exit");
        exitBtn.setBackground(Color.white);
        exitBtn.setForeground(maroon);
        exitBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        exitBtn.setFocusable(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBounds(125,500, 200, 50);
        exitBtn.setFont(tahoma);
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if(!enabled) {
                    return;
                }
                exitBtn.setFocusable(true);
                exitBtn.setContentAreaFilled(true);
                exitBtn.setBackground(maroon);
                exitBtn.setForeground(Color.white);
            }
            public void mouseExited(MouseEvent e) {
                if(!enabled) {
                    return;
                }
                exitBtn.setFocusable(false);
                exitBtn.setContentAreaFilled(false);
                exitBtn.setBackground(Color.white);
                exitBtn.setForeground(maroon);
            }
        });
        exitBtn.addActionListener(this);
    }
    
    //Second panel for logging in with correct username and password
    public void LogInPage() {
        loginP = new JPanel();
        loginP.setVisible(false);
        loginP.setLayout(null);
        loginP.setBackground(Color.WHITE);
        loginP.setBounds(50,200,350,300);
        loginP.setBorder(BorderFactory.createLineBorder(maroon, 2));
        
        loginSecondP = new JPanel();
        loginSecondP.setLayout(null);
        loginSecondP.setBackground(maroon);
        loginSecondP.setBounds(0,0,350,20);
        
        closeLoginBtn = new JButton("\u00d7");
        closeLoginBtn.setFont(new Font("Arial", Font.PLAIN, 23));
        closeLoginBtn.setBounds(333, 2, 15, 15);
        closeLoginBtn.setForeground(Color.white);
        closeLoginBtn.setBackground(maroon);
        closeLoginBtn.setBorder(null);
        closeLoginBtn.setFocusable(false);
        closeLoginBtn.addActionListener(this);
        closeLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        userLoginLbl = new JLabel("Username");
        userLoginLbl.setFont(arial);
        userLoginLbl.setBounds(65, 60, 220, 40);
        userLoginLbl.setForeground(Color.LIGHT_GRAY);
        userLoginLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        passLoginLbl = new JLabel("Password");
        passLoginLbl.setFont(arial);
        passLoginLbl.setBounds(65, 125, 220, 40);
        passLoginLbl.setForeground(Color.LIGHT_GRAY);
        passLoginLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        userLoginTF = new JTextField();
        userLoginTF.setFont(arial);
        userLoginTF.setBounds(50, 60, 250, 40);
        userLoginTF.setForeground(Color.black);
        userLoginTF.setBorder(tfBorder);
        userLoginTF.setOpaque(false);
        userLoginTF.addKeyListener(this);
        
        passLoginTF = new JPasswordField();
        passLoginTF.setFont(arial);
        passLoginTF.setBounds(50, 125, 250, 40);
        passLoginTF.setForeground(Color.black);
        passLoginTF.setBorder(tfBorder);
        passLoginTF.setOpaque(false);
        passLoginTF.setEchoChar('\u2022');
        passLoginTF.addKeyListener(this);
        
        checkPassIconLbl1 = new JLabel();
        checkPassIconLbl1.setFont(arial);
        checkPassIconLbl1.setBounds(270, 135, 20, 20);
        checkPassIconLbl1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkPassIconLbl1.setIcon(scaledCloseEye);
        
        checkPassLoginCB = new JCheckBox("");
        checkPassLoginCB.setFont(new Font("Arial", Font.PLAIN, 10));
        checkPassLoginCB.setBounds(270, 135, 20, 20);
        checkPassLoginCB.setForeground(Color.black);
        checkPassLoginCB.setOpaque(false);
        checkPassLoginCB.setFocusable(false);
        checkPassLoginCB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkPassLoginCB.addActionListener(this);
        
        loginBtn = new JButton("LOG IN");
        loginBtn.setFont(arial);
        loginBtn.setBounds(135, 215 , 80, 25);
        loginBtn.setForeground(Color.black);
        loginBtn.setBackground(Color.white);
        loginBtn.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        loginBtn.setFocusable(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(new Color(236, 236, 236));
            }
            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(Color.white);
            }
        });
        loginBtn.addActionListener(this);
        
        createBtn = new JButton("CREATE NEW ACCOUNT?");
        createBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        createBtn.setBounds(100, 245, 150, 25);
        createBtn.setForeground(Color.black);
        createBtn.setBorder(null);
        createBtn.setFocusable(false);
        createBtn.setContentAreaFilled(false);
        createBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                createBtn.setForeground(new Color(204,102,102));
            }
            public void mouseExited(MouseEvent e) {
                createBtn.setForeground(Color.black);
            }
        });
        createBtn.addActionListener(this);
        
        invalidUserPass = new JLabel("");
        invalidUserPass.setFont(new Font("Arial", Font.PLAIN, 10));
        invalidUserPass.setBounds(50, 180, 249, 20);
        invalidUserPass.setForeground(Color.red);
        invalidUserPass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        invalidUserPass.setHorizontalAlignment(CENTER);
    }
    
    //Third panel for registering an account to use for logging in
    public void RegisterPage() {
        registerP = new JPanel();
        registerP.setVisible(false);
        registerP.setLayout(null);
        registerP.setBackground(Color.WHITE);
        registerP.setBounds(50,65,350,570);
        registerP.setBorder(BorderFactory.createLineBorder(maroon, 2));
        
        registerSecondP = new JPanel();
        registerSecondP.setLayout(null);
        registerSecondP.setBackground(maroon);
        registerSecondP.setBounds(0,0,350,20);
        
        closeRegisterBtn = new JButton("\u00d7");
        closeRegisterBtn.setFont(new Font("Arial", Font.PLAIN, 23));
        closeRegisterBtn.setBounds(333, 2, 15, 15);
        closeRegisterBtn.setForeground(Color.white);
        closeRegisterBtn.setBackground(maroon);
        closeRegisterBtn.setBorder(null);
        closeRegisterBtn.setFocusable(false);
        closeRegisterBtn.addActionListener(this);
        closeRegisterBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        firstNameLbl = new JLabel("First Name");
        firstNameLbl.setFont(arial);
        firstNameLbl.setBounds(45, 50, 108, 40);
        firstNameLbl.setForeground(Color.LIGHT_GRAY);
        firstNameLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        firstNameTF = new JTextField();
        firstNameTF.setFont(arial);
        firstNameTF.setBounds(30, 50, 138, 40);
        firstNameTF.setForeground(Color.black);
        firstNameTF.setBorder(tfBorder);
        firstNameTF.setOpaque(false);
        firstNameTF.addKeyListener(this);
        
        lastNameLbl = new JLabel("Last Name");
        lastNameLbl.setFont(arial);
        lastNameLbl.setBounds(197, 50, 108, 40);
        lastNameLbl.setForeground(Color.LIGHT_GRAY);
        lastNameLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        lastNameTF = new JTextField();
        lastNameTF.setFont(arial);
        lastNameTF.setBounds(182, 50, 138, 40);
        lastNameTF.setForeground(Color.black);
        lastNameTF.setBorder(tfBorder);
        lastNameTF.setOpaque(false);
        lastNameTF.addKeyListener(this);
        
        addressLbl = new JLabel("Address");
        addressLbl.setFont(arial);
        addressLbl.setBounds(45, 115, 255, 40);
        addressLbl.setForeground(Color.LIGHT_GRAY);
        addressLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        addressTF = new JTextField();
        addressTF.setFont(arial);
        addressTF.setBounds(30, 115, 290, 40);
        addressTF.setForeground(Color.black);
        addressTF.setBorder(tfBorder);
        addressTF.setOpaque(false);
        addressTF.addKeyListener(this);
        
        emailLbl = new JLabel("Email");
        emailLbl.setFont(arial);
        emailLbl.setBounds(45, 180, 255, 40);
        emailLbl.setForeground(Color.LIGHT_GRAY);
        emailLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        emailTF = new JTextField();
        emailTF.setFont(arial);
        emailTF.setBounds(30, 180, 290, 40);
        emailTF.setForeground(Color.black);
        emailTF.setBorder(tfBorder);
        emailTF.setOpaque(false);
        emailTF.addKeyListener(this);
        
        phoneNumberLbl = new JLabel("Phone Number");
        phoneNumberLbl.setFont(arial);
        phoneNumberLbl.setBounds(45, 245, 255, 40);
        phoneNumberLbl.setForeground(Color.LIGHT_GRAY);
        phoneNumberLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        phoneNumberTF = new JTextField();
        phoneNumberTF.setFont(arial);
        phoneNumberTF.setBounds(30, 245, 290, 40);
        phoneNumberTF.setForeground(Color.black);
        phoneNumberTF.setBorder(tfBorder);
        phoneNumberTF.setOpaque(false);
        phoneNumberTF.addKeyListener(this);
        
        userRegisterLbl = new JLabel("Username");
        userRegisterLbl.setFont(arial);
        userRegisterLbl.setBounds(45, 310, 255, 40);
        userRegisterLbl.setForeground(Color.LIGHT_GRAY);
        userRegisterLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        userRegisterTF = new JTextField();
        userRegisterTF.setFont(arial);
        userRegisterTF.setBounds(30, 310, 290, 40);
        userRegisterTF.setForeground(Color.black);
        userRegisterTF.setBorder(tfBorder);
        userRegisterTF.setOpaque(false);
        userRegisterTF.addKeyListener(this);
        
        passRegisterLbl = new JLabel("Password");
        passRegisterLbl.setFont(arial);
        passRegisterLbl.setBounds(45, 375, 255, 40);
        passRegisterLbl.setForeground(Color.LIGHT_GRAY);
        passRegisterLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        passRegisterTF = new JPasswordField();
        passRegisterTF.setFont(arial);
        passRegisterTF.setBounds(30, 375, 290, 40);
        passRegisterTF.setForeground(Color.black);
        passRegisterTF.setBorder(tfBorder);
        passRegisterTF.setOpaque(false);
        passRegisterTF.addKeyListener(this);
        
        checkPassIconLbl2 = new JLabel();
        checkPassIconLbl2.setFont(arial);
        checkPassIconLbl2.setBounds(290, 385, 20, 20);
        checkPassIconLbl2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkPassIconLbl2.setIcon(scaledCloseEye);
        
        checkPassRegisterCB = new JCheckBox("");
        checkPassRegisterCB.setFont(new Font("Arial", Font.PLAIN, 10));
        checkPassRegisterCB.setBounds(290, 385, 20, 20);
        checkPassRegisterCB.setForeground(Color.black);
        checkPassRegisterCB.setOpaque(false);
        checkPassRegisterCB.setFocusable(false);
        checkPassRegisterCB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkPassRegisterCB.addActionListener(this);
        
        confirmPassLbl = new JLabel("Confirm Password");
        confirmPassLbl.setFont(arial);
        confirmPassLbl.setBounds(45, 440, 255, 40);
        confirmPassLbl.setForeground(Color.LIGHT_GRAY);
        confirmPassLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        confirmPassTF = new JPasswordField();
        confirmPassTF.setFont(arial);
        confirmPassTF.setBounds(30, 440, 290, 40);
        confirmPassTF.setForeground(Color.black);
        confirmPassTF.setBorder(tfBorder);
        confirmPassTF.setOpaque(false);
        confirmPassTF.addKeyListener(this);
        
        checkPassIconLbl3 = new JLabel();
        checkPassIconLbl3.setFont(arial);
        checkPassIconLbl3.setBounds(290, 450, 20, 20);
        checkPassIconLbl3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkPassIconLbl3.setIcon(scaledCloseEye);
        
        checkConfirmPassCB = new JCheckBox("");
        checkConfirmPassCB.setFont(new Font("Arial", Font.PLAIN, 10));
        checkConfirmPassCB.setBounds(290, 450, 20, 20);
        checkConfirmPassCB.setForeground(Color.black);
        checkConfirmPassCB.setOpaque(false);
        checkConfirmPassCB.setFocusable(false);
        checkConfirmPassCB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkConfirmPassCB.addActionListener(this);
        
        registerAccountBtn = new JButton("SIGN UP");
        registerAccountBtn.setFont(arial);
        registerAccountBtn.setBounds(115, 505, 120, 30);
        registerAccountBtn.setForeground(Color.black);
        registerAccountBtn.setBackground(Color.white);
        registerAccountBtn.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        registerAccountBtn.setFocusable(false);
        registerAccountBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerAccountBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                registerAccountBtn.setBackground(new Color(236, 236, 236));
            }
            public void mouseExited(MouseEvent e) {
                registerAccountBtn.setBackground(Color.white);
            }
        });
        registerAccountBtn.addActionListener(this);
    }
    
    //ActionEvents for each buttons and checkboxes
    @Override
    public void actionPerformed(ActionEvent e) {
        //For showing the login panel; While it is open, directly closing the program is disabled
        if(e.getSource()==accountBtn) {
            loginP.setVisible(true);
            loginShadowP.setVisible(true);
            accountBtn.setEnabled(false);
            exitBtn.setEnabled(false);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            //for the background of accountBtn to not overlap as it ends up in mouseEntered state
            accountBtn.setFocusable(false);
            accountBtn.setContentAreaFilled(false);
            accountBtn.setForeground(maroon);
            //for disabling the mouseListener of accountBtn and exitBtn
            enabled = false;
        }
        
        //For exitting the program
        if(e.getSource()==exitBtn) {
            System.exit(0);
        }
        
        //For closing the login panel, and erasing the input of user in username and password; When it is close, directly closing the program is enabled
        if(e.getSource()==closeLoginBtn) {
            accountBtn.setEnabled(true);
            exitBtn.setEnabled(true);
            loginP.setVisible(false);
            loginShadowP.setVisible(false);
            userLoginTF.setText("");
            userLoginLbl.setText("Username");
            userLoginTF.setBorder(tfBorder);
            passLoginTF.setText("");
            passLoginLbl.setText("Password");
            passLoginTF.setBorder(tfBorder);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            //for enabling the mouseListener of accountBtn and exitBtn
            enabled = true;
        }
        
        //For checkPassLoginCB checkbox
        if(checkPassLoginCB.isSelected()) {
            //For showing the hidden password
            passLoginTF.setEchoChar((char)0);
            checkPassIconLbl1.setIcon(scaledOpenEye);
        } else {
            //For hiding the password once again
            passLoginTF.setEchoChar('\u2022');
            checkPassIconLbl1.setIcon(scaledCloseEye);
        }
        
        //For loggin in to the delivery program
        if(e.getSource()==loginBtn) {
            //For getting the input of user from username and password textfields
            username = userLoginTF.getText();
            password = passLoginTF.getText();
            
            //For connecting and adding values to the database
            try {
                //For connecting to the database
                
                //"jdbc:mysql://[host address]/[database_name], [user], [password]
                Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
                
                //The query for the database
                PreparedStatement st = (PreparedStatement) c.prepareStatement("SELECT username, password FROM account_profile WHERE username=? and password=?");
                
                //The data needed to compare in database
                st.setString(1, username); 
                st.setString(2, password);
                
                //For executing the query above
                ResultSet rs = st.executeQuery();
               
                //adminUser and adminPass are only temporary
                //If TRUE, logs in to the program, and dispose this current program
                if (rs.next()) {
                    JOptionPane.showMessageDialog(loginP, "You have successfully logged in.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    
                    dispose();
                    new MenuSelection(username);
                //If FALSE, highlights the border of username and password textfields
                } else {
                    JOptionPane.showMessageDialog(loginP, "Invalid Username or Password.", "Error!", JOptionPane.ERROR_MESSAGE);
                    
                    if(username.equals(""))
                        userLoginTF.setBorder(tfErrorBorder);
                    if(password.equals(""))
                        passLoginTF.setBorder(tfErrorBorder);
                    
                    if(!username.equals("") && !password.equals(""))
                        passLoginLbl.setText("Password");
                        passLoginTF.setText("");
                        passLoginTF.setBorder(tfErrorBorder);
                        userLoginTF.setBorder(tfErrorBorder);
                }
            //In case of error, this catches the error and tell the programmer the specific error
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        
        //For going to the register panel, and erasing the trace of inputs from user
        if(e.getSource()==createBtn) {
            loginP.setVisible(false);
            loginShadowP.setVisible(false);
            userLoginTF.setText("");
            userLoginLbl.setText("Username");
            userLoginTF.setBorder(tfBorder);
            passLoginTF.setText("");
            passLoginLbl.setText("Password");
            passLoginTF.setBorder(tfBorder);
            registerP.setVisible(true);
            registerShadowP.setVisible(true);
        }
        
        //For closing the register panel, and erasing the inputs from user in all textfields
        if(e.getSource()==closeRegisterBtn) {
            registerP.setVisible(false);
            registerShadowP.setVisible(false);
            loginP.setVisible(true);
            loginShadowP.setVisible(true);
            firstNameLbl.setText("First Name");
            firstNameTF.setText("");
            firstNameTF.setBorder(tfBorder);
            lastNameLbl.setText("Last Name");
            lastNameTF.setText("");
            lastNameTF.setBorder(tfBorder);
            addressLbl.setText("Address");
            addressTF.setText("");
            addressTF.setBorder(tfBorder);
            emailLbl.setText("Email");
            emailTF.setText("");
            emailTF.setBorder(tfBorder);
            phoneNumberLbl.setText("Phone Number");
            phoneNumberTF.setText("");
            phoneNumberTF.setBorder(tfBorder);
            userRegisterLbl.setText("Username");
            userRegisterTF.setText("");
            userRegisterTF.setBorder(tfBorder);
            passRegisterLbl.setText("Password");
            passRegisterTF.setText("");
            passRegisterTF.setBorder(tfBorder);
            confirmPassLbl.setText("Confirm Password");
            confirmPassTF.setText("");
            confirmPassTF.setBorder(tfBorder);
        }
        
        //Both checkPassRegisterCB and checkConfirmPassCB are for checking the hidden password, and hiding the password once again
        if(checkPassRegisterCB.isSelected()) {
            passRegisterTF.setEchoChar((char)0);
            checkPassIconLbl2.setIcon(scaledOpenEye);
        } else {
            passRegisterTF.setEchoChar('\u2022');
            checkPassIconLbl2.setIcon(scaledCloseEye);
        }
        
        if(checkConfirmPassCB.isSelected()) {
            confirmPassTF.setEchoChar((char)0);
            checkPassIconLbl3.setIcon(scaledOpenEye);
        } else {
            confirmPassTF.setEchoChar('\u2022');
            checkPassIconLbl3.setIcon(scaledCloseEye);
        }
        
        //For getting the data from all inputs and insert it into the database
        if(e.getSource()==registerAccountBtn) {
            //All the data that will be used for conditions, and put into the database
            String firstName = firstNameTF.getText().trim();
            String lastName = lastNameTF.getText().trim();
            String address = addressTF.getText().trim();
            String email = emailTF.getText().trim();
            String phoneNumber = phoneNumberTF.getText().trim();
            String username = userRegisterTF.getText().trim();
            String password = passRegisterTF.getText().trim();
            String confirmPass = confirmPassTF.getText().trim();
            
            //For condition in the case that the address is not complete
            int commaCount = 0;
            for(int i=0; i<address.length(); i++) {
                if(address.charAt(i)==',') {
                    commaCount++;
                }
            }
            
            //For getting the initials of first and last name, and for adding uniqueness to customer_id in database
            String initials = "", firstNameUpper = firstName.toUpperCase();
            initials += firstNameUpper.charAt(0);
            if(firstName.contains(" ")) {
                int index = firstNameUpper.indexOf(" ");
                initials += firstNameUpper.charAt(index+1);
            }
            initials += lastName.toUpperCase().charAt(0);
            
            //For connecting and adding values to the database
            try {
                Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
                
                //The prepared query for inserting values into the database
                PreparedStatement st = (PreparedStatement) c.prepareStatement("INSERT INTO account_profile VALUES(CONCAT((SELECT COUNT(*)+1 FROM (SELECT * FROM account_profile) AS tmp),'" + initials + "'),'" + firstName + "','" + lastName + "','" + address + ", Laguna','" + email + "','" + phoneNumber + "','" + username + "','" + password + "')");
                
                //If TRUE, highlights that empty textfields that needs to be filled with text
                if(firstName.equals("") || lastName.equals("") || address.equals("") || email.equals("") || phoneNumber.equals("") || username.equals("") || password.equals("")) {
                    
                    JOptionPane.showMessageDialog(registerP, "Invalid! Please fill out the empty fields.", "Error!", JOptionPane.ERROR_MESSAGE);
                    
                    if(firstName.equals(""))
                        firstNameTF.setBorder(tfErrorBorder);
                    if(lastName.equals(""))
                        lastNameTF.setBorder(tfErrorBorder);
                    if(email.equals(""))
                        addressTF.setBorder(tfErrorBorder);
                    if(email.equals(""))
                        emailTF.setBorder(tfErrorBorder);
                    if(phoneNumber.equals(""))
                        phoneNumberTF.setBorder(tfErrorBorder);
                    if(username.equals(""))
                        userRegisterTF.setBorder(tfErrorBorder);
                    if(password.equals(""))
                        passRegisterTF.setBorder(tfErrorBorder);
                    if(confirmPass.equals(""))
                        confirmPassTF.setBorder(tfErrorBorder);
                //if TRUE, asks the user to follow the format "House #, Street, Brgy, Town/City"
                } else if(commaCount!=3) {
                    JOptionPane.showMessageDialog(registerP, "Invalid! Please follow the address format.", "Error!", JOptionPane.ERROR_MESSAGE);
                    addressTF.setBorder(tfErrorBorder);
                    addressTF.setText("");
                    addressLbl.setText("House #, Street, Brgy, Town/City");
                    
                //If TRUE, asks the user to correct the email; For needing '@' and '.com' as in for correct email
                } else if(!email.contains("@") || !email.contains(".com")) {
                    JOptionPane.showMessageDialog(registerP, "Invalid! Please correct your email.", "Error!", JOptionPane.ERROR_MESSAGE);
                    emailTF.setBorder(tfErrorBorder);
                    
                //If TRUE, asks that user for correct 11-characters Philippines' contact number
                } else if(!phoneNumber.startsWith("09") || phoneNumber.length()!=11) {
                    JOptionPane.showMessageDialog(registerP, "Invalid! Please correct your phone number.", "Error!", JOptionPane.ERROR_MESSAGE);
                    phoneNumberTF.setBorder(tfErrorBorder);
                    
                //If TRUE, asks that user to compare the password and confirm-password; Both password and confirm-password needs to be the same
                } else if(!confirmPass.equals(password)) {
                    JOptionPane.showMessageDialog(registerP, "Invalid! Please confirm your password.", "Error!", JOptionPane.ERROR_MESSAGE);
                    confirmPassTF.setBorder(tfErrorBorder);
                
                //If TRUE, it shows there is no more problems and is ready to insert all the data into the database
                } else {
                    //For running the prepared query above
                    int x = st.executeUpdate();
                    //x==1 means if True
                    if(x==1) {
                        JOptionPane.showMessageDialog(registerP, "You have successfully created your account.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        
                        registerP.setVisible(false);
                        registerShadowP.setVisible(false);
                        loginP.setVisible(true);
                        loginShadowP.setVisible(true);
                        firstNameLbl.setText("First Name");
                        firstNameTF.setText("");
                        lastNameLbl.setText("Last Name");
                        lastNameTF.setText("");
                        addressLbl.setText("Address");
                        addressTF.setText("");
                        emailLbl.setText("Email");
                        emailTF.setText("");
                        phoneNumberLbl.setText("Phone Number");
                        phoneNumberTF.setText("");
                        userRegisterLbl.setText("Username");
                        userRegisterTF.setText("");
                        passRegisterLbl.setText("Password");
                        passRegisterTF.setText("");
                        confirmPassLbl.setText("Confirm Password");
                        confirmPassTF.setText("");
                    }
                }
                
            //In case of error, this catches the error and tell the programmer the specific error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //This is a copy-paste from actionEvent of loginBtn, only copied for the reason so when user press Enter, it would do the same actionEvent
        if(e.getSource()==passLoginTF) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                //For getting the input of user from username and password textfields
                username = userLoginTF.getText();
                password = passLoginTF.getText();

                //For connecting and adding values to the database
                try {
                    //For connecting to the database

                    //"jdbc:mysql://[host address]/[database_name], [user], [password]
                    Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");

                    //The query for the database
                    PreparedStatement st = (PreparedStatement) c.prepareStatement("SELECT username, password FROM account_profile WHERE username=? and password=?");

                    //The data needed to compare in database
                    st.setString(1, username); 
                    st.setString(2, password);

                    //For executing the query above
                    ResultSet rs = st.executeQuery();

                    //adminUser and adminPass are only temporary
                    //If TRUE, logs in to the program, and dispose this current program
                    if (rs.next()) {
                        loginBtn.setBackground(new Color(184,207,229));
                        
                        JOptionPane.showMessageDialog(loginP, "You have successfully logged in.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        loginBtn.setBackground(Color.white);
                        dispose();
                        new MenuSelection(username);
                    //If FALSE, highlights the border of username and password textfields
                    } else {
                        loginBtn.setBackground(new Color(184,207,229));
                        
                        JOptionPane.showMessageDialog(loginP, "Invalid Username or Password.", "Error!", JOptionPane.ERROR_MESSAGE);
                        loginBtn.setBackground(Color.white);

                        if(username.equals(""))
                            userLoginTF.setBorder(tfErrorBorder);
                        if(password.equals(""))
                            passLoginTF.setBorder(tfErrorBorder);

                        if(!username.equals("") && !password.equals(""))
                            passLoginLbl.setText("Password");
                            passLoginTF.setText("");
                            passLoginTF.setBorder(tfErrorBorder);
                    }
                //In case of error, this catches the error and tell the programmer the specific error
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        //for addressTF, only for design purposes
        /*if(e.getSource()==addressTF) {
            String address = addressTF.getText();
            String space = "", concat;
            char character = e.getKeyChar();
            int commaCount = 0, charCount = 0;
            
            for(int i=0; i<address.length(); i++) {
                if(address.charAt(i)==',') {
                    commaCount++;
                }
                charCount++;
            }
            
            if(commaCount==0) {
                for(int i=0; i<charCount; i++) {
                    space += " ";
                }
                concat = ", Street, Brgy, Town/City";
                addressLbl.setText(space + concat);
            } else if(commaCount==1) {
                for(int i=0; i<charCount; i++) {
                    space += " ";
                }
                concat = ", Brgy, Town/City";
                addressLbl.setText(space + concat);
            } else if(commaCount==2) {
                for(int i=0; i<charCount; i++) {
                    space += " ";
                }
                concat = ", Town/City";
                addressLbl.setText(space + concat);
            } else if(commaCount==3) {
                for(int i=0; i<charCount; i++) {
                    space += " ";
                }
                concat = ", Laguna";
                addressLbl.setText(space + concat);
            }
            addressTF.setBorder(tfBorder);
        }*/
    }
    
    //KeyEvents for preventing specified characters in a textfield
    @Override
    public void keyTyped(KeyEvent e) {
        //To get each character that the user types
        char character = e.getKeyChar();
        
        //Both are for preventing 'space' and allowing 'backspace'
        if(e.getSource()==userLoginTF) {
            if(character==' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        } else if(e.getSource()==passLoginTF) {
            if(character==' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        }
        
        //Both firstNameTF and lastNameTF are for preventing other characters other than A-Z, a-z, 'space', and 'backspace'
        if(e.getSource()==firstNameTF) {
            if((character<'A' || character>'Z') && (character<'a' || character>'z') && character!=' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        } else if(e.getSource()==lastNameTF) {
            if((character<'A' || character>'Z') && (character<'a' || character>'z') && character!=' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        //For preventing 'space' and allowing 'backspace'
        } else if(e.getSource()==emailTF) {
            if(character==' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        //For preventing characters other than 0-9 and allowing 'backspace'; AND for only accepting 11 characters
        } else if(e.getSource()==phoneNumberTF) {
            int length = phoneNumberTF.getText().length();
            if((character<'0' || character>'9' || length==11) && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        //All userRegisterTF, passRegisterTF, and confirmPassTF are for preventing 'space' and allowing 'backspace'
        } else if(e.getSource()==userRegisterTF) {
            if(character==' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        } else if(e.getSource()==passRegisterTF) {
            if(character==' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        } else if(e.getSource()==confirmPassTF) {
            if(character==' ' && character!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        }
    }
    
    //KeyEvents for labels over textfields; AND for design only
    @Override
    public void keyReleased(KeyEvent e) {
        
        //All of these are for when textfields are empty, it sets a label a text; Otherwise, when textfields are not empty, it erases the labels
        
        //For login panel: username
        if(e.getSource()==userLoginTF) {
            userLoginLbl.setText("");
            userLoginTF.setBorder(tfBorder);
            if(userLoginTF.getText().equals("")) {
                userLoginLbl.setText("Username");
            }
        //For login panel: password
        } else if(e.getSource()==passLoginTF) {
            passLoginLbl.setText("");
            userLoginTF.setBorder(tfBorder);
            passLoginTF.setBorder(tfBorder);
            if(passLoginTF.getText().equals("")) {
                passLoginLbl.setText("Password");
            }
        }
        //For register panel: first name
        if(e.getSource()==firstNameTF) {
            firstNameLbl.setText("");
            firstNameTF.setBorder(tfBorder);
            if(firstNameTF.getText().equals("")) {
                firstNameLbl.setText("First Name");
            }
        //For register panel: last name
        } else if(e.getSource()==lastNameTF) {
            lastNameLbl.setText("");
            lastNameTF.setBorder(tfBorder);
            if(lastNameTF.getText().equals("")) {
                lastNameLbl.setText("Last Name");
            }
        //For register panel: address
        } else if(e.getSource()==addressTF) {
            addressLbl.setText("");
            addressTF.setBorder(tfBorder);
            if(addressTF.getText().equals("")) {
                addressLbl.setText("House #, Street, Brgy, Town/City");
            }
        //For register panel: email
        } else if(e.getSource()==emailTF) {
            emailLbl.setText("");
            emailTF.setBorder(tfBorder);
            if(emailTF.getText().equals("")) {
                emailLbl.setText("Email");
            }
        //For register panel: phone number
        } else if(e.getSource()==phoneNumberTF) {
            phoneNumberLbl.setText("");
            phoneNumberTF.setBorder(tfBorder);
            if(phoneNumberTF.getText().equals("")) {
                phoneNumberLbl.setText("Phone Number");
            }
        //For register panel: username
        } else if(e.getSource()==userRegisterTF) {
            userRegisterLbl.setText("");
            userRegisterTF.setBorder(tfBorder);
            if(userRegisterTF.getText().equals("")) {
                userRegisterLbl.setText("Username");
            }
        //For register panel: password
        } else if(e.getSource()==passRegisterTF) {
            passRegisterLbl.setText("");
            passRegisterTF.setBorder(tfBorder);
            if(passRegisterTF.getText().equals("")) {
                passRegisterLbl.setText("Password");
            }
        //For register panel: confirm password
        } else if(e.getSource()==confirmPassTF) {
            confirmPassLbl.setText("");
            confirmPassTF.setBorder(tfBorder);
            if(confirmPassTF.getText().equals("")) {
                confirmPassLbl.setText("Confirm Password");
            }
        }
    }
    
}
