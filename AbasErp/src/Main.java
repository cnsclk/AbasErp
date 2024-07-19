import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();

        // Order 1
        Order order1 = new Order(1000);
        order1.addItem(new Item(2000, 12, 100.51));
        order1.addItem(new Item(2001, 31, 200));
        order1.addItem(new Item(2002, 22, 150.86));
        order1.addItem(new Item(2003, 41, 250));
        order1.addItem(new Item(2004, 55, 244));
        orders.add(order1);

        // Order 2
        Order order2 = new Order(1001);
        order2.addItem(new Item(2001, 88, 44.531));
        order2.addItem(new Item(2002, 121, 88.11));
        order2.addItem(new Item(2004, 74, 211));
        order2.addItem(new Item(2002, 14, 88.11));
        orders.add(order2);

        // Order 3
        Order order3 = new Order(1002);
        order3.addItem(new Item(2003, 2, 12.1));
        order3.addItem(new Item(2004, 3, 22.3));
        order3.addItem(new Item(2003, 8, 12.1));
        order3.addItem(new Item(2002, 16, 94));
        order3.addItem(new Item(2005, 9, 44.1));
        order3.addItem(new Item(2006, 19, 90));
        orders.add(order3);

        // a) Üç siparişteki malların toplam tutarının çıktısını veren kod
        double totalAmount = 0;
        for (Order order : orders) {
            for (Item item : order.getItems()) {
                totalAmount += item.getUnitPrice() * item.getQuantity();
            }
        }
        System.out.println("Üç siparişteki malların toplam tutarı: " + totalAmount);

        // b) Üç siparişteki bütün malların ortalama fiyatını bulan kod
        int totalItemCount = 0;
        double totalPriceSum = 0;
        for (Order order : orders) {
            for (Item item : order.getItems()) {
                totalPriceSum += item.getUnitPrice() * item.getQuantity();
                totalItemCount += item.getQuantity();
            }
        }
        double averagePrice = totalPriceSum / totalItemCount;
        System.out.println("Üç siparişteki bütün malların ortalama fiyatı: " + averagePrice);

        // c) Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatını bulan kod
        Map<Integer, Double> itemTotalPrices = new HashMap<>();
        Map<Integer, Integer> itemTotalQuantities = new HashMap<>();

        for (Order order : orders) {
            for (Item item : order.getItems()) {
                int itemNumber = item.getItemNumber();
                itemTotalPrices.putIfAbsent(itemNumber, 0.0);
                itemTotalQuantities.putIfAbsent(itemNumber, 0);

                itemTotalPrices.put(itemNumber,
                        itemTotalPrices.get(itemNumber) + item.getUnitPrice() * item.getQuantity());
                itemTotalQuantities.put(itemNumber,
                        itemTotalQuantities.get(itemNumber) + item.getQuantity());
            }
        }

        for (Map.Entry<Integer, Double> entry : itemTotalPrices.entrySet()) {
            int itemNumber = entry.getKey();
            double totalItemPrice = entry.getValue();
            int totalItemQuantity = itemTotalQuantities.get(itemNumber);
            double avgPrice = totalItemPrice / totalItemQuantity;
            System.out.println("Mal numarası " + itemNumber + " olan malların ortalama fiyatı: " + avgPrice);
        }


        // d) Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğunun çıktısını veren kod
        Map<Integer, Map<Integer, Integer>> itemOrderCounts = new HashMap<>();
        for (Order order : orders) {
            for (Item item : order.getItems()) {
                itemOrderCounts.putIfAbsent(item.getItemNumber(), new HashMap<>());
                Map<Integer, Integer> orderCounts = itemOrderCounts.get(item.getItemNumber());
                orderCounts.put(order.getOrderId(), orderCounts.getOrDefault(order.getOrderId(), 0) + item.getQuantity());
            }
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> entry : itemOrderCounts.entrySet()) {
            int itemNumber = entry.getKey();
            System.out.println("Mal numarası " + itemNumber + " olan malların siparişlerdeki adetleri:");
            for (Map.Entry<Integer, Integer> orderEntry : entry.getValue().entrySet()) {
                int orderId = orderEntry.getKey();
                int count = orderEntry.getValue();
                System.out.println("Sipariş ID: " + orderId + ", Adet: " + count);
            }
        }


    }
}




