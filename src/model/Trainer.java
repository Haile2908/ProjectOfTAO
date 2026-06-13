package model;

import java.time.LocalDate;

public class Trainer extends User {
	private String specialization;
	private LocalDate hireDate;
	private double salary;

	

	public Trainer(String userID, String name, String email, String password, String phone, String role,
			String specialization, LocalDate hireDate, double salary) {
		super(userID, name, email, password, phone, role);
		this.specialization = specialization;
		this.hireDate = hireDate;
		this.salary = salary;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
