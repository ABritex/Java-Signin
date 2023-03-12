package SignUp_ABELEDA;

import javax.swing.*;

import mix.login;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Container;

public class LoginPage extends JFrame implements ActionListener {
    JLabel Login, emailLabel, passwordLabel;
    JTextField emailField;
    JPasswordField passwordField;
    JButton loginButton;
    public String filename = "C:\\Users\\Admin\\Documents\\My Works\\Programming\\SignUp_ABELEDA\\Data\\Accounts";
    Container container;

    public LoginPage() {
        Login = new JLabel("Login");
        Login.setFont(new Font("Courier", Font.BOLD, 20));

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Courier", ABORT, 20));
        emailField = new JTextField();

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Courier", ABORT, 20));
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");

        loginButton.addActionListener(this);

        container = getContentPane();
        container.setLayout(null);
        SetBounds();
        AddComponents();
    }

    public void SetBounds() {
        Login.setBounds(320, 10, 600, 30);

        emailLabel.setBounds(233, 70, 233, 25);
        emailField.setBounds(233, 100, 233, 25);
        passwordLabel.setBounds(233, 150, 233, 25);
        passwordField.setBounds(233, 180, 233, 25);
        loginButton.setBounds(233, 230, 233, 25);
    }

    public void AddComponents() {
        container.add(Login);
        container.add(emailLabel);
        container.add(emailField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(loginButton);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            boolean success = authenticate(email, password);
            if (success) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                AccountInfoPage accountInfoPage = new AccountInfoPage(password);
                accountInfoPage.setSize(700, 600);
                accountInfoPage.setVisible(true);
                dispose(); // close the current login page
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect email or password.");
            }
        }
    }

    private boolean authenticate(String email, String password) {
        File file = new File(filename.toString());
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String storedEmail = parts[3];
                String storedPassword = parts[6];
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
            return false;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: users.txt file not found.");
            return false;
        }
    }

    public static void main(String[] args) {

        LoginPage Frame = new LoginPage();
        Frame.setTitle("SignUp Form");
        Frame.setVisible(true);
        Frame.setSize(700, 600);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(true);
    }

}
