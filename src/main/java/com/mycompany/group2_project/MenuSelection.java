package com.mycompany.group2_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import static javax.swing.BorderFactory.createCompoundBorder;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class MenuSelection extends JFrame implements ActionListener, KeyListener {

    public JPanel restoPanel, menuPanel, homePanel, quickRestoPanel, searchPanel;
    public JComboBox<String> branchComboBox;
    public JButton orderBtn, profileBtn, logoutBtn, quickRestaurantBtn, viewRestaurantsBtn, backBtn, backBtn2, searchBtn, quickLookupBtn, orderBtn2;
    public JLabel space1, space2, addressLbl, underlineLbl, searchLbl;
    public JTextField searchTf;
    public Color maroon = new Color(113, 45, 59);
    public ImageIcon homePage, mcdo, jobee, green, burger;;
    public JTable table;
    public DefaultTableModel tableModel;
    public JScrollPane sp;
    public Hashtable<String, String> hashT;
    public boolean enabled = true;
    public JList<RowSorter.SortKey> sortKeys;
    
    public String[] columns = {"Resto ID", "Restaurant", "Location", "Distance"},
                restaurants = {"McDonald's", "Jollibee", "Greenwich", "Burger King"},
                locations = {"San Pedro", "Binan", "Sta. Rosa", "Cabuyao", "Calamba"};
    public String username, customerId;

    public short chosenCity;
    
    // Logo
    public ImageIcon logoIcon = new ImageIcon("fordaFood.png");

    public MenuSelection(AccountLogin parent) {
        username = parent.userLoginTF.getText();
        QuickRestoPanel();
        setTitle("Restaurant Profile Manager");
        setSize(464, 737);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(logoIcon.getImage());
        setLayout(new CardLayout());
        
        restoPanel = RestoPanel(maroon);
        menuPanel = MenuPanel(maroon);
        homePanel = HomePanel(maroon);

        add(homePanel, "home");
        add(new JScrollPane(restoPanel), "restoP");
        add(new JScrollPane(menuPanel), "Menu");
        
        
        setVisible(true);
    }
    
    // Panel for the Home 
    JPanel HomePanel(Color maroon) {
        JPanel panel = new JPanel();
        panel.setLayout(null);  
        panel.setBorder(BorderFactory.createLineBorder(maroon, 50)); 
        panel.setBackground(Color.WHITE);

        // Home Page
        homePage = new ImageIcon("home_page2.png");
        JLabel background = new JLabel(homePage);
        background.setBounds(0, 0, 450, 700); 

        viewRestaurantsBtn = new JButton("View Restaurants");
        viewRestaurantsBtn.setBounds(155, 260, 225, 50); 
        viewRestaurantsBtn.setFont(new Font("Arial", Font.BOLD, 15));
        viewRestaurantsBtn.setForeground(maroon);
        viewRestaurantsBtn.setBackground(Color.white);
        viewRestaurantsBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        viewRestaurantsBtn.setFocusable(false);
        viewRestaurantsBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                viewRestaurantsBtn.setBackground(maroon);
                viewRestaurantsBtn.setForeground(Color.white);
            }
            public void mouseExited(MouseEvent e) {
                viewRestaurantsBtn.setBackground(Color.white);
                viewRestaurantsBtn.setForeground(maroon);
            }
        });
        viewRestaurantsBtn.addActionListener(this);

        quickRestaurantBtn = new JButton("Quick Restaurant Lookup");
        quickRestaurantBtn.setBounds(155, 370, 225, 50); 
        quickRestaurantBtn.setFont(new Font("Arial", Font.BOLD, 15));
        quickRestaurantBtn.setForeground(maroon);
        quickRestaurantBtn.setBackground(Color.white);
        quickRestaurantBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        quickRestaurantBtn.setFocusable(false);
        quickRestaurantBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                quickRestaurantBtn.setBackground(maroon);
                quickRestaurantBtn.setForeground(Color.white);
            }
            public void mouseExited(MouseEvent e) {
                quickRestaurantBtn.setBackground(Color.white);
                quickRestaurantBtn.setForeground(maroon);
            }
        });
        quickRestaurantBtn.addActionListener(this);

        profileBtn = new JButton("Customer Profile");
        profileBtn.setBounds(155, 479, 225, 50); 
        profileBtn.setFont(new Font("Arial", Font.BOLD, 15));
        profileBtn.setForeground(maroon);
        profileBtn.setBackground(Color.white);
        profileBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        profileBtn.setFocusable(false);
        profileBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                profileBtn.setBackground(maroon);
                profileBtn.setForeground(Color.white);
            }
            public void mouseExited(MouseEvent e) {
                profileBtn.setBackground(Color.white);
                profileBtn.setForeground(maroon);
            }
        });
        profileBtn.addActionListener(this);

        logoutBtn = new JButton("Log Out");
        logoutBtn.setBounds(155, 590, 225, 50); 
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 15));
        logoutBtn.setForeground(maroon);
        logoutBtn.setBackground(Color.white);
        logoutBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        logoutBtn.setFocusable(false);
        logoutBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                logoutBtn.setBackground(maroon);
                logoutBtn.setForeground(Color.white);
            }
            public void mouseExited(MouseEvent e) {
                logoutBtn.setBackground(Color.white);
                logoutBtn.setForeground(maroon);
            }
        });
        logoutBtn.addActionListener(this);

        panel.add(viewRestaurantsBtn);
        panel.add(quickRestaurantBtn);
        panel.add(profileBtn);
        panel.add(logoutBtn);
        panel.add(background);

        return panel;
    }

    // Panel for restaurant panel
    public JPanel RestoPanel(Color maroon) {
        JPanel panel = new JPanel(new BorderLayout());

        backBtn = new JButton("\u2b9c                                                       ");
        backBtn.setPreferredSize(new Dimension(5, 50)); 
        backBtn.setFont(new Font("Sherif", Font.BOLD, 25));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(maroon); 
        backBtn.setBorder(BorderFactory.createLineBorder(maroon));
        backBtn.setOpaque(true); 
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);

        panel.add(backBtn, BorderLayout.NORTH);

        JPanel restaurantPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        restaurantPanel.setBorder(BorderFactory.createEmptyBorder(1, 20, 20, 20));
        restaurantPanel.setBackground(maroon);

        mcdo = new ImageIcon("mcdo.png");
        jobee = new ImageIcon("jobee.png");
        green = new ImageIcon("greenwich.png");
        burger = new ImageIcon("burgerKing.png");

        Image scaledMcdo, scaledJobee, scaledGreen, scaledBurger;
        scaledMcdo = mcdo.getImage().getScaledInstance(430, 147, Image.SCALE_SMOOTH);
        scaledJobee = jobee.getImage().getScaledInstance(430, 147, Image.SCALE_SMOOTH);
        scaledGreen = green.getImage().getScaledInstance(430, 147, Image.SCALE_SMOOTH);
        scaledBurger = burger.getImage().getScaledInstance(430, 147, Image.SCALE_SMOOTH);

        RestoButton("McDonald's", restaurantPanel, scaledMcdo);
        RestoButton("Jollibee", restaurantPanel, scaledJobee);
        RestoButton("Greenwich", restaurantPanel, scaledGreen);
        RestoButton("Burger King", restaurantPanel, scaledBurger);

        panel.add(restaurantPanel, BorderLayout.CENTER);

        return panel;
    }
    
    //Panel for quick restaurant panel
    public void QuickRestoPanel() {
        
        Font arial = new Font("Arial", Font.PLAIN, 15);
        
        quickRestoPanel = new JPanel();
        quickRestoPanel.setVisible(false);
        quickRestoPanel.setLayout(null);
        quickRestoPanel.setBackground(maroon);
        quickRestoPanel.setBounds(0,0,450,700);
        
        addressLbl = new JLabel("Address: " + getAddress());
        addressLbl.setFont(arial);
        addressLbl.setBounds(30, 30, 420, 40);
        addressLbl.setForeground(Color.white);
        
        underlineLbl = new JLabel();
        underlineLbl.setFont(arial);
        underlineLbl.setBounds(30, 40, 390, 40);
        underlineLbl.setForeground(Color.white);
        underlineLbl.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        
        searchPanel = new JPanel();
        searchPanel.setVisible(true);
        searchPanel.setLayout(null);
        searchPanel.setBackground(Color.white);
        searchPanel.setBounds(30,100, 345,40);
        
        searchLbl = new JLabel("Search");
        searchLbl.setFont(arial);
        searchLbl.setBounds(15, 0, 300, 40);
        searchLbl.setForeground(Color.LIGHT_GRAY);
        searchLbl.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        searchTf = new JTextField();
        searchTf.setFont(arial);
        searchTf.setBounds(0, 0, 345, 40);
        searchTf.setForeground(Color.black);
        searchTf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1, true), BorderFactory.createEmptyBorder(0, 14, 0, 14)));
        searchTf.setOpaque(false);
        searchTf.addKeyListener(this);
        
        //For image icon of search button
        ImageIcon searchIcon = new ImageIcon("search_icon.png");
        Image searchScale = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchIcon = new ImageIcon(searchScale);
        
        searchBtn = new JButton("");
        searchBtn.setBounds(385, 105, 30, 30);
        searchBtn.setIcon(scaledSearchIcon);
        searchBtn.setBorder(null);
        searchBtn.setFocusable(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.addActionListener(this);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //For getting different data: restoId, resto, location; 
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setShowGrid(true);
        table.setGridColor(Color.black);
        table.setShowVerticalLines(true);
        sp = new JScrollPane(table);
        sp.setBounds(30, 160, 390, 430);
        
        for(String c : columns) {
            tableModel.addColumn(c);
        }
        AddRows();
        
        quickLookupBtn = new JButton("Quick Restaurant Lookup");
        quickLookupBtn.setFont(new Font("Arial", Font.BOLD, 15));
        quickLookupBtn.setBounds(30, 608, 290, 40);
        quickLookupBtn.setForeground(maroon);
        quickLookupBtn.setBackground(Color.white);
        quickLookupBtn.setBorder(BorderFactory.createLineBorder(maroon, 2));
        quickLookupBtn.setFocusable(false);
        quickLookupBtn.addActionListener(this);
        quickLookupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        orderBtn2 = new JButton("Order Now");
        orderBtn2.setFont(new Font("Arial", Font.BOLD, 15));
        orderBtn2.setBounds(320, 608, 100, 40);
        orderBtn2.setForeground(maroon);
        orderBtn2.setBackground(Color.white);
        orderBtn2.setBorder(BorderFactory.createLineBorder(maroon, 2));
        orderBtn2.setFocusable(false);
        orderBtn2.addActionListener(this);
        orderBtn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        add(quickRestoPanel);
        quickRestoPanel.add(addressLbl);
        quickRestoPanel.add(underlineLbl);
        quickRestoPanel.add(searchPanel);
        searchPanel.add(searchLbl);
        searchPanel.add(searchTf);
        quickRestoPanel.add(searchBtn);
        quickRestoPanel.add(sp);
        quickRestoPanel.add(quickLookupBtn);
        quickRestoPanel.add(orderBtn2);
    }
    
    //For getting the address of the user that logged in
    public String getAddress() {
        String address = "";
        try {
            //"jdbc:mysql://[host address]/[database_name], [user], [password]
            Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/group2_database", "root", "group2");
                
            //The query for the database
            PreparedStatement st = (PreparedStatement) c.prepareStatement("SELECT address, customer_id FROM account_profile WHERE username=?");
                
            //The data needed to compare in database
            st.setString(1, username); 
                
            //For executing the query above
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                address = rs.getString("address");
                customerId = rs.getString("customer_id");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return address;
    }
    
    //For getting the resto Id from two given data using hashtable
    public String getRestoId(String restaurant, String location) {
        
        hashT = new Hashtable<>();
        
        hashT.put("McDonald's", "MD");
        hashT.put("Jollibee", "JB");
        hashT.put("Greenwich", "GW");
        hashT.put("Burger King", "BK");
        hashT.put("San Pedro", "SPC");
        hashT.put("Binan", "BC");
        hashT.put("Sta. Rosa", "SC");
        hashT.put("Cabuyao", "CBC");
        hashT.put("Calamba", "CLC");
        
        String restoId = "";
        
        String[] resto = {"McDonald's", "Jollibee", "Greenwich", "Burger King"},
                loc = {"San Pedro", "Binan", "Sta. Rosa", "Cabuyao", "Calamba"};
        
        for(int i=0; i<resto.length; i++) {
            
            for(int j=0; j<loc.length; j++) {
                if(restaurant.equals(resto[i]) && location.equals(loc[j])) {
                    restoId += hashT.get(restaurant);
                    restoId += hashT.get(location);
                    break;
                }
            }
        }
        return restoId;
    }
    
    //Used Dijkstra algorithim as reference; did not use Dijkstra specifically
    public String getDistance(String addressCity, String location) {
        /* San Pedro --1--> Binan --2--> Sta. Rosa --3--> Cabuyao --4--> Calamba
           San Pedro --1--> Binan ] 
           San Pedro --3--> Sta. Rosa ] 1, 2, 3, and 4 is the distance between one city to another city
           San Pedro --6--> Cabuyao ] 1, 3, 6, and 10 is the distance when the distance between city to city is added
           San Pedro --10--> Calamba ] Same logic applies to other citie
        */
        
        String[] cities = {"San Pedro", "Binan", "Sta. Rosa", "Cabuyao", "Calamba"};
        int[] km = {0, 1, 2, 3, 4};
        
        int distance = 0;
        
        for(int i=0; i<cities.length; i++) {
            
            for(int j=0; j<km.length; j++) {
                if(addressCity.equals(cities[i]) && location.equals(cities[j])) {
                    if(i < j) {
                        for(int k=j; k>i; k--) {
                            distance += km[k];
                        }
                    } else if(i > j) {
                        for(int k=i; k>j; k--) {
                            distance += km[k];
                        }
                    } else {
                        distance = 0;
                    }
                }
            }
        }
        String total = distance + "km";
        
        return total;
    }
    
    public void AddRows() {
        String[] restaurants = {"McDonald's", "Jollibee", "Greenwich", "Burger King"},
                locations = {"San Pedro", "Binan", "Sta. Rosa", "Cabuyao", "Calamba"};
        
        String address = addressLbl.getText();
        String addressCity = "";
        int commaCount = 0;
        for(int i=0; i<address.length(); i++) {
            if(address.charAt(i)==',') {
                commaCount++;
            }
            if(commaCount==3) {
                addressCity += address.charAt(i+2);
                if(address.charAt(i+3)==',') {
                    break;
                }
            }
        }
        
        String id, resto, loc, km;
        
        for(int i=0; i<restaurants.length; i++) {
            
            for(int j=0; j<locations.length; j++) {
                
                resto = restaurants[i];
                loc = locations[j];
                id = getRestoId(resto, loc);
                km = getDistance(addressCity, loc);
                
                String[] object = {id, resto, loc, km};
                tableModel.addRow(object);
            }
        }
    }
  
    //restaurant choices
    public void RestoButton(String name, JPanel parentPanel, Image restoPic) {
        JButton restaurantButton = new JButton(name);
        restaurantButton.setFont(new Font("Arial", Font.BOLD, 20));
        restaurantButton.setHorizontalAlignment(SwingConstants.CENTER);
        restaurantButton.setVerticalAlignment(SwingConstants.CENTER);
        restaurantButton.setPreferredSize(new Dimension(300, 60));
        restaurantButton.addActionListener(this);
        ImageIcon scaledRestoPic = new ImageIcon(restoPic);
        restaurantButton.setIcon(scaledRestoPic);
        parentPanel.add(restaurantButton);
    }
  
    //menu panel for displaying a selected restaurant menu
    public JPanel MenuPanel(Color backgroundColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(backgroundColor);

        branchComboBox = new JComboBox<>(new String[]{
            "-select-", "Binan", "Cabuyao", "Calamba", "San Pedro", "Sta. Rosa"
        });
        branchComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(branchComboBox);
       
        space1 = new JLabel(" ");
        space1.setFont(new Font("arial", Font.PLAIN, 7));
       
        space2 = new JLabel(" ");
        space2.setFont(new Font("arial", Font.PLAIN, 10));
       
        orderBtn = new JButton("Order Now");
        orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderBtn.setPreferredSize(new Dimension(150, 50));
        orderBtn.addActionListener(this);

        return panel;
    }
  
    public short chosenResto;
    public void MenuPanel(String restaurantName) {
        // Clears the current menu and set a new one based on restaurant selection
        menuPanel.removeAll();

        branchComboBox = new JComboBox<>(new String[]{
            "-select-", "Binan", "Cabuyao", "Calamba", "San Pedro", "Sta. Rosa"
        });
        
        //panel for second back button
        JPanel backBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        backBtnPanel.setOpaque(false); 
        backBtn2 = new JButton("\u2b9c ");
        backBtn2.setPreferredSize(new Dimension(80, 50));
        backBtn2.setFont(new Font("Sherif", Font.BOLD, 25));
        backBtn2.setForeground(Color.WHITE);
        backBtn2.setBackground(maroon); 
        backBtn2.setBorder(BorderFactory.createLineBorder(maroon));
        backBtn2.setOpaque(true);
        backBtn2.setFocusable(false);
        backBtn2.addActionListener(this);
        backBtnPanel.add(backBtn2);
        
        JLabel restaurantLabel = new JLabel("Menu for " + restaurantName);
        restaurantLabel.setFont(new Font("Arial", Font.BOLD, 24));
        restaurantLabel.setForeground(Color.WHITE);
        backBtnPanel.add(restaurantLabel);

        menuPanel.add(backBtnPanel, BorderLayout.NORTH);

        JPanel branchResto = new JPanel();
        branchResto.setLayout(new BoxLayout(branchResto, BoxLayout.Y_AXIS));
        branchResto.setOpaque(false); 
        branchComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        branchResto.add(branchComboBox);
        menuPanel.add(branchResto, BorderLayout.CENTER);
        menuPanel.add(branchComboBox);
        menuPanel.add(space1);

        if (restaurantName.equals("Burger King")) {
            chosenResto = 2;
            menuCategory("Whopper", new String[][]{
                    {"(\u20b175) Whopper Jr."},
                    {"(\u20b1177) Whopper"}
            });
            menuCategory("4-Cheese Whopper", new String[][]{
                    {"(\u20b199) 4-Cheese Whopper Jr."},
                    {"(\u20b1198) 4-Cheese Whopper"},
                    {"(\u20b1173) Bacon 4-Cheese Whopper Jr."},
                    {"(\u20b1282) Bacon 4-Cheese Whopper"}
            });
            menuCategory("X-tra Long Chicken", new String[][]{
                    {"(\u20b159) X-tra Long Chicken Jr. Sandwich"},
                    {"(\u20b199) X-tra Long Chicken Sandwich"}
            });
            menuCategory("Plant-Based Whopper", new String[][]{
                    {"(\u20b1136) Plant-Based Whopper Jr."},
                    {"(\u20b1295) Plant-Based Whopper"}
            });
            menuCategory("Chicken King", new String[][]{
                    {"(\u20b1269) BLT Spicy Chicken King"},
                    {"(\u20b1215) Chicken King"},
                    {"(\u20b1229) Spicy Chicken King"}
            });
            menuCategory("Flame-Grilled Cheeseburger", new String[][]{
                    {"(\u20b1225) Flame-Grilled BBQ Hamburger"},
                    {"(\u20b175) Flame-Grilled Hamburger"},
                    {"(\u20b185) Flame-Grilled Cheeseburger"},
                    {"(\u20b1159) Flame-Grilled Double Cheeseburger"}
            });
            menuCategory("Drinks", new String[][]{
                    {"(\u20b175) Coke"},
                    {"(\u20b163) Coke Float"},
                    {"(\u20b173) Rootbeer Float"}
            });
        } else if (restaurantName.equals("Greenwich")) {
            chosenResto = 3;
            menuCategory("Feel G Deals", new String[][]{
                    {"(\u20b1319) 9\" Pizza - Buy One Take One"},
                    {"(\u20b1499) 12\" Pizza - Buy One Take One"},
                    {"(\u20b1679) Buy One Take Two"}
            });
            menuCategory("Pizzawrap", new String[][]{
                    {"(\u20b177) Hawaiian Overload Pizzawrap"},
                    {"(\u20b1122) Hawaiian Overload Pizzawrap Value Meal"},
                    {"(\u20b1223) Hawaiian Overload Pizzawrap Pack of Three"},
                    {"(\u20b1223) Assorted Pizzawrap Pack of Three"},
                    {"(\u20b1111) Roast Beef & Cream Cheese Pizzawrap"}
            });
            menuCategory("Pizza", new String[][]{
                    {"(\u20b1174) All Meat Overload"},
                    {"(\u20b1140) Ham & Cheese Classic"},
                    {"(\u20b1140) Cheeseburger Classic"},
                    {"(\u20b1140) Cheesy Bacon & Ham Classic"},
                    {"(\u20b1162) Beef & Pineapples Overload"}
            });
            menuCategory("Pasta", new String[][]{
                    {"(\u20b199) Lasagna Supreme"},
                    {"(\u20b199) Meaty Spaghetti"},
                    {"(\u20b1144) Creamy Bacon Carbonara"}
            });
            menuCategory("Chicken and Sides", new String[][]{
                    {"(\u20b162) Classic Choco Frozen Cake"},
                    {"(\u20b1308) Chicken and Waves"}
            });
            menuCategory("Drinks", new String[][]{
                    {"(\u20b199) 1.5L Coke"},
                    {"(\u20b185) 1L Pepsi"}
            });
        } else if (restaurantName.equals("McDonald's")) {
            chosenResto = 0;
            menuCategory("Breakfast", new String[][]{
                    {"(\u20b1158) Cheesy Eggdesal"},
                    {"(\u20b137) Hash Browns"},
                    {"(\u20b163) Sausage Platter with Rice"},
                    {"(\u20b166) 3pc. Hotcakes"}
            });
            menuCategory("Fries", new String[][]{
                    {"(\u20b179) Fries"}
            });
            menuCategory("Drinks & Desserts", new String[][]{
                    {"(\u20b166) Coke"},
                    {"(\u20b172) Orange Juice"},
                    {"(\u20b152) Coke McFloat"},
                    {"(\u20b157) McFlurry with Oreo Cookies"},
                    {"(\u20b150) Hot Fudge Sundae"}
            });
            menuCategory("Burgers", new String[][]{
                    {"(\u20b1154) Big Mac"},
                    {"(\u20b154) Burger McDo"},
                    {"(\u20b1135) Double Cheeseburger"},
                    {"(\u20b1146) McChicken Sandwich"},
                    {"(\u20b152) McCrispy Chicken Sandwich"}
            });
            menuCategory("Chicken", new String[][]{
                    {"(\u20b1118) 1pc. Chicken McDo with McSpaghetti"},
                    {"(\u20b180) 1pc. Chicken McDo with Rice"},
                    {"(\u20b160) McCrispy Chicken Fillet with Rice"},
                    {"(\u20b1158) 2pc. Chicken McDo with Rice"},
                    {"(\u20b1178) 6pc. Chicken McNuggets with Fries"}
            });
        } else if (restaurantName.equals("Jollibee")) {
            chosenResto = 1;
            menuCategory("Burgers", new String[][]{
                    {"(\u20b140) Yumburger"},
                    {"(\u20b166) Cheesy Yumburger"},
                    {"(\u20b1169) Champ"},
                    {"(\u20b1239) Aloha Champ"},
                    {"(\u20b191) Bacon Cheesy Yumburger"}
            });
            menuCategory("Chickenjoy", new String[][]{
                    {"(\u20b182) 1pc. Chickenjoy"},
                    {"(\u20b1163) 2pc. Chickenjoy"},
                    {"(\u20b1140) 1pc. Chickenjoy with Burger Steak"},
                    {"(\u20b1139) 1pc. Chickenjoy with Jolly Spaghetti"},
                    {"(\u20b1535) 8pc. Chickenjoy"}
            });
            menuCategory("Spaghetti", new String[][]{
                    {"(\u20b166) Jolly Spaghetti"}
            });
            menuCategory("Burger Steak", new String[][]{
                    {"(\u20b177) 1pc. Burger Steak"},
                    {"(\u20b1117) 2pc. Burger Steak"}
            });
            menuCategory("Sides & Drinks", new String[][]{
                    {"(\u20b140) Jolly Crispy Fries"},
                    {"(\u20b166) Coke"},
                    {"(\u20b142) Pineapple Juice"},
                    {"(\u20b166) Chocolate Sundae"}
            });
        }

        menuPanel.add(space2); 
        menuPanel.add(orderBtn); 
        menuPanel.revalidate(); 
        menuPanel.repaint(); 
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Menu");
    }
  
    //to create and add a menu category to the menu panel
    public void menuCategory(String categoryName, String[][] items) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        categoryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1), categoryName));
        categoryPanel.setBackground(Color.WHITE);

        for (String[] item : items) {
            addMenuItem(item[0], categoryPanel);
        }

        menuPanel.add(categoryPanel);
    }

    //to add a menu item to the category
    public void addMenuItem(String itemName, JPanel parentPanel) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        itemPanel.setBackground(Color.WHITE);

        JLabel itemLabel = new JLabel(itemName);
        itemPanel.add(itemLabel);

        parentPanel.add(itemPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    if (e.getSource() == backBtn) {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "home");
    } else if (e.getSource() == backBtn2) {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "restoP");
    } else if (e.getSource() == profileBtn) {
        
        setVisible(false);
        new CustomerProfile(MenuSelection.this);
    } else if (e.getSource() == logoutBtn) {
        dispose();
        new AccountLogin();
    } else if(e.getSource() == quickRestaurantBtn) {
        homePanel.setVisible(false);
        quickRestoPanel.setVisible(true);
    } else if(e.getSource()==searchBtn) {
        String input = searchTf.getText().trim();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setRowFilter(RowFilter.regexFilter(input));
        table.setRowSorter(sorter);
        
    } else if(e.getSource()==quickLookupBtn) {
        String input = searchTf.getText().trim();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setRowFilter(RowFilter.regexFilter("0km"));
        table.setRowSorter(sorter);
        
    } else if (e.getSource() == orderBtn) {
        chosenCity = (short) branchComboBox.getSelectedIndex();
        if(chosenCity==0) {
            JOptionPane.showMessageDialog(this, "Invalid! Please choose a city above.", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        setVisible(false);
        new OrderSelection(MenuSelection.this);
    } else if(e.getSource()==orderBtn2) {
        String chosenRestaurant;
        String chosenLocation;
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) {
            chosenRestaurant = (String) tableModel.getValueAt(selectedRow, 1);
            chosenLocation = (String) tableModel.getValueAt(selectedRow, 2);
            
            switch(chosenRestaurant) {
                case "McDonald's":
                    chosenResto = 0;
                    break;
                case "Jollibee":
                    chosenResto = 1;
                    break;
                case "Burger King":
                    chosenResto = 2;
                    break;
                case "Greenwich":
                    chosenResto = 3;
                    break;
            }
            
            switch(chosenLocation) {
                case "Binan":
                    chosenCity = 1;
                    break;
                case "Cabuyao":
                    chosenCity = 2;
                    break;
                case "Calamba":
                    chosenCity = 3;
                    break;
                case "San Pedro":
                    chosenCity = 4;
                    break;
                case "Sta. Rosa":
                    chosenCity = 5;
                    break;
            }
            
            setVisible(false);
            new OrderSelection(MenuSelection.this);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid! Please select a row first.", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } else if (e.getSource() == viewRestaurantsBtn) {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "restoP");
    } else {
        String restaurantName = ((JButton) e.getSource()).getText();
        MenuPanel(restaurantName);
    }
  }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //All of these are for when textfields are empty, it sets a label a text; Otherwise, when textfields are not empty, it erases the labels
        
        //For login panel: username
        if(e.getSource()==searchTf) {
            searchLbl.setText("");
            if(searchTf.getText().equals("")) {
                searchLbl.setText("Search");
                table.setRowSorter(null);
            }
        }
    }
}