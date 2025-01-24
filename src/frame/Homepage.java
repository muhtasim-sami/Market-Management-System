package frame;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import management.validation.*;


public class Homepage extends JFrame implements ActionListener {
    
    JButton login, signup, viewProducts;

    public Homepage() {
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
        
        JLabel l1 = new JLabel("Homepage");
        l1.setBounds((int) (((1.5 * w) - 150) / 2), (h - 650), 150, 100);
        l1.setFont(f2);
        l1.setForeground(DARK_GREEN);
        add(l1);
        
        login = new JButton("Login");
        login.setBounds((int) (((1.5 * w) - 193) / 2), 340, 193, 50);
        login.setFont(f1);
        login.setCursor(crsr);
        login.setBackground(DARK_GREEN);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        signup = new JButton("Signup");
        signup.setBounds((int) (((1.5 * w) - 193) / 2), 420, 193, 50);
        signup.setFont(f1);
        signup.setCursor(crsr);        
        signup.setBackground(DARK_GREEN);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        viewProducts = new JButton("View Products");
        viewProducts.setBounds((int) (((1.5 * w) - 193) / 2), 500, 193, 50);
        viewProducts.setFont(f1);
        viewProducts.setCursor(crsr);        
        viewProducts.setBackground(DARK_GREEN);
        viewProducts.setForeground(Color.WHITE);
        viewProducts.addActionListener(this);
        add(viewProducts);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
			dispose();
            new Login();
        }
        if (ae.getSource() == signup) {
			dispose();
            new Signup();
        }
        if (ae.getSource() == viewProducts) {
            DBConnection dbConnection = new DBConnection();
            CustomerView customerView = new CustomerView(dbConnection);
            customerView.setVisible(true);
            this.dispose(); // Close the homepage window
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage();
            }
        });
    }
}