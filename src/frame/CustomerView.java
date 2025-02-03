package frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import management.validation.*;
import management.shop.*;

public class CustomerView extends JFrame {
    private JList<String> shopList;
    private JTable productTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private JButton billing, back;
	private String path = System.getProperty("user.dir");  
	private String background = (path.substring(0, path.length() - 3) + "pic\\Billing System.jpg");
    
    public CustomerView(DBManager DBManager) {
        this.DBManager = DBManager;
        this.shops = new ArrayList<>();
        loadData();
        setupUI();
    }

    private void setupUI() {
        // Fonts
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 = new Font("Arial", Font.BOLD, 13);
        
        // Colors
        Color LIGHT_GREEN = new Color(102, 255, 102);
        Color DARK_GREEN = new Color(0, 153, 0);
		Color DARK_BLUE = new Color(0, 0, 204);

        
        // Cursor and Border
        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);
        
        // Frame setup
        setTitle("Shop Management System");
        setVisible(true);
        setBounds(200, 15, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //getContentPane().setBackground(Color.WHITE);
        setLayout(null);
		
		JLabel l = new JLabel(new ImageIcon(background));
		setContentPane(l);
        
        double w = getWidth();
        int h = (int) getHeight();
        
        // Title
        JLabel titleLabel = new JLabel("Customer View");
        titleLabel.setBounds((int) (((1.5 * w) - 200) / 2), (h - 650), 200, 100);
        titleLabel.setFont(f2);
        titleLabel.setForeground( DARK_BLUE);
        add(titleLabel);

        // Shop List Panel
        JLabel shopLabel = new JLabel("Select Shop");
        shopLabel.setBounds(50, 120, 200, 30);
        shopLabel.setFont(f1);
        shopLabel.setForeground(Color.WHITE);
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

        // Product Table Panel
        JLabel productLabel = new JLabel("Products");
        productLabel.setBounds(50, 380, 200, 30);
        productLabel.setFont(f1);
        productLabel.setForeground(Color.WHITE);
        add(productLabel);

        String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
        productTableModel = new NonEditableTableModel(new Object[][]{}, columnNames); // Use NonEditableTableModel
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
        billing = new JButton("Buy Product");
        billing.setBounds((int) (((1.5 * w) - 193) / 2), 590, 193, 50);
        billing.setFont(f1);
        billing.setCursor(crsr);
        billing.setBackground( DARK_BLUE);
        billing.setForeground(Color.WHITE);
        billing.addActionListener(e -> onBilling());
        add(billing);

        back = new JButton("Back");
        back.setBounds((int) (((0.5 * w) - 193) / 2), 590, 193, 50);
        back.setFont(f1);
        back.setCursor(crsr);
        back.setBackground( DARK_BLUE);
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
        
        // Use the custom NonEditableTableModel
        productTableModel = new NonEditableTableModel(data, columnNames);
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
        this.setVisible(false);
        new Buying(DBManager);
    }

    private void onBack() {
        this.setVisible(false);
        new Homepage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerView(new DBManager()).setVisible(true);
        });
    }
}

class NonEditableTableModel extends DefaultTableModel {
    public NonEditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Make all cells uneditable
        return false;
    }
}