package frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import management.validation.DBConnection;
import management.shop.*;

public class CustomerView extends JFrame {
    private JList<String> shopList;
    private JTable productTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel;
    private ArrayList<Shop> shops;
    private DBConnection dbConnection;

    public CustomerView(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.shops = new ArrayList<>();
        loadData();
        setupUI();
    }

    private void setupUI() {
        setTitle("Shop Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());

        shopListModel = new DefaultListModel<>();
        shopList = new JList<>(shopListModel);

        for (Shop shop : shops) {
            shopListModel.addElement(shop.name);
        }

        shopList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = shopList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedShopId = shops.get(selectedIndex).getId();
                    updateProductTable(selectedShopId);
                }
            }
        });

        String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
        productTableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(productTableModel);

        JScrollPane shopScrollPane = new JScrollPane(shopList);
        JScrollPane productScrollPane = new JScrollPane(productTable);

        leftPanel.add(new JLabel("Shops"), BorderLayout.NORTH);
        leftPanel.add(shopScrollPane, BorderLayout.CENTER);
        rightPanel.add(new JLabel("Products"), BorderLayout.NORTH);
        rightPanel.add(productScrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> onBack());
        add(backButton, BorderLayout.SOUTH);

        leftPanel.setPreferredSize(new Dimension(300, getHeight()));
    }

    private void updateProductTable(String shopId) {
        List<String> productData = new ArrayList<>();
        for (Shop shop : shops) {
            if (shop.getId().equals(shopId)) {
                productData = shop.products;
                break;
            }
        }
        
        String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
        String[][] data = productData.stream()
                .map(product -> product.split(","))
                .toArray(String[][]::new);
        productTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private void loadData() {
        List<String> lines = dbConnection.readFile(dbConnection.getProductData());
        Shop currentShop = null;
        for (String line : lines) {
            if (line.startsWith("Shop: ")) {
                String[] shopInfo = line.substring(6).split(",", 2);
                String shopId = shopInfo[0].trim();
                String shopName = shopInfo[1].trim();
                currentShop = new Shop(shopId, shopName);
                shops.add(currentShop);
            } else if (line.startsWith("Product: ") && currentShop != null) {
                currentShop.products.add(line.substring(9).trim());
            }
        }
    }

    private void onBack() {
        this.setVisible(false);
        new Homepage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerView(new DBConnection()).setVisible(true);
        });
    }
}
