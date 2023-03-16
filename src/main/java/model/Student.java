package model;

public class Student implements Comparable<Student> {
    private String id;
    private String full_name;
    private int gender;
    private String date_of_birth;
    private String address;
    private String phone;
    private String email;
    private double point_GPA;

    public Student() {
    }

    public Student(String id, String full_name, int gender, String date_of_birth, String address, String phone, String email, double point_GPA) {
        this.id = id;
        this.full_name = full_name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.point_GPA = point_GPA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPoint_GPA() {
        return point_GPA;
    }

    public void setPoint_GPA(double point_GPA) {
        this.point_GPA = point_GPA;
    }

    @Override
    public String toString() {
        return "Student[" +
                "id='" + id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", gender=" + gender +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", point_GPA='" + point_GPA + '\'' +
                ']';
    }

    @Override
    public int compareTo(Student o) {
        if(this.point_GPA > o.point_GPA) return -1;
        else if (this.point_GPA < o.point_GPA) {
            return 1;
        }else return 0;
    }
}
