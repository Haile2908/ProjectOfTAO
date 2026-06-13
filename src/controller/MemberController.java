package controller;

import dao.RegistrationDAO;
import dao.PaymentDAO;

import model.Registration;
import model.Payment;

public class MemberController {

	private RegistrationDAO registrationDAO;
	private PaymentDAO paymentDAO;

	public MemberController() {

		registrationDAO = new RegistrationDAO();
		paymentDAO = new PaymentDAO();
	}

	public void registerPackage(Registration registration) {

		registrationDAO.save(registration);
	}

	public void makePayment(Payment payment) {

		paymentDAO.save(payment);
	}
}