package dao;

import dto.MemberDTO;

public interface MemberDAO {
    boolean registerMember(MemberDTO member);
    MemberDTO loginMember(String email, String password);
}
