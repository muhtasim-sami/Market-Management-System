
package management.product;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import management.validation.DBConnection;

public class UpdateProduct extends JFrame implements ActionListener {

    String productId;
    JTextField tfproid, tfproname, tfquantity, tfprice, tfcname;
    JComboBox<String> type;
    JButton add, back;
    DBConnection dbConnection;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdateProduct("");
            }
        });
    }

    public UpdateProduct(String productId) {
        dbConnection = new DBConnection();
        setBounds(200, 15, 800, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.productId = productId;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Product Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel lproid = new JLabel("Product Id");
        lproid.setBounds(50, 150, 150, 30);
        lproid.setFont(new Font("serif", Font.PLAIN, 20));
        add(lproid);

        JLabel tfproid = new JLabel();
        tfproid.setBounds(200, 150, 150, 30);
		tfproid.setFont(new Font("serif", Font.PLAIN, 20));
        add(tfproid);

        JLabel lproname = new JLabel("Product's Name");
        lproname.setBounds(400, 150, 150, 30);
        lproname.setFont(new Font("serif", Font.PLAIN, 20));
        add(lproname);

        tfproname = new JTextField();
        tfproname.setBounds(600, 150, 150, 30);
        add(tfproname);

        JLabel labeltype = new JLabel("Type of Product");
        labeltype.setBounds(50, 200, 150, 30);
        labeltype.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeltype);

        String[] array = {"Personal Care", "Dairy", "Snacks", "Beverage"};
        type = new JComboBox<>(array);
        type.setBounds(200, 200, 150, 30);
        add(type);

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

        // Load product details if productId is provided
        if (!productId.isEmpty()) {
            List<String> productData = dbConnection.readProductData();
            for (String product : productData) {
                String[] details = product.split(",");
                if (details[0].equals(productId)) {
                    tfproid.setText(details[0]);
                    tfproname.setText(details[1]);
                    type.setSelectedItem(details[2]);
                    tfquantity.setText(details[3]);
                    tfprice.setText(details[4]);
                    tfcname.setText(details[5]);
                    break;
                }
            }
        }

        add = new JButton("Update Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            //String proid = tfproid.getText();
            String proname = tfproname.getText();
            String ptype = (String) type.getSelectedItem();
            String quantity = tfquantity.getText();
            String price = tfprice.getText();
            String cname = tfcname.getText();

            String updatedProductDetails = proname + ", " + ptype + ", " + quantity + ", " + price + ", " + cname;
            
            dbConnection.updateProductData(productId,updatedProductDetails);

            JOptionPane.showMessageDialog(null, "Details updated successfully");
            setVisible(false);
            new ProductManagement();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new ProductManagement();
        }
    }
}