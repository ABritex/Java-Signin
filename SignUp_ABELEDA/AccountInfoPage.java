package SignUp_ABELEDA;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccountInfoPage extends JFrame {
    JLabel nameLabel, emailLabel, phoneLabel;
    JTextField nameField, emailField, phoneField;
    Container container;

    public AccountInfoPage(String email) {
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Courier", Font.BOLD, 20));
        nameField = new JTextField();

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Courier", Font.BOLD, 20));
        emailField = new JTextField();

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Courier", Font.BOLD, 20));
        phoneField = new JTextField();

        container = getContentPane();
        container.setLayout(null);
        SetBounds();
        AddComponents();

        // Load account information from file and display it
        String filename = "C:\\Users\\Admin\\Documents\\My Works\\Programming\\SignUp_ABELEDA\\Data\\Accounts";
        File file = new File(filename.toString());
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String storedEmail = parts[0];
                if (storedEmail.equals(email)) {
                    String name = parts[2];
                    String phone = parts[3];
                    nameField.setText(name);
                    emailField.setText(storedEmail);
                    phoneField.setText(phone);
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: accounts file not found.");
        }
    }

    public void SetBounds() {
        nameLabel.setBounds(233, 70, 233, 25);
        nameField.setBounds(233, 100, 233, 25);
        emailLabel.setBounds(233, 150, 233, 25);
        emailField.setBounds(233, 180, 233, 25);
        phoneLabel.setBounds(233, 230, 233, 25);
        phoneField.setBounds(233, 260, 233, 25);
    }

    public void AddComponents() {
        container.add(nameLabel);
        container.add(nameField);
        container.add(emailLabel);
        container.add(emailField);
        container.add(phoneLabel);
        container.add(phoneField);
    }

    public static void main(String[] args) {
        // Example usage:
        String email = "example@gmail.com";
        AccountInfoPage frame = new AccountInfoPage(email);
        frame.setTitle("Account Information");
        frame.setVisible(true);
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
}
