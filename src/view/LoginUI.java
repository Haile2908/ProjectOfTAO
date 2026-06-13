package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginUI extends JFrame {

	private JLabel lblEmail;
	private JLabel lblPassword;

	private JTextField txtEmail;
	private JPasswordField txtPassword;

	private JButton btnLogin;

	public LoginUI() {

		setTitle("WayFitness Gym Management");
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridLayout(1, 2));

		createLeftPanel();
		createRightPanel();
		setVisible(true);
	}

	private void createLeftPanel() {

		JPanel leftPanel = new JPanel();

		leftPanel.setBackground(new Color(120, 0, 0));

		leftPanel.setLayout(new BorderLayout());

		JLabel lblTitle = new JLabel("WAYFITNESS", SwingConstants.CENTER);

		lblTitle.setForeground(Color.WHITE);

		lblTitle.setFont(new Font("Arial", Font.BOLD, 36));

		JLabel lblSub = new JLabel("Gym Management System", SwingConstants.CENTER);

		lblSub.setForeground(Color.WHITE);

		lblSub.setFont(new Font("Arial", Font.PLAIN, 18));

		JPanel center = new JPanel(new GridLayout(2, 1));

		center.setOpaque(false);

		center.add(lblTitle);
		center.add(lblSub);

		leftPanel.add(center, BorderLayout.CENTER);

		add(leftPanel);
	}

	private void createRightPanel() {

		JPanel rightPanel = new JPanel();

		rightPanel.setLayout(null);

		JLabel lblLogin = new JLabel("ĐĂNG NHẬP");

		lblLogin.setFont(new Font("Arial", Font.BOLD, 28));

		lblLogin.setBounds(130, 60, 250, 40);

		lblEmail = new JLabel("Email");

		lblEmail.setBounds(60, 150, 100, 30);

		txtEmail = new JTextField();

		txtEmail.setBounds(60, 180, 300, 35);

		lblPassword = new JLabel("Password");

		lblPassword.setBounds(60, 230, 100, 30);

		txtPassword = new JPasswordField();

		txtPassword.setBounds(60, 260, 300, 35);

		btnLogin = new JButton("LOGIN");

		btnLogin.setBackground(new Color(120, 0, 0));

		btnLogin.setForeground(Color.WHITE);

		btnLogin.setFocusPainted(false);

		btnLogin.setBounds(60, 330, 300, 40);

		rightPanel.add(lblLogin);
		rightPanel.add(lblEmail);
		rightPanel.add(txtEmail);
		rightPanel.add(lblPassword);
		rightPanel.add(txtPassword);
		rightPanel.add(btnLogin);

		rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		add(rightPanel);
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}
}