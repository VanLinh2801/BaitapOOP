// Tạo giao diện IEmployee
interface IEmployee {
    // Định nghĩa hai phương thức
    int calculateSalary(); // Tính lương của nhân viên (giả sử được trả theo tháng)
    String getName(); // Lấy tên của nhân viên
}

// Tạo lớp Employee và triển khai giao diện IEmployee
class Employee implements IEmployee {
    // Khai báo các thuộc tính
    private String name; // Tên của nhân viên
    private int paymentPerHour; // Mức lương theo giờ

    // Tạo constructor để khởi tạo các thuộc tính
    public Employee(String name, int paymentPerHour) {
        this.name = name;
        this.paymentPerHour = paymentPerHour;
    }

    // Tạo phương thức setName
    public void setName(String name) {
        this.name = name;
    }

    // Ghi đè phương thức getName của giao diện IEmployee
    @Override
    public String getName() {
        return name;
    }

    // Tạo phương thức setPaymentPerHour
    public void setPaymentPerHour(int paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    // Tạo phương thức getPaymentPerHour
    public int getPaymentPerHour() {
        return paymentPerHour;
    }

    // Ghi đè phương thức calculateSalary của giao diện IEmployee
    @Override
    public int calculateSalary() {
        return 0;
    }
}

// Tạo lớp PartTimeEmployee kế thừa lớp Employee
class PartTimeEmployee extends Employee {
    // Khai báo các thuộc tính
    private int workingHours; // Số giờ làm việc của nhân viên

    // Tạo constructor
    public PartTimeEmployee(String name, int paymentPerHour, int workingHours) {
        super(name, paymentPerHour);
        this.workingHours = workingHours;
    }

    // Ghi đè phương thức calculateSalary từ lớp cha
    @Override
    public int calculateSalary() {
        return getPaymentPerHour() * workingHours;
    }
}

// Tạo lớp FullTimeEmployee kế thừa lớp Employee
class FullTimeEmployee extends Employee {
    // Tạo constructor
    public FullTimeEmployee(String name, int paymentPerHour) {
        super(name, paymentPerHour);
    }

    // Ghi đè phương thức calculateSalary từ lớp cha, giả sử số giờ làm 1 tháng là 200 giờ
    @Override
    public int calculateSalary() {
        return getPaymentPerHour() * 200;
    }
}