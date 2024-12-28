import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class demoframe extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                demoframe frame = new demoframe();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public demoframe() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Login Page");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setBounds(150, 30, 200, 50);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(50, 100, 120, 30);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBounds(180, 100, 250, 30);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(50, 160, 120, 30);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBounds(180, 160, 250, 30);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.setBounds(180, 230, 100, 40);
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        contentPane.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Adjust the file path based on your project structure
                File userFile = new File("src/Database/Users.txt"); 

                try {
                    if (!userFile.exists()) {
                        JOptionPane.showMessageDialog(contentPane, "No users registered!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    BufferedReader reader = new BufferedReader(new FileReader(userFile));
                    String line;
                    boolean isAuthenticated = false;

                    while ((line = reader.readLine()) != null) {
                        String[] userDetails = line.split(",");
                        if (userDetails.length != 3) continue; // Assuming username, email, password format
                        if (userDetails[0].trim().equals(username) && userDetails[2].trim().equals(password)) {
                            isAuthenticated = true;
                            break;
                        }
                    }
                    reader.close();

                    if (isAuthenticated) {
                        JOptionPane.showMessageDialog(contentPane, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        HomePage homePage = new HomePage();
                        homePage.setVisible(true);
                    } else {
                        int choice = JOptionPane.showConfirmDialog(contentPane, "Invalid username or password. Please try again or sign up.", "Login Failed", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            // User wants to try again
                            usernameField.setText("");
                            passwordField.setText(""); 
                        } else if (choice == JOptionPane.NO_OPTION) {
                            // User wants to sign up
                            dispose();
                            signup signupPage = new signup();
                            signupPage.setVisible(true);
                        } else if (choice == JOptionPane.CANCEL_OPTION) {
                            // User cancels and closes the application
                            dispose(); 
                        }
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}



