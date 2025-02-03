package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.text.SimpleDateFormat;
import management.validation.DBManager;
import management.shop.*;

public class Buying extends JFrame {
    private JList<String> shopList;
    private JTable productTable, selectedProductsTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel, selectedProductsModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private JTextField jtcustomerName, jtcustomerNum, quantityField;
    private JTextArea billArea;
    private TableModelListener quantityFocus;
    private JButton addToCart, generateBill, saveButton, backButton;
	private String customerName;
    private String customerNum;
    private double totalAmount = 0;
	private Font f1 = new Font("Arial", Font.BOLD, 18);
	private Font f2 = new Font("Times New Roman", Font.BOLD, 25);
	private Font f3 = new Font("Arial", Font.BOLD, 13);
	private String path = System.getProperty("user.dir");  
	private String background = (path.substring(0, path.length() - 3) + "pic\\Billing System.jpg");
	
	// Colors
	private Color LIGHT_GREEN = new Color(102, 255, 102);
	private Color DARK_GREEN = new Color(0, 153, 0);
	private Color DARK_BLUE = new Color(0, 0, 204);

	
	// Cursor and Border
	private Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
	private LineBorder lineBorder = new LineBorder(Color.black, 1, true);
	
	private boolean firstTimeOrder;
	private boolean backToOrder;

    public Buying(DBManager DBManager) {
        this.DBManager = DBManager;
        this.shops = new ArrayList<>();
        loadShopData();
        setupUI();
        firstTimeOrder = true;
		backToOrder = false;
		
        setupSelectedProductsPanel(f1, f3,  DARK_BLUE, lineBorder);
        
        setupBillPanel(f1, f3,  DARK_BLUE, lineBorder, crsr);
    }
	
	public Buying(DBManager DBManager, String customerName, String customerNum, DefaultTableModel selectedProductsModel ) {
        this.selectedProductsModel = selectedProductsModel;
		this.DBManager = DBManager;
		this.customerName = customerName;
        this.customerNum = customerNum;
		this.shops = new ArrayList<>();
		firstTimeOrder = false;
		backToOrder = true;

        loadShopData();
        setupUI();
		
		JLabel selectedLabel = new JLabel("Selected Products");
        selectedLabel.setBounds(290, 360, 200, 30);
        selectedLabel.setFont(f1);
        selectedLabel.setForeground( DARK_BLUE);
        add(selectedLabel);
		
		selectedProductsTable = new JTable(selectedProductsModel);
        selectedProductsTable.setFont(f3);
        selectedProductsTable.getTableHeader().setFont(f3);

        JScrollPane selectedScrollPane = new JScrollPane(selectedProductsTable);
        selectedScrollPane.setBounds(290, 400, 770, 150);
        add(selectedScrollPane);
		
		setupBillPanel(f1, f3,  DARK_BLUE, lineBorder, crsr);

    }

