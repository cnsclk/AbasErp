import java.util.ArrayList;
import java.util.List;

public class Order {
    int orderId;
    List<Item> items;

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getOrderId() {
        return orderId;
    }
}
