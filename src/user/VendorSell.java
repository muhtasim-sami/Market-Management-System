package user;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.text.SimpleDateFormat;
import management.validation.DBManager;
import management.shop.*;
import management.product.*;

public class VendorSell extends JFrame {
    private JList<String> shopList;
    private JTable productTable, selectedProductsTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel, selectedProductsModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private JTextField jtcustomerName, jtcustomerNum, quantityField;
    private JTextArea billArea;
    private JButton newProduct, addToCart, generateBill, saveButton, backButton;
    private double totalAmount = 0;
	private String path = System.getProperty("user.dir");  
	private String background = (path.substring(0, path.length() - 3) + "pic\\Background.jpg");
	private ArrayList<Object[]> selectedProductsList = new ArrayList<>();

    public VendorSell(DBManager DBManager) {
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
        setTitle("VendorSell System");
        setBounds(200, 15, 1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //getContentPane().setBackground(Color.ORANGE);
        setLayout(null);
		setVisible(true);
		
		JLabel l = new JLabel(new ImageIcon(background));
		setContentPane(l);

        // Title
        JLabel titleLabel = new JLabel("Vendor Sell System");
        titleLabel.setBounds(450, 20, 300, 50);
        titleLabel.setFont(f2);
        titleLabel.setForeground(DARK_BLUE);
        add(titleLabel);

        // Customer Information Panel
        setupCustomerPanel(f1, f3, DARK_BLUE);
        
        // Shop and Product Selection Panel
        setupShopAndProductPanel(f1, f3, DARK_BLUE, lineBorder);
        
        // Selected Products Panel
        setupSelectedProductsPanel(f1, f3, DARK_BLUE, lineBorder);
        
        // Bill Panel
        setupBillPanel(f1, f3, DARK_BLUE, lineBorder, crsr);
    }

    private void setupCustomerPanel(Font f1, Font f3, Color DARK_BLUE) {
        JPanel customerPanel = new JPanel(null);
        customerPanel.setBounds(20, 80, 1040, 60);
        customerPanel.setBackground(Color.ORANGE);
        
        JLabel customerName = new JLabel("Vendor's Name:");
        customerName.setBounds(10, 20, 150, 20);
        customerName.setFont(f3);
        customerName.setForeground(DARK_BLUE);
        //customerPanel.add(customerName);

        jtcustomerName = new JTextField();
        jtcustomerName.setBounds(160, 20, 150, 20);
        jtcustomerName.setFont(f3);
        //customerPanel.add(jtcustomerName);

        JLabel customerNum = new JLabel("Contact Number:");
        customerNum.setBounds(330, 20, 150, 20);
        customerNum.setFont(f3);
        customerNum.setForeground(DARK_BLUE);
        //customerPanel.add(customerNum);

        jtcustomerNum = new JTextField();
        jtcustomerNum.setBounds(490, 20, 150, 20);
        jtcustomerNum.setFont(f3);
        //customerPanel.add(jtcustomerNum);
		
		newProduct = new JButton("Add New Product");
        newProduct.setBounds(30, 100, 150, 30);
        newProduct.setFont(f3);
        newProduct.setBackground(DARK_BLUE);
        newProduct.setForeground(Color.ORANGE);
        newProduct.addActionListener(e -> addNewProducts());
        add(newProduct);

        //add(customerPanel);
    }

    private void setupShopAndProductPanel(Font f1, Font f3, Color DARK_BLUE, LineBorder lineBorder) {
        // Shop List
        JLabel shopLabel = new JLabel("Select Shop");
        shopLabel.setBounds(20, 150, 200, 30);
        shopLabel.setFont(f1);
        shopLabel.setForeground(DARK_BLUE);
        add(shopLabel);

        shopListModel = new DefaultListModel<>();
        shopList = new JList<>(shopListModel);
        shopList.setFont(f3);
        shopList.setBorder(lineBorder);
        
        for (Shop shop : shops) {
            shopListModel.addElement(shop.name);
        }

        JScrollPane shopScrollPane = new JScrollPane(shopList);
        shopScrollPane.setBounds(20, 190, 250, 150);
        add(shopScrollPane);

        // Product Table
        JLabel productLabel = new JLabel("Available Products");
        productLabel.setBounds(290, 150, 200, 30);
        productLabel.setFont(f1);
        productLabel.setForeground(DARK_BLUE);
        add(productLabel);

        String[] columnNames = {"ProductID", "ProductName", "Type", "Available", "Price", "CompanyName"};
        productTableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(productTableModel);
        productTable.setFont(f3);
        productTable.getTableHeader().setFont(f3);

        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(290, 190, 770, 150);
        add(productScrollPane);

        // Quantity Panel
        JPanel quantityPanel = new JPanel(null);
        quantityPanel.setBounds(20, 350, 450, 50);
        quantityPanel.setBackground(Color.ORANGE);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(30, 360, 80, 30);
        quantityLabel.setFont(f3);
        quantityLabel.setForeground(DARK_BLUE);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(110, 360, 80, 30);
        quantityField.setFont(f3);
        add(quantityField);

        addToCart = new JButton("Sell");
        addToCart.setBounds(200, 360, 120, 30);
        addToCart.setFont(f3);
        addToCart.setBackground(DARK_BLUE);
        addToCart.setForeground(Color.ORANGE);
        addToCart.addActionListener(e -> addToSelectedProducts());
        add(addToCart);

        //add(quantityPanel);

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
    }

    private void setupSelectedProductsPanel(Font f1, Font f3, Color DARK_BLUE, LineBorder lineBorder) {
        JLabel selectedLabel = new JLabel("Selected Products");
        selectedLabel.setBounds(20, 410, 200, 30);
        selectedLabel.setFont(f1);
        selectedLabel.setForeground(DARK_BLUE);
        add(selectedLabel);

        String[] columnNames = {"ProductID", "ProductName", "Quantity", "Price", "Total"};
        selectedProductsModel = new DefaultTableModel(columnNames, 0);
        selectedProductsTable = new JTable(selectedProductsModel);
        selectedProductsTable.setFont(f3);
        selectedProductsTable.getTableHeader().setFont(f3);

        JScrollPane selectedScrollPane = new JScrollPane(selectedProductsTable);
        selectedScrollPane.setBounds(20, 450, 1040, 150);
        add(selectedScrollPane);
    }

    private void setupBillPanel(Font f1, Font f3, Color DARK_BLUE, LineBorder lineBorder, Cursor crsr) {
        // Bill Area
        billArea = new JTextArea();
        billArea.setFont(f3);
        billArea.setEditable(false);
        JScrollPane billScroll = new JScrollPane(billArea);
        billScroll.setBounds(20, 610, 1040, 180);
        add(billScroll);

        // Buttons
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(500, 350, 720, 50);
        buttonPanel.setBackground(Color.ORANGE);

        generateBill = new JButton("Generate Bill");
        generateBill.setBounds(510, 360, 150, 30);
        generateBill.setFont(f3);
        generateBill.setCursor(crsr);
        generateBill.setBackground(DARK_BLUE);
        generateBill.setForeground(Color.ORANGE);
        generateBill.addActionListener(e -> generateBill());
        add(generateBill);

        saveButton = new JButton("Save Bill");
        saveButton.setBounds(680, 360, 150, 30);
        saveButton.setFont(f3);
        saveButton.setCursor(crsr);
        saveButton.setBackground(DARK_BLUE);
        saveButton.setForeground(Color.ORANGE);
        saveButton.addActionListener(e -> saveBill());
        add(saveButton);

        backButton = new JButton("Back");
        backButton.setBounds(850, 360, 150, 30);
        backButton.setFont(f3);
        backButton.setCursor(crsr);
        backButton.setBackground(DARK_BLUE);
        backButton.setForeground(Color.ORANGE);
        backButton.addActionListener(e -> goBack());
        add(backButton);

        //add(buttonPanel);
    }
	private void addNewProducts (){
		this.setVisible(false);
		new AddProduct("VendorSell");
	}

    private void addToSelectedProducts() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product first");
            return;
        }

