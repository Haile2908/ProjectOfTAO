package controller;

import dao.ScheduleDAO;
import model.Schedule;

public class TrainerController {

	private ScheduleDAO scheduleDAO;

	public TrainerController() {
		scheduleDAO = new ScheduleDAO();
	}

	public void addSchedule(Schedule schedule) {

		scheduleDAO.save(schedule);
	}
}