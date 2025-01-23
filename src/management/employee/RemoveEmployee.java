
package management.employee;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener {
    
    Choice cEmpId;
    JButton delete, back;
    
    public RemoveEmployee() {
		setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 400);
        setLocation(300, 150);

        Font f1 = new Font("Arial",Font.BOLD ,18);
		Font f2 = new Font("Times New Roman",Font.BOLD ,25);
		Font f3 = new Font("Arial",Font.BOLD ,13);
		
		Color VERY_LIGHT_BLUE = new Color(51,204,255);
		Color LIGHT_GREEN = new Color(102,255,102);
		Color DARK_BLUE = new Color(0,0,204);
		Color DARK_GREEN = new Color(0,153,0);
		
		Cursor crsr = new Cursor(Cursor.HAND_CURSOR);

		LineBorder lineBorder = new LineBorder(Color.black, 1, true);
		
        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50, 50, 100, 30);
        add(labelempId);
        
        cEmpId = new Choice();
        cEmpId.setBounds(200, 50, 150, 30);
        add(cEmpId);
        
        
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);
        
        JLabel lblname = new JLabel();
        lblname.setBounds(200, 100, 100, 30);
        add(lblname);
        
        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);
        
        JLabel lblphone = new JLabel();
        lblphone.setBounds(200, 150, 100, 30);
        add(lblphone);
        
        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 200, 100, 30);
        add(labelemail);
        
        JLabel lblemail = new JLabel();
        lblemail.setBounds(200, 200, 100, 30);
        add(lblemail);
		
		JTextField tfusername = new JTextField();
		tfusername.setBorder(lineBorder );
		//tfusername.setHorizontalAlignment(JTextField.CENTER);
		tfusername.setBounds(150, 150, 100, 60);
		tfusername.setFont(f1);
		tfusername.setForeground(Color.BLACK);        
		add(tfusername);
        
        JTextField tfpassword = new JPasswordField();
		tfpassword.setBorder(lineBorder );
		//tfpassword.setHorizontalAlignment(JTextField.CENTER);
		tfpassword.setBounds(150, 150, 100, 30);
		tfpassword.setFont(f1);
		tfpassword.setForeground(Color.BLACK);        
		add(tfpassword);
        
        
        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    /*
                    while(rs.next()) {
                        lblname.setText(rs.getString("name"));
                        lblphone.setText(rs.getString("phone"));
                        lblemail.setText(rs.getString("email"));
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);
        
        back = new JButton("Back");
        back.setBounds(220, 300, 100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 600, 400);
        add(image);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                
                new EmployeeManagement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new EmployeeManagement();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
              
                new RemoveEmployee();
            }
        });
    }
}
