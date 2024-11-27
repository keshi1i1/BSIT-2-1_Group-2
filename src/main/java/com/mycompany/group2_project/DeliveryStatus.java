/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group2_project;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




  public class DeliveryStatus extends JFrame implements MouseListener, ActionListener {
      
      private JButton pastBtn, currBtn, checkStatusBtn;
      private JLabel HdrTitle, backLabel, StatusLabel;
      private String OrderID = "12345", CustomerID = "678910", RestaurantID = "Local1", Order = "#0@YEQu1";
      private JPanel MidPanel, BtnPanel, ProgressPanel, BgPanel, HdrPanel, BtmPanel;
      private OrderStatus currentStatus;
      private JProgressBar ProgressBar;
      private JTable orderTable;
      private JScrollPane orderScrollPane;
      private ImageIcon LogoIcon;

    
      /*enum instead of string, based on my understanding
      because it is much more applicable in my part which is the status of deliveries
      it is much strict, you can't mess with the text it has to be one of these four */
      enum OrderStatus {
          PROCESSING, 
          ORDER_PLACED, 
          COMPLETE, 
          CANCELLED; 
    
     //so it can have space when it's being called out          
     @Override
     public String toString(){
         return name().replace("_", " ");
     }
      }
      DeliveryStatus(){
          // Icon logo for our project 
          LogoIcon = new ImageIcon("fordafood.png");
          // Delivery status
          currentStatus = OrderStatus.PROCESSING;
          // datas need for my table
          String[] idnames = {"Order ID", "Customer ID", "Restaurant ID", "Order", "Status"};
          Object[][] data = {
              {"#oRa1", "#19023", "J4L1b0T", "Order", currentStatus.toString()},
          };
              
          // Frame 
          setSize(464, 737);
          setIconImage(LogoIcon.getImage());
          setResizable(false);
          setLayout(null);
          setTitle("Processing Order");
          setLocationRelativeTo(null);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          // Wallpaper for my frame to make it look clean
          BgPanel = new JPanel();
          BgPanel.setLayout(null);
          BgPanel.setBounds(1, 1, 470, 740);
          BgPanel.setBackground(Color.WHITE);
          add(BgPanel);
          // Header panel, where the big title is located 
          HdrPanel = new JPanel();
          HdrPanel.setLayout(null);
          HdrPanel.setBounds(0, 0, 450, 75);
          HdrPanel.setBackground(new Color(113, 48, 59));
          BgPanel.add(HdrPanel);
          // Middle Panel, where the main part is
          MidPanel = new JPanel();
          MidPanel.setLayout(null);
          MidPanel.setBounds(12, 130, 425, 450);
          MidPanel.setBackground(new Color(113,48,59));
          BgPanel.add(MidPanel);
          // this is where you can see the status of the orders
          DefaultTableModel tableModel = new DefaultTableModel (data, idnames);
          orderTable = new JTable(tableModel);
          orderScrollPane = new JScrollPane(orderTable);
          orderScrollPane.setBounds(10, 10, 405, 370);
          MidPanel.add(orderScrollPane);
          // shows how close you are to getting your food
          ProgressBar = new JProgressBar(0, 100);
          ProgressBar.setValue(25);
          ProgressBar.setStringPainted(true);
          ProgressBar.setString("PLACED ORDERS");
          ProgressBar.setBounds(10, 390, 405, 40);
          MidPanel.add(ProgressBar);
          // This is what you can inside of the Header Panel
          HdrTitle = new JLabel("PROCESSING");
          HdrTitle.setFont(new Font("arial", Font.BOLD, 20));
          HdrTitle.setForeground(Color.WHITE);
          HdrTitle.setBounds(60, 23, 200, 30);
          HdrPanel.add(HdrTitle);
          // Where the buttons is located at
          BtmPanel = new JPanel();
          BtmPanel.setLayout(null);
          BtmPanel.setBounds(0, 630, 450, 110);
          BtmPanel.setBackground(new Color (113, 45, 59));
          BgPanel.add(BtmPanel);
          // Order Status
          StatusLabel = new JLabel("Delivery Status: " + currentStatus);
          StatusLabel.setBounds(10, 450, 405, 30);
          StatusLabel.setForeground(Color.WHITE);
          MidPanel.add(StatusLabel);
          // Think of this as your history of orders when you click it
          pastBtn = new JButton("Past Orders");
          pastBtn.setBounds(230, 17, 150, 30);
          pastBtn.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
          BtmPanel.add(pastBtn);
          // set as a enabled false
          currBtn = new JButton("Current Orders");
          currBtn.setBounds(70, 17, 150, 30);
          currBtn.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
          BtmPanel.add(currBtn);
          currBtn.setEnabled(false);
          // Buttons so you can see the status of your orders
          checkStatusBtn = new JButton("Check Delivery Status");
          checkStatusBtn.setBounds(145, 590, 170, 30);
          checkStatusBtn.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
          BgPanel.add(checkStatusBtn);
          //Back button it let you go to the previous frame before my frame
          backLabel = new JLabel("\u2b9c");
          backLabel.setBounds(25, 21, 50, 30);
          backLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
          backLabel.setForeground(Color.WHITE);
          HdrPanel.add(backLabel);
          

         // to make the buttons do stuff when you click them 
          pastBtn.addActionListener(this);
          currBtn.addActionListener(this);
          checkStatusBtn.addActionListener(this);
          checkStatusBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));  
          backLabel.addMouseListener(this);
          backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
          setVisible(true);
      }
      //For the back button beside the procesing label
        @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == backLabel){
            this.dispose();
        }
            
    }
      //if you remove this other 3 the mouse event will not work
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
      //this will update the status to show the current orders
     private void updateOrderTable(){
         DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
         tableModel.setValueAt(currentStatus.toString(), 0, 4);
      
     } 
     //This will showcase the 4 status depends on the order process
      private void updateProgressBar(){
        switch (currentStatus){
                case PROCESSING:
                    ProgressBar.setValue(25);
                    break;
               case ORDER_PLACED:
                   ProgressBar.setValue(50);
                   ProgressBar.setString("PROCESSING");
                   break;
               case COMPLETE:
                   ProgressBar.setValue(100);
                   break;
               case CANCELLED:
                   ProgressBar.setValue(0);
                   break;
                   
       }
    }
        //this is the method to what will happen if click the Past button and Current Button
         @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == checkStatusBtn){
            int stats = orderTable.getSelectedRow();
            currentStatus = OrderStatus.ORDER_PLACED;
            if(stats == -1){
               JOptionPane.showMessageDialog(this, "Please Select an Order First");
            }else {
                StatusLabel.setText("Delivery Status: " + currentStatus);
                updateOrderTable();
                updateProgressBar();
            }

       }
       if (e.getSource() == currBtn){
        
           currentStatus = OrderStatus.CANCELLED;
           StatusLabel.setText("Delivery Status: " + currentStatus);
           updateOrderTable();
           updateProgressBar();

    }
       if (e.getSource() == pastBtn){
             this.dispose();
             
             //new OrderHistoryMain()

           
       }

    }
   
}
