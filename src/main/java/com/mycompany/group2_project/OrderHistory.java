
package com.mycompany.group2_project;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class OrderHistory extends JFrame implements MouseListener, ActionListener {
    
    private JPanel panelHdr, panelBottom;
    private JLabel hdrText, hdrExit, hdrStatus;
    private JTable tbCom, tbCan;
    private JButton btnCurrent, btnPast, btnCompleted, btnCancelled;
    private JScrollPane spCom, spCan ;
    private String[] var = {"CustomerId", "Resto Id",  "OrderItems", "Status"};
    private String[] varTwo = {"CustomerId", "Resto Id",  "OrderItems", "Status"};
    private DefaultTableModel modelOne, modelTwo;
    private ImageIcon imgLogo;
    private Color brColor = new Color(113, 45, 59);
    private Border paneBorder = BorderFactory.createLineBorder(brColor, 28);
    private String fName, lName, add, num;
    
    OrderHistory() {
        
        setSize(464, 737);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        imgLogo = new ImageIcon("fordaFood.png");
        setIconImage(imgLogo.getImage());
        setTitle("Order History");
        
        //Creating Header
        panelHdr = new JPanel();
        panelHdr.setBounds(0, 0, 450, 70);
        panelHdr.setBackground(new Color(113, 45, 59));
        panelHdr.setLayout(null);
        
        //Back button at the header
        hdrExit = new JLabel("\u2b9c");
        hdrExit.setBounds(25, 21, 25, 31);
        hdrExit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
        hdrExit.setForeground(Color.WHITE);
        hdrExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //Order label at the header
        hdrText = new JLabel("ORDER HISTORY: ");
        hdrText.setFont(new Font("arial", Font.BOLD, 20));
        hdrText.setBounds(60, 23, 180, 30);
        hdrText.setForeground(Color.WHITE);
        
        //Status at the header
        hdrStatus = new JLabel("COMPLETED");
        hdrStatus.setFont(new Font("arial", Font.BOLD, 20));
        hdrStatus.setBounds(240, 23, 150, 30);
        hdrStatus.setForeground(Color.WHITE);
        
        //Button for Completed Orders Tab
        btnCompleted = new JButton("Completed Orders");
        btnCompleted.setBounds(60, 82, 160, 30);
        btnCompleted.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 11));
        btnCompleted.setEnabled(false);
        
        //Button for Cancelled Orders Tab
        btnCancelled = new JButton("Cancelled Orders");
        btnCancelled.setBounds(240, 82, 160, 30);
        btnCancelled.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 11));
        
        //Bottom panel of the frame
        panelBottom = new JPanel();
        panelBottom.setBounds(0, 630, 450, 70);
        panelBottom.setBackground(new Color(113, 45, 59));
        panelBottom.setLayout(null);
        
        //Button for the Current Orders tab
        btnCurrent = new JButton("Current Orders");
        btnCurrent.setBounds(70, 17, 150, 30);
        btnCurrent.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
        
        //Button for the Past Orders tab
        btnPast = new JButton("Past Orders");
        btnPast.setBounds(230, 17, 150, 30);
        btnPast.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
        btnPast.setEnabled(false);
        
        //Creating table for the Completed Orders
        modelOne = new DefaultTableModel(var, 0);
        tbCom = new JTable(modelOne);
        spCom = new JScrollPane(tbCom);
        spCom.setBounds(15, 125, 420, 490);
        tbCom.setFont(new Font("Arial", Font.PLAIN, 10));
        spCom.setBorder(paneBorder);
        tbCom.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbCom.getColumnModel().getColumn(1).setPreferredWidth(15);
        tbCom.getColumnModel().getColumn(3).setPreferredWidth(10);
        tbCom.setRowHeight(40);
        
        modelTwo = new DefaultTableModel();
        tbCan = new JTable(modelTwo);
        
        //mouse and action listeners
        hdrExit.addMouseListener(this);
        tbCom.addMouseListener(this);
        tbCan.addMouseListener(this);
        btnCurrent.addActionListener(this);
        btnCompleted.addActionListener(this);
        btnCancelled.addActionListener(this);
        
        //Adding Components
        add(panelHdr);
        panelHdr.add(hdrExit);
        panelHdr.add(hdrText);
        panelHdr.add(hdrStatus);
        add(panelBottom);
        panelBottom.add(btnPast);
        panelBottom.add(btnCurrent);
        add(btnCompleted);
        add(btnCancelled);
        add(spCom);
        setVisible(true);
        
        dataBase();
        databaseConnection();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Function of the back button
        if(e.getSource() == hdrExit) {
            this.dispose();
            //new CustomerProfile();
        } else if(e.getSource() == tbCom) {
            //For more info
            int selectedRow = tbCom.getSelectedRow();
            if(selectedRow != -1) {
                String value = modelOne.getValueAt(selectedRow, 2).toString();
                String items = value.replaceAll("," , "\n");
                JOptionPane.showMessageDialog(this, "Name: " + fName + " " + lName + "\nPhone Number: " + num + "\nAddress: "+ add + "\n" + 
                        "-----------------------------------------------------------------------------------\n" + items + 
                        "-----------------------------------------------------------------------------------\nFROM: FordaFOOD", "Order Info", JOptionPane.PLAIN_MESSAGE);
            }
        } else if(e.getSource() == tbCan) {
            //For more info
            int selectedRow = tbCan.getSelectedRow();
            if(selectedRow != -1) {
                String value = modelTwo.getValueAt(selectedRow, 2).toString();
                String items = value.replaceAll("," , "\n");
                JOptionPane.showMessageDialog(this, "Name: " + fName + " " + lName + "\nPhone Number: " + num + "\nAddress: "+ add + "\n" + 
                        "-----------------------------------------------------------------------------------\n" + items + 
                        "-----------------------------------------------------------------------------------\nFROM: FordaFOOD", "Order Info", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    
    //di nag rrun pag tinanggal ko 
    //di ko sila ginagamit
    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Function for the Current Orders tab
        if(e.getSource() == btnCurrent) {
            this.dispose();
            //new DeliveryStatus();
        } else if(e.getSource() == btnCancelled) {
            //Function for the Cancelled Tab Button 
            spCom.setVisible(false);
            btnCancelled.setEnabled(false);
            btnCompleted.setEnabled(true);
            hdrStatus.setText("CANCELLED");
            
            //Creating another Table for the Cancelled Orders
            modelTwo = new DefaultTableModel(varTwo, 0);
            tbCan = new JTable(modelTwo);
            spCan = new JScrollPane(tbCan);
            spCan.setBounds(15, 125, 420, 490);
            tbCan.setFont(new Font("Arial", Font.PLAIN, 10));
            spCan.setBorder(paneBorder);
            tbCan.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbCan.getColumnModel().getColumn(1).setPreferredWidth(15);
            tbCan.getColumnModel().getColumn(3).setPreferredWidth(10);
            tbCan.setRowHeight(40);
            add(spCan);
            
            tbCan.addMouseListener(this);
            databaseConnection();
            
                 
        } else if (e.getSource() == btnCompleted) {
            //Function for the Cancelled Tab Button
            btnCompleted.setEnabled(false);
            btnCancelled.setEnabled(true);
            hdrStatus.setText("COMPLETED");
            
            spCan.setVisible(false);
            spCom.setVisible(true);
        }
    }
    //Database for Order Data
    public void databaseConnection() {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
            modelOne.setRowCount(0);
            PreparedStatement st = c.prepareStatement("SELECT * FROM order_data WHERE status = 'RECEIVED'");
            ResultSet receivedRes = st.executeQuery();
            
            while (receivedRes.next()) {
                String customerId = receivedRes.getString("customerId");
                String resto = receivedRes.getString("restoId");
                String orderId = receivedRes.getString("orderItems");
                String status = receivedRes.getString("status");
                Object[] variable = {customerId, resto, orderId, status};
                modelOne.addRow(variable);
            } 
            
            PreparedStatement stCancel = c.prepareStatement("SELECT * FROM order_data WHERE status = 'CANCELLED'");
            ResultSet cancelledRes = stCancel.executeQuery();
            
            while (cancelledRes.next()) {
                String customerId = cancelledRes.getString("customerId");
                String resto = cancelledRes.getString("restoId");
                String orderId = cancelledRes.getString("orderItems");
                String status = cancelledRes.getString("status");
                Object[] variable = {customerId, resto, orderId, status};
                modelTwo.addRow(variable);
            }
            
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    } 
    //Database for Account info
    public void dataBase() {
        try {
            
            String customerId = "1JVR";
            
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
            PreparedStatement st = c.prepareStatement("SELECT first_name, last_name, address, phone_number FROM account_profile WHERE customer_id=?");
            st.setString(1, customerId);
            ResultSet customerRes = st.executeQuery();
            
            while (customerRes.next()) {
                fName = customerRes.getString("first_name");
                lName = customerRes.getString("last_name");
                add = customerRes.getString("address");
                num = customerRes.getString("phone_number");
                
            }
            
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
