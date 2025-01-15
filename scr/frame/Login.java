package frame;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import management.security.*;


public class Login extends JFrame implements ActionListener{
    
    JTextField tfusername ;
    JPasswordField tfpassword;
    JButton login,signup;
    public Login() {
		
		
		String [] s = {"Market Owner","Shop owner","Coustomer","Vendor"}; 
		
		boolean shopOwner, Coustomer, Vendor;
		
		Font f1 = new Font("Arial",Font.BOLD ,18);
		Font f2 = new Font("Times New Roman",Font.BOLD ,25);
		Font f3 = new Font("Arial",Font.BOLD ,13);
		
		Color VERY_LIGHT_BLUE = new Color(51,204,255);
		Color LIGHT_GREEN = new Color(102,255,102);
		Color DARK_BLUE = new Color(0,0,204);
		Color DARK_GREEN = new Color(0,153,0);
		
		Cursor crsr = new Cursor(Cursor.HAND_CURSOR);

		LineBorder lineBorder = new LineBorder(Color.black, 1, true);
		
        setVisible(true);
        setBounds(200,15,800,700);
		
		double w = getWidth();
		int h = (int)getHeight();
		
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
		JLabel l1 = new JLabel("Login");
		l1.setBounds((int)(((1.5 * w ) - 150 )/2),(h - 650 ),150,100);
		l1.setFont(f2);
		l1.setForeground(DARK_GREEN);
		add(l1);
		
		JLabel l2 = new JLabel("Enter username ");
		l2.setBounds((int)(((1.5 * w ) - 470 )/2),((h - 350 )/2),260,30);
		l2.setFont(f3);
		l2.setForeground(Color.GRAY);
		add(l2);

		JLabel l3 = new JLabel("Enter password ");
		l3.setBounds((int)(((1.5 * w ) - 470 )/2),((h - 250 )/2),260,30);		
		l3.setFont(f3);
		l3.setForeground(Color.GRAY);
		add(l3);
        
        tfusername = new JTextField();
		tfusername.setBorder(lineBorder );
		//tfusername.setHorizontalAlignment(JTextField.CENTER);
		tfusername.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 350 )/2),250,40);
		tfusername.setFont(f1);
		tfusername.setForeground(Color.BLACK);        
		add(tfusername);
        
        tfpassword = new JPasswordField();
		tfpassword.setBorder(lineBorder );
		//tfpassword.setHorizontalAlignment(JTextField.CENTER);
		tfpassword.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 250 )/2),250,40);
		tfpassword.setFont(f1);
		tfpassword.setEchoChar('$');
		tfpassword.setForeground(Color.BLACK);        
		add(tfpassword);
        
        login = new JButton("LOGIN");
        login.setBounds((int)(((1.5 * w)  - 193 )/2),340,193,50);
		login.setFont(f1);
		login.setCursor(crsr);
		login.setBackground(DARK_GREEN);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        signup = new JButton("Sign-Up");
		signup.setBounds((int)(((1.5 * w ) - 193 )/2),420,193,50);
		signup.setFont(f1);
		signup.setCursor(crsr);        
		signup.setBackground(DARK_GREEN);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

		DefaultListCellRenderer lRenderer = new DefaultListCellRenderer();
		lRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
		
		/*
		JComboBox cmb1 = new JComboBox(s);
		cmb1.setRenderer(lRenderer);
		cmb1.setBounds((int)((w * .5 - 200 )/2),(h - 550 ),200,50);;
		cmb1.setBackground(LIGHT_GREEN);
		cmb1.setForeground(Color.BLACK);
		cmb1.setFont(f1);
		cmb1.setEditable(false);
		cmb1.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String s = (String) cmb1.getSelectedItem();
				switch (s) {
                    case "Shop owner":
						boolean shopOwner = true;
                        break;
                    case "Coustomer":
						boolean Coustomer = true;
                        break;
                    case "Vendor":
						boolean Vendor = true;
                        break;
				}
			}
		});
		add(cmb1);
		*/
    }
    
    public void actionPerformed(ActionEvent ae) {
        String username = tfusername.getText();
        char[] password = tfpassword.getPassword();
        String test = "";
		
        for(int i = 0; i < password.length; i++){
            test += password[i];
        }

        if(ae.getSource() == login){
			if (new Security().usernameAndPasswordCheck(username,test)){
				setVisible(false);
			}
			 else {
				JOptionPane.showMessageDialog(null, "Invalid username or password");
				tfusername.setText("");
				tfpassword.setText("");
			}
		}
        if(ae.getSource()==signup){
            this.setVisible(false);
            new Signup();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Login();
            }
        });
    }
}
