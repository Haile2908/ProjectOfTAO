package model;

import java.time.LocalDate;

public class Registration {
	private String registrationID;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean status;
	private LocalDate createdAt;

	public Registration(String registrationID, LocalDate startDate, LocalDate endDate, boolean status,
			LocalDate createdAt) {
		super();
		this.registrationID = registrationID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.createdAt = createdAt;
	}

	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public void register() {

	}

	public void cancel() {

	}

	public boolean checkStatus() {
		return status;
	}

	public void renew() {

	}
}
