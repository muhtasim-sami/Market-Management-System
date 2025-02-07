package frame;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import management.validation.*;

public class Billing extends JFrame {
    private JTextArea billArea;
    private JTable selectedProductsTable;
    private DefaultTableModel selectedProductsModel;
	private JTextField jtcustomerName, jtcustomerNum;
    private JButton addMoreButton;
    private double totalAmount;
    private DBManager DBManager;
    private String customerName = null;
    private String customerNum = null;
	private String path = System.getProperty("user.dir");  
	private String background = (path.substring(0, path.length() - 3) + "pic\\Background.jpg");
	private ArrayList<Object[]> selectedProductsList = new ArrayList<>();

	
    public Billing(DBManager DBManager, DefaultTableModel selectedProductsModel, double totalAmount, ArrayList<Object[]> selectedProductsList) {

        this.DBManager = DBManager;
        this.selectedProductsModel = selectedProductsModel;
        this.totalAmount = totalAmount;
        this.selectedProductsList = selectedProductsList;
        //this.billArea = billArea;

        setupUI();

    }

    public Billing(DBManager DBManager,String customerName, String customerNum, DefaultTableModel selectedProductsModel, double totalAmount, ArrayList<Object[]> selectedProductsList) {

        this.DBManager = DBManager;
        this.customerName = customerName;
        this.customerNum = customerNum;
        this.selectedProductsModel = selectedProductsModel;
        this.totalAmount = totalAmount;
        this.selectedProductsList = selectedProductsList;
        //this.billArea = billArea;

        setupUI();
		jtcustomerName.setText(customerName);
		jtcustomerNum.setText(customerNum);
    }

    private void setupUI() {
        // Fonts
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f3 = new Font("Arial", Font.BOLD, 13);

		Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
		LineBorder lineBorder = new LineBorder(Color.black, 1, true);
		
        // Colors
        //Color DARK_GREEN = new Color(0, 153, 0);
        Color DARK_BLUE = new Color(0, 0, 204);

        // Frame setup
        setTitle("Billing System");
        setBounds(200, 15, 1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);
		
		JLabel l = new JLabel(new ImageIcon(background));
		setContentPane(l);

        // Title
        JLabel titleLabel = new JLabel("Billing System");
        titleLabel.setBounds(450, 20, 200, 50);
        titleLabel.setFont(f1);
        titleLabel.setForeground(DARK_BLUE);
        add(titleLabel);

        // Selected Products Panel
        JLabel selectedLabel = new JLabel("Selected Products");
        selectedLabel.setBounds(330, 100, 200, 30);
        selectedLabel.setFont(f1);
        selectedLabel.setForeground(DARK_BLUE);
        add(selectedLabel);

        selectedProductsTable = new JTable(selectedProductsModel);
        selectedProductsTable.setFont(f3);
        selectedProductsTable.getTableHeader().setFont(f3);

        JScrollPane selectedScrollPane = new JScrollPane(selectedProductsTable);
        selectedScrollPane.setBounds(330, 140, 700, 150);
        add(selectedScrollPane);

		billArea = new JTextArea();
        billArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        billArea.setEditable(false);

        // Bill Area
        JScrollPane billScroll = new JScrollPane(billArea);
        billScroll.setBounds(330, 310, 700, 180);
        add(billScroll);

        // Buttons
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(20, 200, 300, 150);
        buttonPanel.setBackground(Color.WHITE);

        addMoreButton = new JButton("Add More");
        addMoreButton.setBounds(30, 250, 150, 30);
        addMoreButton.setFont(f3);
        addMoreButton.setBackground(DARK_BLUE);
        addMoreButton.setForeground(Color.WHITE);
        addMoreButton.addActionListener(e -> addMoreProducts());
        add(addMoreButton);

        JButton saveButton = new JButton("Save Bill");
        saveButton.setBounds(30, 290, 150, 30);
        saveButton.setFont(f3);
        saveButton.setBackground(DARK_BLUE);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> saveBill());
        add(saveButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(30, 330, 150, 30);
        backButton.setFont(f3);
        backButton.setBackground(DARK_BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> addMoreProducts());
        add(backButton);
		
		JButton generateBill = new JButton("Generate Bill");
        generateBill.setBounds(30, 370, 150, 30);
        generateBill.setFont(f3);
        generateBill.setCursor(crsr);
        generateBill.setBackground(DARK_BLUE);
        generateBill.setForeground(Color.WHITE);
        generateBill.addActionListener(e -> generateBill());
        add(generateBill);

        //add(buttonPanel);
		setupCustomerPanel(f1, f3, DARK_BLUE, customerName, customerNum);
		//generateBill();
    }

    private void addMoreProducts() {
		customerDetails();
        this.setVisible(false);
        new Buying(DBManager, customerName, customerNum, selectedProductsModel, selectedProductsList);
    }

