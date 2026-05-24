package daoimpl;

import dao.MemberDAO;
import db.DBConnection;
import dto.MemberDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public boolean registerMember(MemberDTO member) {
        String sql = "INSERT INTO members(name, email, password) VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPassword());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error registering member: " + e.getMessage());
            return false;
        }
    }

    @Override
    public MemberDTO loginMember(String email, String password) {
        String sql = "SELECT * FROM members WHERE email=? AND BINARY password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                // Do not set password
                return member;
            }

        } catch (SQLException e) {
            System.err.println("Error logging in member: " + e.getMessage());
        }
        return null;
    }
}
