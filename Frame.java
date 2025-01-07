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

class ShopOwnerFrame extends JFrame{
		
	public ShopOwnerFrame(){
		setVisible(true);
		setBounds(200,15,800,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(null);
		
		Font f1 = new Font("Arial",Font.BOLD ,18);
		Font f2 = new Font("Times New Roman",Font.BOLD ,25);
		Font f3 = new Font("Arial",Font.BOLD ,13);

		JLabel l1 = new JLabel("Registration");
		l1.setBounds(((getWidth() - 150 )/2),(getHeight() - 650 ),150,100);
		l1.setFont(f2);
		l1.setForeground(Color.BLACK);
		add(l1);
		
		JLabel l2 = new JLabel("Enter username ");
		l2.setBounds(((getWidth() - 470 )/2),((getHeight() - 350 )/2),260,30);
		l2.setFont(f3);
		l2.setForeground(Color.GRAY);
		add(l2);

		JLabel l3 = new JLabel("Enter password ");
		l3.setBounds(((getWidth() - 470 )/2),((getHeight() - 250 )/2),260,30);		
		l3.setFont(f3);
		l3.setForeground(Color.GRAY);
		add(l3);
		
		JButton b1 = new JButton("logIn");
		b1.setBounds(((getWidth() - 193 )/2),340,193,50);
		b1.setFont(f1);
		//b1.setCursor(crsr);
		b1.setForeground(Color.BLACK);
		b1.addActionListener ( new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
			}
		});
		add(b1);

		JButton b2 = new JButton("signUp");
		b2.setBounds(((getWidth() - 193 )/2),420,193,50);
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
		
		JTextField t1 = new JTextField();
		t1.setBounds(((getWidth() - 210 )/2),((getHeight() - 350 )/2),250,40);
		t1.setFont(f1);
		t1.setForeground(Color.BLACK);
		//t1.setBackground(Color.BLACK);
		add(t1);

		JPasswordField pf = new JPasswordField();
		pf.setBounds(((getWidth() - 210 )/2),((getHeight() - 250 )/2),250,40);
		pf.setFont(f1);
		pf.setEchoChar('$');
		pf.setForeground(Color.BLACK);
		//pf.setBackground(Color.BLACK);
		add(pf);
		
	}
}
	
class Main{
	public static void main(String [] args){
		ShopOwnerFrame f = new ShopOwnerFrame();
	}
}