        String quantityStr = quantityField.getText().trim();
        if (quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter quantity");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            int availableQuantity = Integer.parseInt(productTable.getValueAt(selectedRow, 3).toString());
            
            String productId = productTable.getValueAt(selectedRow, 0).toString();
            String productName = productTable.getValueAt(selectedRow, 1).toString();
            double price = Double.parseDouble(productTable.getValueAt(selectedRow, 4).toString());
            double total = price * quantity;

            selectedProductsModel.addRow(new Object[]{
                productId,
                productName,
                quantity,
                price,
                total
            });

            totalAmount += total;
            quantityField.setText("");

            // Update available quantity in product table
            productTable.setValueAt(availableQuantity + quantity, selectedRow, 3);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity");
        }
    }

    private void generateBill() {
        try {
			for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
				String productId = selectedProductsModel.getValueAt(i, 0).toString();
				int quantity = Integer.parseInt(selectedProductsModel.getValueAt(i, 2).toString());
				
				// Update the quantity in database
				DBManager.increasedProductQuantity(productId, quantity);
			}

			// Now generate the bill as before
			StringBuilder bill = new StringBuilder();
			bill.append("************************************************************\n");
			bill.append("************************* BILLING INVOICE *******************\n");
			bill.append("************************************************************\n\n");
			bill.append("Customer Name: ").append(jtcustomerName.getText()).append("\n");
			bill.append("Contact Number: ").append(jtcustomerNum.getText()).append("\n");
			bill.append("Date: ").append(java.time.LocalDate.now()).append("\n\n");
			bill.append("Products:\n");
			bill.append("---------------------------------------------------------------\n");
			bill.append(String.format("%-10s %-20s %-10s %-10s %-10s\n", 
				"ID", "Name", "Quantity", "Price", "Total"));
			bill.append("---------------------------------------------------------------\n");

			for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
				bill.append(String.format("%-10s %-20s %-10s %-10.2f %-10.2f\n",
					selectedProductsModel.getValueAt(i, 0),
					selectedProductsModel.getValueAt(i, 1),
					selectedProductsModel.getValueAt(i, 2),
					selectedProductsModel.getValueAt(i, 3),
					selectedProductsModel.getValueAt(i, 4)));
			}

			bill.append("---------------------------------------------------------------\n");
			bill.append(String.format("Total Amount: %.2f\n", totalAmount));
			bill.append("---------------------------------------------------------------\n");
			bill.append("\nThank you for selling!\n");
			
			billArea.setText(bill.toString());
			
			// Show success message
			JOptionPane.showMessageDialog(this, "Bill generated and quantities updated successfully!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"Error updating quantities: " + e.getMessage(),
				"Update Error",
				JOptionPane.ERROR_MESSAGE);
		}
    }

    private void saveBill() {
        if (billArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please generate bill first");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getParentFile(), file.getName() + ".txt");
            }
            
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(billArea.getText());
                JOptionPane.showMessageDialog(this, "Bill saved successfully");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving bill: " + e.getMessage());
            }
        }
    }

    private void goBack() {
        this.setVisible(false);
        new VendorView(DBManager);
    }

    private void loadData() {
        List<String> lines = DBManager.readShopAndProductData();
        Shop currentShop = null;
        
        try {
            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }
                
                if (line.startsWith("Shop: ")) {
                    String[] shopInfo = line.substring(6).split(", ", 4);
                    if (shopInfo.length >= 2) {
                        String shopId = shopInfo[0].trim();
                        String shopName = shopInfo[1].trim();
                        currentShop = new Shop(shopId, shopName);
                        shops.add(currentShop);
                    }
                } else if (line.startsWith("Product: ") && currentShop != null) {
                    String productInfo = line.substring(9).trim();
                    // Validate product info before adding
                    if (isValidProductInfo(productInfo)) {
                        currentShop.products.add(productInfo);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading shop and product data: " + e.getMessage(),
                "Data Loading Error",
                JOptionPane.ERROR_MESSAGE);
        }

        // If no shops were loaded, show error message
        if (shops.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No shop data was loaded. Please check the data source.",
                "Data Loading Warning",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean isValidProductInfo(String productInfo) {
        String[] parts = productInfo.split(",");
        // Expecting: ProductID, ProductName, Type, Quantity, Price, CompanyName
        if (parts.length != 6) {
            return false;
        }
        
        try {
            // Validate ProductID (non-empty)
            if (parts[0].trim().isEmpty()) {
                return false;
            }
            
            // Validate Quantity (must be a positive integer)
            int quantity = Integer.parseInt(parts[3].trim());
            if (quantity < 0) {
                return false;
            }
            
            // Validate Price (must be a positive number)
            double price = Double.parseDouble(parts[4].trim());
            if (price < 0) {
                return false;
            }
            
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void updateProductTable(String shopId) {
        // Clear existing table data
        productTableModel.setRowCount(0);
        
        // Find the selected shop
        Shop selectedShop = shops.stream()
            .filter(shop -> shop.getId().equals(shopId))
            .findFirst()
            .orElse(null);
            
        if (selectedShop != null) {
            // Add each product to the table
            for (String productInfo : selectedShop.products) {
                String[] data = productInfo.split(",");
                if (data.length == 6) { // Ensure we have all required fields
                    productTableModel.addRow(new Object[]{
                        data[0].trim(), // ProductID
                        data[1].trim(), // ProductName
                        data[2].trim(), // Type
                        data[3].trim(), // Quantity
                        data[4].trim(), // Price
                        data[5].trim()  // CompanyName
                    });
                }
            }
        }
        
        // Notify the table that data has changed
        productTableModel.fireTableDataChanged();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DBManager dbManager = new DBManager();
                new VendorSell(dbManager).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error initializing the application: " + e.getMessage());
            }
        });
    }
}