    private void saveBill() {
        if (billArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No bill to save");
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
	private void generateBill() {
		if (selectedProductsModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "No products selected");
			return;
		}
		customerDetails();
		
		if (customerName.isEmpty() || customerNum.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter customer details");
			return;
		}
		
		int n = JOptionPane.showConfirmDialog (null, "Do you want buy more products ?" ,
			 "Info" , JOptionPane.YES_NO_CANCEL_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			addMoreProducts();
		}
		else if ((n == JOptionPane.NO_OPTION) || (n == JOptionPane.CANCEL_OPTION))  {
			Object[] possibleValues = { "Pay Directly", "Cash On Delivery", "Cancel Order" };

			Object selectedValue = JOptionPane.showInputDialog(null,
				"How do you like to Pay ?", "Input",
				JOptionPane.INFORMATION_MESSAGE, null,
				possibleValues, possibleValues[0]
			);
			if (selectedValue == "Pay Directly"){
				bill();
				confermBill();
			}
			else if (selectedValue == "Cash On Delivery"){
				bill();
				//confermBill();
			}
		}

		

	}
	
	private void setupCustomerPanel(Font f1, Font f3, Color DARK_BLUE, String name, String number) {
        
        JLabel customerName = new JLabel("Customer's Name:");
        customerName.setBounds(30, 100, 120, 20);
        customerName.setFont(f3);
        customerName.setForeground(DARK_BLUE);
        add(customerName);

        jtcustomerName = new JTextField(name);
        jtcustomerName.setBounds(30, 130, 150, 30);
        jtcustomerName.setFont(f3);
        jtcustomerName.setText(name);
        add(jtcustomerName);

        JLabel customerNum = new JLabel("Contact Number:");
        customerNum.setBounds(30, 170, 120, 20);
        customerNum.setFont(f3);
        customerNum.setForeground(DARK_BLUE);
        add(customerNum);

        jtcustomerNum = new JTextField(number);
        jtcustomerNum.setBounds(30, 200, 150, 30);
        jtcustomerNum.setFont(f3);
        jtcustomerNum.setText(number);
        add(jtcustomerNum);

    }
	public void confermBill(){
		for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
			String productId = selectedProductsModel.getValueAt(i, 0).toString();
			int quantity = Integer.parseInt(selectedProductsModel.getValueAt(i, 2).toString());
			
			// Update the quantity in database
			DBManager.decreasedProductQuantity(productId, quantity);
		}
	}
	public void paymentOption(){
		
	}
	
	public void customerDetails(){
		customerName = jtcustomerName.getText();
		customerNum = jtcustomerNum.getText();
	}
	
	public void bill(){
		try {

			// Now generate the bill as before
			StringBuilder bill = new StringBuilder();
			bill.append("************************************************************\n");
			bill.append("************************* Billing INVOICE *******************\n");
			bill.append("************************************************************\n\n");
			bill.append(" Customer Name: ").append(jtcustomerName.getText()).append("\n");
			bill.append(" Contact Number: ").append(jtcustomerNum.getText()).append("\n");
			bill.append(" Date: ").append(java.time.LocalDate.now()).append("\n\n");
			bill.append(" Products:\n");
			bill.append("---------------------------------------------------------------\n");
			bill.append(String.format(" %-20s %-15s %-10s %-10s %-10s\n", 
				"ID", "Name", "Quantity", "Price", "Total"));
			bill.append("---------------------------------------------------------------\n");
			
			String[] productName = new String[selectedProductsModel.getRowCount()];
			String maxName = selectedProductsModel.getValueAt(0, 1).toString();

			for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
				productName[i] = selectedProductsModel.getValueAt(i, 1).toString();
				if (maxName.length() < productName[i].length()) {
					maxName = productName[i];
				}
			}
			
			for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
				for (int j = maxName.length(); j < productName[i].length(); j++){
					productName[i] += " ";
				}
				productName[i] += "\t";
			}
			
			

			for (int i = 0; i < selectedProductsModel.getRowCount(); i++) {
				bill.append(String.format(" %-10s %-20s %-10s %-10.2f %-10.2f\n",
					selectedProductsModel.getValueAt(i, 0),
					//selectedProductsModel.getValueAt(i, 1),
					productName[i],
					selectedProductsModel.getValueAt(i, 2),
					selectedProductsModel.getValueAt(i, 3),
					selectedProductsModel.getValueAt(i, 4))
				);
			}


			bill.append("---------------------------------------------------------------\n");
			bill.append(String.format(" Total Amount: %.2f\n", totalAmount));
			bill.append("---------------------------------------------------------------\n");
			bill.append("\n Thank you for shopping with us!\n");
			
			billArea.setText(bill.toString());
			
			// Show success message
			//JOptionPane.showMessageDialog(this, "Bill generated and quantities updated successfully!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"Error updating quantities: " + e.getMessage(),
				"Update Error",
				JOptionPane.ERROR_MESSAGE
			);
		}
	}
	

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DBManager dbManager = new DBManager();
                //new Billing(dbManager, "John Doe", "1234567890", new DefaultTableModel(), 0.0, new JTextArea()).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error initializing the application: " + e.getMessage());
            }
        });
    }
}