    private void setupUI() {
        // Fonts
        
        // Frame setup
        setTitle("Buying System");
        setBounds(200, 15, 1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //getContentPane().setBackground(Color.WHITE);
        setLayout(null);
		setVisible(true);
		
		JLabel l = new JLabel(new ImageIcon(background));
		setContentPane(l);

        // Title
        JLabel titleLabel = new JLabel("Buying System");
        titleLabel.setBounds(450, 20, 200, 50);
        titleLabel.setFont(f2);
        titleLabel.setForeground( DARK_BLUE);
        add(titleLabel);

        // Customer Information Panel
        //setupCustomerPanel(f1, f3,  DARK_BLUE);
        
        // Shop and Product Selection Panel
        setupShopAndProductPanel(f1, f3,  DARK_BLUE, lineBorder);
    }

    /*private void setupCustomerPanel(Font f1, Font f3, Color  DARK_BLUE) {
        JPanel customerPanel = new JPanel(null);
        customerPanel.setBounds(20, 80, 1040, 60);
        customerPanel.setBackground(Color.WHITE);
        
        JLabel customerName = new JLabel("Customer's Name:");
        customerName.setBounds(10, 20, 150, 20);
        customerName.setFont(f3);
        customerName.setForeground( DARK_BLUE);
        customerPanel.add(customerName);

        jtcustomerName = new JTextField();
        jtcustomerName.setBounds(160, 20, 150, 20);
        jtcustomerName.setFont(f3);
        customerPanel.add(jtcustomerName);

        JLabel customerNum = new JLabel("Contact Number:");
        customerNum.setBounds(330, 20, 150, 20);
        customerNum.setFont(f3);
        customerNum.setForeground( DARK_BLUE);
        customerPanel.add(customerNum);

        jtcustomerNum = new JTextField();
        jtcustomerNum.setBounds(490, 20, 150, 20);
        jtcustomerNum.setFont(f3);
        customerPanel.add(jtcustomerNum);

        add(customerPanel);
    }*/

    private void setupShopAndProductPanel(Font f1, Font f3, Color  DARK_BLUE, LineBorder lineBorder) {
        // Shop List
        JLabel shopLabel = new JLabel("Select Shop");
        shopLabel.setBounds(20, 150, 200, 30);
        shopLabel.setFont(f1);
        shopLabel.setForeground( DARK_BLUE);
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
        //productLabel.setEnabled(false);
        add(productLabel);

        String[] columnNames = {"ProductID", "ProductName", "Type", "Available", "Price", "CompanyName"};
        productTableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(productTableModel);
        productTable.setFont(f3);
        productTable.getTableHeader().setFont(f3);
        //productTable.setModel(tableModel);
        //productTable.setEnabled(false);
        //productTable.addTableModelListener(quantityFocus);

        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(290, 190, 770, 150);
        add(productScrollPane);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(30, 360, 60, 30);
        quantityLabel.setFont(f3);
        quantityLabel.setForeground( DARK_BLUE);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(100, 360, 80, 30);
        quantityField.setFont(f3);
        add(quantityField);

        addToCart = new JButton("Add to Cart");
        addToCart.setBounds(30, 400, 150, 30);
        addToCart.setFont(f3);
        addToCart.setBackground( DARK_BLUE);
        addToCart.setForeground(Color.WHITE);
        addToCart.addActionListener(e -> addToSelectedProducts());
        add(addToCart);

        shopList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = shopList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedShopId = shops.get(selectedIndex).getId();
                    updateProductTable(selectedShopId);
                }
            }
        });
		
		quantityFocus = new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				quantityField.requestFocus();
			}
		};
    }

    private void setupSelectedProductsPanel(Font f1, Font f3, Color  DARK_BLUE, LineBorder lineBorder) {
        JLabel selectedLabel = new JLabel("Selected Products");
        selectedLabel.setBounds(290, 360, 200, 30);
        selectedLabel.setFont(f1);
        selectedLabel.setForeground( DARK_BLUE);
        add(selectedLabel);

        String[] columnNames = {"ProductID", "ProductName", "Quantity", "Price", "Total"};
        selectedProductsModel = new DefaultTableModel(columnNames, 0);
        selectedProductsTable = new JTable(selectedProductsModel);
        selectedProductsTable.setFont(f3);
        selectedProductsTable.getTableHeader().setFont(f3);

        JScrollPane selectedScrollPane = new JScrollPane(selectedProductsTable);
        selectedScrollPane.setBounds(290, 400, 770, 150);
        add(selectedScrollPane);
    }

    private void setupBillPanel(Font f1, Font f3, Color  DARK_BLUE, LineBorder lineBorder, Cursor crsr) {
        
        generateBill = new JButton("Order");
        generateBill.setBounds(30, 440, 150, 30);
        generateBill.setFont(f3);
        generateBill.setCursor(crsr);
        generateBill.setBackground( DARK_BLUE);
        generateBill.setForeground(Color.WHITE);
        generateBill.setVisible(firstTimeOrder);
        generateBill.addActionListener(e -> firstTImeBilling());
        add(generateBill);
		
        generateBill = new JButton("Back to Order");
        generateBill.setBounds(30, 440, 150, 30);
        generateBill.setFont(f3);
        generateBill.setCursor(crsr);
        generateBill.setBackground( DARK_BLUE);
        generateBill.setForeground(Color.WHITE);
        generateBill.addActionListener(e -> backToBilling());
        generateBill.setVisible(backToOrder);
        add(generateBill);

        backButton = new JButton("Back");
        backButton.setBounds(30, 480, 150, 30);
        backButton.setFont(f3);
        backButton.setCursor(crsr);
        backButton.setBackground( DARK_BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> goBack());
        add(backButton);
		
		JButton cancelButton = new JButton("Cancel Order");
		cancelButton.setBounds(30, 520, 150, 30);
		cancelButton.setFont(f3);
		cancelButton.setBackground( DARK_BLUE);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(e -> cancelSelectedRow());
		add(cancelButton);

        //add(buttonPanel);
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
            
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity");
                return;
            }
            
            if (quantity > availableQuantity) {
                JOptionPane.showMessageDialog(this, "Not enough quantity available");
                return;
            }

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
            productTable.setValueAt(availableQuantity - quantity, selectedRow, 3);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity");
        }
    }
	
	public void firstTImeBilling(){
		new Billing(DBManager, selectedProductsModel, totalAmount);
		setVisible(false);
	}
	
	public void backToBilling(){
		new Billing(DBManager,customerName, customerNum, selectedProductsModel, totalAmount);
		setVisible(false);
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
        new CustomerView(DBManager);
    }

    private void loadShopData() {
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
        productTableModel.addTableModelListener(quantityFocus);
    }
	/*
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
    }*/
	
	public void quantityFocus(){
		quantityField.requestFocus();
	}
	
	
	DefaultTableModel tableModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
		//all cells false
		return false;
		}
	};
	
	private void cancelSelectedRow() {
		int selectedRow = selectedProductsTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a product to cancel");
			return;
		}

		// Get the product details from the selected row
		String productId = selectedProductsTable.getValueAt(selectedRow, 0).toString();
		int quantity = Integer.parseInt(selectedProductsTable.getValueAt(selectedRow, 2).toString());
		double total = Double.parseDouble(selectedProductsTable.getValueAt(selectedRow, 4).toString());

		// Update the total amount
		totalAmount -= total;

		// Find the product in the product table and update its quantity
		for (int i = 0; i < productTable.getRowCount(); i++) {
			if (productTable.getValueAt(i, 0).toString().equals(productId)) {
				int currentQuantity = Integer.parseInt(productTable.getValueAt(i, 3).toString());
				productTable.setValueAt(currentQuantity + quantity, i, 3);
				break;
			}
		}

		// Remove the row from the selected products table
		selectedProductsModel.removeRow(selectedRow);
	}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DBManager dbManager = new DBManager();
                new Buying(dbManager).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error initializing the application: " + e.getMessage());
            }
        });
    }
}
