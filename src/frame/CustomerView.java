package frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import management.validation.DBConnection;

public class CustomerView extends JFrame {
    private JList<String> shopList;
    private JList<String> productList;
    private DefaultListModel<String> shopListModel;
    private DefaultListModel<String> productListModel;
    private ArrayList<Shop> shops;
    private DBConnection dbConnection;

    public CustomerView() {
        dbConnection = DBConnection.getInstance();
        shops = new ArrayList<>();
        loadData();
        setupUI();
    }

    private void setupUI() {
        setTitle("Shop Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());

        // Create panels
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());

        // Initialize list models
        shopListModel = new DefaultListModel<>();
        productListModel = new DefaultListModel<>();

        // Create lists
        shopList = new JList<>(shopListModel);
        productList = new JList<>(productListModel);

        // Add shops to the list model
        for (Shop shop : shops) {
            shopListModel.addElement(shop.name);
        }

        // Add selection listener to shop list
        shopList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = shopList.getSelectedIndex();
                if (selectedIndex != -1) {
                    updateProductList(selectedIndex);
                }
            }
        });

        // Create scroll panes
        JScrollPane shopScrollPane = new JScrollPane(shopList);
        JScrollPane productScrollPane = new JScrollPane(productList);

        // Add labels
        leftPanel.add(new JLabel("Shops"), BorderLayout.NORTH);
        rightPanel.add(new JLabel("Products"), BorderLayout.NORTH);

        // Add scroll panes to panels
        leftPanel.add(shopScrollPane, BorderLayout.CENTER);
        rightPanel.add(productScrollPane, BorderLayout.CENTER);

        // Create buttons panel
        JPanel buttonPanel = new JPanel();
        JButton addShopButton = new JButton("Add Shop");
        JButton addProductButton = new JButton("Add Product");
        buttonPanel.add(addShopButton);
        buttonPanel.add(addProductButton);

        // Add button listeners
        addShopButton.addActionListener(e -> addShop());
        addProductButton.addActionListener(e -> addProduct());

        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set panel sizes
        leftPanel.setPreferredSize(new Dimension(300, getHeight()));
    }

    private void updateProductList(int shopIndex) {
        productListModel.clear();
        if (shopIndex >= 0 && shopIndex < shops.size()) {
            for (String product : shops.get(shopIndex).products) {
                productListModel.addElement(product);
            }
        }
    }

    private void addShop() {
        String shopName = JOptionPane.showInputDialog(this, "Enter shop name:");
        if (shopName != null && !shopName.trim().isEmpty()) {
            Shop newShop = new Shop(shopName.trim());
            shops.add(newShop);
            shopListModel.addElement(shopName);
            saveData();
        }
    }

    private void addProduct() {
        int selectedShopIndex = shopList.getSelectedIndex();
        if (selectedShopIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a shop first!");
            return;
        }
        String productName = JOptionPane.showInputDialog(this, "Enter product name:");
        if (productName != null && !productName.trim().isEmpty()) {
            shops.get(selectedShopIndex).products.add(productName.trim());
            updateProductList(selectedShopIndex);
            saveData();
        }
    }

    private void loadData() {
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
    }

    private void saveData() {
        List<String> content = new ArrayList<>();
        for (Shop shop : shops) {
            content.add("Shop: " + shop.name);
            for (String product : shop.products) {
                content.add("Product: " + product);
            }
        }
        dbConnection.writeFile(dbConnection.shopData, content);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerView().setVisible(true);
        });
    }
}