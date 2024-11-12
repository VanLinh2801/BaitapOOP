import java.util.ArrayList;
import java.util.List;

// Lớp KhachHang đại diện cho khách hàng
class KhachHang {
    // Khai báo các thuộc tính của khách hàng
    private String diaChi;
    private int khachHangID;
    private String tenKH;
    private List<HoaDon> hoaDons;

    // Tạo constructor
    public KhachHang(String diaChi, int khachHangID, String tenKH) {
        this.diaChi = diaChi;
        this.khachHangID = khachHangID;
        this.tenKH = tenKH;
        this.hoaDons = new ArrayList<>();
    }

    // Phương thức để thêm hóa đơn vào danh sách của khách hàng
    public void themHoaDon(HoaDon hoaDon) {
        hoaDons.add(hoaDon);
    }

    // Phương thức để lấy tên khách hàng
    public String getTenKH() {
        return tenKH;
    }
}

// Lớp HoaDon đại diện cho hóa đơn bán hàng
class HoaDon {
    // Khai báo các thuộc tiính của hóa đơn
    private int hoaDonID;
    private KhachHang kh; // Khách hàng liên quan đến hóa đơn
    private String ngayBan;
    private float tongTien;
    private List<MatHangMua> matHangMuas; // Danh sách các mặt hàng mua trong hóa đơn

    // Tạo constructor
    public HoaDon(int hoaDonID, KhachHang kh, String ngayBan) {
        this.hoaDonID = hoaDonID;
        this.kh = kh;
        this.ngayBan = ngayBan;
        this.tongTien = 0;
        this.matHangMuas = new ArrayList<>();
    }

    // Phương thức để thêm mặt hàng mua vào hóa đơn
    public void themMatHangMua(MatHangMua matHangMua) {
        matHangMuas.add(matHangMua);
        tongTien += matHangMua.getSl() * matHangMua.getMh().getGia();
    }

    // Phương thức để in thông tin hóa đơn
    public void inHoaDon() {
        System.out.println("Mã hóa đơn: " + hoaDonID);
        System.out.println("Khách hàng: " + kh.getTenKH());
        System.out.println("Ngày bán: " + ngayBan);
        System.out.println("Tổng tiền: " + tongTien);
        System.out.println("Mặt hàng mua:");
        for (MatHangMua matHangMua : matHangMuas) {
            System.out.println(" - " + matHangMua.getMh().getTenMH() + ": "
                    + matHangMua.getSl() + " x " + matHangMua.getMh().getGia());
        }
    }
}

// Lớp MatHangMua đại diện cho một mặt hàng được mua trong hóa đơn
class MatHangMua {
    private HoaDon hd; // Hóa đơn liên quan đến mặt hàng mua
    private int matHangMuaID;
    private int sl;
    private MatHang mh; // Thông tin mặt hàng

    // Tạo constructor
    public MatHangMua(HoaDon hd, int matHangMuaID, int sl, MatHang mh) {
        this.hd = hd;
        this.matHangMuaID = matHangMuaID;
        this.sl = sl;
        this.mh = mh;
    }

    // Phương thức để lấy số lượng mua
    public int getSl() {
        return sl;
    }

    // Phương thức để lấy thông tin mặt hàng
    public MatHang getMh() {
        return mh;
    }
}

// Lớp MatHang đại diện cho mặt hàng trong kho
class MatHang {
    // Khai báo các thuộc tính của mặt hàng
    private float gia;
    private int matHangID;
    private int soLuong;
    private String tenMH;

    // Tạo constructor
    public MatHang(float gia, int matHangID, int soLuong, String tenMH) {
        this.gia = gia;
        this.matHangID = matHangID;
        this.soLuong = soLuong;
        this.tenMH = tenMH;
    }

    // Phương thức để lấy giá của mặt hàng
    public float getGia() {
        return gia;
    }

    // Phương thức để lấy tên mặt hàng
    public String getTenMH() {
        return tenMH;
    }

    // Phương thức để thêm số lượng mặt hàng vào kho
    public void themMatHangVaoKho(int soLuong) {
        this.soLuong += soLuong;
    }
}

// Thực thi chương trình
public class BanHang {
    public static void main(String[] args) {
        // Tạo các mặt hàng
        MatHang X = new MatHang(100.0f, 1, 10, "X");
        MatHang Y = new MatHang(200.0f, 2, 20, "Y");
        MatHang Z = new MatHang(300.0f, 3, 30, "Z");

        // Tạo khách hàng "Nguyễn Văn A"
        KhachHang nguyenVanA = new KhachHang("Mo Lao - Ha Dong", 1, "Nguyễn Văn A");

        // Tạo một hóa đơn cho khách hàng "Nguyễn Văn A"
        HoaDon hoaDon = new HoaDon(1, nguyenVanA, "10/11/2024");

        // Tạo các mặt hàng mà khách hàng "Nguyễn Văn A" mua
        MatHangMua muaX = new MatHangMua(hoaDon, 1, 2, X);
        MatHangMua muaY = new MatHangMua(hoaDon, 2, 1, Y);

        // Thêm các mặt hàng mua vào hóa đơn
        hoaDon.themMatHangMua(muaX);
        hoaDon.themMatHangMua(muaY);

        // Thêm hóa đơn vào danh sách hóa đơn của khách hàng "Nguyễn Văn A"
        nguyenVanA.themHoaDon(hoaDon);

        // In thông tin hóa đơn
        hoaDon.inHoaDon();
    }
}