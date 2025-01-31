/*
package frame;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Billing extends JFrame implements ActionListener {
    Choice cproductId, cproductName;
    JTextField jtq2, jtq, jtcustomerName, jtcustomerNum;
    int total = 0;
    JTable jt;
    JButton bt1, bt2, bill, refresh, saveas;
    DefaultTableModel model;
    JTextArea area;
    boolean flag = false;
    int count = 2;
    final JFileChooser fc = new JFileChooser();
	
	private JList<String> shopList;
    private JTable productTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private JButton billing, back;

    public Billing() {
        // Fonts
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 = new Font("Arial", Font.BOLD, 13);
        
        // Colors
        Color LIGHT_GREEN = new Color(102, 255, 102);
        Color DARK_GREEN = new Color(0, 153, 0);
        Color GRAY = new Color(128, 128, 128);
        
        // Border
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);

        setLayout(null);
        setTitle("Billing System");
        setBounds(200, 15, 1100, 1000);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        JLabel titleLabel = new JLabel("Billing System");
        titleLabel.setBounds(450, 20, 200, 40);
        titleLabel.setFont(f2);
        titleLabel.setForeground(DARK_GREEN);
        add(titleLabel);

        // Customer Details Section
        JLabel customerName = new JLabel("Customer's Name");
        customerName.setBounds(20, 80, 150, 20);
        customerName.setFont(f3);
        customerName.setForeground(GRAY);
        add(customerName);

        jtcustomerName = new JTextField();
        jtcustomerName.setBounds(170, 80, 150, 20);
        jtcustomerName.setFont(f3);
        jtcustomerName.setBorder(lineBorder);
        add(jtcustomerName);

        JLabel customerNum = new JLabel("Contact Number");
        customerNum.setBounds(340, 80, 150, 20);
        customerNum.setFont(f3);
        customerNum.setForeground(GRAY);
        add(customerNum);

        jtcustomerNum = new JTextField();
        jtcustomerNum.setBounds(500, 80, 150, 20);
        jtcustomerNum.setFont(f3);
        jtcustomerNum.setBorder(lineBorder);
        add(jtcustomerNum);

        // Product Selection Section
        JLabel title1 = new JLabel("Product Details:");
        title1.setBounds(20, 120, 200, 30);
        title1.setFont(f1);
        title1.setForeground(DARK_GREEN);
        add(title1);

        // Table Setup
        String[] columns = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
        model = new DefaultTableModel(columns, 0);
        jt = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        
        // Apply table styling
        jt.setFont(f3);
        jt.getTableHeader().setFont(f3);
        jt.setRowHeight(25);
        jt.setGridColor(Color.LIGHT_GRAY);
        jt.getTableHeader().setBackground(DARK_GREEN);
        jt.getTableHeader().setForeground(Color.WHITE);
        jt.setSelectionBackground(LIGHT_GREEN);
        jt.setSelectionForeground(Color.BLACK);
        
        // Add table scrollpane with styling
        JScrollPane jsp = new JScrollPane(jt);
        jsp.setBounds(20, 200, 600, 500);
        jsp.setBorder(lineBorder);
        add(jsp);

        // Bill Text Area
        area = new JTextArea();
        area.setFont(f3);
        area.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane jspta = new JScrollPane(area);
        jspta.setBounds(700, 200, 350, 500);
        jspta.setBorder(lineBorder);
        add(jspta);

        // Buttons
        bt1 = createStyledButton("Add Item", 700, 80, 100, 30, f3, DARK_GREEN);
        bt2 = createStyledButton("Add by Name", 700, 120, 100, 30, f3, DARK_GREEN);
        refresh = createStyledButton("Refresh", 810, 80, 100, 30, f3, DARK_GREEN);
        saveas = createStyledButton("Save As", 920, 80, 100, 30, f3, DARK_GREEN);
        bill = createStyledButton("Generate Bill", 810, 120, 210, 30, f3, DARK_GREEN);

        // Rest of your existing code for choice components and action listeners
        // ...

        setVisible(true);
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height, Font font, Color bgColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        add(button);
        return button;
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

    // Your existing actionPerformed method and other methods...
    public void actionPerformed(ActionEvent ae) {
        // Keep your existing action handling code
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Billing());
    }
}
*/
/*
package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import management.validation.DBManager;
import management.shop.*;

public class Billing extends JFrame implements ActionListener {
    private Choice cproductId, cproductName;
    private JTextField jtq2, jtq, jtcustomerName, jtcustomerNum;
    private JTable jt;
    private JButton bt1, bt2, bill, refresh, saveas;
    private JTextArea area;
    private DefaultTableModel model;
    private final JFileChooser fc;
    private JList<String> shopList;
    private JTable productTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private Shop currentShop;
    private int total;
    private boolean flag;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Billing());
    }

    public Billing(Shop currentShop) {
        this.DBManager = DBManager;
        this.currentShop = currentShop;
        this.shops = new ArrayList<>();
        this.total = 0;
        this.flag = false;
        this.fc = new JFileChooser();
        
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
        
        // Cursor and Border
        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);
        
        // Frame setup
        setTitle("Billing System");
        setBounds(200, 15, 1100, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Billing System");
        titleLabel.setBounds(450, 20, 200, 50);
        titleLabel.setFont(f2);
        titleLabel.setForeground(DARK_GREEN);
        add(titleLabel);
		
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

        // Customer Information Panel
        setupCustomerPanel(f1, f3, DARK_GREEN);
        
        // Product Details Panel
        setupProductPanel(f1, f3, DARK_GREEN, lineBorder);
        
        // Buttons Panel
        setupButtonsPanel(f1, crsr, DARK_GREEN);
        
        // Bill Area
        setupBillArea(f3, lineBorder);
    }

    private void setupCustomerPanel(Font f1, Font f3, Color DARK_GREEN) {
        JPanel customerPanel = new JPanel(null);
        customerPanel.setBounds(20, 80, 1040, 60);
        customerPanel.setBackground(Color.WHITE);
        
        JLabel customerName = new JLabel("Customer's Name");
        customerName.setBounds(10, 20, 150, 20);
        customerName.setFont(f3);
        customerName.setForeground(DARK_GREEN);
        customerPanel.add(customerName);

        jtcustomerName = new JTextField();
        jtcustomerName.setBounds(160, 20, 150, 20);
        jtcustomerName.setFont(f3);
        customerPanel.add(jtcustomerName);

        JLabel customerNum = new JLabel("Contact Number");
        customerNum.setBounds(330, 20, 150, 20);
        customerNum.setFont(f3);
        customerNum.setForeground(DARK_GREEN);
        customerPanel.add(customerNum);

        jtcustomerNum = new JTextField();
        jtcustomerNum.setBounds(490, 20, 150, 20);
        jtcustomerNum.setFont(f3);
        customerPanel.add(jtcustomerNum);

        add(customerPanel);
    }

    private void setupProductPanel(Font f1, Font f3, Color DARK_GREEN, LineBorder lineBorder) {
        JPanel productPanel = new JPanel(null);
        productPanel.setBounds(20, 150, 1040, 300);
        productPanel.setBackground(Color.WHITE);

        JLabel title1 = new JLabel("Product Details");
        title1.setBounds(10, 10, 200, 30);
        title1.setFont(f1);
        title1.setForeground(DARK_GREEN);
        productPanel.add(title1);

        // Product Selection Components
        setupProductSelectionComponents(productPanel, f3, DARK_GREEN);

        // Product Table
        setupProductTableComponent(productPanel, f3, lineBorder);

        add(productPanel);
    }

    private void setupProductSelectionComponents(JPanel panel, Font f3, Color DARK_GREEN) {
        JLabel lproid = new JLabel("Select Product by ID");
        lproid.setBounds(10, 50, 250, 30);
        lproid.setFont(f3);
        lproid.setForeground(DARK_GREEN);
        panel.add(lproid);

        cproductId = new Choice();
        cproductId.setBounds(260, 50, 150, 30);
        panel.add(cproductId);

        jtq = new JTextField();
        jtq.setBounds(420, 50, 100, 30);
        panel.add(jtq);

        bt1 = new JButton("Add");
        bt1.setBounds(530, 50, 100, 30);
        bt1.setFont(f3);
        bt1.setBackground(DARK_GREEN);
        bt1.setForeground(Color.WHITE);
        bt1.addActionListener(this);
        panel.add(bt1);

        loadProductData();
    }

    private void setupProductTableComponent(JPanel panel, Font f3, LineBorder lineBorder) {
        String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
        model = new DefaultTableModel(columnNames, 0);
        jt = new JTable(model);
        jt.setFont(f3);
        jt.getTableHeader().setFont(f3);
        jt.setBorder(lineBorder);

        JScrollPane tableScroll = new JScrollPane(jt);
        tableScroll.setBounds(10, 100, 1020, 190);
        panel.add(tableScroll);
    }

    private void setupButtonsPanel(Font f1, Cursor crsr, Color DARK_GREEN) {
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(20, 460, 1040, 50);
        buttonPanel.setBackground(Color.WHITE);

        bill = new JButton("Generate Bill");
        bill.setBounds(10, 10, 150, 30);
        bill.setFont(f1);
        bill.setCursor(crsr);
        bill.setBackground(DARK_GREEN);
        bill.setForeground(Color.WHITE);
        bill.addActionListener(this);
        buttonPanel.add(bill);

        refresh = new JButton("Refresh");
        refresh.setBounds(170, 10, 150, 30);
        refresh.setFont(f1);
        refresh.setCursor(crsr);
        refresh.setBackground(DARK_GREEN);
        refresh.setForeground(Color.WHITE);
        refresh.addActionListener(this);
        buttonPanel.add(refresh);

        saveas = new JButton("Save Bill");
        saveas.setBounds(330, 10, 150, 30);
        saveas.setFont(f1);
        saveas.setCursor(crsr);
        saveas.setBackground(DARK_GREEN);
        saveas.setForeground(Color.WHITE);
        saveas.addActionListener(this);
        buttonPanel.add(saveas);

        add(buttonPanel);
    }

    private void setupBillArea(Font f3, LineBorder lineBorder) {
        area = new JTextArea();
        area.setFont(f3);
        area.setBorder(lineBorder);
        
        JScrollPane billScroll = new JScrollPane(area);
        billScroll.setBounds(20, 520, 1040, 400);
        add(billScroll);
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

    // Rest of the methods remain the same as in your previous code
    // Include loadData(), updateProductTable(), actionPerformed(), etc.
	/*
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
    private void loadProductData() {
        List<String> productData = DBManager.readProductData();
        for (String product : productData) {
            String[] details = product.split(",");
            cproductId.add(details[0]);
            cproductName.add(details[1]);
        }
    }
    
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == calculate) {
            try {/*
                int quantity = Integer.parseInt(tfQuantity.getText());
                double price = Double.parseDouble(productData[4]); // Assuming index 4 is price
                int availableQuantity = Integer.parseInt(productData[3]); // Assuming index 3 is quantity
                
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid quantity");
                    return;
                }
                
                if (quantity > availableQuantity) {
                    JOptionPane.showMessageDialog(null, "Not enough stock available. Maximum available: " + availableQuantity);
                    return;
                }
                
                totalAmount = quantity * price;
                //totalAmountLabel.setText(String.format("Total Amount: $%.2f" + totalAmount));
                totalAmountLabel.setText("Total Amount: $ " +  totalAmount);
                confirm.setEnabled(true);
               
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity");
            }
        }
        else if (ae.getSource() == confirm) {
            // Here you would add the logic to:
            // 1. Update the product quantity in the database
            // 2. Generate and save the bill
            // 3. Show success message
            JOptionPane.showMessageDialog(null, "Purchase successful! Total amount paid: $" + String.format("%.2f", totalAmount));
            setVisible(false);
            new CustomerView(dbManager);
        }
        else if (ae.getSource() == back) {
            setVisible(false);
            new CustomerView(dbManager);
        }    
	}

    // ... (rest of the methods remain unchanged)
}


/*
package frame;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import management.validation.*;
import management.shop.*;

public class Billing extends JFrame implements ActionListener {
    
    private DBManager dbManager;
    private Shop shop;
    private String[] productData;
    
    private JTextField tfQuantity;
    private JLabel totalAmountLabel;
    private JButton calculate, confirm, back;
    private double totalAmount = 0.0;
    
    public Billing(DBManager dbManager, Shop shop, String[] productData) {
        this.dbManager = dbManager;
        this.shop = shop;
        this.productData = productData;
        
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 = new Font("Arial", Font.BOLD, 13);
        
        Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
        Color LIGHT_GREEN = new Color(102, 255, 102);
        Color DARK_BLUE = new Color(0, 0, 204);
        Color DARK_GREEN = new Color(0, 153, 0);        
        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);
        
        setVisible(true);
        setBounds(200, 15, 800, 700);
        
        double w = getWidth();
        int h = (int) getHeight();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel titleLabel = new JLabel("Billing");
        titleLabel.setBounds((int) (((1.5 * w) - 150) / 2), (h - 680), 150, 100);
        titleLabel.setFont(f2);
        titleLabel.setForeground(DARK_GREEN);
        add(titleLabel);
        
        // Product Details Labels
        JLabel shopLabel = new JLabel(	  "Shop Name:     	" + shop.name);
        shopLabel.setBounds((int) (((1.5 * w) - 470) / 2), ((h - 500) / 2), 260, 30);
        shopLabel.setFont(f1);
        shopLabel.setForeground(Color.BLACK);
        add(shopLabel);
        
        JLabel productLabel = new JLabel( "Product:       	" + productData[1]); // Assuming index 1 is product name
        productLabel.setBounds((int) (((1.5 * w) - 470) / 2), ((h - 400) / 2), 260, 30);
        productLabel.setFont(f1);
        productLabel.setForeground(Color.BLACK);
        add(productLabel);
        
        JLabel priceLabel = new JLabel(   "Price:         	$" + productData[4]); // Assuming index 4 is price
        priceLabel.setBounds((int) (((1.5 * w) - 470) / 2), ((h - 300) / 2), 260, 30);
        priceLabel.setFont(f1);
        priceLabel.setForeground(Color.BLACK);
        add(priceLabel);
        
        JLabel quantityLabel = new JLabel("Enter Quantity: 	");
        quantityLabel.setBounds((int) (((1.5 * w) - 470) / 2), ((h - 200) / 2), 260, 30);
        quantityLabel.setFont(f1);
        quantityLabel.setForeground(Color.BLACK);
        add(quantityLabel);
        
        tfQuantity = new JTextField();
        tfQuantity.setBorder(lineBorder);
        tfQuantity.setBounds((int) (((1.5 * w) - 210) / 2), ((h - 200) / 2), 250, 40);
        tfQuantity.setFont(f1);
        tfQuantity.setForeground(Color.BLACK);        
        add(tfQuantity);
        
        totalAmountLabel = new JLabel("Total Amount: $0.00");
        totalAmountLabel.setBounds((int) (((1.5 * w) - 470) / 2), ((h - 80) / 2), 260, 30);
        totalAmountLabel.setFont(f1);
        totalAmountLabel.setForeground(DARK_BLUE);
        add(totalAmountLabel);
        
        calculate = new JButton("Calculate");
        calculate.setBounds((int) (((1.5 * w) - 193) / 2), 420, 193, 50);
        calculate.setFont(f1);
        calculate.setCursor(crsr);        
        calculate.setBackground(DARK_GREEN);
        calculate.setForeground(Color.WHITE);
        calculate.addActionListener(this);
        add(calculate);
        
        confirm = new JButton("Confirm");
        confirm.setBounds((int) (((1.5 * w) - 193) / 2), 490, 193, 50);
        confirm.setFont(f1);
        confirm.setCursor(crsr);        
        confirm.setBackground(DARK_GREEN);
        confirm.setForeground(Color.WHITE);
        confirm.addActionListener(this);
        confirm.setEnabled(false); // Initially disabled until calculation is done
        add(confirm);
        
        back = new JButton("Back");
        back.setBounds((int) (((1.5 * w) - 193) / 2), 560, 193, 50);
        back.setFont(f1);
        back.setCursor(crsr);        
        back.setBackground(DARK_GREEN);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == calculate) {
            //try {
                int quantity = Integer.parseInt(tfQuantity.getText());
                double price = Double.parseDouble(productData[4]); // Assuming index 4 is price
                int availableQuantity = Integer.parseInt(productData[3]); // Assuming index 3 is quantity
                
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid quantity");
                    return;
                }
                
                if (quantity > availableQuantity) {
                    JOptionPane.showMessageDialog(null, "Not enough stock available. Maximum available: " + availableQuantity);
                    return;
                }
                
                totalAmount = quantity * price;
                //totalAmountLabel.setText(String.format("Total Amount: $%.2f" + totalAmount));
                totalAmountLabel.setText("Total Amount: $ " +  totalAmount);
                confirm.setEnabled(true);
                
           /* } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity");
            }
        }
        else if (ae.getSource() == confirm) {
            // Here you would add the logic to:
            // 1. Update the product quantity in the database
            // 2. Generate and save the bill
            // 3. Show success message
            JOptionPane.showMessageDialog(null, "Purchase successful! Total amount paid: $" + String.format("%.2f", totalAmount));
            setVisible(false);
            new CustomerView(dbManager);
        }
        else if (ae.getSource() == back) {
            setVisible(false);
            new CustomerView(dbManager);
        }
    }
}
*/

