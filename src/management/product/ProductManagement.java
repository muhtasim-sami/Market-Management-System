package management.product;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import frame.*;

public class ProductManagement extends JFrame implements ActionListener {
    
    JButton view, add, update, remove, logout;

    public ProductManagement() {
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        
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
        
        add = new JButton("Add Product");
        add.setBounds((int) (((1.5 * getWidth()) - 193) / 2), 200, 193, 50);
        add.setFont(f1);
        add.setCursor(crsr);
        add.setBackground(DARK_GREEN);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);
        
        view = new JButton("View Products");
        view.setBounds((int) (((1.5 * getWidth()) - 193) / 2), 280, 193, 50);
        view.setFont(f1);
        view.setCursor(crsr);
        view.setBackground(DARK_GREEN);
        view.setForeground(Color.WHITE);
        view.addActionListener(this);
        add(view);
        
        update = new JButton("Update Products");
        update.setBounds((int) (((1.5 * getWidth()) - 193) / 2), 360, 193, 50);
        update.setFont(f1);
        update.setCursor(crsr);
        update.setBackground(DARK_GREEN);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        remove = new JButton("Remove Products");
        remove.setBounds((int) (((1.5 * getWidth()) - 193) / 2), 440, 193, 50);
        remove.setFont(f1);
        remove.setCursor(crsr);
        remove.setBackground(DARK_GREEN);
        remove.setForeground(Color.WHITE);
        remove.addActionListener(this);
        add(remove);
        
        logout = new JButton("Logout");
        logout.setBounds((int) (((1.5 * getWidth()) - 193) / 2), 520, 193, 50);
        logout.setFont(f1);
        logout.setCursor(crsr);
        logout.setBackground(DARK_GREEN);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddProduct("");
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewProduct();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewProduct();
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveProduct();
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new Login();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ProductManagement();
    }
}