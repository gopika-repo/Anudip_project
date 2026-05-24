package ui;

import service.MemberService;
import dto.MemberDTO;
import javax.swing.*;
import java.awt.GridLayout;

public class MemberLoginFrame extends JFrame {
    private MemberService memberService = new MemberService();

    public MemberLoginFrame() {
        setTitle("Member Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            MemberDTO member = memberService.loginMember(email, password);
            if (member != null) {
                new MemberDashboardFrame(member);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginBtn);

        setVisible(true);
    }
}
