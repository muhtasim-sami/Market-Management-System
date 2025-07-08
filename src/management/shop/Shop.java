package management.shop;

import management.validation.DBConnection;
import management.validation.DBManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Shop {
    public String id;
    public String name;
    public String location;
    public String owner;
    public String phone;
    public ArrayList<String> products;
	private DBManager DBManager = new DBManager();
    private DBConnection DBConnection = new DBConnection();
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

    public static void saveShops(DBManager DBManager,DBConnection DBConnection, List<Shop> shops) {
        List<String> content = new ArrayList<>();
        for (Shop shop : shops) {
            content.add("Shop: " + shop.id + ", " + shop.name);
            for (String product : shop.products) {
                content.add("Product: " + product);
            }
        }
        DBConnection.writeFile(content);
    }

    public String getId() {
        return id;
    }
	
	
	
	private boolean isValidProductInfo(String productInfo) {
        // Add validation logic here
        return productInfo != null && !productInfo.trim().isEmpty();
    }

}
