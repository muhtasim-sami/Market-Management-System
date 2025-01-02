import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Color;

class Frame extends JFrame{
	private JTextField tf;
	public Frame(){
		set();
	}
	public void set(){
		Container c = this.getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(null);
		
		this.setVisible(true);
		this.setBounds(200,15,800,700);
		//this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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