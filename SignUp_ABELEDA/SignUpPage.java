package SignUp_ABELEDA;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.text.*;

public class SignUpPage extends JFrame {
    private JLabel SignUp, FirstName, LastName, MiddleName, Email, ReEmail, PhoneNumber, Code, Password, RePassword,
            Gender, BirthDate, Address, Term;
    private JTextField FirstNameText, LastNameText, MiddleNameText, EmailText, ReEmailText, PhoneNumberText, CodeText;
    private JPasswordField PasswordText, RePasswordText;
    private JButton SendCode;
    private JComboBox Month, Day, Year;
    private JRadioButton Male, Female, Other;
    private JTextArea AdressText;
    private JCheckBox TermsNCondition;
    private JButton Submit;

    public String filename = "C:\\Users\\Admin\\Documents\\My Works\\Programming\\SignUp_ABELEDA\\Data\\Accounts";
    Container container;

    class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String text,
                AttributeSet attr) throws BadLocationException {
            String filteredText = filterString(text);
            super.insertString(fb, offset, filteredText, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                AttributeSet attrs) throws BadLocationException {
            String filteredText = filterString(text);
            super.replace(fb, offset, length, filteredText, attrs);
        }

        private String filterString(String text) {
            StringBuilder filteredText = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (Character.isDigit(c)) {
                    filteredText.append(c);
                }
            }
            return filteredText.toString();
        }
    }

    class LimitedLengthDocument extends PlainDocument {
        private int limit;

        public LimitedLengthDocument(int limit) {
            super();
            this.limit = limit;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    // usage

    public SignUpPage() {
        SignUp = new JLabel("Sign Up Form");

        SignUp.setFont(new Font("Courier", Font.BOLD, 20));

        FirstName = new JLabel("First Name :");
        FirstName.setFont(new Font("Courier", ABORT, 15));
        FirstNameText = new JTextField();

        LastName = new JLabel("Last Name :");
        LastName.setFont(new Font("Courier", ABORT, 15));
        LastNameText = new JTextField();

        MiddleName = new JLabel("Middle Name :");
        MiddleName.setFont(new Font("Courier", ABORT, 15));
        MiddleNameText = new JTextField();

        Email = new JLabel("Email :");
        Email.setFont(new Font("Courier", ABORT, 15));
        EmailText = new JTextField();

        ReEmail = new JLabel("Re-enter Email :");
        ReEmail.setFont(new Font("Courier", ABORT, 15));
        ReEmailText = new JTextField();

        PhoneNumber = new JLabel("Phone Number (+63) :");
        PhoneNumber.setFont(new Font("Courier", ABORT, 15));
        PhoneNumberText = new JTextField();
        ((AbstractDocument) PhoneNumberText.getDocument()).setDocumentFilter(new NumericFilter());
        PhoneNumberText.setDocument(new LimitedLengthDocument(10));

        Code = new JLabel("Code :");
        Code.setFont(new Font("Courier", ABORT, 15));
        CodeText = new JTextField();
        SendCode = new JButton("Send Code");

        Password = new JLabel("Password :");
        Password.setFont(new Font("Courier", ABORT, 15));
        PasswordText = new JPasswordField();

        RePassword = new JLabel("Re-enter Password :");
        RePassword.setFont(new Font("Courier", ABORT, 15));
        RePasswordText = new JPasswordField();

        Gender = new JLabel("Gender :");
        Gender.setFont(new Font("Courier", ABORT, 15));
        Male = new JRadioButton("Male");
        Female = new JRadioButton("Female");
        Other = new JRadioButton("Others");

        BirthDate = new JLabel("Birthdate :");
        BirthDate.setFont(new Font("Courier", ABORT, 15));
        String[] month = { "January", "February", "March", "April", "June", "July", "August", "September", "October",
                "November", "December" };
        Month = new JComboBox(month);
        String[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
        Day = new JComboBox(day);
        String[] year = { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
                "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022",
                "2023" };
        Year = new JComboBox(year);

        Address = new JLabel("Address :");
        Address.setFont(new Font("Courier", ABORT, 15));
        AdressText = new JTextArea();

        TermsNCondition = new JCheckBox(
                "I accept the");
        TermsNCondition.setFont(new Font("Courier", ABORT, 10));

        Term = new JLabel("<html><a href=''>terms & conditions</a></html>");
        Term.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Term.setFont(new Font("Courier", ABORT, 10));
        Term.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the hyperlink in the default web browser
                try {
                    Desktop.getDesktop().browse(new URI("https://www.example.com/terms-and-conditions"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        Submit = new JButton("Submit Form");

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user's input from the text fields
                String firstName = FirstNameText.getText();
                String lastName = LastNameText.getText();
                String middleName = MiddleNameText.getText();
                String email = EmailText.getText();
                String phoneNumber = PhoneNumberText.getText();
                String code = CodeText.getText();
                String password = new String(PasswordText.getPassword());
                String gender = "";
                if (Male.isSelected()) {
                    gender = "Male";
                } else if (Female.isSelected()) {
                    gender = "Female";
                } else if (Other.isSelected()) {
                    gender = "Other";
                }
                String birthdate = Month.getSelectedItem().toString() + " " + Day.getSelectedItem().toString() + ", "
                        + Year.getSelectedItem().toString();
                String address = AdressText.getText();

                // Check if the user has accepted the terms and conditions
                if (!TermsNCondition.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please accept the terms and conditions to continue.");
                    return;
                }

                // Validate the user's input (you can add your own validation code here)
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()
                        || code.isEmpty() || password.isEmpty() || gender.isEmpty() || birthdate.isEmpty()
                        || address.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                    return;
                }

                // Save the user's information to a file
                Path file = Paths.get(filename.toString());
                try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                    writer.write(firstName + "," + lastName + "," + middleName + "," + email + "," + phoneNumber + ","
                            + code + "," + password + "," + gender + "," + birthdate + "," + address);
                    writer.newLine();
                    JOptionPane.showMessageDialog(null, "Your information has been saved to the file.");
                    // Clear the text fields
                    FirstNameText.setText("");
                    LastNameText.setText("");
                    MiddleNameText.setText("");
                    EmailText.setText("");
                    PhoneNumberText.setText("");
                    CodeText.setText("");
                    PasswordText.setText("");
                    Male.setSelected(false);
                    Female.setSelected(false);
                    Other.setSelected(false);
                    Month.setSelectedIndex(0);
                    Day.setSelectedIndex(0);
                    Year.setSelectedIndex(0);
                    AdressText.setText("");
                    TermsNCondition.setSelected(false);
                    FirstPage firstPage = new FirstPage();
                    firstPage.setSize(700, 600);
                    firstPage.setVisible(true);
                    dispose();

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while saving your information to the file.");
                }
            }
        });
        container = getContentPane();
        container.setLayout(null);
        SetBounds();
        AddComponents();
    }

    public void SetBounds() {
        SignUp.setBounds(280, 10, 600, 30);
        FirstName.setBounds(10, 60, 100, 25);
        FirstNameText.setBounds(10, 90, 130, 25);
        LastName.setBounds(180, 60, 100, 25);
        LastNameText.setBounds(180, 90, 130, 25);
        MiddleName.setBounds(10, 120, 100, 25);
        MiddleNameText.setBounds(10, 150, 130, 25);
        Email.setBounds(10, 180, 100, 25);
        EmailText.setBounds(10, 210, 300, 25);
        ReEmail.setBounds(10, 240, 140, 25);
        ReEmailText.setBounds(10, 270, 300, 25);
        PhoneNumber.setBounds(10, 300, 200, 25);
        PhoneNumberText.setBounds(10, 330, 300, 25);
        Code.setBounds(10, 360, 100, 25);
        CodeText.setBounds(10, 390, 180, 25);
        SendCode.setBounds(200, 390, 110, 25);
        Password.setBounds(370, 60, 100, 25);
        PasswordText.setBounds(370, 90, 300, 25);
        RePassword.setBounds(370, 120, 150, 25);
        RePasswordText.setBounds(370, 150, 300, 25);
        Gender.setBounds(370, 180, 100, 25);
        Male.setBounds(370, 210, 60, 25);
        Female.setBounds(440, 210, 70, 25);
        Other.setBounds(520, 210, 100, 25);
        BirthDate.setBounds(370, 240, 100, 25);
        Month.setBounds(370, 270, 80, 25);
        Day.setBounds(470, 270, 40, 25);
        Year.setBounds(530, 270, 80, 25);
        Address.setBounds(370, 300, 100, 25);
        AdressText.setBounds(370, 330, 300, 90);
        TermsNCondition.setBounds(10, 440, 82, 25);
        Term.setBounds(91, 440, 250, 25);
        Submit.setBounds(240, 480, 200, 36);

    }

    public void AddComponents() {
        container.add(SignUp);
        container.add(FirstName);
        container.add(FirstNameText);
        container.add(LastName);
        container.add(LastNameText);
        container.add(MiddleName);
        container.add(MiddleNameText);
        container.add(Email);
        container.add(EmailText);
        container.add(ReEmail);
        container.add(ReEmailText);
        container.add(PhoneNumber);
        container.add(PhoneNumberText);
        container.add(Code);
        container.add(CodeText);
        container.add(SendCode);
        container.add(Password);
        container.add(PasswordText);
        container.add(RePassword);
        container.add(RePasswordText);
        container.add(Gender);
        container.add(Male);
        container.add(Female);
        container.add(Other);
        container.add(BirthDate);
        container.add(Month);
        container.add(Day);
        container.add(Year);
        container.add(Address);
        container.add(AdressText);
        container.add(TermsNCondition);
        container.add(Submit);
        container.add(Term);

    }

    public static void main(String[] Args) {
        SignUpPage Frame = new SignUpPage();
        Frame.setTitle("SignUp Form");
        Frame.setVisible(true);
        Frame.setSize(700, 600);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(true);
    }
}
