import java.util.ArrayList;
import java.util.List;

// 1. Component (Interface chung cho cả File và Folder)
interface FileSystemComponent {
    void showDetails();
    int getSize(); // Tính dung lượng
}

// 2. Leaf (Lá - Đối tượng đơn lẻ, không chứa con)
class File implements FileSystemComponent {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails() {
        System.out.println("- File: " + name + " (" + size + "MB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// 3. Composite (Cành - Chứa danh sách các Component khác)
class Folder implements FileSystemComponent {
    private String name;
    // Quan trọng: List chứa Component chứ không chỉ chứa File
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void showDetails() {
        System.out.println("+ Folder: " + name);
        // Đệ quy: Gọi showDetails của tất cả con
        for (FileSystemComponent child : children) {
            child.showDetails(); 
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        // Đệ quy: Cộng tổng size của tất cả con
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }
}