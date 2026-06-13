package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import model.Payment;

public class PaymentDAO {

	private static ArrayList<Payment> payments = new ArrayList<>();

	static {
		if (payments.isEmpty()) {
			payments.add(new Payment("PM01", 500000, "Đã thanh toán", LocalDate.of(2026, 6, 1), "Tiền mặt"));
			payments.add(new Payment("PM02", 2700000, "Đã thanh toán", LocalDate.of(2026, 6, 5), "Chuyển khoản"));
			payments.add(new Payment("PM03", 5000000, "Chưa thanh toán", LocalDate.of(2026, 6, 10), "Thẻ"));
		}
	}

	public void save(Payment payment) {
		payments.add(payment);
	}

	public ArrayList<Payment> getAll() {
		return payments;
	}

	public Payment findById(String id) {
		for (Payment p : payments) {
			if (p.getPaymentId().equalsIgnoreCase(id)) {
				return p;
			}
		}
		return null;
	}

	public boolean delete(String id) {
		Payment payment = findById(id);
		if (payment != null) {
			payments.remove(payment);
			return true;
		}
		return false;
	}

	public boolean update(Payment updatedPayment) {
		for (int i = 0; i < payments.size(); i++) {
			if (payments.get(i).getPaymentId().equalsIgnoreCase(updatedPayment.getPaymentId())) {
				payments.set(i, updatedPayment);
				return true;
			}
		}
		return false;
	}
}