/*
package frame;

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

public class Billing extends JFrame {
    private JList<String> shopList;
    private JTable productTable, selectedProductsTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel, selectedProductsModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private JTextField jtcustomerName, jtcustomerNum, quantityField;
    private JTextArea billArea;
    private JButton addToCart, generateBill, saveButton, backButton;
    private double totalAmount = 0;

    public Billing(DBManager DBManager) {
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
        
        // Cursor and Border
        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);
        
        // Frame setup
        setTitle("Billing System");
        setBounds(200, 15, 1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
		setVisible(true);

        // Title
        JLabel titleLabel = new JLabel("Billing System");
        titleLabel.setBounds(450, 20, 200, 50);
        titleLabel.setFont(f2);
        titleLabel.setForeground(DARK_GREEN);
        add(titleLabel);

        // Customer Information Panel
        setupCustomerPanel(f1, f3, DARK_GREEN);
        
        // Shop and Product Selection Panel
        setupShopAndProductPanel(f1, f3, DARK_GREEN, lineBorder);
        
        // Selected Products Panel
        setupSelectedProductsPanel(f1, f3, DARK_GREEN, lineBorder);
        
        // Bill Panel
        setupBillPanel(f1, f3, DARK_GREEN, lineBorder, crsr);
    }

    private void setupCustomerPanel(Font f1, Font f3, Color DARK_GREEN) {
        JPanel customerPanel = new JPanel(null);
        customerPanel.setBounds(20, 80, 1040, 60);
        customerPanel.setBackground(Color.WHITE);
        
        JLabel customerName = new JLabel("Customer's Name:");
        customerName.setBounds(10, 20, 150, 20);
        customerName.setFont(f3);
        customerName.setForeground(DARK_GREEN);
        customerPanel.add(customerName);

        jtcustomerName = new JTextField();
        jtcustomerName.setBounds(160, 20, 150, 20);
        jtcustomerName.setFont(f3);
        customerPanel.add(jtcustomerName);

        JLabel customerNum = new JLabel("Contact Number:");
        customerNum.setBounds(330, 20, 150, 20);
        customerNum.setFont(f3);
        customerNum.setForeground(DARK_GREEN);
        customerPanel.add(customerNum);

        jtcustomerNum = new JTextField();
        jtcustomerNum.setBounds(490, 20, 150, 20);
        jtcustomerNum.setFont(f3);
        customerPanel.add(jtcustomerNum);

        add(customerPanel);
    }

    private void setupShopAndProductPanel(Font f1, Font f3, Color DARK_GREEN, LineBorder lineBorder) {
        // Shop List
        JLabel shopLabel = new JLabel("Select Shop");
        shopLabel.setBounds(20, 150, 200, 30);
        shopLabel.setFont(f1);
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
        shopScrollPane.setBounds(20, 190, 250, 150);
        add(shopScrollPane);

        // Product Table
        JLabel productLabel = new JLabel("Available Products");
        productLabel.setBounds(290, 150, 200, 30);
        productLabel.setFont(f1);
        productLabel.setForeground(DARK_GREEN);
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
        quantityPanel.setBackground(Color.WHITE);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 10, 80, 30);
        quantityLabel.setFont(f3);
        quantityLabel.setForeground(DARK_GREEN);
        quantityPanel.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(90, 10, 80, 30);
        quantityField.setFont(f3);
        quantityPanel.add(quantityField);

        addToCart = new JButton("Add to Cart");
        addToCart.setBounds(180, 10, 120, 30);
        addToCart.setFont(f3);
        addToCart.setBackground(DARK_GREEN);
        addToCart.setForeground(Color.WHITE);
        addToCart.addActionListener(e -> addToSelectedProducts());
        quantityPanel.add(addToCart);

        add(quantityPanel);

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

    private void setupSelectedProductsPanel(Font f1, Font f3, Color DARK_GREEN, LineBorder lineBorder) {
        JLabel selectedLabel = new JLabel("Selected Products");
        selectedLabel.setBounds(20, 410, 200, 30);
        selectedLabel.setFont(f1);
        selectedLabel.setForeground(DARK_GREEN);
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

    private void setupBillPanel(Font f1, Font f3, Color DARK_GREEN, LineBorder lineBorder, Cursor crsr) {
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
        buttonPanel.setBackground(Color.WHITE);

        generateBill = new JButton("Generate Bill");
        generateBill.setBounds(10, 10, 150, 30);
        generateBill.setFont(f3);
        generateBill.setCursor(crsr);
        generateBill.setBackground(DARK_GREEN);
        generateBill.setForeground(Color.WHITE);
        generateBill.addActionListener(e -> generateBill());
        buttonPanel.add(generateBill);

        saveButton = new JButton("Save Bill");
        saveButton.setBounds(180, 10, 150, 30);
        saveButton.setFont(f3);
        saveButton.setCursor(crsr);
        saveButton.setBackground(DARK_GREEN);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> saveBill());
        buttonPanel.add(saveButton);

        backButton = new JButton("Back");
        backButton.setBounds(350, 10, 150, 30);
        backButton.setFont(f3);
        backButton.setCursor(crsr);
        backButton.setBackground(DARK_GREEN);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> goBack());
        buttonPanel.add(backButton);

        add(buttonPanel);
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

    private void generateBill() {
        if (selectedProductsModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No products selected");
            return;
        }

        if (jtcustomerName.getText().trim().isEmpty() || jtcustomerNum.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter customer details");
            return;
        }

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
        bill.append("\nThank you for shopping with us!\n");
        
        billArea.setText(bill.toString());
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
                new Billing(dbManager).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error initializing the application: " + e.getMessage());
            }
        });
    }
}


import java.awt.print.*;
import java.awt.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Billing extends JFrame implements Printable {
    // Add these new fields to your existing fields
    private JButton printButton;
    private JTable billTable;
    private DefaultTableModel billTableModel;
    private SimpleDateFormat dateFormat;
    
    // In your setupBillPanel method, add this after other buttons
    private void setupBillPanel(Font f1, Font f3, Color DARK_GREEN, LineBorder lineBorder, Cursor crsr) {
        // ... (existing code remains the same)

        printButton = new JButton("Print Bill");
        printButton.setBounds(490, 10, 150, 30);
        printButton.setFont(f3);
        printButton.setCursor(crsr);
        printButton.setBackground(DARK_GREEN);
        printButton.setForeground(Color.WHITE);
        printButton.addActionListener(e -> printBill());
        buttonPanel.add(printButton);

        // Initialize bill table
        String[] billColumns = {"Item", "Details"};
        billTableModel = new DefaultTableModel(billColumns, 0);
        billTable = new JTable(billTableModel);
        billTable.setFont(f3);
        billTable.getTableHeader().setFont(f3);
        billTable.setEnabled(false);
        
        // Initialize date formatter
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    private void generateBill() {
        if (selectedProductsModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No products selected");
            return;
        }

        if (jtcustomerName.getText().trim().isEmpty() || jtcustomerNum.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter customer details");
            return;
        }

        // Clear previous bill data
        billTableModel.setRowCount(0);

        // Add header information
        billTableModel.addRow(new Object[]{"BILLING INVOICE", ""});
        billTableModel.addRow(new Object[]{"Date", dateFormat.format(new Date())});
        billTableModel.addRow(new Object[]{"Customer Name", jtcustomerName.getText()});
        billTableModel.addRow(new Object[]{"Contact Number", jtcustomerNum.getText()});
        billTableModel.addRow(new Object[]{"", ""});  // Empty row for spacing
        billTableModel.addRow(new Object[]{"PRODUCT DETAILS", ""});
        
        // Add column headers for products
        billTableModel.addRow(new Object[]{"Product ID | Name | Quantity | Price | Total", ""});
        
        // Add products
        double total = 0;
        for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
            String productDetails = String.format("%-10s | %-20s | %-8s | %-8.2f | %-8.2f",
                selectedProductsModel.getValueAt(i, 0),
                selectedProductsModel.getValueAt(i, 1),
                selectedProductsModel.getValueAt(i, 2),
                selectedProductsModel.getValueAt(i, 3),
                selectedProductsModel.getValueAt(i, 4));
            billTableModel.addRow(new Object[]{productDetails, ""});
            total += (Double) selectedProductsModel.getValueAt(i, 4);
        }

        // Add total
        billTableModel.addRow(new Object[]{"", ""});  // Empty row for spacing
        billTableModel.addRow(new Object[]{"Total Amount", String.format("%.2f", total)});
        billTableModel.addRow(new Object[]{"", ""});  // Empty row for spacing
        billTableModel.addRow(new Object[]{"Thank you for shopping with us!", ""});

        // Update the bill area with the table
        updateBillArea();
    }

    private void updateBillArea() {
        StringBuilder billText = new StringBuilder();
        for (int i = 0; i < billTableModel.getRowCount(); i++) {
            billText.append(billTableModel.getValueAt(i, 0))
                   .append(" ")
                   .append(billTableModel.getValueAt(i, 1))
                   .append("\n");
        }
        billArea.setText(billText.toString());
    }

    private void printBill() {
        if (billTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please generate bill first");
            return;
        }

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        if (job.printDialog()) {
            try {
                job.print();
                JOptionPane.showMessageDialog(this, "Bill printed successfully!");
            } catch (PrinterException pe) {
                JOptionPane.showMessageDialog(this, 
                    "Error printing: " + pe.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

        // Calculate dimensions
        double pageWidth = pageFormat.getImageableWidth();
        double pageHeight = pageFormat.getImageableHeight();
        int lineHeight = g2d.getFontMetrics().getHeight();
        int y = lineHeight;

        // Draw company header
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        String companyHeader = "Your Company Name";
        int headerWidth = g2d.getFontMetrics().stringWidth(companyHeader);
        g2d.drawString(companyHeader, (int)((pageWidth - headerWidth) / 2), y);
        y += lineHeight * 2;

        // Reset font for bill content
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

        // Draw bill content
        for (int i = 0; i < billTableModel.getRowCount(); i++) {
            String line = billTableModel.getValueAt(i, 0) + " " + billTableModel.getValueAt(i, 1);
            
            // Bold headers
            if (i == 0 || i == 5) {
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
            } else {
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            }

            // Draw the line
            g2d.drawString(line, 50, y);
            y += lineHeight;

            // Add extra spacing after headers
            if (i == 0 || i == 4) {
                y += lineHeight / 2;
            }
        }

        // Draw footer
        y += lineHeight;
        g2d.setFont(new Font("Arial", Font.ITALIC, 10));
        String footer = "This is a computer generated bill";
        g2d.drawString(footer, 50, y);

        return PAGE_EXISTS;
    }
}

package frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
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

public class Billing extends JFrame implements Printable {
    // Add these new fields with existing fields
    private JButton printButton;
    private SimpleDateFormat dateFormat;
    
    // Keep all existing fields...
    private JList<String> shopList;
    private JTable productTable, selectedProductsTable;
    private DefaultListModel<String> shopListModel;
    private DefaultTableModel productTableModel, selectedProductsModel;
    private ArrayList<Shop> shops;
    private DBManager DBManager;
    private JTextField jtcustomerName, jtcustomerNum, quantityField;
    private JTextArea billArea;
    private JButton addToCart, generateBill, saveButton, backButton;
    private double totalAmount = 0;

    // Modify the setupBillPanel method to add print button
    private void setupBillPanel(Font f1, Font f3, Color DARK_GREEN, LineBorder lineBorder, Cursor crsr) {
        // Existing bill area setup remains the same
        billArea = new JTextArea();
        billArea.setFont(f3);
        billArea.setEditable(false);
        JScrollPane billScroll = new JScrollPane(billArea);
        billScroll.setBounds(20, 610, 1040, 180);
        add(billScroll);

        // Buttons
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(500, 350, 720, 50);
        buttonPanel.setBackground(Color.WHITE);

        // Existing buttons
        generateBill = new JButton("Generate Bill");
        generateBill.setBounds(10, 10, 150, 30);
        generateBill.setFont(f3);
        generateBill.setCursor(crsr);
        generateBill.setBackground(DARK_GREEN);
        generateBill.setForeground(Color.WHITE);
        generateBill.addActionListener(e -> generateBill());
        buttonPanel.add(generateBill);

        saveButton = new JButton("Save Bill");
        saveButton.setBounds(180, 10, 150, 30);
        saveButton.setFont(f3);
        saveButton.setCursor(crsr);
        saveButton.setBackground(DARK_GREEN);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> saveBill());
        buttonPanel.add(saveButton);

        // New print button
        printButton = new JButton("Print Bill");
        printButton.setBounds(350, 10, 150, 30);
        printButton.setFont(f3);
        printButton.setCursor(crsr);
        printButton.setBackground(DARK_GREEN);
        printButton.setForeground(Color.WHITE);
        printButton.addActionListener(e -> printBill());
        buttonPanel.add(printButton);

        // Back button moved to the end
        backButton = new JButton("Back");
        backButton.setBounds(520, 10, 150, 30);
        backButton.setFont(f3);
        backButton.setCursor(crsr);
        backButton.setBackground(DARK_GREEN);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> goBack());
        buttonPanel.add(backButton);

        add(buttonPanel);
        
        // Initialize date formatter
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    // Add these new methods for printing functionality
    private void printBill() {
        if (billArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please generate bill first");
            return;
        }

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        if (job.printDialog()) {
            try {
                job.print();
                JOptionPane.showMessageDialog(this, "Bill printed successfully!");
            } catch (PrinterException pe) {
                JOptionPane.showMessageDialog(this, 
                    "Error printing: " + pe.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
/*
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // Set up fonts
        Font titleFont = new Font("Arial", Font.BOLD, 16);
        Font normalFont = new Font("Arial", Font.PLAIN, 12);
        Font headerFont = new Font("Arial", Font.BOLD, 12);

        int y = 20;
        int lineHeight = g2d.getFontMetrics(normalFont).getHeight();

        // Print header
        g2d.setFont(titleFont);
        String header = "BILLING INVOICE";
        int headerWidth = g2d.getFontMetrics().stringWidth(header);
        g2d.drawString(header, (int)((pageFormat.getImageableWidth() - headerWidth) / 2), y);
        y += lineHeight * 2;

        // Print customer details
        g2d.setFont(headerFont);
        y = printText(g2d, "Customer Details:", y, lineHeight);
        g2d.setFont(normalFont);
        y = printText(g2d, "Name: " + jtcustomerName.getText(), y, lineHeight);
        y = printText(g2d, "Contact: " + jtcustomerNum.getText(), y, lineHeight);
        y = printText(g2d, "Date: " + dateFormat.format(new Date()), y, lineHeight);
        y += lineHeight;

        // Print products header
        g2d.setFont(headerFont);
        y = printText(g2d, "Products:", y, lineHeight);
        y += lineHeight/2;

        // Print column headers
        String columnHeader = String.format("%-10s %-20s %-10s %-10s %-10s",
            "ID", "Name", "Qty", "Price", "Total");
        g2d.drawString(columnHeader, 20, y);
        y += lineHeight;

        // Print separator line
        g2d.drawLine(20, y-5, 500, y-5);
        y += lineHeight/2;

        // Print products
        g2d.setFont(normalFont);
        for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
            String line = String.format("%-10s %-20s %-10s %-10.2f %-10.2f",
                selectedProductsModel.getValueAt(i, 0),
                selectedProductsModel.getValueAt(i, 1),
                selectedProductsModel.getValueAt(i, 2),
                selectedProductsModel.getValueAt(i, 3),
                selectedProductsModel.getValueAt(i, 4));
            g2d.drawString(line, 20, y);
            y += lineHeight;
        }

        // Print separator line
        y += lineHeight/2;
        g2d.drawLine(20, y-5, 500, y-5);
        y += lineHeight;

        // Print total
        g2d.setFont(headerFont);
        g2d.drawString(String.format("Total Amount: %.2f", totalAmount), 350, y);
        y += lineHeight * 2;

        // Print footer
        g2d.setFont(new Font("Arial", Font.ITALIC, 10));
        String footer = "Thank you for shopping with us!";
        g2d.drawString(footer, 20, y);

        return PAGE_EXISTS;
    }

    private int printText(Graphics2D g2d, String text, int y, int lineHeight) {
        g2d.drawString(text, 20, y);
        return y + lineHeight;
    }

    // All other existing methods remain the same...
}

*/



