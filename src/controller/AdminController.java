package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.GymPackageDAO;
import dao.MemberDAO;
import dao.PaymentDAO;
import dao.ScheduleDAO;
import dao.TrainerDAO;

import model.GymPackage;
import model.Member;
import model.Payment;
import model.Schedule;
import model.Trainer;
import view.AdminUI;

public class AdminController {

	private AdminUI view;

	private MemberDAO memberDAO;
	private TrainerDAO trainerDAO;
	private GymPackageDAO packageDAO;
	private ScheduleDAO scheduleDAO;
	private PaymentDAO paymentDAO;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public AdminController(AdminUI view) {
		this.view = view;

		memberDAO = new MemberDAO();
		trainerDAO = new TrainerDAO();
		packageDAO = new GymPackageDAO();
		scheduleDAO = new ScheduleDAO();
		paymentDAO = new PaymentDAO();

		loadMembers();
		loadTrainers();
		loadPackages();
		loadSchedules();
		loadPayments();

		initEvents();
	}

	// ================= EVENTS =================
	private void initEvents() {
		initMemberEvents();
		initTrainerEvents();
		initPackageEvents();
		initScheduleEvents();
		initPaymentEvents();
	}

	// ================= MEMBER =================
	private void initMemberEvents() {

		view.getBtnAddMember().addActionListener(e -> {

			String id = input("Mã hội viên:");
			if (id == null)
				return;

			String name = input("Tên:");
			String phone = input("SĐT:");
			String goal = input("Mục tiêu:");

			memberDAO.save(new Member(id, name, id + "@gmail.com", "123", phone, "MEMBER", 0, 0, goal, true));

			loadMembers();
		});

		view.getBtnEditMember().addActionListener(e -> {
			int row = view.getTblMember().getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Vui lòng chọn hội viên cần sửa.");
				return;
			}

			String id = view.getTblMember().getValueAt(row, 0).toString();
			Member m = memberDAO.findById(id);
			if (m == null)
				return;

			String name = input("Tên (" + m.getName() + "):");
			String phone = input("SĐT (" + m.getPhone() + "):");
			String goal = input("Mục tiêu (" + m.getGoal() + "):");

			String newName = (name != null) ? name : m.getName();
			String newPhone = (phone != null) ? phone : m.getPhone();
			String newGoal = (goal != null) ? goal : m.getGoal();

			Member updated = new Member(m.getUserID(), newName, m.getEmail(), m.getPassword(), newPhone, m.getRole(),
					m.getHeight(), m.getWeight(), newGoal, m.isMembershipStatus());

			memberDAO.save(updated);
			loadMembers();
		});

		view.getBtnDeleteMember().addActionListener(e -> {
			int row = view.getTblMember().getSelectedRow();
			if (row == -1)
				return;

			String id = view.getTblMember().getValueAt(row, 0).toString();
			memberDAO.delete(id);
			loadMembers();
		});

