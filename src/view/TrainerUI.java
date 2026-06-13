package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;

public class TrainerUI extends JFrame {

	private CardLayout cardLayout;
	private JPanel contentPanel;

	private JTable tblProfile;
	private JButton btnUpdateProfile;

	public TrainerUI() {

		setTitle("WAYFITNESS TRAINER DASHBOARD");
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		createHeader();
		createSidebar();
		createContent();

		setVisible(true);
	}

	// ================= HEADER =================

	private void createHeader() {

		JPanel header = new JPanel(new BorderLayout());

		header.setBackground(new Color(139, 0, 16));
		header.setPreferredSize(new Dimension(0, 55));

		JLabel title = new JLabel("WAYFITNESS TRAINER DASHBOARD");

		title.setForeground(Color.WHITE);
		title.setFont(new Font("Segoe UI", Font.BOLD, 18));
		title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		JLabel trainerInfo = new JLabel("Trainer");

		trainerInfo.setForeground(Color.WHITE);
		trainerInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

		header.add(title, BorderLayout.WEST);
		header.add(trainerInfo, BorderLayout.EAST);

		add(header, BorderLayout.NORTH);
	}

	// ================= SIDEBAR =================

	private void createSidebar() {

		JPanel sidebar = new JPanel();

		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

		sidebar.setBackground(new Color(38, 48, 62));

		sidebar.setPreferredSize(new Dimension(220, 0));

		JButton btnProfile = createMenuButton("Hồ sơ");

		JButton btnMember = createMenuButton("Hội viên");

		JButton btnSchedule = createMenuButton("Lịch tập");

		JButton btnPayment = createMenuButton("Thanh toán");
		JButton btnLogout= createMenuButton("Đăng xuất");

		sidebar.add(Box.createVerticalStrut(10));
		sidebar.add(btnProfile);
		sidebar.add(btnMember);
		sidebar.add(btnSchedule);
		sidebar.add(btnPayment);
		sidebar.add(btnLogout);

		add(sidebar, BorderLayout.WEST);

		btnProfile.addActionListener(e -> cardLayout.show(contentPanel, "PROFILE"));

		btnMember.addActionListener(e -> cardLayout.show(contentPanel, "MEMBER"));

		btnSchedule.addActionListener(e -> cardLayout.show(contentPanel, "SCHEDULE"));

		btnPayment.addActionListener(e -> cardLayout.show(contentPanel, "PAYMENT"));

        // ================= ĐĂNG XUẤT =================
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this, "Bạn có chắc muốn đăng xuất?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                LoginUI loginUI = new LoginUI();
                new LoginController(loginUI);
            }
        });
	}

	private JButton createMenuButton(String text) {

		JButton btn = new JButton(text);

		btn.setMaximumSize(new Dimension(220, 45));

		btn.setBackground(new Color(38, 48, 62));

		btn.setForeground(Color.WHITE);

		btn.setHorizontalAlignment(SwingConstants.LEFT);

		btn.setFocusPainted(false);

		return btn;
	}

	// ================= CONTENT =================

	private void createContent() {

		cardLayout = new CardLayout();

		contentPanel = new JPanel(cardLayout);

		contentPanel.add(profilePanel(), "PROFILE");

		/*
		 * chayj thu giao dien
		 */
		contentPanel.add(new JPanel(), "MEMBER");

		contentPanel.add(new ScheduleUI(), "SCHEDULE");

		contentPanel.add(new PaymentUI(), "PAYMENT");

		add(contentPanel, BorderLayout.CENTER);

		cardLayout.show(contentPanel, "PROFILE");
	}

	// ================= PROFILE =================

	private JPanel profilePanel() {

		JPanel panel = new JPanel(new BorderLayout(10, 10));

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel lblTitle = new JLabel("THÔNG TIN HUẤN LUYỆN VIÊN");

		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

		panel.add(lblTitle, BorderLayout.NORTH);

		String[] columns = { "Thuộc tính", "Giá trị" };

		DefaultTableModel model = new DefaultTableModel(columns, 0) {

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};

		tblProfile = new JTable(model);

		tblProfile.setRowHeight(30);

		panel.add(new JScrollPane(tblProfile), BorderLayout.CENTER);

		btnUpdateProfile = new JButton("Cập nhật hồ sơ");

		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		bottom.add(btnUpdateProfile);

		panel.add(bottom, BorderLayout.SOUTH);

		return panel;
	}

	// ================= GETTER =================

	public JTable getTblProfile() {
		return tblProfile;
	}

	public JButton getBtnUpdateProfile() {
		return btnUpdateProfile;
	}

	// ================= TEST =================

	public static void main(String[] args) {
		new TrainerUI();
	}
}