package ui;

import service.AdminService;
import dto.BookDTO;
import dto.BorrowRecordDTO;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdminDashboardFrame extends JFrame {
    private AdminService adminService = new AdminService();

    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton addBookBtn = new JButton("Add Book");
        JButton viewBooksBtn = new JButton("View All Books");
        JButton viewBorrowsBtn = new JButton("View All Borrows");
        JButton logoutBtn = new JButton("Logout");

        addBookBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Enter Title:");
            if (title == null || title.isEmpty()) return;

            String author = JOptionPane.showInputDialog(this, "Enter Author:");
            if (author == null || author.isEmpty()) return;

            String genre = JOptionPane.showInputDialog(this, "Enter Genre:");
            if (genre == null || genre.isEmpty()) return;

            String copiesStr = JOptionPane.showInputDialog(this, "Enter Total Copies:");
            if (copiesStr == null || copiesStr.isEmpty()) return;

            try {
                int copies = Integer.parseInt(copiesStr);
                BookDTO book = new BookDTO(title, author, genre, copies, copies);
                if (adminService.addBook(book)) {
                    JOptionPane.showMessageDialog(this, "Book added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        viewBooksBtn.addActionListener(e -> new BrowseBooksFrame());

        viewBorrowsBtn.addActionListener(e -> {
            JFrame borrowsFrame = new JFrame("All Borrow Records");
            borrowsFrame.setSize(700, 400);
            borrowsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            borrowsFrame.setLocationRelativeTo(null);
            borrowsFrame.setLayout(new BorderLayout());

            String[] columnNames = {"Record ID", "Member ID", "Book ID", "Borrow Date", "Due Date", "Return Date", "Status"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            List<BorrowRecordDTO> allBorrows = adminService.getAllBorrows();
            for (BorrowRecordDTO record : allBorrows) {
                Object[] row = {
                    record.getRecordId(),
                    record.getMemberId(),
                    record.getBookId(),
                    record.getBorrowDate(),
                    record.getDueDate(),
                    record.getReturnDate(),
                    record.getStatus()
                };
                tableModel.addRow(row);
            }

            JTable borrowsTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(borrowsTable);

            borrowsFrame.add(scrollPane, BorderLayout.CENTER);
            borrowsFrame.setVisible(true);
        });

        logoutBtn.addActionListener(e -> {
            new MainFrame();
            dispose();
        });

        add(addBookBtn);
        add(viewBooksBtn);
        add(viewBorrowsBtn);
        add(logoutBtn);

        setVisible(true);
    }
}
