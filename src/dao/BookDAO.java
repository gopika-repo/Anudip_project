package dao;

import dto.BookDTO;
import java.util.List;

public interface BookDAO {
    boolean addBook(BookDTO book);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(int bookId);
    boolean updateAvailableCopies(int bookId, int delta);  // +1 on return, -1 on borrow
}
