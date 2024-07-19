public class Item {
    int itemNumber;
    double unitPrice;
    int quantity;

    public Item(int itemNumber, int quantity, double unitPrice) {
        this.itemNumber = itemNumber;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
