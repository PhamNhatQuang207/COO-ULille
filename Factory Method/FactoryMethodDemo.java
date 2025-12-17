// 1. PRODUCT (Sản phẩm chung)
// Mọi phương tiện đều phải biết "chở hàng"
interface Transport {
    void deliver();
}

// 2. CONCRETE PRODUCTS (Các loại phương tiện cụ thể)
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Xe tải: Chở hàng trên đường bộ 🚛");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Tàu thủy: Chở hàng trên đường biển 🚢");
    }
}

// 3. CREATOR (Nhà máy trừu tượng)
// Class này định nghĩa phương thức tạo, nhưng KHÔNG biết tạo cái gì cụ thể
abstract class Logistics {
    // --- ĐÂY LÀ FACTORY METHOD ---
    // Để abstract để bắt buộc lớp con phải tự cài đặt việc tạo đối tượng
    public abstract Transport createTransport();

    // Logic nghiệp vụ chung (Dùng sản phẩm mà không cần biết nó là Truck hay Ship)
    public void planDelivery() {
        // Gọi Factory Method để lấy đối tượng
        Transport transport = createTransport(); 
        
        // Sử dụng đối tượng
        transport.deliver();
    }
}

// 4. CONCRETE CREATORS (Các nhà máy cụ thể)
// Mỗi nhà máy chịu trách nhiệm tạo ra 1 loại sản phẩm tương ứng

// Nhà máy đường bộ -> Tạo Xe tải
class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

// Nhà máy đường biển -> Tạo Tàu thủy
class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}
public class FactoryMethodDemo {
    public static void main(String[] args) {
        // Kịch bản 1: Khách muốn chuyển đường bộ
        // Client không cần đụng đến từ khóa "new Truck()"
        Logistics logic1 = new RoadLogistics();
        logic1.planDelivery(); 
        // Output: Xe tải: Chở hàng trên đường bộ 🚛

        // Kịch bản 2: Khách muốn chuyển đường biển
        Logistics logic2 = new SeaLogistics();
        logic2.planDelivery();
        // Output: Tàu thủy: Chở hàng trên đường biển 🚢
    }
}