		view.getBtnRefreshMember().addActionListener(e -> loadMembers());
	}

	// ================= TRAINER =================
	private void initTrainerEvents() {

		view.getBtnAddTrainer().addActionListener(e -> {

			String id = input("Mã HLV:");
			if (id == null)
				return;

			String name = input("Tên:");
			String phone = input("SĐT:");
			String spec = input("Chuyên môn:");

			Double salary = inputDouble("Lương:");
			if (salary == null)
				return;

			trainerDAO.save(
					new Trainer(id, name, id + "@gmail.com", "123", phone, "TRAINER", spec, LocalDate.now(), salary));

			loadTrainers();
		});

		view.getBtnEditTrainer().addActionListener(e -> {
			int row = view.getTblTrainer().getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Vui lòng chọn HLV cần sửa.");
				return;
			}

			String id = view.getTblTrainer().getValueAt(row, 0).toString();
			Trainer t = trainerDAO.findById(id);
			if (t == null)
				return;

			String name = input("Tên (" + t.getName() + "):");
			String phone = input("SĐT (" + t.getPhone() + "):");
			String spec = input("Chuyên môn (" + t.getSpecialization() + "):");
			String salaryStr = input("Lương (" + t.getSalary() + "):");

			String newName = (name != null) ? name : t.getName();
			String newPhone = (phone != null) ? phone : t.getPhone();
			String newSpec = (spec != null) ? spec : t.getSpecialization();
			double newSalary = t.getSalary();
			if (salaryStr != null) {
				try {
					newSalary = Double.parseDouble(salaryStr);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(view, "Lương không hợp lệ, giữ giá trị cũ.");
				}
			}

			Trainer updated = new Trainer(t.getUserID(), newName, t.getEmail(), t.getPassword(), newPhone, t.getRole(),
					newSpec, t.getHireDate(), newSalary);

			trainerDAO.save(updated);
			loadTrainers();
		});

		view.getBtnDeleteTrainer().addActionListener(e -> {
			int row = view.getTblTrainer().getSelectedRow();
			if (row == -1)
				return;

			String id = view.getTblTrainer().getValueAt(row, 0).toString();
			trainerDAO.delete(id);
			loadTrainers();
		});

		view.getBtnRefreshTrainer().addActionListener(e -> loadTrainers());
	}

	// ================= PACKAGE =================
	private void initPackageEvents() {

		view.getBtnAddPackage().addActionListener(e -> {

			String id = input("Mã gói:");
			if (id == null)
				return;

			String name = input("Tên gói:");
			String duration = input("Thời hạn (VD: 1 Tháng):");
			String desc = input("Mô tả:");

			Double price = inputDouble("Giá:");
			if (price == null)
				return;

			packageDAO.save(new GymPackage(id, name, duration, desc, price, true));
			loadPackages();
		});

		view.getBtnEditPackage().addActionListener(e -> {
			int row = view.getTblPackage().getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Vui lòng chọn gói tập cần sửa.");
				return;
			}

			String id = view.getTblPackage().getValueAt(row, 0).toString();
			GymPackage p = findPackageById(id);
			if (p == null)
				return;

			String name = input("Tên gói (" + p.getName() + "):");
			String duration = input("Thời hạn (" + p.getDuration() + "):");
			String priceStr = input("Giá (" + p.getPrice() + "):");

			String newName = (name != null) ? name : p.getName();
			String newDuration = (duration != null) ? duration : p.getDuration();
			double newPrice = p.getPrice();
			if (priceStr != null) {
				try {
					newPrice = Double.parseDouble(priceStr);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(view, "Giá không hợp lệ, giữ giá trị cũ.");
				}
			}

			GymPackage updated = new GymPackage(p.getPackageId(), newName, newDuration, p.getDescription(), newPrice,
					p.isStatus());

			packageDAO.save(updated);
			loadPackages();
		});

		view.getBtnDeletePackage().addActionListener(e -> {
			int row = view.getTblPackage().getSelectedRow();
			if (row == -1)
				return;

			String id = view.getTblPackage().getValueAt(row, 0).toString();
			packageDAO.delete(id);
			loadPackages();
		});

		view.getBtnRefreshPackage().addActionListener(e -> loadPackages());
	}

	// ================= SCHEDULE =================
	private void initScheduleEvents() {

		view.getBtnAddSchedule().addActionListener(e -> {

			String id = input("Mã lịch:");
			if (id == null)
				return;

			String dateStr = input("Ngày (yyyy-MM-dd):");
			LocalDate date;
			try {
				date = LocalDate.parse(dateStr, dateFormatter);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Ngày không hợp lệ.");
				return;
			}

			String time = input("Giờ (VD: 06:00 - 07:00):");
			String room = input("Phòng:");

			scheduleDAO.save(new Schedule(id, time, date, room, true));
			loadSchedules();
		});

		view.getBtnEditSchedule().addActionListener(e -> {
			int row = view.getTblSchedule().getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Vui lòng chọn lịch cần sửa.");
				return;
			}

			String id = view.getTblSchedule().getValueAt(row, 0).toString();
			Schedule s = scheduleDAO.findById(id);
			if (s == null)
				return;

			String dateStr = input("Ngày (" + s.getDate() + "):");
			String time = input("Giờ (" + s.getTime() + "):");
			String room = input("Phòng (" + s.getRoom() + "):");

			LocalDate newDate = s.getDate();
			if (dateStr != null) {
				try {
					newDate = LocalDate.parse(dateStr, dateFormatter);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(view, "Ngày không hợp lệ, giữ giá trị cũ.");
				}
			}
			String newTime = (time != null) ? time : s.getTime();
			String newRoom = (room != null) ? room : s.getRoom();

			Schedule updated = new Schedule(s.getScheduleId(), newTime, newDate, newRoom, s.isStatus());
			scheduleDAO.update(updated);
			loadSchedules();
		});

		view.getBtnDeleteSchedule().addActionListener(e -> {
			int row = view.getTblSchedule().getSelectedRow();
			if (row == -1)
				return;

			String id = view.getTblSchedule().getValueAt(row, 0).toString();
			scheduleDAO.delete(id);
			loadSchedules();
		});

		view.getBtnRefreshSchedule().addActionListener(e -> loadSchedules());
	}

	// ================= PAYMENT (CHỈ LÀM MỚI) =================
	private void initPaymentEvents() {
		view.getBtnRefreshPayment().addActionListener(e -> loadPayments());
	}

	// ================= LOAD DATA =================
	public void loadMembers() {
		DefaultTableModel model = (DefaultTableModel) view.getTblMember().getModel();
		model.setRowCount(0);

		for (Member m : memberDAO.getAll()) {
			model.addRow(new Object[] { m.getUserID(), m.getName(), m.getPhone(), m.getGoal() });
		}
	}

	public void loadTrainers() {
		DefaultTableModel model = (DefaultTableModel) view.getTblTrainer().getModel();
		model.setRowCount(0);

		for (Trainer t : trainerDAO.getAll()) {
			model.addRow(
					new Object[] { t.getUserID(), t.getName(), t.getPhone(), t.getSpecialization(), t.getSalary() });
		}
	}

	public void loadPackages() {
		DefaultTableModel model = (DefaultTableModel) view.getTblPackage().getModel();
		model.setRowCount(0);

		for (GymPackage p : packageDAO.getAll()) {
			model.addRow(new Object[] { p.getPackageId(), p.getName(), p.getDuration(), p.getPrice(), p.isStatus() });
		}
	}

	public void loadSchedules() {
		DefaultTableModel model = (DefaultTableModel) view.getTblSchedule().getModel();
		model.setRowCount(0);

		for (Schedule s : scheduleDAO.getAll()) {
			model.addRow(new Object[] { s.getScheduleId(), s.getDate(), s.getTime(), s.getRoom(), s.isStatus() });
		}
	}

	public void loadPayments() {
		DefaultTableModel model = (DefaultTableModel) view.getTblPayment().getModel();
		model.setRowCount(0);

		for (Payment p : paymentDAO.getAll()) {
			model.addRow(
					new Object[] { p.getPaymentId(), p.getPaymentDate(), p.getAmount(), p.getMethod(), p.getStatus() });
		}
	}

	// ================= HELPERS =================
	private GymPackage findPackageById(String id) {
		for (GymPackage p : packageDAO.getAll()) {
			if (p.getPackageId().equalsIgnoreCase(id)) {
				return p;
			}
		}
		return null;
	}

	private String input(String msg) {
		String s = JOptionPane.showInputDialog(view, msg);
		if (s == null || s.trim().isEmpty())
			return null;
		return s.trim();
	}

	private Double inputDouble(String msg) {
		String s = JOptionPane.showInputDialog(view, msg);
		if (s == null || s.trim().isEmpty())
			return null;

		try {
			return Double.parseDouble(s.trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Nhập số hợp lệ.");
			return null;
		}
	}
}