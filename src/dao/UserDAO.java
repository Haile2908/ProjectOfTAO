package dao;

import java.util.ArrayList;

import model.User;

public class UserDAO {

	private ArrayList<User> users = DBConnection.getInstance().getUsers();

	public void save(User user) {
		users.add(user);
	}

	public ArrayList<User> getAll() {
		return users;
	}

	public User findById(String id) {

		for (User user : users) {

			if (user.getUserID().equals(id)) {
				return user;
			}
		}

		return null;
	}

	public User login(String email, String password) {

		for (User user : users) {

			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {

				return user;
			}
		}

		return null;
	}
}