package com.mycompany.group2_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class MenuSelection extends JFrame implements ActionListener {

    private JPanel restoPanel, menuPanel, homePanel;
    private JComboBox<String> branchComboBox;
    private JButton orderBtn, profileBtn, logoutBtn, viewRestaurantsBtn, backBtn, backBtn2;
    private JLabel space1, space2;
    private Color maroon = new Color(113, 45, 59);
    private ImageIcon homePage, mcdo, jobee, green, burger;;
    
    public short chosenCity;
    
    // Logo
    private ImageIcon logoIcon = new ImageIcon("fordaFood.png");

    public MenuSelection() {
        setTitle("Restaurant Profile Manager");
        setSize(464, 737);
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
        homePage = new ImageIcon("home_page.png");
        JLabel background = new JLabel(homePage);
        background.setBounds(0, 0, 450, 700); 

        viewRestaurantsBtn = new JButton("View Restaurants");
        viewRestaurantsBtn.setBounds(125, 410, 200, 50); 
        viewRestaurantsBtn.setFont(new Font("Arial", Font.BOLD, 17));
        viewRestaurantsBtn.setForeground(maroon);
        viewRestaurantsBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        viewRestaurantsBtn.setOpaque(false);
        viewRestaurantsBtn.setContentAreaFilled(false);
        viewRestaurantsBtn.addActionListener(this);

        profileBtn = new JButton("Profile");
        profileBtn.setBounds(125, 470, 200, 50); 
        profileBtn.setFont(new Font("Arial", Font.BOLD, 17));
        profileBtn.setForeground(maroon);
        profileBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        profileBtn.setOpaque(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.addActionListener(this);

        logoutBtn = new JButton("Log Out");
        logoutBtn.setBounds(125, 530, 200, 50); 
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 17));
        logoutBtn.setForeground(maroon);
        logoutBtn.setBorder(BorderFactory.createLineBorder(maroon, 3));
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.addActionListener(this);

        panel.add(viewRestaurantsBtn);
        panel.add(profileBtn);
        panel.add(logoutBtn);
        panel.add(background);

        return panel;
    }

    // Panel for restaurant panel
    private JPanel RestoPanel(Color maroon) {
        JPanel panel = new JPanel(new BorderLayout());

        backBtn = new JButton("<                                      ");
        backBtn.setPreferredSize(new Dimension(5, 50)); 
        backBtn.setFont(new Font("Arial", Font.BOLD, 35));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(maroon); 
        backBtn.setBorder(BorderFactory.createLineBorder(maroon));
        backBtn.setOpaque(true); 
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
  
    public short chosenResto;
  
    //restaurant choices
    private void RestoButton(String name, JPanel parentPanel, Image restoPic) {
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
    private JPanel MenuPanel(Color backgroundColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(backgroundColor);

        branchComboBox = new JComboBox<>(new String[]{
                "Muntinlupa", "San Pedro", "Binan", "Sta. Rosa", "Cabuyao"
        });
        branchComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(branchComboBox);
       
        space1 = new JLabel("  ");
        space1.setFont(new Font("arial", Font.PLAIN, 7));
       
        space2 = new JLabel("  ");
        space2.setFont(new Font("arial", Font.PLAIN, 10));
       
        orderBtn = new JButton("Order Now");
        orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderBtn.setPreferredSize(new Dimension(150, 50));
        orderBtn.addActionListener(this);

        return panel;
    }
  
    private void MenuPanel(String restaurantName) {
        // Clears the current menu and set a new one based on restaurant selection
        menuPanel.removeAll();

        branchComboBox = new JComboBox<>(new String[]{
            "-select-", "Binan", "Cabuyao", "Calamba", "San Pedro", "Sta. Rosa"
        });
        
        //panel for second back button
        JPanel backBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        backBtnPanel.setOpaque(false); 
        backBtn2 = new JButton("<      ");
        backBtn2.setPreferredSize(new Dimension(80, 50));
        backBtn2.setFont(new Font("Arial", Font.BOLD, 20));
        backBtn2.setForeground(Color.WHITE);
        backBtn2.setBackground(maroon); 
        backBtn2.setBorder(BorderFactory.createLineBorder(maroon));
        backBtn2.setOpaque(true);
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
                    {"(₱75) Whopper Jr."},
                    {"(₱177) Whopper"}
            });
            menuCategory("4-Cheese Whopper", new String[][]{
                    {"(₱99) 4-Cheese Whopper Jr."},
                    {"(₱198) 4-Cheese Whopper"},
                    {"(₱173) Bacon 4-Cheese Whopper Jr."},
                    {"(₱282) Bacon 4-Cheese Whopper"}
            });
            menuCategory("X-tra Long Chicken", new String[][]{
                    {"(₱59) X-tra Long Chicken Jr. Sandwich"},
                    {"(₱99) X-tra Long Chicken Sandwich"}
            });
            menuCategory("Plant-Based Whopper", new String[][]{
                    {"(₱136) Plant-Based Whopper Jr."},
                    {"(₱295) Plant-Based Whopper"}
            });
            menuCategory("Chicken King", new String[][]{
                    {"(₱269) BLT Spicy Chicken King"},
                    {"(₱215) Chicken King"},
                    {"(₱229) Spicy Chicken King"}
            });
            menuCategory("Flame-Grilled Cheeseburger", new String[][]{
                    {"(₱225) Flame-Grilled BBQ Hamburger"},
                    {"(₱75) Flame-Grilled Hamburger"},
                    {"(₱85) Flame-Grilled Cheeseburger"},
                    {"(₱159) Flame-Grilled Double Cheeseburger"}
            });
            menuCategory("Drinks", new String[][]{
                    {"(₱75) Coke"},
                    {"(₱63) Coke Float"},
                    {"(₱73) Rootbeer Float"}
            });
        } else if (restaurantName.equals("Greenwich")) {
            chosenResto = 3;
            menuCategory("Feel G Deals", new String[][]{
                    {"(₱319) 9\" Pizza - Buy One Take One"},
                    {"(₱499) 12\" Pizza - Buy One Take One"},
                    {"(₱679) Buy One Take Two"}
            });
            menuCategory("Pizzawrap", new String[][]{
                    {"(₱77) Hawaiian Overload Pizzawrap"},
                    {"(₱122) Hawaiian Overload Pizzawrap Value Meal"},
                    {"(₱223) Hawaiian Overload Pizzawrap Pack of Three"},
                    {"(₱223) Assorted Pizzawrap Pack of Three"},
                    {"(₱111) Roast Beef & Cream Cheese Pizzawrap"}
            });
            menuCategory("Pizza", new String[][]{
                    {"(₱174) All Meat Overload"},
                    {"(₱140) Ham & Cheese Classic"},
                    {"(₱140) Cheeseburger Classic"},
                    {"(₱140) Cheesy Bacon & Ham Classic"},
                    {"(₱162) Beef & Pineapples Overload"}
            });
            menuCategory("Pasta", new String[][]{
                    {"(₱99) Lasagna Supreme"},
                    {"(₱99) Meaty Spaghetti"},
                    {"(₱144) Creamy Bacon Carbonara"}
            });
            menuCategory("Chicken and Sides", new String[][]{
                    {"(₱62) Classic Choco Frozen Cake"},
                    {"(₱308) Chicken and Waves"}
            });
            menuCategory("Drinks", new String[][]{
                    {"(₱99) 1.5L Coke"},
                    {"(₱85) 1L Pepsi"}
            });
        } else if (restaurantName.equals("McDonald's")) {
            chosenResto = 0;
            menuCategory("Breakfast", new String[][]{
                    {"(₱158) Cheesy Eggdesal"},
                    {"(₱37) Hash Browns"},
                    {"(₱63) Sausage Platter with Rice"},
                    {"(₱66) 3pc. Hotcakes"}
            });
            menuCategory("Fries", new String[][]{
                    {"(₱79) Fries"}
            });
            menuCategory("Drinks & Desserts", new String[][]{
                    {"(₱66) Coke"},
                    {"(₱72) Orange Juice"},
                    {"(₱52) Coke McFloat"},
                    {"(₱57) McFlurry with Oreo Cookies"},
                    {"(₱50) Hot Fudge Sundae"}
            });
            menuCategory("Burgers", new String[][]{
                    {"(₱154) Big Mac"},
                    {"(₱54) Burger McDo"},
                    {"(₱135) Double Cheeseburger"},
                    {"(₱146) McChicken Sandwich"},
                    {"(₱52) McCrispy Chicken Sandwich"}
            });
            menuCategory("Chicken", new String[][]{
                    {"(₱118) 1pc. Chicken McDo with McSpaghetti"},
                    {"(₱80) 1pc. Chicken McDo with Rice"},
                    {"(₱60) McCrispy Chicken Fillet with Rice"},
                    {"(₱158) 2pc. Chicken McDo with Rice"},
                    {"(₱178) 6pc. Chicken McNuggets with Fries"}
            });
        } else if (restaurantName.equals("Jollibee")) {
            chosenResto = 1;
            menuCategory("Burgers", new String[][]{
                    {"(₱40) Yumburger"},
                    {"(₱66) Cheesy Yumburger"},
                    {"(₱169) Champ"},
                    {"(₱239) Aloha Champ"},
                    {"(₱91) Bacon Cheesy Yumburger"}
            });
            menuCategory("Chickenjoy", new String[][]{
                    {"(₱82) 1pc. Chickenjoy"},
                    {"(₱163) 2pc. Chickenjoy"},
                    {"(₱140) 1pc. Chickenjoy with Burger Steak"},
                    {"(₱139) 1pc. Chickenjoy with Jolly Spaghetti"},
                    {"(₱535) 8pc. Chickenjoy"}
            });
            menuCategory("Spaghetti", new String[][]{
                    {"(₱66) Jolly Spaghetti"}
            });
            menuCategory("Burger Steak", new String[][]{
                    {"(₱77) 1pc. Burger Steak"},
                    {"(₱117) 2pc. Burger Steak"}
            });
            menuCategory("Sides & Drinks", new String[][]{
                    {"(₱40) Jolly Crispy Fries"},
                    {"(₱66) Coke"},
                    {"(₱42) Pineapple Juice"},
                    {"(₱66) Chocolate Sundae"}
            });
        }

        menuPanel.add(space2); 
        menuPanel.add(orderBtn); 
        menuPanel.revalidate(); 
        menuPanel.repaint(); 
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Menu");
    }
  
    //to create and add a menu category to the menu panel
    private void menuCategory(String categoryName, String[][] items) {
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
    private void addMenuItem(String itemName, JPanel parentPanel) {
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
        dispose();
        //new CustomerProfile();
    } else if (e.getSource() == logoutBtn) {
        dispose();
        //new AccountLogin();
    } else if (e.getSource() == orderBtn) {
        chosenCity = (short) branchComboBox.getSelectedIndex();
        setVisible(false);
       // new OrderSelection(MenuSelection.this);
    } else if (e.getSource() == viewRestaurantsBtn) {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "restoP");
    } else {String restaurantName = ((JButton) e.getSource()).getText();
        MenuPanel(restaurantName);
    }
  }
}
    