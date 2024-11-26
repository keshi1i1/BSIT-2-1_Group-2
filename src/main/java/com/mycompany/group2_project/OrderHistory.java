/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group2_project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class OrderHistory extends JFrame implements MouseListener, ActionListener {
    
    private JPanel panelHdr, panelBottom;
    private JLabel hdrText, hdrExit, lblPastOrders, lblDeliveryTimes;
    private JTable tbHistory;
    private JButton btnCurrent, btnPast, btnReview;
    private JScrollPane scrollPane;
    private String[] name = {"Restaurant", "Items"};
    private DefaultTableModel model;
    
    OrderHistory() {
        
        setSize(464, 737);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        
        //Creating Header
        Border hdrBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
        panelHdr = new JPanel();
        panelHdr.setBounds(0, 0, 450, 71);
        panelHdr.setBackground(new Color(113, 45, 59));
        panelHdr.setLayout(null);
        panelHdr.setBorder(hdrBorder);
        
        //Back button at the header
        hdrExit = new JLabel("\u2b9c");
        hdrExit.setBounds(25, 21, 25, 31);
        hdrExit.setForeground(Color.WHITE);
        hdrExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //Order label at the header
        hdrText = new JLabel("Orders");
        hdrText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        hdrText.setBounds(60, 23, 100, 30);
        hdrText.setForeground(Color.WHITE);
        
        //Button for Reviewing Orders
        btnReview = new JButton("Review Order");
        btnReview.setBounds(300, 85, 135, 24);
        btnReview.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
        
        //Delivery times label
        lblPastOrders = new JLabel("Delivery Times:");
        lblPastOrders.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        lblPastOrders.setBounds(25, 82, 150, 30);
        lblPastOrders.setForeground(Color.BLACK);
        
        //Delivery count
        lblDeliveryTimes = new JLabel("0");
        lblDeliveryTimes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        lblDeliveryTimes.setBounds(180, 82, 50, 30);
        lblDeliveryTimes.setForeground(Color.BLACK);
        
        //Bottom panel of the frame
        Border btmBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
        panelBottom = new JPanel();
        panelBottom.setBounds(0, 635, 450, 110);
        panelBottom.setBackground(new Color(113, 45, 59));
        panelBottom.setBorder(btmBorder);
        panelBottom.setLayout(null);
        
        //Button for the Current Orders tab
        btnCurrent = new JButton("Current Orders");
        btnCurrent.setBounds(70, 17, 150, 30);
        btnCurrent.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        
        //Button for the Past Orders tab
        btnPast = new JButton("Past Orders");
        btnPast.setBounds(230, 17, 150, 30);
        btnPast.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        btnPast.setEnabled(false);
        
        //Creating custom color for scrollpane border
        Color brColor = new Color(113, 45, 59);
        Border paneBorder = BorderFactory.createLineBorder(brColor, 28);
        
        //creating table with scrollpane
        model = new DefaultTableModel(name, 1);
        tbHistory = new JTable(model);
        scrollPane = new JScrollPane(tbHistory);
        scrollPane.setBounds(15, 125, 420, 490);
        scrollPane.setBorder(paneBorder);
        tbHistory.getColumnModel().getColumn(0).setPreferredWidth(35);
        tbHistory.setRowHeight(50);
        
        //mouse and action listeners
        hdrExit.addMouseListener(this);
        btnCurrent.addActionListener(this);
        
        //Adding Components
        add(panelHdr);
        panelHdr.add(hdrExit);
        panelHdr.add(hdrText);
        add(panelBottom);
        panelBottom.add(btnPast);
        panelBottom.add(btnCurrent);
        add(btnReview);
        add(lblPastOrders);
        add(lblDeliveryTimes);
        add(scrollPane);
        setVisible(true);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Function of the back button
        if(e.getSource() == hdrExit) {
            this.dispose();
            //new ProjectMain();
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
            //new DeliveryStatusMain();
        }
    }
}



