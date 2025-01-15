
package management.employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ViewProduct extends JFrame implements ActionListener{
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
          public void run(){
              new ViewProduct();
          }
      });
    }

    JTable table;
    Choice cproductId;
    JButton search, print, update, back;

    ViewProduct(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20, 20, 250, 20);
        add(searchlbl);
        
        cproductId = new Choice();
        cproductId.setBounds(300, 20, 150, 20);
        add(cproductId);
        
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
        
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == search) {
            String query = "select * from product where proId = '"+cproductId.getSelectedItem()+"'";
            
        } else if (ae.getSource() == print) {
            
        } else if (ae.getSource() == update) {
            setVisible(false);
            // new UpdateEmployee(cproductId.getSelectedItem());
            //new updateProduct(cproductId.getSelectedItem());
        } else {
            setVisible(false);
            //new Home();
        }
    }
}
