package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import model.Trainer;

public class TrainerDAO {
    // Giả lập cơ sở dữ liệu tĩnh bằng cách dùng một danh sách cục bộ nếu DBConnection chưa có sẵn dữ liệu
    private static ArrayList<Trainer> trainers = new ArrayList<>();

    static {
        if (trainers.isEmpty()) {
            trainers.add(new Trainer("T01", "Nguyễn Văn A", "a@gmail.com", "123", "0909000001", "TRAINER", "Gym", LocalDate.of(2024, 1, 1), 12000000));
            trainers.add(new Trainer("T02", "Trần Văn B", "b@gmail.com", "123", "0909000002", "TRAINER", "Yoga", LocalDate.of(2023, 6, 15), 10000000));
        }
    }

    public void save(Trainer trainer) {
        int index = -1;
        for (int i = 0; i < trainers.size(); i++) {
            if (trainers.get(i).getUserID().equals(trainer.getUserID())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            trainers.set(index, trainer); // Nếu trùng ID -> Thực hiện SỬA
        } else {
            trainers.add(trainer); // Nếu ID mới -> Thực hiện THÊM
        }
    }

    public void delete(String trainerId) {
        trainers.removeIf(t -> t.getUserID().equals(trainerId));
    }

    public ArrayList<Trainer> getAll() {
        return trainers;
    }

    public Trainer findById(String id) {
        for (Trainer trainer : trainers) {
            if (trainer.getUserID().equals(id)) {
                return trainer;
            }
        }
        return null;
    }
}