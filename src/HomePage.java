import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel welcomeLabel;
    private JLabel imageLabel; 

    public HomePage() {
        setTitle("Furna - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Set background image
        try {
            Image backgroundImage = ImageIO.read(new File("C:\\Users\\Laptop Wala\\Downloads\\WhatsApp Image 2024-12-13 at 17.39.22_7aec411d.jpg")); 
            contentPane.setBackground(new Color(0)); // Transparent color with image pattern
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading background image.");
        }

        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Welcome to Furna!");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(250, 10, 300, 50);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE); 
        contentPane.add(lblTitle);

        // Create a vertical dashboard panel
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
        dashboardPanel.setBounds(20, 50, 150, 400); // Adjust bounds as needed
        dashboardPanel.setBackground(new Color(255, 255, 255, 150)); // Semi-transparent white background
        contentPane.add(dashboardPanel);

        // Create dashboard buttons
        JButton productsButton = new JButton("Products");
        JButton myCartButton = new JButton("My Cart");
        JButton adminButton = new JButton("Admin");

        // Set button background and foreground colors
        productsButton.setBackground(new Color(255, 255, 255)); // Light blue
        productsButton.setForeground(Color.BLACK);
        myCartButton.setBackground(new Color(255, 255, 255)); // Light blue
        myCartButton.setForeground(Color.BLACK);
        adminButton.setBackground(new Color(255, 255, 255)); // Light blue
        adminButton.setForeground(Color.BLACK);

        // Add buttons to the dashboard panel
        dashboardPanel.add(productsButton);
        dashboardPanel.add(myCartButton);
        dashboardPanel.add(adminButton);

        // Add action listeners to buttons
        productsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current HomePage
                ProductsPage productsPage = new ProductsPage();
                productsPage.setVisible(true);
            }
        });

        myCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current HomePage
                MyCartPage myCartPage = new MyCartPage();
                myCartPage.setVisible(true);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current HomePage
                AdminLogin adminLoginPage = new AdminLogin(); 
                adminLoginPage.setVisible(true);
            }
        });

        // Current page indicator
        JLabel currentPageLabel = new JLabel("Current Page: Home");
        currentPageLabel.setBounds(20, 550, 200, 30); // Adjust bounds as needed
        currentPageLabel.setForeground(Color.WHITE); // Set text color to white
        contentPane.add(currentPageLabel);
    }
}

class ProductsPage extends JFrame {
    // ... Implementation for displaying products ...
}

class MyCartPage extends JFrame {
    // ... Implementation for displaying user's cart ...
}

class AdminLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static List<String> adminUsernames = new ArrayList<>(); 

    public AdminLogin() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Initialize admin usernames (replace with actual admin usernames)
        adminUsernames.add("admin1");
        adminUsernames.add("admin2"); 

        JLabel lblTitle = new JLabel("Admin Login");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setBounds(120, 30, 200, 50);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(50, 100, 120, 30);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBounds(180, 100, 200, 30);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(50, 160, 120, 30);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBounds(180, 160, 200, 30);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.setBounds(150, 220, 100, 40);
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        contentPane.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (adminUsernames.contains(username) && password.equals("admin")) { // Example: Basic password check
                    JOptionPane.showMessageDialog(contentPane, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    AdminControlPanel adminControlPanel = new AdminControlPanel();
                    adminControlPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Invalid admin credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

class AdminControlPanel extends JFrame {
    // ... Implementation for admin control panel (product management, etc.) ...
}