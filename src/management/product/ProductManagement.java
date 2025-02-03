package management.product;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import frame.*;
import management.validation.DBManager;
import management.shop.Shop;

public class ProductManagement extends JFrame implements ActionListener {

    JButton view, add, update, remove, logout, search, print, updateProduct;
    private JList<String> shopList;
    private JTable productTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private Choice cproductId;

    public ProductManagement() {
        DBManager = new DBManager();
        this.shops = new ArrayList<>();
        loadData();
        setupUI();
    }

    private void setupUI() {
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 = new Font("Arial", Font.BOLD, 13);

        Color DARK_GREEN = new Color(0, 153, 0);

        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);

        setVisible(true);
        setBounds(200, 15, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Product Management");
        heading.setBounds((int) (((1.5 * getWidth()) - 300) / 2), 20, 300, 40);
        heading.setFont(f2);
        heading.setForeground(DARK_GREEN);
        add(heading);

        // Shop List Panel
        JLabel shopLabel = new JLabel("Select Shop");
        shopLabel.setBounds(400, 100, 200, 30);
        shopLabel.setFont(f3);
        shopLabel.setForeground(DARK_GREEN);
        add(shopLabel);

        shopListModel = new DefaultListModel<>();
        shopList = new JList<>(shopListModel);
        shopList.setFont(f3);
        shopList.setBorder(lineBorder);

        for (Shop shop : shops) {
            shopListModel.addElement(shop.name);
        }

        JScrollPane shopScrollPane = new JScrollPane(shopList);
        shopScrollPane.setBounds(400, 140, 250, 200);
        add(shopScrollPane);

        // Product Table Panel
        JLabel productLabel = new JLabel("Products");
        productLabel.setBounds(50, 360, 200, 30);
        productLabel.setFont(f3);
        productLabel.setForeground(DARK_GREEN);
        add(productLabel);

        String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
        productTableModel = new NonEditableTableModel(new Object[][]{}, columnNames); // Use NonEditableTableModel
        productTable = new JTable(productTableModel);
        productTable.setFont(f3);
        productTable.getTableHeader().setFont(f3);

        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(50, 400, 700, 150);
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
        add = new JButton("Add Product");
        add.setBounds(50, 100, 150, 40);
        add.setFont(f3);
        add.setCursor(crsr);
        add.setBackground(DARK_GREEN);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        /*
		view = new JButton("View Products");
        view.setBounds(50, 150, 150, 40);
        view.setFont(f3);
        view.setCursor(crsr);
        view.setBackground(DARK_GREEN);
        view.setForeground(Color.WHITE);
        view.addActionListener(this);
        add(view);
		*/

        update = new JButton("Update Products");
        update.setBounds(50, 150, 150, 40);
        update.setFont(f3);
        update.setCursor(crsr);
        update.setBackground(DARK_GREEN);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        remove = new JButton("Remove Products");
        remove.setBounds(50, 200, 150, 40);
        remove.setFont(f3);
        remove.setCursor(crsr);
        remove.setBackground(DARK_GREEN);
        remove.setForeground(Color.WHITE);
        remove.addActionListener(this);
        add(remove);

        logout = new JButton("Logout");
        logout.setBounds(50, 250, 150, 40);
        logout.setFont(f3);
        logout.setCursor(crsr);
        logout.setBackground(DARK_GREEN);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);

        // ViewProduct Components at the Bottom
        JLabel searchLabel = new JLabel("Search by Product Id");
        searchLabel.setBounds(50, 580, 200, 20);
        searchLabel.setFont(f3);
        searchLabel.setForeground(DARK_GREEN);
        add(searchLabel);

        cproductId = new Choice();
        cproductId.setBounds(250, 580, 150, 20);
        add(cproductId);

        // Populate the choice with product IDs
        List<String> productData = DBManager.readProductData();
        for (String product : productData) {
            String[] details = product.split(",");
            cproductId.add(details[0]);
        }

        search = new JButton("Search");
        search.setBounds(420, 580, 120, 20);
        search.setFont(f3);
        search.setCursor(crsr);
        search.setBackground(DARK_GREEN);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(550, 580, 120, 20);
        print.setFont(f3);
        print.setCursor(crsr);
        print.setBackground(DARK_GREEN);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        updateProduct = new JButton("Update");
        updateProduct.setBounds(680, 580, 120, 20);
        updateProduct.setFont(f3);
        updateProduct.setCursor(crsr);
        updateProduct.setBackground(DARK_GREEN);
        updateProduct.setForeground(Color.WHITE);
        updateProduct.addActionListener(this);
        add(updateProduct);
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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddProduct("");
        }/* else if (ae.getSource() == view) {
            setVisible(false);
            new ViewProduct();
        }*/ else if (ae.getSource() == update) {
            setVisible(false);
            new ViewProduct();
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveProduct();
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new Login();
        } else if (ae.getSource() == search) {
            String selectedProId = cproductId.getSelectedItem();
            List<String> productData = DBManager.readProductData();
            String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
            String[][] data = productData.stream()
                    .filter(product -> product.split(",")[0].equals(selectedProId))
                    .map(product -> product.split(","))
                    .toArray(String[][]::new);
            productTable.setModel(new NonEditableTableModel(data, columnNames));
        } else if (ae.getSource() == print) {
            try {
                productTable.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == updateProduct) {
            setVisible(false);
            new UpdateProduct(cproductId.getSelectedItem());
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ProductManagement();
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