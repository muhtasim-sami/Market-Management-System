package management.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import management.validation.DBManager;

public class RemoveProduct extends JFrame implements ActionListener {
    private Choice cProId;
    private JTextField tfProName, tfCName, tfQuantity;
    private JButton delete, back;
    private DBManager DBManager;
    private List<String> productData;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RemoveProduct());
    }

    public RemoveProduct() {
        DBManager = new DBManager();
        setBounds(200, 15, 800, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel labelproId = new JLabel("Product Id");
        labelproId.setBounds(50, 50, 100, 30);
        add(labelproId);

        cProId = new Choice();
        cProId.setBounds(200, 50, 150, 30);
        add(cProId);

        productData = DBManager.readProductData();
        for (String product : productData) {
			String[] details = product.split(", ");
			cProId.add(details[0]);
        }

        JLabel labelname = new JLabel("Product Name");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);

        tfProName = new JTextField();
        tfProName.setBounds(200, 100, 150, 30);
        tfProName.setEditable(false);
        add(tfProName);

        JLabel labelcname = new JLabel("Company's Name");
        labelcname.setBounds(50, 150, 100, 30);
        add(labelcname);

        tfCName = new JTextField();
        tfCName.setBounds(200, 150, 150, 30);
        tfCName.setEditable(false);
        add(tfCName);

        JLabel labelquantity = new JLabel("Quantity");
        labelquantity.setBounds(50, 200, 100, 30);
        add(labelquantity);

        tfQuantity = new JTextField();
        tfQuantity.setBounds(200, 200, 150, 30);
        tfQuantity.setEditable(false);
        add(tfQuantity);

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        cProId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                for (String product : productData) {
					String[] details = product.split(", ");
					if (details[0].equals(cProId.getSelectedItem())) {
						tfProName.setText(details[1]);
						tfCName.setText(details[5]);
						tfQuantity.setText(details[3]);
						break;
					}
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            String selectedProId = cProId.getSelectedItem();
            /*
            for (int i = 0; i < productData.size(); i++) {
                if (productData.get(i).startsWith("Product: ")) {
                    String[] details = productData.get(i).substring(9).split(",");
                    if (details[0].equals(selectedProId)) {
                        productData.remove(i);
                        break;
                    }
                }
            }
            */
            DBManager.removeProductData(selectedProId);
            
            JOptionPane.showMessageDialog(null, "Product Deleted Successfully");
            setVisible(false);
            new ProductManagement();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new ProductManagement();
        }
    }
}