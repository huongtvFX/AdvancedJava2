import dao.StudentDao;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("+---------------------------+------------------------+");
            System.out.println("|               QUẢN LÝ THÔNG TIN SINH VIÊN          |");
            System.out.println("+---------------------------+------------------------+");
            System.out.println("| 1. Danh sách sinh viên                             |");
            System.out.println("| 2. Nhập sinh viên mới                              |");
            System.out.println("| 3. Xóa sinh viên theo mã                           |");
            System.out.println("| 4. Cập nhật thông tin sinh viên                    |");
            System.out.println("| 5. Tìm một sinh viên theo họ tên (mã)              |");
            System.out.println("| 6. Sắp xếp sinh viên theo diểm số GPA tăng dần     |");
            System.out.println("| 7. In ra tất cả sinh viên nữ ở HN có GPA trên 2.5  |");
            System.out.println("| 8. Sắp xếp sinh viên theo họ tên                   |");
            System.out.println("| 0. Thoát                                           |");
            System.out.println("+---------------------------+------------------------+");
            System.out.print("Mời bạn chọn chức năng: ");
            String n = scanner.nextLine();
            switch (n) {
                case "1":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("| Danh sách sinh viên                 |");
                    System.out.println("+---------------------------+---------+");
                    DanhSach();
                    break;

                case "2":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("|  Nhập sinh viên mới                 |");
                    System.out.println("+---------------------------+---------+");

                    System.out.println("Nhập thông tin nhân viên");
                    ThemSinhVien(scanner);
                    break;

                case "3":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("| Xóa sinh viên theo mã               |");
                    System.out.println("+---------------------------+---------+");
                    XoaSinhVien(scanner, studentDao);
                    break;
                case "4":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("|  Cập nhật thông tin sinh viên       |");
                    System.out.println("+---------------------------+---------+");
                    CapNhatSinhVien(scanner, studentDao);
                    break;

                case "5":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("|  Tìm một sinh viên theo họ tên (mã) |");
                    System.out.println("+---------------------------+---------+");

                    break;

                case "6":
                    System.out.println("+---------------------------+-------------------+");
                    System.out.println("|  Sắp xếp sinh viên theo diểm số GPA tăng dần  |");
                    System.out.println("+---------------------------+-------------------+");
                    DanhSachTangDanGPA();
                    break;

                case "7":
                    System.out.println("+---------------------------+---------------------+");
                    System.out.println("|  In ra tất cả sinh viên nữ ở HN có GPA trên 2.5 |");
                    System.out.println("+---------------------------+---------------------+");
                    DanhSachNuGPAHn();
                    break;

                case "8":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("|  Sắp xếp sinh viên theo họ tên      |");
                    System.out.println("+---------------------------+---------+");
                    SapXepTheoTen();
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    static void DanhSach() {
        List<Student> studentList = StudentDao.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s %-20s", "STT", "Mã sinh viên", "Họ tên", "Giới tính", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < studentList.size(); i++) {
            Student stud = studentList.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", (i + 1), stud.getId(), stud.getFull_name(), stud.getGender(), stud.getAddress());
        }
    }
    static void ThemSinhVien(Scanner scanner) {
        StudentDao studentDao = new StudentDao();
        System.out.print("Mã sinh viên: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập giới tính 0 or 1: ");
        int gender = scanner.nextInt();
        System.out.print("Ngày sinh (dd-mm-yyyy): ");
        scanner.nextLine();
        String dateOfBirth = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập sđt: ");
        String phone = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập điểm GPA: ");
        float pointGPA = Float.parseFloat(scanner.nextLine());

        List<Student> studentList = studentDao.getAll();

        boolean checkEmail = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getEmail().equals(email)) {
                checkEmail = true;
            }
        }
        if (checkEmail) {
            System.out.println("Email đã tồn tại!");
        } else {
            studentDao.InsertStudent(new Student(id, fullName, gender, dateOfBirth, address, phone, email, pointGPA));
        }
    }
    static void XoaSinhVien(Scanner scanner, StudentDao studentDao) {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String id = scanner.nextLine();
        studentDao.delete(id);
    }
    static void CapNhatSinhVien(Scanner scanner, StudentDao studentDao){
        System.out.print("Nhập mã sinh viên cần cập nhật: ");
        String id = scanner.nextLine();
        Student student = studentDao.getById(id);
        if(student == null) {
            System.out.println("Không tồn tại sinh viên có mã id " + id );
            return;
        }
        System.out.print("Nhập tên: ");
        student.setFull_name(scanner.nextLine());
        System.out.print("Nhập giới tính: ");
        student.setGender(Integer.parseInt(scanner.nextLine()));
        System.out.print("Nhập ngày sinh: ");
        student.setDate_of_birth(scanner.nextLine());
        System.out.print("Nhập địa chỉ: ");
        student.setAddress(scanner.nextLine());
        System.out.print("Nhập số điện thoại: ");
        student.setPhone(scanner.nextLine());
        System.out.print("Nhập email: ");
        student.setEmail(scanner.nextLine());
        System.out.print("Nhập điểm: ");
        student.setPoint_GPA(Float.parseFloat(scanner.nextLine()));

        studentDao.update(id, student);
    }
    static void DanhSachTangDanGPA() {
        List<Student> studentList = StudentDao.getAllGPADesc();
        if(studentList.size() == 0) System.out.println("Không tồn tại sinh viên!");
        for (int i = 0; i < studentList.size(); i++) {
            Student stud = studentList.get(i);
            System.out.println(stud.toString());
        }
    }
    static void DanhSachNuGPAHn() {
        List<Student> studentList = StudentDao.getAllGPAHn();
        if(studentList.size() == 0) System.out.println("Không tồn tại sinh viên!");
        for (int i = 0; i < studentList.size(); i++) {
            Student stud = studentList.get(i);
            System.out.println(stud.toString());
        }
    }
    static void SapXepTheoTen() {
        List<Student> studentList = StudentDao.getAll();
        if(studentList.size() == 0) System.out.println("Không tồn tại sinh viên!");
        else{
            List<Student> lstSVSort = StudentDao.sortFullName(studentList);
            for (int i = 0; i < lstSVSort.size(); i++) {
                Student stud = lstSVSort.get(i);
                System.out.println(stud.toString());
            }
        }
    }
}
