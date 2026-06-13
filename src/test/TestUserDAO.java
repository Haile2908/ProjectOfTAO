package test;

import dao.UserDAO;
import model.User;
import model.UserFactory;

public class TestUserDAO {

	public static void main(String[] args) {

		UserDAO dao = new UserDAO();

		User member = UserFactory.createUser("MEMBER", "M01", "Hai Le", "hai@gmail.com", "123", "0909123456");

		dao.save(member);

		User result = dao.login("hai@gmail.com", "123");

		if (result != null) {
			System.out.println("Dang nhap thanh cong");
			System.out.println(result.getName());
			System.out.println(result.getRole());
		}
	}
}