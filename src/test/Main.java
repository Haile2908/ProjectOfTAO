package test;

import controller.LoginController;
import dao.UserDAO;
import model.User;
import model.UserFactory;
import view.LoginUI;

public class Main {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        User admin = UserFactory.createUser("ADMIN", "A01", "Admin", "admin@gmail.com", "123", "0909805705");
        User member = UserFactory.createUser("MEMBER", "M01", "Hai", "hai@gmail.com", "123", "0909");
        User trainer = UserFactory.createUser("TRAINER", "T01", "Nam", "nam@gmail.com", "123", "0909");

        userDAO.save(admin);
        userDAO.save(member);
        userDAO.save(trainer);

        LoginUI loginUI = new LoginUI();
        new LoginController(loginUI);
    }
}