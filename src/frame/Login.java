package frame;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import management.validation.*;

public class Login extends JFrame implements ActionListener {
    
    JTextField tfusername;
    JPasswordField tfpassword;
    JButton login, signup, back, showPass;
    JCheckBox cb1 = new JCheckBox("Show Password");
    JCheckBox cb2 = new JCheckBox("hide Password");
	
	private String path = System.getProperty("user.dir");  
    private String loginIcon = (path.substring(0, path.length() - 3) + "pic\\login.jpg");
    private String background = (path.substring(0, path.length() - 3) + "pic\\Background.jpg");

    public Login() {
        String[] s = {"Market Owner", "Shop owner", "Customer", "Vendor"}; 
        
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 = new Font("Arial", Font.BOLD, 13);
        
        Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
        Color LIGHT_GREEN = new Color(102, 255, 102);
        Color DARK_BLUE = new Color(0, 0, 204);
        Color DARK_GREEN = new Color(0, 153, 0);
		Color ORANGE_BACKGROUND = new Color(224, 117, 30); 

        
        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);
        
        setVisible(true);
        setBounds(200, 15, 800, 700);
        
        double w = getWidth();
        int h = (int) getHeight();
        
        ButtonGroup bg1 = new ButtonGroup();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //getContentPane().setBackground(Color.WHITE);
		getContentPane().setBackground(ORANGE_BACKGROUND);                      
        setLayout(null);
		
		JLabel l = new JLabel(new ImageIcon(background));
		setContentPane(l);
		
		
		System.out.println(loginIcon);
		
        JLabel l1 = new JLabel("Login");
        l1.setBounds((int) (((1.5 * w) - 150) / 2), (h - 650), 150, 100);
        l1.setFont(f2);
        l1.setForeground(DARK_BLUE);
        add(l1);
        
        JLabel l2 = new JLabel("Enter username ");
        l2.setBounds((int) (((1.5 * w) - 470) / 2), ((h - 350) / 2), 260, 30);
        l2.setFont(f3);
        l2.setForeground(Color.WHITE);
        add(l2);

        JLabel l3 = new JLabel("Enter password ");
        l3.setBounds((int) (((1.5 * w) - 470) / 2), ((h - 250) / 2), 260, 30);        
        l3.setFont(f3);
        l3.setForeground(Color.WHITE);
        add(l3);
		
		JLabel l4 = new JLabel(new ImageIcon(loginIcon));
		l4.setBounds(0,30,370,600);
		add(l4);
		
        tfusername = new JTextField();
        tfusername.setBorder(lineBorder);
        tfusername.setBounds((int) (((1.5 * w) - 210) / 2), ((h - 350) / 2), 250, 40);
        tfusername.setFont(f1);
        tfusername.setForeground(Color.BLACK);        
        tfusername.addKeyListener(nextTextField);        
        add(tfusername);
        
        tfpassword = new JPasswordField();
        tfpassword.setBorder(lineBorder);
        tfpassword.setBounds((int) (((1.5 * w) - 210) / 2), ((h - 250) / 2), 250, 40);
        tfpassword.setFont(f1);
        tfpassword.setEchoChar('$');
        tfpassword.setForeground(Color.BLACK);        
        tfpassword.addKeyListener(loginKey);        
        add(tfpassword);
        
        login = new JButton("LOGIN");
        login.setBounds((int) (((1.5 * w) - 193) / 2), 340, 193, 50);
        login.setFont(f1);
        login.setCursor(crsr);
        login.setBackground(DARK_BLUE);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        signup = new JButton("Sign-Up");
        signup.setBounds((int) (((1.5 * w) - 193) / 2), 420, 193, 50);
        signup.setFont(f1);
        signup.setCursor(crsr);        
        signup.setBackground(DARK_BLUE);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        back = new JButton("Back");
        back.setBounds((int) (((1.5 * w) - 193) / 2), 500, 193, 50);
        back.setFont(f1);
        back.setCursor(crsr);        
        back.setBackground(DARK_BLUE);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        cb1.setBounds((int) (((1.5 * w) - 210) / 2), ((h - 150) / 2), 250, 20);
        cb1.setBackground(LIGHT_GREEN);
        cb1.setForeground(Color.BLACK);
        cb1.setFont(f1);
        cb1.addActionListener(this);
        bg1.add(cb1);
        add(cb1);
		
		cb2.setBounds((int) (((1.5 * w) - 210) / 2), ((h - 150) / 2), 250, 20);
        cb2.setBackground(LIGHT_GREEN);
        cb2.setForeground(Color.BLACK);
        cb2.setFont(f1);
        cb2.addActionListener(this);
        bg1.add(cb2);
        add(cb2);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());
            Security sc = new Security(new DBConnection());
            if (sc.checkCredentials(username, password)) {
                sc.afterLogin(username, password);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                tfusername.setText("");
                tfpassword.setText(""); 
            }
        }
        if (ae.getSource() == signup) {
            this.setVisible(false);
            new Signup();
        }
        if (ae.getSource() == back) {
            this.setVisible(false);
            new Homepage();
        }
        if (ae.getSource() == cb1) {
            tfpassword.setEchoChar('\u0000');
			cb1.setVisible(false);
			cb2.setVisible(true);
        }
		if (ae.getSource() == cb2){
            tfpassword.setEchoChar('$');
			cb2.setVisible(false);
			cb1.setVisible(true);        
		}
    }
	KeyListener nextTextField = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				tfpassword.requestFocus(); 
			}
		}
	};
    
	KeyListener loginKey = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				login.doClick(); 
			}
		}
	};
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
}
