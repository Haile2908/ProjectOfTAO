package dao;

import java.util.ArrayList;
import java.util.List;
import model.Member;

public class MemberDAO {
    // Sử dụng static để danh sách được lưu trữ xuyên suốt phiên chạy ứng dụng
    private static List<Member> members = new ArrayList<>();

    // Khối mã nguồn tĩnh (static block) để tự động nạp dữ liệu giả lập khi ứng dụng khởi chạy
    static {
        if (members.isEmpty()) {
            // Khởi tạo mẫu 3 hội viên với đầy đủ 10 tham số theo cấu trúc mới của class Member
            members.add(new Member(
                "M01", "Nguyễn Văn Hải", "hai@gmail.com", "123", "0909123456", 
                "MEMBER", 175.0, 70.5, "Tăng cơ", true
            ));
            
            members.add(new Member(
                "M02", "Trần Tuấn Nam", "nam@gmail.com", "123", "0912345678", 
                "MEMBER", 168.0, 75.2, "Giảm mỡ", true
            ));
            
            members.add(new Member(
                "M03", "Lê Thị Thuỷ", "thuy@gmail.com", "123", "0987654321", 
                "MEMBER", 158.0, 48.0, "Giữ dáng", true
            ));
        }
    }

    /**
     * Lấy ra toàn bộ danh sách hội viên phục vụ cho việc đổ dữ liệu lên JTable
     */
    public List<Member> getAll() {
        return members;
    }

    /**
     * Hàm SAVE thông minh: Tự động kiểm tra ID.
     * - Nếu ID đã tồn tại -> Thực hiện cập nhật thông tin (SỬA).
     * - Nếu ID chưa tồn tại -> Thực hiện lưu mới vào danh sách (THÊM).
     */
    public void save(Member member) {
        int index = -1;
        
        // Vòng lặp tìm kiếm xem ID của hội viên đã có trong hệ thống chưa
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getUserID().equalsIgnoreCase(member.getUserID())) {
                index = i;
                break;
            }
        }
        
        if (index != -1) {
            // Tìm thấy ID trùng -> Thay thế phần tử cũ bằng phần tử mới cập nhật (SỬA)
            members.set(index, member);
        } else {
            // Không tìm thấy trùng ID -> Thêm mới vào cuối danh sách (THÊM)
            members.add(member);
        }
    }

    /**
     * Chức năng XÓA hội viên theo mã ID
     */
    public void delete(String memberId) {
        // Tự động tìm kiếm hội viên có ID trùng khớp và xóa khỏi ArrayList
        members.removeIf(m -> m.getUserID().equalsIgnoreCase(memberId));
    }

    /**
     * Tìm kiếm một hội viên cụ thể dựa theo ID (Phục vụ cho chức năng xem chi tiết nếu cần)
     */
    public Member findById(String id) {
        for (Member member : members) {
            if (member.getUserID().equalsIgnoreCase(id)) {
                return member;
            }
        }
        return null; // Trả về null nếu không tìm thấy hội viên nào khớp
    }
}