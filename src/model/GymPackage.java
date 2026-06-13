package model;

public class GymPackage {
	private String packageId;
	private String name;
	private String duration;
	private String description;
	private double price;
	private boolean status;

	public GymPackage(String packageId, String name, String duration, String description, double price,
			boolean status) {
		super();
		this.packageId = packageId;
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.price = price;
		this.status = status;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void displayInfo() {

	}

	public void updatePrice(double price) {

	}

	public void activatePackage() {
		status = true;
	}

	public void disablePackage() {
		status = false;
	}
}
