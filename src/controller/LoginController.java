package controller;

import javax.swing.JOptionPane;

import view.AdminUI;
import view.MemberUI;
import view.TrainerUI;
import view.LoginUI;

import model.User;
import dao.UserDAO;

public class LoginController {

    private LoginUI view;
    private UserDAO userDAO;

    public LoginController(LoginUI view) {

        this.view = view;
        this.userDAO = new UserDAO();

        view.getBtnLogin().addActionListener(e -> login());
    }

    private void login() {
        String email = view.getTxtEmail().getText();
        String password = new String(view.getTxtPassword().getPassword());
        User user = userDAO.login(email, password);

        if (user != null) {
            view.dispose();

            switch (user.getRole().toUpperCase()) {
            case "ADMIN":
                AdminUI adminUI = new AdminUI();
                new AdminController(adminUI);
                break;
            case "MEMBER":
                new MemberUI();
                break;
            case "TRAINER":
                new TrainerUI();
                break;
            }
        } else {
            JOptionPane.showMessageDialog(view, "Sai email hoặc mật khẩu!");
        }
    }
}