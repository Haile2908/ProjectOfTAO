package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import model.Schedule;

public class ScheduleDAO {

    private static ArrayList<Schedule> schedules = new ArrayList<>();

    static {
        if (schedules.isEmpty()) {
            schedules.add(new Schedule("S01", "06:00 - 07:00", LocalDate.of(2026, 6, 15), "Phòng A", true));
            schedules.add(new Schedule("S02", "08:00 - 09:00", LocalDate.of(2026, 6, 15), "Phòng B", true));
            schedules.add(new Schedule("S03", "17:00 - 18:00", LocalDate.of(2026, 6, 16), "Phòng A", false));
        }
    }

    public void save(Schedule schedule) {
        int index = -1;
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getScheduleId().equalsIgnoreCase(schedule.getScheduleId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            schedules.set(index, schedule);
        } else {
            schedules.add(schedule);
        }
    }

    public ArrayList<Schedule> getAll() {
        return schedules;
    }

    public Schedule findById(String id) {
        for (Schedule s : schedules) {
            if (s.getScheduleId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        Schedule schedule = findById(id);
        if (schedule != null) {
            schedules.remove(schedule);
            return true;
        }
        return false;
    }

    public boolean update(Schedule updatedSchedule) {
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getScheduleId().equalsIgnoreCase(updatedSchedule.getScheduleId())) {
                schedules.set(i, updatedSchedule);
                return true;
            }
        }
        return false;
    }
}