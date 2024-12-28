import java.awt.*;

import java.awt.event.*;

import java.io.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;



public class signup extends JFrame {



private static final long serialVersionUID = 1L;

private JPanel contentPane;

private JTextField usernameField;

private JPasswordField passwordField;

private JPasswordField confirmPasswordField;

private JTextField emailField;

private JButton togglePasswordVisibility;

private boolean isPasswordVisible = false;



/**

* Launch the application.

*/

public static void main(String[] args) {

EventQueue.invokeLater(new Runnable() {

public void run() {

try {

signup frame = new signup();

frame.setVisible(true);

} catch (Exception e) {

e.printStackTrace();

}

}

});

}



/**

* Create the frame.

*/

public signup() {

setTitle("Sign Up Page");

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

setBounds(100, 100, 500, 500);

contentPane = new JPanel();

contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

contentPane.setLayout(null);

contentPane.setBackground(new Color(240, 248, 255));



setContentPane(contentPane);



JLabel lblTitle = new JLabel("Sign Up");

lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));

lblTitle.setBounds(180, 30, 200, 50);

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



JLabel lblEmail = new JLabel("Email:");

lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));

lblEmail.setBounds(50, 160, 120, 30);

contentPane.add(lblEmail);



emailField = new JTextField();

emailField.setFont(new Font("Tahoma", Font.PLAIN, 16));

emailField.setBounds(180, 160, 250, 30);

contentPane.add(emailField);

emailField.setColumns(10);



JLabel lblPassword = new JLabel("Password:");

lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

lblPassword.setBounds(50, 220, 120, 30);

contentPane.add(lblPassword);



passwordField = new JPasswordField();

passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));

passwordField.setBounds(180, 220, 250, 30);

contentPane.add(passwordField);



togglePasswordVisibility = new JButton("👁");

togglePasswordVisibility.setBounds(440, 220, 30, 30);

togglePasswordVisibility.setFocusPainted(false);

togglePasswordVisibility.setFont(new Font("Tahoma", Font.PLAIN, 16));

contentPane.add(togglePasswordVisibility);



togglePasswordVisibility.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {

if (isPasswordVisible) {

passwordField.setEchoChar('*');

isPasswordVisible = false;

} else {

passwordField.setEchoChar((char) 0);

isPasswordVisible = true;

}

}

});



JLabel lblConfirmPassword = new JLabel("Confirm Password:");

lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

lblConfirmPassword.setBounds(50, 280, 160, 30);

contentPane.add(lblConfirmPassword);



confirmPasswordField = new JPasswordField();

confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));

confirmPasswordField.setBounds(220, 280, 210, 30);

contentPane.add(confirmPasswordField);



JButton btnSignUp = new JButton("Sign Up");

btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 16));

btnSignUp.setBounds(180, 350, 100, 40);

btnSignUp.setBackground(new Color(60, 179, 113));

btnSignUp.setForeground(Color.WHITE);

btnSignUp.setFocusPainted(false);

contentPane.add(btnSignUp);



btnSignUp.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {

String username = usernameField.getText();

String email = emailField.getText();

String password = new String(passwordField.getPassword());

String confirmPassword = new String(confirmPasswordField.getPassword());



if (!password.equals(confirmPassword)) {

JOptionPane.showMessageDialog(contentPane, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);

return;

}



File userFile = new File("src/Database/Users.txt");



try {

if (!userFile.exists()) {

userFile.getParentFile().mkdirs();

userFile.createNewFile();

}



BufferedReader reader = new BufferedReader(new FileReader(userFile));

String line;

while ((line = reader.readLine()) != null) {

String[] userDetails = line.split(",");

if (userDetails[1].equals(email)) {

JOptionPane.showMessageDialog(contentPane, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);

reader.close();

return;

}

}

reader.close();



BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true));

writer.write(username + "," + email + "," + password);

writer.newLine();

writer.close();



JOptionPane.showMessageDialog(contentPane, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);



dispose();

demoframe demoFrame = new demoframe();

demoFrame.setVisible(true);



} catch (IOException ioException) {

ioException.printStackTrace();

}

}

});



JButton btnCancel = new JButton("Login");

btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));

btnCancel.setBounds(330, 350, 100, 40);

btnCancel.setBackground(new Color(220, 20, 60));

btnCancel.setForeground(Color.WHITE);

btnCancel.setFocusPainted(false);

contentPane.add(btnCancel);



btnCancel.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {

dispose();

demoframe demoFrame = new demoframe();

demoFrame.setVisible(true);

}

});

}

}

