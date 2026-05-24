package ui;

import service.BorrowService;
import dto.MemberDTO;
import javax.swing.*;
import java.awt.GridLayout;

public class MemberDashboardFrame extends JFrame {
    private BorrowService borrowService = new BorrowService();

    public MemberDashboardFrame(MemberDTO member) {

        setTitle("Member Dashboard - " + member.getName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        JButton browseBooksBtn = new JButton("Browse Books");
        JButton borrowBtn = new JButton("Borrow a Book");
        JButton returnBtn = new JButton("Return a Book");
        JButton myBorrowsBtn = new JButton("My Borrows");
        JButton logoutBtn = new JButton("Logout");

        browseBooksBtn.addActionListener(e -> new BrowseBooksFrame());

        borrowBtn.addActionListener(e -> {
            String bookIdStr = JOptionPane.showInputDialog(this, "Enter Book ID:");
            if (bookIdStr != null && !bookIdStr.isEmpty()) {
                try {
                    int bookId = Integer.parseInt(bookIdStr);
                    int recordId = borrowService.borrowBook(member.getMemberId(), bookId);
                    if (recordId > 0) {
                        JOptionPane.showMessageDialog(this, "Borrowed! Record ID: " + recordId);
                    } else {
                        JOptionPane.showMessageDialog(this, "Book not available.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Book ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        returnBtn.addActionListener(e -> {
            String recordIdStr = JOptionPane.showInputDialog(this, "Enter Record ID:");
            if (recordIdStr != null && !recordIdStr.isEmpty()) {
                String bookIdStr = JOptionPane.showInputDialog(this, "Enter Book ID:");
                if (bookIdStr != null && !bookIdStr.isEmpty()) {
                    try {
                        int recordId = Integer.parseInt(recordIdStr);
                        int bookId = Integer.parseInt(bookIdStr);
                        if (borrowService.returnBook(recordId, bookId)) {
                            JOptionPane.showMessageDialog(this, "Book returned successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Return failed!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        myBorrowsBtn.addActionListener(e -> new MyBorrowsFrame(member.getMemberId()));

        logoutBtn.addActionListener(e -> {
            new MainFrame();
            dispose();
        });

        add(browseBooksBtn);
        add(borrowBtn);
        add(returnBtn);
        add(myBorrowsBtn);
        add(logoutBtn);

        setVisible(true);
    }
}
