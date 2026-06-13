package model;

import java.time.LocalDate;

public class Payment {
	private String paymentId;
	private double amount;
	private String status;
	private LocalDate paymentDate;
	private String method;

	public Payment(String paymentId, double amount, String status, LocalDate paymentDate, String method) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.status = status;
		this.paymentDate = paymentDate;
		this.method = method;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void pay() {

	}

	public void refund() {

	}

	public void printInvoice() {

	}
}
