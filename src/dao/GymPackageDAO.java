package dao;

import java.util.ArrayList;
import java.util.List;
import model.GymPackage;

public class GymPackageDAO {
    private static List<GymPackage> packages = new ArrayList<>();

    static {
        if (packages.isEmpty()) {
            packages.add(new GymPackage("P01", "Gói Cơ Bản", "1 Tháng", "Tập tự do giờ thấp điểm", 500000, true));
            packages.add(new GymPackage("P02", "Gói VIP", "6 Tháng", "Tập toàn thời gian + Tủ đồ riêng", 2700000, true));
            packages.add(new GymPackage("P03", "Gói Huấn Luyện Viên", "3 Tháng", "Tèm kèm 1-1 với HLV chuyên nghiệp", 5000000, false));
        }
    }

    public List<GymPackage> getAll() {
        return packages;
    }

    public void save(GymPackage gymPackage) {
        int index = -1;
        for (int i = 0; i < packages.size(); i++) {
            if (packages.get(i).getPackageId().equalsIgnoreCase(gymPackage.getPackageId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            packages.set(index, gymPackage); // SỬA
        } else {
            packages.add(gymPackage); // THÊM
        }
    }

    public void delete(String packageId) {
        packages.removeIf(p -> p.getPackageId().equalsIgnoreCase(packageId));
    }
}