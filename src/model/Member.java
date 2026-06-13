package model;

public class Member extends User {
	private double height;
	private double weight;
	private String goal;
	private boolean membershipStatus;

	public Member(String userID, String name, String email, String password, String phone, String role, double height,
			double weight, String goal, boolean membershipStatus) {
		super(userID, name, email, password, phone, role);
		this.height = height;
		this.weight = weight;
		this.goal = goal;
		this.membershipStatus = membershipStatus;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public boolean isMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(boolean membershipStatus) {
		this.membershipStatus = membershipStatus;
	}
}
