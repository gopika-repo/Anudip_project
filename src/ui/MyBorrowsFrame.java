package ui;

import service.BorrowService;
import dto.BorrowRecordDTO;
import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MyBorrowsFrame extends JFrame {
    private BorrowService borrowService = new BorrowService();

    public MyBorrowsFrame(int memberId) {
        setTitle("My Borrow History");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnNames = {"Record ID", "Book ID", "Borrow Date", "Due Date", "Return Date", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<BorrowRecordDTO> borrows = borrowService.getMyBorrows(memberId);
        for (BorrowRecordDTO record : borrows) {
            Object[] row = {
                record.getRecordId(),
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

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
