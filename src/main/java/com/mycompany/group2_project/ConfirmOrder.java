/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group2_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Alfred Gualberto
 */
public class ConfirmOrder extends JFrame {
    
    private JScrollPane spCartConfirm;
    private JList<String> listCartConfirm;
    private DefaultListModel<String> dlmCartConfirm;
    private JButton btnConfirm, btnCancel;
    private JLabel lblAddress, lblCost, lblCostShow, lblContact, lblContactNum, lblCart;
    private JTextArea txtaAddressSet;
    private ImageIcon imgLogo;
    
    ConfirmOrder(OrderSelection parent) {
        //disable parent frame
        parent.setEnabled(false);
        //updating the address
        String address = parent.txtfBLSt.getText().concat("\nBrgy "+parent.brgyInput).concat(", "+parent.cityInput+" City");
        //modify main frame
        setLocation(536, 210);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Confirm Order");
        setSize(464,407);
        //set Logo
        imgLogo = new ImageIcon("Image.png");
        setIconImage(imgLogo.getImage());
        
        //displaying the cart
        lblCart = new JLabel("Cart");
        lblCart.setFont(new Font("Sherif", Font.BOLD, 14));
        lblCart.setBounds(10,0,430,20);
        add(lblCart);
        
        spCartConfirm = new JScrollPane();
        dlmCartConfirm = new DefaultListModel<>();
        listCartConfirm = new JList<>(dlmCartConfirm);
        listCartConfirm.setFont(new Font("Sherif", Font.BOLD, 15));
        
        spCartConfirm = new JScrollPane(listCartConfirm);
        spCartConfirm.setFocusable(false);
        spCartConfirm.setBounds(10,25,430,170);
        add(spCartConfirm);
        
        //displaying address
        lblAddress = new JLabel("Address: ");
        lblAddress.setBounds(10,195,100,30);
        lblAddress.setFont(new Font("Sherif", Font.BOLD, 14));
        add(lblAddress);
        
        txtaAddressSet = new JTextArea(address);
        txtaAddressSet.setBackground(new Color(238,238,238));
        txtaAddressSet.setEditable(false);
        txtaAddressSet.setFocusable(false);
        txtaAddressSet.setBounds(15,220,420,40);
        txtaAddressSet.setFont(new Font("Sherif", Font.PLAIN, 14));
        add(txtaAddressSet);
        
        //displaying contact number
        lblContact = new JLabel("Contact #:");
        lblContact.setFont(new Font("Sherif", Font.BOLD, 14));
        lblContact.setBounds(10, 260, 100, 30);
        add(lblContact);
        
        lblContactNum = new JLabel(parent.lblNum.getText());
        lblContactNum.setFont(new Font("Sherif", Font.PLAIN, 14));
        lblContactNum.setBounds(15, 280, 100, 30);
        add(lblContactNum);
        
        //displaying total cost
        lblCost = new JLabel("Total Cost:");
        lblCost.setFont(new Font("Sherif", Font.BOLD, 14));
        lblCost.setBounds(290, 260, 100, 30);
        add(lblCost);
        
        lblCostShow = new JLabel("₱ "+String.valueOf(parent.priceTotal));
        lblCostShow.setFont(new Font("Sherif", Font.PLAIN, 14));
        lblCostShow.setBounds(300, 280, 100, 30);
        add(lblCostShow);
        
        //buttons
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(230, 320, 210, 40);
        add(btnCancel);
        
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(10, 320, 210, 40);
        add(btnConfirm);
        
        //display items in the cart
        for(int i=0;i<parent.dlmItemCart.getSize();i++){
            dlmCartConfirm.addElement(parent.dlmItemCart.getElementAt(i));
        }
        
        //action listener
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setEnabled(true);
                dispose(); 
            }
        });
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmChoice = JOptionPane.showConfirmDialog(parent, "Do you want to check your order?", "Order Checker", JOptionPane.YES_NO_OPTION);
                if (confirmChoice == JOptionPane.YES_OPTION) {
                    dispose();
                    parent.dispose();
                    new DeliveryStatus();
                } else if (confirmChoice == JOptionPane.NO_OPTION) {
                    parent.priceTotal = 0;
                    parent.lblTotal.setText("Total Cost:                       ₱0");
                    parent.setEnabled(true);
                    parent.txtfBLSt.setText("");
                    parent.cmbCity.setSelectedIndex(0);
                    parent.dlmItemCart.removeAllElements();
                    dispose();
                }
                
            }
        });
        
        setVisible(true);

    }
}
