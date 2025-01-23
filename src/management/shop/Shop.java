
package management.shop;

import management.validation.DBConnection;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    public String name;
    public ArrayList<String> products;

    public Shop(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public static List<Shop> loadShops(DBConnection dbConnection) {
        List<Shop> shops = new ArrayList<>();
        List<String> lines = dbConnection.readFile(dbConnection.shopData);
        Shop currentShop = null;
        for (String line : lines) {
            if (line.startsWith("Shop: ")) {
                String shopName = line.substring(6);
                currentShop = new Shop(shopName);
                shops.add(currentShop);
            } else if (line.startsWith("Product: ") && currentShop != null) {
                String productName = line.substring(9);
                currentShop.products.add(productName);
            }
        }
        return shops;
    }

    public static void saveShops(DBConnection dbConnection, List<Shop> shops) {
        List<String> content = new ArrayList<>();
        for (Shop shop : shops) {
            content.add("Shop: " + shop.name);
            for (String product : shop.products) {
                content.add("Product: " + product);
            }
        }
        dbConnection.writeFile(dbConnection.shopData, content);
    }
}
