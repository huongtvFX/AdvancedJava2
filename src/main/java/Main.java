import dao.StudentDao;
import model.Student;

import java.util.ArrayList;
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
            System.out.println("| 0. thoat                                           |");
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

                case "3":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("| Xóa sinh viên theo mã               |");
                    System.out.println("+---------------------------+---------+");

                    break;
                case "4":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("|  Cập nhật thông tin sinh viên       |");
                    System.out.println("+---------------------------+---------+");

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

                    break;

                case "7":
                    System.out.println("+---------------------------+---------------------+");
                    System.out.println("|  In ra tất cả sinh viên nữ ở HN có GPA trên 2.5 |");
                    System.out.println("+---------------------------+---------------------+");

                    break;

                case "8":
                    System.out.println("+---------------------------+---------+");
                    System.out.println("|  Sắp xếp sinh viên theo họ tên      |");
                    System.out.println("+---------------------------+---------+");

                    break;
                default:
                    System.exit(0);
            }

        }

    }

    static void DanhSach(){
        List<Student> studentList = StudentDao.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s %-20s", "STT","Mã sinh viên", "Họ tên", "Giới tính", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < studentList.size(); i++) {
            Student stud = studentList.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", (i+1), stud.getId(), stud.getFull_name(), stud.getGender(), stud.getAddress());
        };
    }

    static void ThemSinhVien(Scanner scanner){
        StudentDao studentDao = new StudentDao();
        System.out.print("Mã sinh viên: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập giơi tính 0 or 1: ");
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
        double pointGPA = scanner.nextDouble();

        List<Student> studentList = studentDao.getAll();

        boolean checkEmail = false;
        for (int i=0;i< studentList.size();i++) {
            if(studentList.get(i).getEmail().equals(email)){
                checkEmail = true;
            }
        }
        if(checkEmail){
            System.out.println("Email đã tồn tại!");
        }else {
            Student s = new Student(id, fullName,gender,dateOfBirth,address,phone,email,pointGPA);
            studentDao.InsertStud(s);
        }

    }
}