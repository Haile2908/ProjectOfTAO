package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;

public class AdminUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    // Khai báo các JTable toàn cục của Hội viên và HLV
    private JTable tblMember;
    private JTable tblTrainer;

    // Khai báo các lớp giao diện con đã tách riêng (ĐỂ LẤY DỮ LIỆU)
    private GymPackageUI packagePanel;
    private ScheduleUI schedulePanel;
    private PaymentUI paymentPanel;

    // Khai báo các nút bấm của tab Hội Viên
    private JButton btnAddMember, btnEditMember, btnDeleteMember, btnRefreshMember;
    
    // Khai báo các nút bấm của tab Huấn Luyện Viên
    private JButton btnAddTrainer, btnEditTrainer, btnDeleteTrainer, btnRefreshTrainer;

    // Bảng màu giao diện
    private final Color COLOR_PRIMARY = new Color(139, 0, 16);
    private final Color COLOR_SIDEBAR = new Color(38, 48, 62);
    private final Color COLOR_SIDEBAR_ACTIVE = new Color(48, 61, 78);
    private final Color COLOR_BG = new Color(240, 242, 245);
    private final Color COLOR_MENU_BORDER = new Color(52, 65, 82);

    public AdminUI() {
        setTitle("Giao diện Admin");
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createHeader();
        createSidebar();
        createContent();

        setVisible(true);
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PRIMARY);
        header.setPreferredSize(new Dimension(0, 55));

        JLabel title = new JLabel("WAYFITNESS ADMIN DASHBOARD");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JLabel adminInfo = new JLabel("Admin");
        adminInfo.setForeground(Color.WHITE);
        adminInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        adminInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        header.add(title, BorderLayout.WEST);
        header.add(adminInfo, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
    }

    private void createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(COLOR_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(260, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JButton btnMember = createMenuButton("Quản lý hội viên");
        JButton btnTrainer = createMenuButton("Quản lý huấn luyện viên");
        JButton btnSchedule = createMenuButton("Quản lý lịch tập");
        JButton btnPackage = createMenuButton("Quản lý gói tập");
        JButton btnPayment = createMenuButton("Quản lý thanh toán");
        JButton btnLogout = createMenuButton("Đăng xuất");

        sidebar.add(btnMember);
        sidebar.add(btnTrainer);
        sidebar.add(btnSchedule);
        sidebar.add(btnPackage);
        sidebar.add(btnPayment);
        sidebar.add(Box.createVerticalGlue());

        btnLogout.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, COLOR_MENU_BORDER),
                BorderFactory.createEmptyBorder(0, 20, 0, 0)));
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);
        btnMember.setBackground(COLOR_SIDEBAR_ACTIVE);

        btnMember.addActionListener(e -> {
            resetButtons(btnMember, btnTrainer, btnSchedule, btnPackage, btnPayment);
            cardLayout.show(contentPanel, "MEMBER");
        });
        btnTrainer.addActionListener(e -> {
            resetButtons(btnTrainer, btnMember, btnSchedule, btnPackage, btnPayment);
            cardLayout.show(contentPanel, "TRAINER");
        });
        btnSchedule.addActionListener(e -> {
            resetButtons(btnSchedule, btnMember, btnTrainer, btnPackage, btnPayment);
            cardLayout.show(contentPanel, "SCHEDULE");
        });
        btnPackage.addActionListener(e -> {
            resetButtons(btnPackage, btnMember, btnTrainer, btnSchedule, btnPayment);
            cardLayout.show(contentPanel, "PACKAGE");
        });
        btnPayment.addActionListener(e -> {
            resetButtons(btnPayment, btnMember, btnTrainer, btnSchedule, btnPackage);
            cardLayout.show(contentPanel, "PAYMENT");
        });

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
        btn.setMaximumSize(new Dimension(260, 55));
        btn.setPreferredSize(new Dimension(260, 55));
        btn.setBackground(COLOR_SIDEBAR);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_MENU_BORDER),
                BorderFactory.createEmptyBorder(0, 20, 0, 0)));
        return btn;
    }

    private void resetButtons(JButton active, JButton... others) {
        active.setBackground(COLOR_SIDEBAR_ACTIVE);
        for (JButton btn : others) {
            btn.setBackground(COLOR_SIDEBAR);
        }
    }

    private void createContent() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Khởi tạo đối tượng từ các class UI riêng
        packagePanel = new GymPackageUI();
        schedulePanel = new ScheduleUI();
        paymentPanel = new PaymentUI();

        contentPanel.add(new MemberManagementPanel(), "MEMBER");
        contentPanel.add(new TrainerManagementPanel(), "TRAINER");
        
        // Đưa các Panel tách biệt vào content
        contentPanel.add(schedulePanel, "SCHEDULE");
        contentPanel.add(packagePanel, "PACKAGE");
        contentPanel.add(paymentPanel, "PAYMENT");

        add(contentPanel, BorderLayout.CENTER);
    }

    // --- PANEL HỘI VIÊN ---
    private class MemberManagementPanel extends JPanel {
        public MemberManagementPanel() {
            setLayout(new BorderLayout(15, 15));
            setBackground(COLOR_BG);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JPanel topPanel = new JPanel(new GridBagLayout());
            topPanel.setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblTitle = new JLabel("QUẢN LÝ HỘI VIÊN");
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
            gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
            topPanel.add(lblTitle, gbc);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            searchPanel.setOpaque(false);
            searchPanel.add(new JLabel("Tìm kiếm: "));
            searchPanel.add(new JTextField(20));
            searchPanel.add(new JButton("Tìm"));
            gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.0;
            topPanel.add(searchPanel, gbc);

            JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            actionPanel.setOpaque(false);
            
            btnAddMember = new JButton("Thêm");
            btnAddMember.setBackground(new Color(40, 167, 69));
            btnAddMember.setForeground(Color.WHITE);
            btnEditMember = new JButton("Sửa");
            btnDeleteMember = new JButton("Xóa");
            btnRefreshMember = new JButton("Làm mới");

            actionPanel.add(btnAddMember);
            actionPanel.add(btnEditMember);
            actionPanel.add(btnDeleteMember);
            actionPanel.add(btnRefreshMember);

            gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
            gbc.insets = new Insets(15, 0, 5, 0);
            topPanel.add(actionPanel, gbc);
            add(topPanel, BorderLayout.NORTH);

            String[] columns = { "ID", "Tên", "Sđt", "Mục Tiêu" };
            tblMember = new JTable(new DefaultTableModel(columns, 0));
            tblMember.setRowHeight(30);
            add(new JScrollPane(tblMember), BorderLayout.CENTER);
        }
    }

    // --- PANEL HUẤN LUYỆN VIÊN ---
    private class TrainerManagementPanel extends JPanel {
        public TrainerManagementPanel() {
            setLayout(new BorderLayout(15, 15));
            setBackground(COLOR_BG);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JPanel topPanel = new JPanel(new GridBagLayout());
            topPanel.setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblTitle = new JLabel("QUẢN LÝ HUẤN LUYỆN VIÊN");
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
            gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
            topPanel.add(lblTitle, gbc);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            searchPanel.setOpaque(false);
            searchPanel.add(new JLabel("Tìm kiếm: "));
            searchPanel.add(new JTextField(20));
            searchPanel.add(new JButton("Tìm"));
            gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.0;
            topPanel.add(searchPanel, gbc);

            JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            actionPanel.setOpaque(false);
            
            btnAddTrainer = new JButton("Thêm");
            btnAddTrainer.setBackground(new Color(40, 167, 69));
            btnAddTrainer.setForeground(Color.WHITE);
            btnEditTrainer = new JButton("Sửa");
            btnDeleteTrainer = new JButton("Xóa");
            btnRefreshTrainer = new JButton("Làm mới");

            actionPanel.add(btnAddTrainer);
            actionPanel.add(btnEditTrainer);
            actionPanel.add(btnDeleteTrainer);
            actionPanel.add(btnRefreshTrainer);

            gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
            gbc.insets = new Insets(15, 0, 5, 0);
            topPanel.add(actionPanel, gbc);
            add(topPanel, BorderLayout.NORTH);

            String[] columns = { "Mã HLV", "Họ tên", "SĐT", "Chuyên môn", "Lương" };
            tblTrainer = new JTable(new DefaultTableModel(columns, 0));
            tblTrainer.setRowHeight(30);
            add(new JScrollPane(tblTrainer), BorderLayout.CENTER);
        }
    }

    // ================= GETTER HỘI VIÊN & HUẤN LUYỆN VIÊN =================
    public JTable getTblMember() { return tblMember; }
    public JTable getTblTrainer() { return tblTrainer; }

    public JButton getBtnAddMember() { return btnAddMember; }
    public JButton getBtnEditMember() { return btnEditMember; }
    public JButton getBtnDeleteMember() { return btnDeleteMember; }
    public JButton getBtnRefreshMember() { return btnRefreshMember; }

    public JButton getBtnAddTrainer() { return btnAddTrainer; }
    public JButton getBtnEditTrainer() { return btnEditTrainer; }
    public JButton getBtnDeleteTrainer() { return btnDeleteTrainer; }
    public JButton getBtnRefreshTrainer() { return btnRefreshTrainer; }
    
    // ================= GETTER LIÊN KẾT ĐẾN TAB LỊCH TẬP (ScheduleUI) =================
    public JTable getTblSchedule() { 
        return schedulePanel.getTblSchedule(); 
    }
    public JButton getBtnAddSchedule() { 
        return schedulePanel.getBtnAdd(); 
    }
    public JButton getBtnEditSchedule() { 
        return schedulePanel.getBtnEdit(); 
    }
    public JButton getBtnDeleteSchedule() { 
        return schedulePanel.getBtnDelete(); 
    }
    public JButton getBtnRefreshSchedule() { 
        return schedulePanel.getBtnRefresh(); 
    }

    // ================= GETTER LIÊN KẾT ĐẾN TAB GÓI TẬP (GymPackageUI) =================
    public JTable getTblPackage() { 
        return packagePanel.getTblPackage(); 
    }
    public JButton getBtnAddPackage() { 
        return packagePanel.getBtnAdd(); 
    }
    public JButton getBtnEditPackage() { 
        return packagePanel.getBtnEdit(); 
    }
    public JButton getBtnDeletePackage() { 
        return packagePanel.getBtnDelete(); 
    }
    public JButton getBtnRefreshPackage() { 
        return packagePanel.getBtnRefresh(); 
    }

    // ================= GETTER LIÊN KẾT ĐẾN TAB THANH TOÁN (PaymentUI) =================
    public JTable getTblPayment() {
        return paymentPanel.getTblPayment();
    }
    public JButton getBtnRefreshPayment() {
        return paymentPanel.getBtnRefresh();
    }

    // Member
    public void addMemberRow(Object[] row) {
        ((DefaultTableModel) tblMember.getModel()).addRow(row);
    }

    public void clearMemberTable() {
        ((DefaultTableModel) tblMember.getModel()).setRowCount(0);
    }

    // Trainer
    public void addTrainerRow(Object[] row) {
        ((DefaultTableModel) tblTrainer.getModel()).addRow(row);
    }

    public void clearTrainerTable() {
        ((DefaultTableModel) tblTrainer.getModel()).setRowCount(0);
    }

    // Package
    public void addPackageRow(Object[] row) {
        ((DefaultTableModel) packagePanel.getTblPackage().getModel()).addRow(row);
    }

    public void clearPackageTable() {
        ((DefaultTableModel) packagePanel.getTblPackage().getModel()).setRowCount(0);
    }

    // Schedule
    public void addScheduleRow(Object[] row) {
        ((DefaultTableModel) schedulePanel.getTblSchedule().getModel()).addRow(row);
    }

    public void clearScheduleTable() {
        ((DefaultTableModel) schedulePanel.getTblSchedule().getModel()).setRowCount(0);
    }

    // Payment
    public void addPaymentRow(Object[] row) {
        ((DefaultTableModel) paymentPanel.getTblPayment().getModel()).addRow(row);
    }

    public void clearPaymentTable() {
        ((DefaultTableModel) paymentPanel.getTblPayment().getModel()).setRowCount(0);
    }
}