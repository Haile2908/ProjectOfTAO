package test;

import model.*;

public class TestModel {

	public static void main(String[] args) {
// createUser(String role, String userID, String name, String email, String password, String phone)
		User member = UserFactory.createUser("MEMBER", "M01", "Haile", "hai@gmail.com", "123", "0909 123 456");
		System.out.println(member.getName());
		System.out.println(member.getRole());
		

	}
}