package ui;

import service.BookService;
import dto.BookDTO;
import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BrowseBooksFrame extends JFrame {
    private BookService bookService = new BookService();

    public BrowseBooksFrame() {
        setTitle("All Books");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Title", "Author", "Genre", "Available Copies"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<BookDTO> books = bookService.getAllBooks();
        for (BookDTO book : books) {
            Object[] row = {
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getAvailableCopies()
            };
            tableModel.addRow(row);
        }

        JTable booksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
