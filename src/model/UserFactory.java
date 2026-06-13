package model;

public class UserFactory {

	public static User createUser(String role, String userID, String name, String email, String password,
			String phone) {

		switch (role.toUpperCase()) {

		case "MEMBER":
			return new Member(userID, name, email, password, phone, role, 0, 0, "", false);

		case "TRAINER":
			return new Trainer(userID, name, email, password, phone, role, "", null, 0);

		case "ADMIN":
			return new Admin(userID, name, email, password, phone, role, 1);

		default:
			throw new IllegalArgumentException("Vai trò không hợp lệ");
		}
	}
}
