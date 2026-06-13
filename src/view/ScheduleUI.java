package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScheduleUI extends JPanel {
    private JTable tblSchedule;
    // Khai báo nút bấm thành thuộc tính lớp để xuất Getter
    private JButton btnAdd, btnEdit, btnDelete, btnRefresh;

    public ScheduleUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(240, 242, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("QUẢN LÝ LỊCH TẬP");
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
        
        btnAdd = new JButton("Thêm"); 
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnRefresh = new JButton("Làm mới");

        actionPanel.add(btnAdd);
        actionPanel.add(btnEdit);
        actionPanel.add(btnDelete);
        actionPanel.add(btnRefresh);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.insets = new Insets(15, 0, 5, 0);
        topPanel.add(actionPanel, gbc);
        add(topPanel, BorderLayout.NORTH);
        
        String[] columns = { "Mã lịch", "Ngày", "Giờ", "Phòng", "HLV" };
        tblSchedule = new JTable(new DefaultTableModel(columns, 0));
        tblSchedule.setRowHeight(30);
        add(new JScrollPane(tblSchedule), BorderLayout.CENTER);
    }

    // --- Hệ thống Getter cung cấp cho AdminUI ---
    public JTable getTblSchedule() { return tblSchedule; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
}