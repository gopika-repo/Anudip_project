package ui;

import service.MemberService;
import dto.MemberDTO;
import javax.swing.*;
import java.awt.GridLayout;

public class RegisterFrame extends JFrame {
    private MemberService memberService = new MemberService();

    public RegisterFrame() {
        setTitle("Register");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton registerBtn = new JButton("Register");

        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MemberDTO member = new MemberDTO(name, email, password);
            if (memberService.registerMember(member)) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                new MainFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(registerBtn);

        setVisible(true);
    }
}
