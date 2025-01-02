import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
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

class Frame extends JFrame{
	
	Container c;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JButton b1;
	JButton b2;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JPasswordField pf;
	Font f1;
	Font f2;
	Font f3;
	
	private JTextField tf;
	public Frame(){
		set();
	}
	public void set(){
		
		this.setVisible(true);
		this.setBounds(200,15,800,700);
		//this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l1 = new JLabel("Registration");
		l2 = new JLabel("Enter username ");
		
		b1 = new JButton("LogIn");
		b2 = new JButton("SignUp");
		t1 = new JTextField();
		pf = new JPasswordField();
		
		f1 = new Font("Arial",Font.BOLD ,18);
		f2 = new Font("Times New Roman",Font.BOLD ,25);
		f3 = new Font("Arial",Font.BOLD ,13);
		
		Container c = this.getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(null);
		
		c.add(l1);
		c.add(l2);
		c.add(l3);
		//c.add(l4);
		//c.add(l5);
		c.add(t1);
		//c.add(t2);
		//c.add(t3);
		c.add(pf);
		c.add(b1);
		c.add(b2);
		
		l1.setBounds(((this.getWidth() - 150 )/2),(this.getHeight() - 650 ),150,100);
		l1.setFont(f2);
		l1.setForeground(Color.BLACK);
		
		l2.setBounds(((this.getWidth() - 470 )/2),((this.getHeight() - 350 )/2),260,30);
		l2.setFont(f3);
		l2.setForeground(Color.GRAY);
		
		l3.setBounds(((this.getWidth() - 470 )/2),((this.getHeight() - 250 )/2),260,30);		
		l3.setFont(f3);
		l3.setForeground(Color.GRAY);
		
		b1.setBounds(((this.getWidth() - 193 )/2),440,193,50);
		b1.setFont(f1);
		//b1.setCursor(crsr);
		b1.setForeground(Color.BLACK);
		
		b2.setBounds(((this.getWidth() - 193 )/2),520,193,50);
		b2.setFont(f1);
		//b2.setCursor(crsr);
		b2.setForeground(Color.BLACK);
		
		t1.setBounds(((this.getWidth() - 210 )/2),((this.getHeight() - 350 )/2),250,40);
		t1.setFont(f1);
		t1.setForeground(Color.BLACK);
		//t1.setBackground(Color.BLACK);

		pf.setBounds(((this.getWidth() - 210 )/2),((this.getHeight() - 250 )/2),250,40);
		pf.setFont(f1);
		pf.setEchoChar('$');
		pf.setForeground(Color.BLACK);
		//pf.setBackground(Color.BLACK);
		
		
	}
}
class Main{
	public static void main(String [] args){
		Frame f = new Frame();
	}
}


/*
	- Shop owner
	- vendor
	- brodker
	- buyer
*/