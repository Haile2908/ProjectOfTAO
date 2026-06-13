package test;

import dao.MemberDAO;
import dao.UserDAO;
import model.*;


public class TestDAO {

	public static void main(String[] args) {

		MemberDAO memberDAO = new MemberDAO();
		UserDAO userDAO = new UserDAO();

		User member = UserFactory.createUser("MEMBER", "M01", "Hai Le", "hai@gmail.com", "123", "0909123456");
		userDAO.save(member);
		
		System.out.println(userDAO.login("hai@gmail.com", "123").getName());
	}
}