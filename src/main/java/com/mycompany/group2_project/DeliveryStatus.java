package com.mycompany.group2_project;

import javax.swing.*;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class DeliveryStatus extends JFrame implements MouseListener, ActionListener, ListSelectionListener {

    private JButton pastBtn, currBtn, cancelBtn, recBtn, infoBtn, refBtn;
    private JLabel HdrTitle, backLabel, StatusLabel;
    private JPanel MidPanel, BtnPanel, BgPanel, HdrPanel, BtmPanel;
    private JProgressBar ProgressBar;
    private JTable orderTable;
    private JScrollPane orderScrollPane;
    private ImageIcon LogoIcon;
    private Connection con;
    private ArrayList<String> rows;
    private DefaultTableModel df;
    
    DeliveryStatus() {
        // Icon logo for our project 
        LogoIcon = new ImageIcon("fordafood.png");
        Connect();
        

        // Frame setup
        setSize(464, 737);
        setIconImage(LogoIcon.getImage());
        setResizable(false);
        setLayout(null);
        setTitle("Delivery Status");
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

        // Title
        HdrTitle = new JLabel("ORDER STATUS");
        HdrTitle.setFont(new Font("arial", Font.BOLD, 20));
        HdrTitle.setForeground(Color.WHITE);
        HdrTitle.setBounds(60, 23, 200, 30);
        HdrPanel.add(HdrTitle);

        // Back button, it let you go to the previous frame before my frame
        backLabel = new JLabel("\u2b9c");
        backLabel.setBounds(25, 21, 50, 30);
        backLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
        backLabel.setForeground(Color.WHITE);
        HdrPanel.add(backLabel);
        backLabel.addMouseListener(this);

        // Middle Panel, where the main part is
        MidPanel = new JPanel();
        MidPanel.setLayout(null);
        MidPanel.setBounds(12, 130, 425, 450);
        MidPanel.setBackground(new Color(113, 48, 59));
        BgPanel.add(MidPanel);

        // This is where you can see the orders
        DefaultTableModel tableModel = new DefaultTableModel();
        orderTable = new JTable(tableModel);
        orderTable.setDefaultEditor(Object.class, null);
        orderScrollPane = new JScrollPane(orderTable);
        orderScrollPane.setBounds(10, 10, 405, 370);
        MidPanel.add(orderScrollPane);

        // Shows how close you are to getting your food
        ProgressBar = new JProgressBar(0, 100);
        ProgressBar.setValue(25);
        ProgressBar.setStringPainted(true);
        ProgressBar.setString("PROCESSING");
        ProgressBar.setBounds(10, 390, 405, 40);
        MidPanel.add(ProgressBar);

        // Status label
        StatusLabel = new JLabel("Delivery Status: PROCESSING");
        StatusLabel.setBounds(10, 450, 405, 30);
        StatusLabel.setForeground(Color.WHITE);
        MidPanel.add(StatusLabel);

        // Where the buttons is located at
        BtmPanel = new JPanel();
        BtmPanel.setLayout(null);
        BtmPanel.setBounds(0, 630, 450, 110);
        BtmPanel.setBackground(new Color(113, 45, 59));
        BgPanel.add(BtmPanel);

        // your history of orders when you click it
        pastBtn = new JButton("Past Orders");
        pastBtn.setBounds(230, 17, 150, 30);
        BtmPanel.add(pastBtn);

        currBtn = new JButton("Current Orders");
        currBtn.setBounds(70, 17, 150, 30);
        currBtn.setEnabled(false);
        BtmPanel.add(currBtn);

        cancelBtn = new JButton("Cancel Order");
        cancelBtn.setBounds(10, 590, 110, 30);
        cancelBtn.setEnabled(false);
        BgPanel.add(cancelBtn);

        recBtn = new JButton("Received");
        recBtn.setBounds(340, 590, 100, 30);
        recBtn.setEnabled(false);
        BgPanel.add(recBtn);

        infoBtn = new JButton("More Info");
        infoBtn.setBounds(235, 590, 90, 30);
        BgPanel.add(infoBtn);
        
        refBtn = new JButton("Refresh");
        refBtn.setBounds(135, 590, 90, 30);
        BgPanel.add(refBtn);
        
        
        // Button actions
        pastBtn.addActionListener(this);
        pastBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        currBtn.addActionListener(this);
        currBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        cancelBtn.addActionListener(this);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        recBtn.addActionListener(this);
        recBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        infoBtn.addActionListener(this);
        infoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        refBtn.addActionListener(this);
        refBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        orderTable.getSelectionModel().addListSelectionListener(this);

        dataElements(); 
          
        setVisible(true);
    }

    public void Connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dataElements() {
        df = (DefaultTableModel) orderTable.getModel();
        df.setColumnIdentifiers(new String[]{"Order ID", "Customer ID", "Restaurant ID", "Order", "Status"});

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM order_data WHERE status = 'PROCESSING' OR status = 'IN TRANSIT'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String orderId = rs.getString("orderId");
                String customerId = rs.getString("customerId");
                String restaurantId = rs.getString("restoId");
                String orderItems = rs.getString("orderItems");
                String status = rs.getString("status").toUpperCase();
                
               

                df.addRow(new Object[]{orderId, customerId, restaurantId, orderItems, status});

                int rowIndex = df.getRowCount() - 1;

            if ("PROCESSING".equals(status)) {
                    Timer processingTimer = new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String updateSql = "UPDATE order_data SET status = 'IN TRANSIT' WHERE orderId = ? and status='PROCESSING'";
                                PreparedStatement updatePst = con.prepareStatement(updateSql);
                                updatePst.setString(1, orderId);
                                updatePst.executeUpdate();
                                for(int i=0; i<df.getRowCount(); i++) {
                                String value = (String) df.getValueAt(i, 4);
                                    if(value.equals("PROCESSING")) {
                                    df.setValueAt("IN TRANSIT", i, 4);
            }
        }     
                                
                            if (orderTable.getSelectedRow() == rowIndex){
                                updateProgressBarFromStatus("IN TRANSIT");
                                cancelBtn.setEnabled(false);
                                
                            }
                            sortPriorityQueue();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    processingTimer.setRepeats(false);
                    processingTimer.start();
                    
            }
                     }              
                  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
    }
    
 

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow != -1) {
                String status = orderTable.getValueAt(selectedRow, 4).toString().toUpperCase();
             
                updateProgressBarFromStatus(status);
                
                if ("PROCESSING".equals(status)) {
                    cancelBtn.setEnabled(true);
                } else {
                    cancelBtn.setEnabled(false);
                }
                
                if ("IN TRANSIT".equals(status)){
                    recBtn.setEnabled(true);
                } else {
                    recBtn.setEnabled(false);
                }
            }
        }
    }
    
    private void updateProgressBarFromStatus(String status) {
        switch (status){
            case "PROCESSING":
                ProgressBar.setValue(25);
                ProgressBar.setString("PROCESSING");
                StatusLabel.setText("Delivery Status: PROCESSING");
                break;
            case "IN TRANSIT":
                ProgressBar.setValue(50);
                ProgressBar.setString("IN TRANSIT");
                StatusLabel.setText("Delivery Status: IN TRANSIT");   
                break;
            case "RECEIVED":
                ProgressBar.setValue(100);
                ProgressBar.setString("RECEIVED");
                StatusLabel.setText("Delivery Status: RECEIVED");   
                break;
            case "CANCELLED":
                ProgressBar.setValue(0);
                ProgressBar.setString("CANCELLED");
                StatusLabel.setText("Delivery Status: CANCELLED");   
                break;    
                
        }   
    }
    private int getPriority(String status) {
    switch (status) {
        case "IN TRANSIT":
            return 1;
        case "PROCESSING":
            return 2;
        case "CANCELLED":
        case "RECEIVED":
            return 3;   
    }
        return 0;
}
    
    private void sortPriorityQueue(){
        DefaultTableModel dm = (DefaultTableModel) orderTable.getModel();
        
         java.util.List<String[]> rows = new java.util.ArrayList<>();
        
        for (int i = 0; i < dm.getRowCount(); i++){
            String[] row = new String[dm.getColumnCount()];
            for (int j = 0; j < dm.getColumnCount(); j++){
                row[j] = dm.getValueAt(i, j).toString();
            }
            rows.add(row);
        }
        rows.sort((row1, row2) -> {
        String status1 = row1[4];
        String status2 = row2[4];
        
        return Integer.compare(getPriority(status1), getPriority(status2));
    });
    
    // Clear and re-add sorted rows
    dm.setRowCount(0);
    for (String[] row : rows) {
        dm.addRow(row);
        
    }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recBtn) {
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow != -1) {
                String orderId = orderTable.getValueAt(selectedRow, 0).toString();
                String status = orderTable.getValueAt(selectedRow, 4).toString().toUpperCase();

                if ("IN TRANSIT".equals(status)) {
                    try {
                        String updateSql = "UPDATE order_data SET status = 'RECEIVED' WHERE orderId = ?";
                        PreparedStatement pst = con.prepareStatement(updateSql);
                        pst.setString(1, orderId);
                        pst.executeUpdate();
                        orderTable.setValueAt("RECEIVED", selectedRow, 4);
                        
                        updateProgressBarFromStatus("RECEIVED");
                        recBtn.setEnabled(false);
                        JOptionPane.showMessageDialog(this, "Order " + orderId + " marked as Received.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Order must be In Transit to mark as Received.");
                }
            }
        }else if (e.getSource() == cancelBtn){
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow != -1){
                String OrderId = orderTable.getValueAt(selectedRow, 0).toString();
                String status = orderTable.getValueAt(selectedRow, 4).toString().toUpperCase();
                
                 if ("PROCESSING".equals(status)){
                     try {
                         String updatedSql = "UPDATE order_data SET status = 'CANCELLED' WHERE orderId = ?";
                         PreparedStatement pst = con.prepareStatement(updatedSql);
                         pst.setString(1, OrderId);
                         int rowsUpdated = pst.executeUpdate();
                         
                         if (rowsUpdated > 0){
                             orderTable.setValueAt("CANCELLED", selectedRow, 4);
                             
                             updateProgressBarFromStatus("CANCELLED");
                             cancelBtn.setEnabled(false);
                             JOptionPane.showMessageDialog(this, "Orders been cancelled");
                             
                             sortPriorityQueue();
                         }  
                     } catch (SQLException ex) {
                         ex.printStackTrace();
                     }
                 }
            }
        } 
        if (e.getSource() == pastBtn){
               this.dispose();
               //New OrderHistoryMain()
        }
         if(e.getSource() == infoBtn) {
             try{
                 String username = "jestervon08", fname = "", lname = "", addr = "", pn = "";
                 PreparedStatement pst = con.prepareStatement("SELECT * FROM account_profile WHERE username=?");
                 pst.setString(1, username);
                 ResultSet rs = pst.executeQuery();
                 
                 

            if (rs.next()) {
                 fname = rs.getString("first_name");
                 lname = rs.getString("last_name");
                 addr = rs.getString("address");
                 pn = rs.getString("phone_number");
                
                
            }
            //For more info
            int selectedRow = orderTable.getSelectedRow();
            if(selectedRow != -1) {
                String value = orderTable.getValueAt(selectedRow, 3).toString();
                String items = value.replaceAll("," , "\n");
                JOptionPane.showMessageDialog(this, "Name: " + fname + " " + lname + "\nPhone Number: " + pn + "\nAddress: "+ addr + "\n" + 
                        "-----------------------------------------------------------------------------------\n" + items + 
                        "-----------------------------------------------------------------------------------\nFROM: FordaFOOD", "Order Info", JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Invalid! Please select a row first", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            }catch (SQLException ext){
                    ext.printStackTrace();
                    }
                
            
    } 
      if (e.getSource() == refBtn) {
          df.setRowCount(0);
//           for(int i = 0; i < df.getRowCount(); i++){
//               df.removeRow(i);
//           }
          dataElements();
      }  
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == backLabel) {
            dispose();
            
            //New CustomerProfile()
        }
    }
    
    
    

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

        
    
}