import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.*;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;

class ShopOwnerFrame extends JFrame{

	public ShopOwnerFrame(){
		setVisible(true);
		setBounds(200,15,800,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout (FlowLayout.CENTER));
		
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(null);
		
		String [] s = {"Market Owner","Shop owner","Coustomer","Vendor"}; 
		
		Font f1 = new Font("Arial",Font.BOLD ,18);
		Font f2 = new Font("Times New Roman",Font.BOLD ,25);
		Font f3 = new Font("Arial",Font.BOLD ,13);
		
		Color VERY_LIGHT_BLUE = new Color(51,204,255);
		Color LIGHT_GREEN = new Color(102,255,102);
		Color DARK_BLUE = new Color(0,0,204);
	
		
		double w = getWidth();
		int h = (int)getHeight();
		
		

		JLabel l1 = new JLabel("Registration");
		l1.setBounds((int)(((1.5 * w ) - 150 )/2),(h - 650 ),150,100);
		l1.setFont(f2);
		l1.setForeground(DARK_BLUE);
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
		
		JButton b1 = new JButton("logIn");
		b1.setBounds((int)(((1.5 * w)  - 193 )/2),340,193,50);
		b1.setFont(f1);
		//b1.setCursor(crsr);
		b1.setForeground(Color.BLACK);
		b1.addActionListener ( new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
			}
		});
		add(b1);

		JButton b2 = new JButton("signUp");
		b2.setBounds((int)(((1.5 * w ) - 193 )/2),420,193,50);
		b2.setFont(f1);
		//b2.setCursor(crsr);
		b2.setForeground(Color.BLACK);
		b2.addActionListener ( new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				//fr2.dispose();
				//fr1.setVisible(true);
			}			
		});
		add(b2);
		
		LineBorder lineBorder = new LineBorder(Color.black, 1, true);
		
		JTextField t1 = new JTextField();
		t1.setBorder(lineBorder );
		t1.setHorizontalAlignment(JTextField.CENTER);
		t1.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 350 )/2),250,40);
		t1.setFont(f1);
		t1.setForeground(Color.BLACK);
		//t1.setBackground(Color.BLACK);
		add(t1);

		JPasswordField pf = new JPasswordField();
		pf.setBorder(lineBorder );
		pf.setHorizontalAlignment(JTextField.CENTER);
		pf.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 250 )/2),250,40);
		pf.setFont(f1);
		pf.setEchoChar('$');
		pf.setForeground(Color.BLACK);
		//pf.setBackground(Color.BLACK);
		add(pf);
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new FlowLayout (FlowLayout.CENTER, 100 , 50));
		pnl.setSize((int)(w / 2) - 100 , h );
		pnl.setBackground(DARK_BLUE);
		add(pnl, BorderLayout.WEST);
		
		DefaultListCellRenderer lRenderer = new DefaultListCellRenderer();
		lRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items

		JLabel l4 = new JLabel("Create Account");
		l4.setBounds((int)(((1.5 * w ) - 150 )/2),(h - 650 ),150,100);
		l4.setFont(f2);
		l4.setForeground(Color.WHITE);
		pnl.add(l4);
		
		JComboBox cmb1 = new JComboBox(s);
		cmb1.setRenderer(lRenderer);
		cmb1.setBounds((int)((w * .5 - 200 )/2),(h - 350 ),200,50);;
		//cmb1.setBackground(LIGHT_GREEN);
		cmb1.setForeground(Color.BLACK);
		cmb1.setFont(f1);
		cmb1.setEditable(false);
		pnl.add(cmb1);

	}
}
	
class Main{
	public static void main(String [] args){
		new ShopOwnerFrame();
	}
}