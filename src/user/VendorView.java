
package user;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import management.validation.DBManager;
import management.shop.*;
import frame.*;

public class VendorView extends JFrame {
    private JList<String> shopList;
    private JTable productTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private JButton billing, back;
    
    public VendorView(DBManager DBManager) {
        this.DBManager = DBManager;
        this.shops = new ArrayList<>();
        loadData();
        setupUI();
    }

    private void setupUI() {
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 = new Font("Arial", Font.BOLD, 13);
        
        Color LIGHT_GREEN = new Color(102, 255, 102);
        Color DARK_GREEN = new Color(0, 153, 0);
        
        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);
        
        setTitle("Shop Management System");
        setVisible(true);
        setBounds(200, 15, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        double w = getWidth();
        int h = (int) getHeight();
        
        JLabel titleLabel = new JLabel("Vendor View");
        titleLabel.setBounds((int) (((1.5 * w) - 200) / 2), (h - 650), 200, 100);
        titleLabel.setFont(f2);
        titleLabel.setForeground(DARK_GREEN);
        add(titleLabel);

        JLabel shopLabel = new JLabel("Select Shop");
        shopLabel.setBounds(50, 120, 200, 30);
        shopLabel.setFont(f1);
        shopLabel.setForeground(Color.GRAY);
        add(shopLabel);

        shopListModel = new DefaultListModel<>();
        shopList = new JList<>(shopListModel);
        shopList.setFont(f3);
        shopList.setBorder(lineBorder);
        
        for (Shop shop : shops) {
            shopListModel.addElement(shop.name);
        }

        JScrollPane shopScrollPane = new JScrollPane(shopList);
        shopScrollPane.setBounds(50, 160, 250, 200);
        add(shopScrollPane);

        JLabel productLabel = new JLabel("Products");
        productLabel.setBounds(50, 380, 200, 30);
        productLabel.setFont(f1);
        productLabel.setForeground(Color.GRAY);
        add(productLabel);

        String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
        productTableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(productTableModel);
        productTable.setFont(f3);
        productTable.getTableHeader().setFont(f3);

        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(50, 420, 700, 150);
        add(productScrollPane);

        // Add selection listener
        shopList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = shopList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedShopId = shops.get(selectedIndex).getId();
                    updateProductTable(selectedShopId);
                }
            }
        });

        // Buttons
        billing = new JButton("Sell");
        billing.setBounds((int) (((1.5 * w) - 193) / 2), 590, 193, 50);
        billing.setFont(f1);
        billing.setCursor(crsr);
        billing.setBackground(DARK_GREEN);
        billing.setForeground(Color.WHITE);
        billing.addActionListener(e -> onBilling());
        add(billing);

        back = new JButton("Log-out");
        back.setBounds((int) (((0.5 * w) - 193) / 2), 590, 193, 50);
        back.setFont(f1);
        back.setCursor(crsr);
        back.setBackground(DARK_GREEN);
        back.setForeground(Color.WHITE);
        back.addActionListener(e -> onBack());
        add(back);
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
        productTableModel = new DefaultTableModel(data, columnNames);
        productTable.setModel(productTableModel);
    }

    private void loadData() {
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
    }

    private void onBilling() {
		/*
        int selectedShopIndex = shopList.getSelectedIndex();
        int selectedProductRow = productTable.getSelectedRow();
        
        if (selectedShopIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a shop first");
            return;
        }
        
        if (selectedProductRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product first");
            return;
        }
        
        Shop selectedShop = shops.get(selectedShopIndex);
        String[] productData = selectedShop.products.get(selectedProductRow).split(",");
        */
        this.setVisible(false);
        //new Billing(DBManager, selectedShop, productData);
		new VendorSell(DBManager);
    }

    private void onBack() {
        this.setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VendorView(new DBManager()).setVisible(true);
        });
    }
}
