package ui;

import javax.swing.*;
import java.awt.GridLayout;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton memberLoginBtn = new JButton("Member Login");
        JButton registerBtn = new JButton("Register");
        JButton adminLoginBtn = new JButton("Admin Login");
        JButton exitBtn = new JButton("Exit");

        memberLoginBtn.addActionListener(e -> {
            new MemberLoginFrame();
            dispose();
        });

        registerBtn.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });

        adminLoginBtn.addActionListener(e -> {
            String password = JOptionPane.showInputDialog(this, "Enter Admin Password:");
            if (password != null && password.equals("admin123")) {
                new AdminDashboardFrame();
                dispose();
            } else if (password != null) {
                JOptionPane.showMessageDialog(this, "Access Denied", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        add(memberLoginBtn);
        add(registerBtn);
        add(adminLoginBtn);
        add(exitBtn);

        setVisible(true);
    }
}
