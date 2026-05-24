package service;

import dao.BorrowDAO;
import daoimpl.BorrowDAOImpl;
import dto.BookDTO;
import dto.BorrowRecordDTO;
import java.util.List;

public class AdminService {
    private BookService bookService = new BookService();
    private BorrowDAO borrowDAO = new BorrowDAOImpl();

    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    public boolean addBook(BookDTO book) {
        return bookService.addBook(book);
    }

    public List<BorrowRecordDTO> getAllBorrows() {
        return borrowDAO.getAllBorrows();
    }
}
