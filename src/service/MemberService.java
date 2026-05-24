package service;

import dao.MemberDAO;
import daoimpl.MemberDAOImpl;
import dto.MemberDTO;

public class MemberService {
    private MemberDAO memberDAO = new MemberDAOImpl();

    public boolean registerMember(MemberDTO member) {
        return memberDAO.registerMember(member);
    }

    public MemberDTO loginMember(String email, String password) {
        return memberDAO.loginMember(email, password);
    }
}
