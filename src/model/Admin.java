package model;

public class Admin extends User {
	private int adminLevel;

	public Admin(String userID, String name, String email, String password, String phone, String role, int adminLevel) {
		super(userID, name, email, password, phone, role);
		this.adminLevel = adminLevel;
	}

	public int getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}

}
