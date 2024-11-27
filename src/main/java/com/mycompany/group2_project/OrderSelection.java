/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group2_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Alfred Gualberto
 */
public class OrderSelection extends JFrame implements ActionListener {
    
    private JLabel lblName, lblNameDesc, lblNumDesc, lblAddress, lblBLSt, lblBrgy, lblCity, lblTotal;
    public JLabel lblNum;
    public JComboBox cmbBrgy, cmbCity;
    public JTextField txtfBLSt;
    private ImageIcon imgLogo;
    
    
    //Panel - header
    private JPanel pnlHeader;
    private JLabel lblResto;
    private JButton btnBack;
    
    //Panel - order
    private JPanel pnlItem;
    private JList<String> listItemSelect, listItemCart;
    public DefaultListModel<String> dlmItemSelect, dlmItemCart;
    private JScrollPane spItemSelect, spItemCart;
    private JLabel lblItemSelect, lblItemCart, txtfItemQt;
    private JButton btnAdd, btnRem, btnAddQt, btnMinQt, btnOrder;
    
    private int itemQt=1; //food quantity
    private short restoChoice; //temporary //for displaying diff food menus
    
    //prices
    public int priceAdd, priceRem, priceTotal;
    
    //locations arrays
    private String brgyBinan[] = {"-select-","Biñan", "Bungahan", "Canlalay", "Casile", "Dela Paz", "Ganado", "Langkiwa", "Loma", "Malaban", "Malamig", "Mampalasan", "Platero", "Poblacion","San Antonio", "San Francisco (Halang)", "San Jose", "San Vicente","Sto. Domingo", "Soro-Soro", "Sto. Niño", "Sto. Tomas (Calabuso)","Timbao", "Tubigan", "Zapote"};
    private String brgyCabuyao[] = {"-select-","Baclaran", "Banay-Banay", "Banlic", "Bigaa","Butong", "Casile", "Diezmo", "Gulod","Mamatid", "Marinig", "Niugan", "Pittland","Pulo", "Sala", "San Isidro", "Barangay I Poblacion","Barangay II Poblacion", "Barangay III Poblacion"};
    private String brgyMuntinlupa[] = {"-select-","Alabang", "Bayanan", "Buli", "Cupang","New Alabang Village", "Poblacion", "Putatan", "Sucat","Tunasan"};
    private String brgySanPedro[] = {"-select-","Bagong Silang", "Calendola", "Chrysanthemum", "Cuyab","Estrella", "Fatima", "GSIS", "Landayan","Langgam", "Laram", "Magsaysay", "Maharlika","Narra", "Nueva", "Pacita 1", "Pacita 2","Poblacion", "Riverside", "Rosario", "Sampaguita","San Antonio", "San Lorenzo Ruiz", "San Roque", "San Vicente", "Santo Niño", "United Bayanihan", "United Better Living"};
    private String brgyStaRosa[] = {"-select-","Aplaya", "Balibago", "Caingin", "Dila","Dita", "Don Jose", "Ibaba", "Kanluran","Labas", "Macabling", "Malitlit", "Malusak","Market Area", "Pook", "Pulong Santa Cruz", "Santo Domingo","Sinalhan", "Tagapo"};
    String brgy[] = {"-select-"};
    
