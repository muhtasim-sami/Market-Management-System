
package management.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import management.validation.DBManager;

public class ViewProduct extends JFrame implements ActionListener {

    JTable table;
    Choice cproductId;
    JButton search, print, update, back;
    DBManager DBManager;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewProduct();
            }
        });
    }

    public ViewProduct() {
        DBManager = new DBManager();
        setBounds(200, 15, 800, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel searchlbl = new JLabel("Search by Product Id");
        searchlbl.setBounds(20, 20, 250, 20);
        add(searchlbl);

        cproductId = new Choice();
        cproductId.setBounds(300, 20, 150, 20);
        add(cproductId);

        // Populate the choice with product IDs
        List<String> productData = DBManager.readProductData();
        for (String product : productData) {
            String[] details = product.split(",");
            cproductId.add(details[0]);
        }

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20, 70, 120, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(160, 70, 120, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(300, 70, 120, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(440, 70, 120, 20);
        back.addActionListener(this);
        add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String selectedProId = cproductId.getSelectedItem();
            List<String> productData = DBManager.readProductData();
            String[] columnNames = {"ProductID", "ProductName", "Type", "Quantity", "Price", "CompanyName"};
            String[][] data = productData.stream()
                    .filter(product -> product.split(",")[0].equals(selectedProId))
                    .map(product -> product.split(","))
                    .toArray(String[][]::new);
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateProduct(cproductId.getSelectedItem());
        } else if (ae.getSource() == back) {
            setVisible(false);
            new ProductManagement();
        }
    }
}


