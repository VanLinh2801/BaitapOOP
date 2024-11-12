import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Tạo lớp OrderList
class OrderList {
    // Khai báo thuộc tính orders đại diện cho danh sách hóa đơn
    private List<Order> orders;

    // Tạo constructor
    public OrderList() {
        this.orders = new ArrayList<>();
    }

    // Tạo phương thức add để thêm 1 hóa đơn mới vào danh sách
    public void add(Order order) {
        orders.add(order);
    }

    // Tạo phương thức remove để xóa 1 hóa đơn khỏi danh sách
    public void remove(Order order) {
        orders.remove(order);
    }

    // Tạo phương thức getCount để xác định số lượng hóa đơn trong danh sách
    public int getCount() {
        return orders.size();
    }

    // Tạo phương thức OrderIterator trả về một đối tượng Itertor để duyệt qua danh sách hóa đơn
    public OrderIterator getIterator() {
        return new OrderIterator(orders);
    }
}

// Tạo lớp Order đại diện cho một hóa đơn
class Order {
    // Khai báo thuộc tính customer đại diện cho khách hàng đặt hóa đơn
    private Customer customer;
    // Khai báo thuộc tính orderLines đại diện cho danh sách các mặt hàng trong hóa đơn
    private List<OrderLine> orderLines;
    // Khai báo thuộc tính total đại diện cho tổng số tiền của hóa đơn
    private Currency total;

    // Tạo constructor
    public Order(Customer customer) {
        this.customer = customer;
        this.orderLines = new ArrayList<>();
        this.total = new Currency(0);
    }

    // Tạo phương thức addLine để thêm một mặt hàng vào hóa đơn
    public void addLine(OrderLine orderLine) {
        orderLines.add(orderLine);
        total.add(orderLine.getPrice().multiply(orderLine.getQuantity()));
    }

    // Tạo phương thức removeLine để xóa một mặt hàng khỏi hóa đơn
    public void removeLine(OrderLine orderLine) {
        orderLines.remove(orderLine);
        total.subtract(orderLine.getPrice().multiply(orderLine.getQuantity()));
    }

    // Tạo phương thức getTotal để trả về tổng số tiền của hóa đơn
    public Currency getTotal() {
        return total;
    }
}

// Tạo lớp Customer đại diện cho khách hàng
class Customer {
    // Khai báo thuộc tính của khách hàng
    private String address;
    private String code;
    private String name;

    // Tạo constructor
    public Customer(String address, String code, String name) {
        this.address = address;
        this.code = code;
        this.name = name;
    }
}

// Tạo lớp OrderLine đại diện cho từng mặt hàng trong hóa đơn
class OrderLine {
    // Khai báo thuộc tính của mặt hàng
    private int quantity;
    private Currency price;

    // Tạo constructor
    public OrderLine(int quantity, Currency price) {
        this.quantity = quantity;
        this.price = price;
    }

    // Tạo phương thức getQuantity để trả về số lượng của mặt hàng
    public int getQuantity() {
        return quantity;
    }

    // Tạo phương thức getPrice để trả về giá của mặt hàng
    public Currency getPrice() {
        return price;
    }
}

// Tạo lớp Currency đại diện cho đơn vị tiền tệ
class Currency {
    // Khai báo thuộc tính amount đại diện cho số tiền
    private double amount;

    // Tạo constructor
    public Currency(double amount) {
        this.amount = amount;
    }

    // Tạo các phương thức để tính toán tiền tệ
    public void add(Currency other) {
        this.amount += other.amount;
    }

    public void subtract(Currency other) {
        this.amount -= other.amount;
    }

    public Currency multiply(int factor) {
        return new Currency(this.amount * factor);
    }

    // Override phương thức toString để hiển thị số tiền
    @Override
    public String toString() {
        return String.format("%.2f$", amount);
    }
}

// Tạo lớp OrderIterator để duyệt qua danh sách hóa đơn, triển khai interface Iterator có sẵn trong gói java.util
class OrderIterator implements Iterator<Order> {
    // Khai báo thuộc tính orders đại diện cho danh sách hóa đơn
    private List<Order> orders;
    // Khai báo thuộc tính position đại diện cho vị trí hiện tại trong danh sách
    private int position;

    // Tạo constructor
    public OrderIterator(List<Order> orders) {
        this.orders = orders;
        this.position = 0;
    }

    // Tạo phương thức hasNext để kiểm tra còn hóa đơn nào trong danh sách không
    @Override
    public boolean hasNext() {
        return position < orders.size();
    }

    // Tạo phương thức next để lấy hóa đơn tiếp theo trong danh sách
    @Override
    public Order next() {
        return orders.get(position++);
    }
}