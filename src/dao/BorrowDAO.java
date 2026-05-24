package dao;

import dto.BorrowRecordDTO;
import java.util.List;

public interface BorrowDAO {
    boolean isBookAvailable(int bookId);
    int borrowBook(BorrowRecordDTO record);       // returns generated record_id
    boolean returnBook(int recordId);
    List<BorrowRecordDTO> getBorrowsByMember(int memberId);
    List<BorrowRecordDTO> getAllBorrows();
}
