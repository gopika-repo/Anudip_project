package daoimpl;

import dao.BorrowDAO;
import db.DBConnection;
import dto.BorrowRecordDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAOImpl implements BorrowDAO {

    @Override
    public boolean isBookAvailable(int bookId) {
        String sql = "SELECT available_copies FROM books WHERE book_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("available_copies") > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error checking book availability: " + e.getMessage());
        }
        return false;
    }

    @Override
    public int borrowBook(BorrowRecordDTO record) {
        String sql = "INSERT INTO borrow_records(member_id, book_id, borrow_date, due_date, status) VALUES(?, ?, ?, ?, 'BORROWED')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, record.getMemberId());
            pstmt.setInt(2, record.getBookId());
            pstmt.setDate(3, record.getBorrowDate());
            pstmt.setDate(4, record.getDueDate());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error borrowing book: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean returnBook(int recordId) {
        String sql = "UPDATE borrow_records SET status='RETURNED', return_date=CURDATE() WHERE record_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, recordId);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error returning book: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<BorrowRecordDTO> getBorrowsByMember(int memberId) {
        List<BorrowRecordDTO> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records WHERE member_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, memberId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BorrowRecordDTO record = new BorrowRecordDTO();
                record.setRecordId(rs.getInt("record_id"));
                record.setMemberId(rs.getInt("member_id"));
                record.setBookId(rs.getInt("book_id"));
                record.setBorrowDate(rs.getDate("borrow_date"));
                record.setDueDate(rs.getDate("due_date"));
                record.setReturnDate(rs.getDate("return_date"));
                record.setStatus(rs.getString("status"));
                borrows.add(record);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving borrows by member: " + e.getMessage());
        }
        return borrows;
    }

    @Override
    public List<BorrowRecordDTO> getAllBorrows() {
        List<BorrowRecordDTO> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BorrowRecordDTO record = new BorrowRecordDTO();
                record.setRecordId(rs.getInt("record_id"));
                record.setMemberId(rs.getInt("member_id"));
                record.setBookId(rs.getInt("book_id"));
                record.setBorrowDate(rs.getDate("borrow_date"));
                record.setDueDate(rs.getDate("due_date"));
                record.setReturnDate(rs.getDate("return_date"));
                record.setStatus(rs.getString("status"));
                borrows.add(record);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving all borrows: " + e.getMessage());
        }
        return borrows;
    }
}
