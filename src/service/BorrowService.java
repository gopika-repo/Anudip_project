package service;

import dao.BorrowDAO;
import dao.BookDAO;
import daoimpl.BorrowDAOImpl;
import daoimpl.BookDAOImpl;
import dto.BorrowRecordDTO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class BorrowService {
    private BorrowDAO borrowDAO = new BorrowDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    public int borrowBook(int memberId, int bookId) {
        // 1. Check if book is available
        if (!borrowDAO.isBookAvailable(bookId)) {
            return -1;
        }

        // 2. Calculate due date = borrow_date + 14 days
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14);

        // 3. Create BorrowRecordDTO
        BorrowRecordDTO record = new BorrowRecordDTO(
            memberId,
            bookId,
            Date.valueOf(borrowDate),
            Date.valueOf(dueDate),
            "BORROWED"
        );

        // 4. Call borrowDAO.borrowBook(record)
        int recordId = borrowDAO.borrowBook(record);

        // If recordId > 0: update available copies and return recordId
        if (recordId > 0) {
            bookDAO.updateAvailableCopies(bookId, -1);
            return recordId;
        }

        // 5. Else return -1
        return -1;
    }

    public boolean returnBook(int recordId, int bookId) {
        // 1. Call borrowDAO.returnBook(recordId)
        boolean success = borrowDAO.returnBook(recordId);

        // 2. If true: update available copies and return true
        if (success) {
            bookDAO.updateAvailableCopies(bookId, 1);
            return true;
        }

        // 3. Else return false
        return false;
    }

    public List<BorrowRecordDTO> getMyBorrows(int memberId) {
        return borrowDAO.getBorrowsByMember(memberId);
    }
}
