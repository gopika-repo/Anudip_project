package service;

import dao.BookDAO;
import daoimpl.BookDAOImpl;
import dto.BookDTO;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAOImpl();

    public boolean addBook(BookDTO book) {
        return bookDAO.addBook(book);
    }

    public List<BookDTO> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public BookDTO getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }
}
