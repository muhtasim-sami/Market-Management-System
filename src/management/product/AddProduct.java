package management.product;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import management.validation.DBManager;
import user.*;

public class AddProduct extends JFrame implements ActionListener {

    JTextField type, tfproname, tfquantity, tfprice, tfcname;
    JComboBox<String> shop;
    JButton add1, back;
    DBManager DBManager;
	String user;

    public AddProduct(String user) {
		this.user = user;
        DBManager = new DBManager();
        setBounds(200, 15, 800, 700);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Product Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel lproid = new JLabel("Product Type");
        lproid.setBounds(50, 150, 150, 30);
        lproid.setFont(new Font("serif", Font.PLAIN, 20));
        add(lproid);

        type = new JTextField();
        type.setBounds(200, 150, 150, 30);
        add(type);

        JLabel lproname = new JLabel("Product's Name");
        lproname.setBounds(400, 150, 150, 30);
        lproname.setFont(new Font("serif", Font.PLAIN, 20));
        add(lproname);

        tfproname = new JTextField();
        tfproname.setBounds(600, 150, 150, 30);
        add(tfproname);

        JLabel labeltype = new JLabel("Shop Name");
        labeltype.setBounds(50, 200, 150, 30);
        labeltype.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeltype);

		List<String> lines = DBManager.readShopAndProductData();
		List<String> shopArray = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("Shop: ")) {
                String[] shopInfo = line.substring(6).split(", ", 4);
                String shopName = shopInfo[1].trim();
				shopArray.add(shopName);
            } 
        }

        //String[] array = {"Personal Care", "Dairy", "Snacks", "Beverage"};
		String[] array = shopArray.toArray(new String[shopArray.size()]);
        shop = new JComboBox<>(array);
        shop.setBounds(200, 200, 150, 30);
        add(shop);

        JLabel lquantity = new JLabel("Quantity");
        lquantity.setBounds(400, 200, 150, 30);
        lquantity.setFont(new Font("serif", Font.PLAIN, 20));
        add(lquantity);

        tfquantity = new JTextField();
        tfquantity.setBounds(600, 200, 150, 30);
        add(tfquantity);

        JLabel labelprice = new JLabel("Price");
        labelprice.setBounds(50, 250, 150, 30);
        labelprice.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelprice);

        tfprice = new JTextField();
        tfprice.setBounds(200, 250, 150, 30);
        add(tfprice);

        JLabel labelcname = new JLabel("Company's Name");
        labelcname.setBounds(400, 250, 150, 30);
        labelcname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelcname);

        tfcname = new JTextField();
        tfcname.setBounds(600, 250, 150, 30);
        add(tfcname);

        add1 = new JButton("Add Details");
        add1.setBounds(250, 350, 150, 40);
        add1.addActionListener(this);
        add1.setBackground(Color.BLACK);
        add1.setForeground(Color.WHITE);
        add(add1);

        back = new JButton("Back");
        back.setBounds(450, 350, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add1) {
            String ptype = type.getText();
            String proname = tfproname.getText();
            String pshop = (String) shop.getSelectedItem();
            String quantity = tfquantity.getText();
            String price = tfprice.getText();
            String cname = tfcname.getText();

			List<String> productDetails = Arrays.asList(proname, ptype, quantity, price, cname);

            //String productDetails = proname + "," + ptype + "," + quantity + "," + price + "," + cname;
            //List<String> productData = DBManager.readProductData();
            //productData.add(productDetails);
            DBManager.addProductData(productDetails,pshop);

            JOptionPane.showMessageDialog(null, "Product Added Successfully");
            setVisible(false);
            new ProductManagement();
        } else if (ae.getSource() == back) {
			if (user == "VendorSell"){
				new VendorSell(new DBManager());
			}
			else {
				new ProductManagement();				
			}
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddProduct("");
            }
        });
    }
}