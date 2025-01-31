package management.shop;

import management.validation.DBManager;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    public String id;
    public String name;
    public ArrayList<String> products;

    public Shop(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public static List<Shop> loadShops(DBManager DBManager) {
        List<Shop> shops = new ArrayList<>();
        List<String> lines = DBManager.readShopAndProductData();
        Shop currentShop = null;
        for (String line : lines) {
            if (line.startsWith("Shop: ")) {
                String[] shopInfo = line.substring(6).split(", ", 4);
                String shopId = shopInfo[0].trim();
                String shopName = shopInfo[1].trim();
                currentShop = new Shop(shopId, shopName);
                shops.add(currentShop);
            } else if (line.startsWith("Product: ") && currentShop != null) {
                currentShop.products.add(line.substring(9).trim());
            }
        }
        return shops;
    }

    public static void saveShops(DBManager DBManager, List<Shop> shops) {
        List<String> content = new ArrayList<>();
        for (Shop shop : shops) {
            content.add("Shop: " + shop.id + ", " + shop.name);
            for (String product : shop.products) {
                content.add("Product: " + product);
            }
        }
        DBManager.writeFile(content);
    }

    public String getId() {
        return id;
    }
}