    private String city[] = {"-select-","Biñan", "Cabuyao", "Muntinlupa", "San Pedro", "Sta. Rosa"};
    public String brgyInput, cityInput;

    
    OrderSelection(/*MenuSelection parent*/) {
        //modify main frame
        setSize(464, 737);
        setTitle("Food Delivery");
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLogo
        imgLogo = new ImageIcon("Image.png");
        setIconImage(imgLogo.getImage());
        
        //header panel
        pnlHeader = new JPanel();
        pnlHeader.setBounds(0, 0, 464, 45);
        pnlHeader.setBackground(new Color(113, 45, 59));
        pnlHeader.setLayout(null);
        add(pnlHeader);
        
        //name of resto/fastfood
        lblResto = new JLabel("Resto");
        lblResto.setForeground(Color.WHITE);
        lblResto.setFont(new Font("Sherif", Font.BOLD, 20));
        pnlHeader.add(lblResto);
        
        btnBack = new JButton("\u2b9c");
        btnBack.setBounds(5,0,30,45);
        btnBack.setFont(new Font("Sherif", Font.PLAIN, 25));
        btnBack.setBorder(null);
        btnBack.setOpaque(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        btnBack.setContentAreaFilled(false);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusable(false);
        pnlHeader.add(btnBack);
        
        //customer name
        lblNameDesc = new JLabel("Name:");
        lblNameDesc.setBounds(20,50,100,30);
        lblNameDesc.setFont(new Font("Sherif", Font.PLAIN, 15));
        add(lblNameDesc);
        
        lblName = new JLabel("From database");
        lblName.setBounds(100, 50, 330, 30);
        lblName.setFont(new Font("Sherif", Font.PLAIN, 15));
        add(lblName);
        
        //customer contact number
        lblNumDesc = new JLabel("Contact #:");
        lblNumDesc.setBounds(20, 80, 100, 30);
        lblNumDesc.setFont(new Font("Sherif", Font.PLAIN, 15));
        add(lblNumDesc);
        
        lblNum = new JLabel("From database");
        lblNum.setBounds(100, 80, 330, 30);
        lblNum.setFont(new Font("Sherif", Font.PLAIN, 15));
        add(lblNum);
        
        //customer address
        lblAddress = new JLabel("Address:");
        lblAddress.setBounds(20, 110, 200, 30);
        lblAddress.setFont(new Font("Sherif", Font.PLAIN, 15));
        add(lblAddress);
        
        lblBLSt = new JLabel("Block, Lot, Street, Subdivision");
        lblBLSt.setBounds(30, 130, 200, 30);
        lblBLSt.setFont(new Font("Sherif", Font.BOLD, 10));
        add(lblBLSt);
        
        txtfBLSt = new JTextField();
        txtfBLSt.setBounds(30, 155, 400, 25);
        txtfBLSt.setBorder(null);
        txtfBLSt.setFont(new Font("Sherif", Font.PLAIN, 15));
        add(txtfBLSt);
        
        //city combobox
        lblCity = new JLabel("City");
        lblCity.setBounds(30, 175, 175, 30);
        lblCity.setFont(new Font("Sherif", Font.BOLD, 10));
        add(lblCity);
        
        cmbCity = new JComboBox(city);
        cmbCity.setBounds(30, 200, 190,25);
        cmbCity.setFont(new Font("Sherif", Font.PLAIN, 15));
        cmbCity.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        add(cmbCity);
        
        //brgy combobox
        lblBrgy = new JLabel("Barangay");
        lblBrgy.setBounds(240, 175, 175, 30);
        lblBrgy.setFont(new Font("Sherif", Font.BOLD, 10));
        add(lblBrgy);
        
        cmbBrgy = new JComboBox(brgy);
        cmbBrgy.setBounds(240, 200, 190,25);
        cmbBrgy.setFont(new Font("Sherif", Font.PLAIN, 15));
        cmbBrgy.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        add(cmbBrgy);
        
        //panel for food in menu and cart (buttons)
        pnlItem = new JPanel();
        pnlItem.setBounds(0, 245, 464, 455);
        pnlItem.setBackground(new Color(113, 45, 59));
        pnlItem.setLayout(null);
        add(pnlItem);
        
        //food-item select/menu
        dlmItemSelect = new DefaultListModel<>();
        listItemSelect = new JList<>(dlmItemSelect);
        listItemSelect.setFont(new Font("Sherif", Font.BOLD, 15));
        spItemSelect = new JScrollPane(listItemSelect);
        spItemSelect.setBounds(10, 40, 429, 130);
        pnlItem.add(spItemSelect);
        
        lblItemSelect = new JLabel("Food Menu");
        lblItemSelect.setFont(new Font("Sherif", Font.BOLD, 15));
        lblItemSelect.setForeground(Color.WHITE);
        lblItemSelect.setBounds(10, 10, 330, 30);
        pnlItem.add(lblItemSelect);

        //food-item cart
        dlmItemCart = new DefaultListModel<>();
        listItemCart = new JList<>(dlmItemCart);
        listItemCart.setFont(new Font("Sherif", Font.BOLD, 15));
        spItemCart = new JScrollPane(listItemCart);
        spItemCart.setBounds(10, 200, 429, 130);
        pnlItem.add(spItemCart);
        
        lblItemCart = new JLabel("Cart");
        lblItemCart.setFont(new Font("Sherif", Font.BOLD, 15));
        lblItemCart.setForeground(Color.WHITE);
        lblItemCart.setBounds(10, 170, 330, 30);
        pnlItem.add(lblItemCart);
        
        //display food quantity
        txtfItemQt = new JLabel("1");
        txtfItemQt.setFocusable(false);
        txtfItemQt.setFont(new Font("Sherif", Font.BOLD, 15));
        txtfItemQt.setForeground(Color.WHITE);
        txtfItemQt.setBounds(220, 340, 50, 50);
        pnlItem.add(txtfItemQt);
        
        //displaying price
        lblTotal = new JLabel("Total Cost:                       ₱0");
        lblTotal.setFont(new Font("Sherif", Font.BOLD, 15));
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setBounds(30, 395, 240, 50);
        pnlItem.add(lblTotal);

        //panel buttons
        btnAdd = new JButton("Add Item"); //adding food to cart
        btnAdd.setBounds(10, 340, 110, 50);
        btnAdd.setFocusable(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        pnlItem.add(btnAdd);
        
        btnAddQt = new JButton("+"); //increment quantity
        btnAddQt.setBounds(130, 340, 60, 50);
        btnAddQt.setFocusable(false);
        btnAddQt.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        pnlItem.add(btnAddQt);
        
        btnMinQt = new JButton("-"); //decrement quantity
        btnMinQt.setBounds(260, 340, 60, 50);
        btnMinQt.setFocusable(false);
        btnMinQt.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        pnlItem.add(btnMinQt);
        
        btnRem = new JButton("Remove Item"); //remove food from cart
        btnRem.setBounds(330, 340, 110, 50);
        btnRem.setFocusable(false);
        btnRem.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        pnlItem.add(btnRem);
        
        btnOrder = new JButton("Order"); //set order - going to confirm frame
        btnOrder.setBounds(330, 395, 110, 50);
        btnOrder.setBorder(null);
        btnOrder.setBackground(new Color(254, 236, 55));
        btnOrder.setFocusable(false);
        btnOrder.setCursor(new Cursor(Cursor.HAND_CURSOR){
        });
        pnlItem.add(btnOrder);
        
        //food-items in select
        //restoChoice = get value/index from previous frame
        
//        restoChoice = parent.chosenResto;
        
        restoChoice = 0; //what resto/fastfood is chosen from the prev frame
        
        switch(restoChoice){
            case 0:
                restoOneMD();
                break;
            case 1:
                restoTwoJB();
                break;
            case 2:
                restoThreeBK();
                break;
            case 3:
                restoFourGW();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid Resto", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

        //Action Listeners
        btnAdd.addActionListener(this);
        btnAddQt.addActionListener(this);
        btnMinQt.addActionListener(this);
        btnOrder.addActionListener(this);
        btnRem.addActionListener(this);
        cmbCity.addActionListener(this);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//                parent.setVisible(true);
            }
        });
        
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //add item
        if (e.getSource()==btnAdd) {
            int itemIndexSelected = listItemSelect.getSelectedIndex(); //get index of selected item in the menu
            
            if (!(itemIndexSelected<0)) {
                
                String itemToCart = dlmItemSelect.getElementAt(itemIndexSelected); //getting food from the menu
                //getting the price of the food (converting character to string)
                String priceItemSelected = Character.toString(itemToCart.charAt(2)).concat(Character.toString(itemToCart.charAt(3)));
                if (itemToCart.charAt(4)!=')') {
                    priceItemSelected = Character.toString(itemToCart.charAt(2)).concat(Character.toString(itemToCart.charAt(3))).concat(Character.toString(itemToCart.charAt(4)));
                }
                //convert string to int - getting price by multiplying item quantity to price food
                priceAdd = itemQt*Integer.parseInt(priceItemSelected);
                //displaying quantity of food, name of food, price of food in the cart
                if (itemQt>1) {
                    dlmItemCart.addElement("("+itemQt+")"+itemToCart.concat("    :       ₱"+priceAdd));
                    txtfItemQt.setText("1");
                } else {
                    dlmItemCart.addElement("(1)"+itemToCart+"    :       ₱"+priceAdd);
                    txtfItemQt.setText("1");
                }
                
                priceTotal += priceAdd; //updating total price
                lblTotal.setText("Total Cost:                       ₱"+priceTotal); //displaying total price
                txtfItemQt.setText("1"); //resetting quantity display
                listItemSelect.clearSelection(); //clear selection
                itemQt=1; //resetting quantity

            } else {
                JOptionPane.showMessageDialog(this, "Select a food in the list first", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        //remove item
        if (e.getSource()==btnRem) {
            int itemIndexCart = listItemCart.getSelectedIndex(); //getting the index of the selected item in the cart
            
            if (!(itemIndexCart<0)) {

                String itemInCart = dlmItemCart.getElementAt(itemIndexCart); //getting the food from the cart
                String inCartQt = Character.toString(itemInCart.charAt(1)); //getting the quantity of selected food in the cart
                int cartNewQt = Integer.parseInt(inCartQt)-itemQt; //updating the quantity of the food in the cart

                //replacing the old quantity by new quantity
                String itemInCartUpd = itemInCart.replace("("+inCartQt+")", "("+cartNewQt+")");
                
                //cut the end of previous price - changing the price due to updated quantity
                String inCartNewPrice=null;
                //remove the old price
                if(itemInCartUpd.charAt(itemInCartUpd.length()-4)!='₱'){
                    inCartNewPrice = itemInCartUpd.substring(0, itemInCartUpd.length()-4);
                } else if (itemInCartUpd.charAt(itemInCartUpd.length()-3)!='₱') {
                    inCartNewPrice = itemInCartUpd.substring(0, itemInCartUpd.length()-3);
                }
                //getting the price of the food (characters to string)
                String priceCartSelected = Character.toString(inCartNewPrice.charAt(5)).concat(Character.toString(inCartNewPrice.charAt(6)));
                if (itemInCartUpd.charAt(7)!=')') {
                    priceCartSelected = Character.toString(inCartNewPrice.charAt(5)).concat(Character.toString(inCartNewPrice.charAt(6))).concat(Character.toString(inCartNewPrice.charAt(7)));
                }
                
                try {
                    //convert string to int - getting price by multiplying new item quantity to price food
                    int priceUpd = cartNewQt*Integer.parseInt(priceCartSelected); //for updating price in the cart
                    priceRem = itemQt*Integer.parseInt(priceCartSelected); //for accurate total price removal
                
                    if (itemQt<=Integer.parseInt(inCartQt)) {                    
                        //removing quantity of food, name of food, price of food in the cart base on set quantity
                        if (cartNewQt>0) {
                            dlmItemCart.remove(itemIndexCart);
                            dlmItemCart.add(itemIndexCart, inCartNewPrice.concat(String.valueOf(priceUpd)));
                        } else {
                            dlmItemCart.remove(itemIndexCart);
                        }
                        
                        priceTotal -= priceRem; //updating total price
                        lblTotal.setText("Total Cost:                       ₱"+priceTotal); //displaying total price
                        listItemCart.clearSelection(); //clear selection
                        txtfItemQt.setText("1"); //update quantity display
                        itemQt=1; //update quantity
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid item removal", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a food in the cart first", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //quantity
        if (e.getSource()==btnAddQt) {
            
            if (itemQt==9) {
                itemQt=9; //maximum of 9 quantity per item
                txtfItemQt.setText(String.valueOf(itemQt));
            } else {
                itemQt++; //increment number of quantity
                txtfItemQt.setText(String.valueOf(itemQt));
            }
        } else if (btnMinQt == e.getSource()) {
            if (itemQt>1) {
                itemQt--; //decrement number of quantity
                txtfItemQt.setText(String.valueOf(itemQt));
            }
        }
        
        //order button
        if (e.getSource()==btnOrder) {
            
            if (dlmItemCart.size()!=0) {
                //conditions must be fulfilled before going the order confirm frame
                if (!txtfBLSt.getText().isEmpty()&&cmbCity.getSelectedIndex()!=0&&cmbBrgy.getSelectedIndex()!=0) {
                    brgyInput = (String) cmbBrgy.getSelectedItem(); //get the brgy for the next frame
                    cityInput = (String) cmbCity.getSelectedItem(); //get the city for the next frame
                    new ConfirmOrder(OrderSelection.this); //go to next frame - OrderSelection frame is set to parent
                }else{
                    JOptionPane.showMessageDialog(this, "Fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cart is empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //check city - change brgy
        if (e.getSource()==cmbCity) {
            int citySelected = cmbCity.getSelectedIndex(); //get index of selected city
            remove(cmbBrgy); //remove the combo box of brgy
            //select the combo box of brgy base on selected city
            switch (citySelected) {
                case 0:
                    cmbBrgy = new JComboBox(brgy);
                    break;
                case 1:
                    cmbBrgy = new JComboBox(brgyBinan);
                    break;
                case 2:
                    cmbBrgy = new JComboBox(brgyCabuyao);
                    break;
                case 3:
                    cmbBrgy = new JComboBox(brgyMuntinlupa);
                    break;
                case 4:
                    cmbBrgy = new JComboBox(brgySanPedro);
                    break;
                case 5:
                    cmbBrgy = new JComboBox(brgyStaRosa);
                    break;
                default:
                    throw new AssertionError();
            }
        
            cmbBrgy.setBounds(240, 200, 190,25);
            cmbBrgy.setFont(new Font("Sherif", Font.PLAIN, 15));
            add(cmbBrgy); //adding updated combo box for brgy
        }
    }
    
    //for the restaurants and its food menu
    public void restoOneMD(){
        
        lblResto.setText("McDonald's");
        lblResto.setBounds(170,10,300,30);

        dlmItemSelect.addElement("(₱154) Big Mac");
        dlmItemSelect.addElement("(₱54) Burger McDo");
        dlmItemSelect.addElement("(₱135) Double Cheeseburger");
        dlmItemSelect.addElement("(₱146) McChicken Sandwich");
        dlmItemSelect.addElement("(₱52) McCrispy Chicken Sandwich");
        dlmItemSelect.addElement("(₱118) 1pc. Chicken McDo with McSpaghetti");
        dlmItemSelect.addElement("(₱80) 1pc. Chicken McDo with Rice");
        dlmItemSelect.addElement("(₱60) McCrispy Chicken Fillet with Rice");
        dlmItemSelect.addElement("(₱158) 2pc. Chicken McDo with Rice");
        dlmItemSelect.addElement("(₱178) 6pc. Chicken McNuggets with Fries");
        dlmItemSelect.addElement("(₱66) Coke");
        dlmItemSelect.addElement("(₱72) Orange Juice");
        dlmItemSelect.addElement("(₱52) Coke McFloat");
        dlmItemSelect.addElement("(₱57) McFlurry with Oreo Cookies");
        dlmItemSelect.addElement("(₱50) Hot Fudge Sundae");
        dlmItemSelect.addElement("(₱79) Fries");
        dlmItemSelect.addElement("(₱158) Cheesy Eggdesal");
        dlmItemSelect.addElement("(₱37) Hash Browns");
        dlmItemSelect.addElement("(₱63) Sausage Platter with Rice");
        dlmItemSelect.addElement("(₱66) 3pc. Hotcakes");
    }
    
    public void restoTwoJB(){
        
        lblResto.setText("Jollibee");
        lblResto.setBounds(250,10,300,30);

        dlmItemSelect.addElement("(₱40) Yumburger");
        dlmItemSelect.addElement("(₱66) Cheesy Yumburger");
        dlmItemSelect.addElement("(₱169) Champ");
        dlmItemSelect.addElement("(₱239) Aloha Champ");
        dlmItemSelect.addElement("(₱91) Bacon Cheesy Yumburger");
        dlmItemSelect.addElement("(₱82) 1pc. Chickenjoy");
        dlmItemSelect.addElement("(₱163) 2pc. Chickenjoy");
        dlmItemSelect.addElement("(₱140) 1pc. Chickenjoy with Burger Steak");
        dlmItemSelect.addElement("(₱139) 1pc. Chickenjoy with Jolly Spaghetti");
        dlmItemSelect.addElement("(₱535) 8pc. Chickenjoy");
        dlmItemSelect.addElement("(₱235) Jolly Spaghetti Pan");
        dlmItemSelect.addElement("(₱59) Jolly Spaghetti");
        dlmItemSelect.addElement("(₱112) Jolly Spaghetti with Burger Steak");
        dlmItemSelect.addElement("(₱59) 1pc. Burger Steak");
        dlmItemSelect.addElement("(₱117) 2pc. Burger Steak");
        dlmItemSelect.addElement("(₱450) 8pc. Burger Steak");
        dlmItemSelect.addElement("(₱48) Jolly Crispy Fries");
        dlmItemSelect.addElement("(₱53) Coke");
        dlmItemSelect.addElement("(₱64) Ice Tea");
        dlmItemSelect.addElement("(₱52) Coke Float");
        dlmItemSelect.addElement("(₱64) Cookies and Cream Sundae");
    }
    
    public void restoThreeBK(){
        
        lblResto.setText("Burger King");
        lblResto.setBounds(250,10,300,30);
        
        dlmItemSelect.addElement("(₱75) Whopper Jr.");
        dlmItemSelect.addElement("(₱177) Whopper");
        dlmItemSelect.addElement("(₱99) 4-Cheese Whopper Jr.");
        dlmItemSelect.addElement("(₱198) 4-Cheese Whopper");
        dlmItemSelect.addElement("(₱173) Bacon 4-Cheese Whopper Jr.");
        dlmItemSelect.addElement("(₱282) Bacon 4-Cheese Whopper");
        dlmItemSelect.addElement("(₱59) X-tra Long Chicken Jr. Sandwich");
        dlmItemSelect.addElement("(₱99) X-tra Long Chicken Sandwich");
        dlmItemSelect.addElement("(₱136) Plant-Based Whopper Jr.");
        dlmItemSelect.addElement("(₱295) Plant-Based Whopper");
        dlmItemSelect.addElement("(₱269) BLT Spicy Chicken King");
        dlmItemSelect.addElement("(₱215) Chicken King");
        dlmItemSelect.addElement("(₱229) Spicy Chicken King");
        dlmItemSelect.addElement("(₱225) Flame-Grilled BBQ Hamburger");
        dlmItemSelect.addElement("(₱75) Flame-Grilled Hamburger");
        dlmItemSelect.addElement("(₱85) Flame-Grilled Cheeseburger");
        dlmItemSelect.addElement("(₱159) Flame-Grilled Double Cheeseburger");
        dlmItemSelect.addElement("(₱75) Coke");
        dlmItemSelect.addElement("(₱63) Coke Float");
        dlmItemSelect.addElement("(₱73) Rootbeer Float");
    }
    
    public void restoFourGW(){
        
        lblResto.setText("Greenwich");
        lblResto.setBounds(250,10,300,30);
                
        dlmItemSelect.addElement("(₱319) 9\" Pizza - Buy  One Take One");
        dlmItemSelect.addElement("(₱499) 12\" Pizza - Buy  One Take One");
        dlmItemSelect.addElement("(₱679) Buy One Take Two Pizza");
        dlmItemSelect.addElement("(₱77) Hawaiian Overload Pizzawrap");
        dlmItemSelect.addElement("(₱122) Hawaiian Overload Pizzawrap Value Meal");
        dlmItemSelect.addElement("(₱223) Hawaiian Overload Pizzawrap Pack of Three");
        dlmItemSelect.addElement("(₱223) Assorted Pizzawrap Pack of Three");
        dlmItemSelect.addElement("(₱111) Roast Beef & Cream Cheese Pizzawrap");
        dlmItemSelect.addElement("(₱174) All Meat Overload");
        dlmItemSelect.addElement("(₱140) Ham & Cheese Classic");
        dlmItemSelect.addElement("(₱140) Cheeseburger Classic");
        dlmItemSelect.addElement("(₱140) Cheesy Bacon & Ham Classic");
        dlmItemSelect.addElement("(₱162) Beef & Pineapples Overload");
        dlmItemSelect.addElement("(₱99) Lasagna Supreme");
        dlmItemSelect.addElement("(₱99) Meaty Spaghetti");
        dlmItemSelect.addElement("(₱144) Creamy Bacon Carbonara");
        dlmItemSelect.addElement("(₱62) Classic Choco Frozen  Cake");
        dlmItemSelect.addElement("(₱308) Chicken and Waves");
        dlmItemSelect.addElement("(₱99) 1.5L Coke");
        dlmItemSelect.addElement("(₱85) 1L Pepsi");
    }
    
}
