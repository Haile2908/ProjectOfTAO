package dao;

import java.util.ArrayList;

import model.*;

public class DBConnection {

	private static DBConnection instance;

	private ArrayList<User> users;
	private ArrayList<Member> members;
	private ArrayList<Trainer> trainers;
	private ArrayList<GymPackage> packages;
	private ArrayList<Registration> registrations;
	private ArrayList<Payment> payments;
	private ArrayList<Schedule> schedules;

	private DBConnection() {

		users = new ArrayList<>();
		members = new ArrayList<>();
		trainers = new ArrayList<>();
		packages = new ArrayList<>();
		registrations = new ArrayList<>();
		payments = new ArrayList<>();
		schedules = new ArrayList<>();
	}

	public static DBConnection getInstance() {

		if (instance == null) {
			instance = new DBConnection();
		}

		return instance;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public ArrayList<Trainer> getTrainers() {
		return trainers;
	}

	public ArrayList<GymPackage> getPackages() {
		return packages;
	}

	public ArrayList<Registration> getRegistrations() {
		return registrations;
	}

	public ArrayList<Payment> getPayments() {
		return payments;
	}

	public ArrayList<Schedule> getSchedules() {
		return schedules;
	}
}