import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Homepage extends JFrame {
    private JList<String> shopList;
    private JTextArea productArea;
    private List<Shop> shops;

    public Homepage() {
        setTitle("Shop Homepage");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load shops and products from file
        shops = loadShops("shops.txt");

        // Create UI components
        shopList = new JList<>(shops.stream().map(Shop::getName).toArray(String[]::new));
        productArea = new JTextArea();
        productArea.setEditable(false);

        // Add listeners
        shopList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = shopList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Shop selectedShop = shops.get(selectedIndex);
                    displayProducts(selectedShop);
                }
            }
        });

        // Layout
        setLayout(new BorderLayout());
        add(new JScrollPane(shopList), BorderLayout.WEST);
        add(new JScrollPane(productArea), BorderLayout.CENTER);
    }

    private List<Shop> loadShops(String filename) {
        List<Shop> shops = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 1) {
                    String shopName = parts[0];
                    String[] products = parts[1].split(",");
                    shops.add(new Shop(shopName, products));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shops;
    }

    private void displayProducts(Shop shop) {
        StringBuilder sb = new StringBuilder();
        for (String product : shop.getProducts()) {
            sb.append(product).append("\n");
        }
        productArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Homepage homepage = new Homepage();
            homepage.setVisible(true);
        });
    }
}

class Shop {
    private String name;
    private String[] products;

    public Shop(String name, String[] products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public String[] getProducts() {
        return products;
    }
}
