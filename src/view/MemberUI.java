package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;

public class MemberUI extends JFrame {

	private CardLayout cardLayout;
	private JPanel contentPanel;

	// ===== PROFILE =====
	private JButton btnUpdateProfile;
	private JTable tblProfile;

	public MemberUI() {

		setTitle("WayFitness Member Dashboard");
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		createHeader();
		createSidebar();
		createContent();

		setVisible(true);
	}

	// =====================================================
	// HEADER
	// =====================================================

	private void createHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(new Color(139, 0, 16));
		header.setPreferredSize(new Dimension(0, 55));
		JLabel title = new JLabel("WAYFITNESS MEMBER DASHBOARD");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Segoe UI", Font.BOLD, 18));
		title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		JLabel memberInfo = new JLabel("Member");
		memberInfo.setForeground(Color.WHITE);
		memberInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		header.add(title, BorderLayout.WEST);
		header.add(memberInfo, BorderLayout.EAST);
		add(header, BorderLayout.NORTH);
	}

	// =====================================================
	// SIDEBAR
	// =====================================================

	private void createSidebar() {
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.setBackground(new Color(38, 48, 62));
		sidebar.setPreferredSize(new Dimension(220, 0));
		JButton btnProfile = createMenuButton("Hồ sơ");
		JButton btnPackage = createMenuButton("Gói tập");
		JButton btnSchedule = createMenuButton("Lịch tập");
		JButton btnPayment = createMenuButton("Thanh toán");
		JButton btnLogout = createMenuButton("Đăng xuất");
		sidebar.add(Box.createVerticalStrut(10));
		sidebar.add(btnProfile);
		sidebar.add(btnPackage);
		sidebar.add(btnSchedule);
		sidebar.add(btnPayment);
		sidebar.add(Box.createVerticalGlue());
		sidebar.add(btnLogout);
		add(sidebar, BorderLayout.WEST);
		btnProfile.addActionListener(e -> cardLayout.show(contentPanel, "PROFILE"));
		btnPackage.addActionListener(e -> cardLayout.show(contentPanel, "PACKAGE"));
		btnSchedule.addActionListener(e -> cardLayout.show(contentPanel, "SCHEDULE"));
		btnPayment.addActionListener(e -> cardLayout.show(contentPanel, "PAYMENT"));
		// đăng xuất 
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
		btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setFocusPainted(false);
		return btn;
	}

	// =====================================================
	// CONTENT
	// =====================================================

	private void createContent() {

		cardLayout = new CardLayout();

		contentPanel = new JPanel(cardLayout);

		contentPanel.add(profilePanel(), "PROFILE");

		contentPanel.add(new GymPackageUI(), "PACKAGE");

		contentPanel.add(new ScheduleUI(), "SCHEDULE");

		contentPanel.add(new PaymentUI(), "PAYMENT");

		add(contentPanel, BorderLayout.CENTER);

		cardLayout.show(contentPanel, "PROFILE");
	}

	// =====================================================
	// PROFILE PANEL
	// =====================================================

	private JPanel profilePanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JLabel lblTitle = new JLabel("THÔNG TIN HỘI VIÊN");
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
		JScrollPane scrollPane = new JScrollPane(tblProfile);
		panel.add(scrollPane, BorderLayout.CENTER);
		btnUpdateProfile = new JButton("Cập nhật thông tin");
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottom.add(btnUpdateProfile);
		panel.add(bottom, BorderLayout.SOUTH);
		return panel;
	}

	public static void main(String[] args) {
		new MemberUI();
	}
}