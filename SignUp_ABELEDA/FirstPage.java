package SignUp_ABELEDA;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class FirstPage extends JFrame {
    JButton loginButton, signupButton;
    private Container container;

    public FirstPage() {
        setTitle("SignUp Form");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        loginButton = new JButton("Login");

        signupButton = new JButton("Sign up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpPage signUpPage = new SignUpPage();
                signUpPage.setSize(700, 600);
                signUpPage.setVisible(true);
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setSize(700, 600);
                loginPage.setVisible(true);
                dispose();
            }
        });
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();

    }

    public void setBounds() {
        loginButton.setBounds(233, 190, 233, 50);
        signupButton.setBounds(233, 260, 233, 50);
    }

    public void addComponents() {
        container.add(loginButton);
        container.add(signupButton);
    }

    public static void main(String[] args) {
        FirstPage firstPage = new FirstPage();
        firstPage.setVisible(true);
    }

}
