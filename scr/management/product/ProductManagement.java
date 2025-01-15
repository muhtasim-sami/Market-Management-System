package management.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ProductManagement extends JFrame implements ActionListener{
    
    JButton view, add, update, remove;

    
    public ProductManagement(){
		
        setVisible(true);
        setBounds(200,15,800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/ProductManagement.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);
        
        JLabel heading = new JLabel("Product Management ");
        heading.setBounds(620, 20, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 28));
        add(heading);
        
        add = new JButton("Add Product");
        add.setBounds(650, 80, 150, 40);
        add.addActionListener(this);
        add(add);
        
        view = new JButton("View Products");
        view.setBounds(820, 80, 150, 40);
        view.addActionListener(this);
        add(view);
        
        update = new JButton("Update Products");
        update.setBounds(650, 140, 150, 40);
        update.addActionListener(this);
        add(update);
        
        remove = new JButton("Remove Products");
        remove.setBounds(820, 140, 150, 40);
        remove.addActionListener(this);
        add(remove);
        
        JButton bt = new JButton("Logout");
        bt.setBounds(730,200,150,40);
        bt.addActionListener(this);
        add(bt);
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            //new addProduct();
        } else if (ae.getSource() == view) {
            setVisible(false);
            //new viewproduct();
        } else if (ae.getSource() == update) {
            setVisible(false);
            //new viewproduct();
        } else if(ae.getSource()==remove){
            setVisible(false);
            //new removeProduct();
        } else{
            System.exit(0);
        }
    }
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new ProductManagement();
            }
        });
    }


}