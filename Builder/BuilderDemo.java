// 1. Product (Sản phẩm phức tạp)
class Computer {
    // Các thuộc tính (thường là private final để bất biến)
    private String CPU;
    private String RAM;
    private boolean hasBluetooth;
    private boolean hasWifi;

    // Constructor để private để bắt buộc dùng Builder
    private Computer(ComputerBuilder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.hasBluetooth = builder.hasBluetooth;
        this.hasWifi = builder.hasWifi;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Wifi=" + hasWifi + "]";
    }

    // 2. Static Inner Class: Builder
    public static class ComputerBuilder {
        private String CPU;
        private String RAM;
        private boolean hasBluetooth;
        private boolean hasWifi;

        // Bắt buộc phải có CPU và RAM mới được xây
        public ComputerBuilder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        // Các hàm set trả về chính Builder (Fluent Interface)
        public ComputerBuilder setBluetooth(boolean enabled) {
            this.hasBluetooth = enabled;
            return this; // Trả về chính nó để chấm tiếp (.)
        }

        public ComputerBuilder setWifi(boolean enabled) {
            this.hasWifi = enabled;
            return this;
        }

        // Hàm chốt hạ để tạo ra Product
        public Computer build() {
            return new Computer(this);
        }
    }
}

// 3. Sử dụng
public class BuilderDemo {
    public static void main(String[] args) {
        // Cách viết code rất đẹp, dễ đọc (Fluent API)
        Computer gamingPC = new Computer.ComputerBuilder("Core i9", "32GB")
                .setWifi(true)       // Tùy chọn
                .setBluetooth(true)  // Tùy chọn
                .build();            // Xây!

        Computer officePC = new Computer.ComputerBuilder("Core i3", "8GB")
                // Không cần Wifi hay Bluetooth thì khỏi gọi
                .build();

        System.out.println(gamingPC);
    }
}