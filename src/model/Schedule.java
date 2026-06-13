package model;

import java.time.LocalDate;

public class Schedule {
	private String scheduleId;
	private String time;
	private LocalDate date;
	private String room;
	private boolean status;
	public Schedule(String scheduleId, String time, LocalDate date, String room, boolean status) {
		super();
		this.scheduleId = scheduleId;
		this.time = time;
		this.date = date;
		this.room = room;
		this.status = status;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
