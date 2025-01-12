
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener{
   
	JComboBox cmb1;
    JTextField t1;
	JPasswordField p1,p2,p3;
    JButton bt1,bt2;

    public Signup(){
        
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
        setTitle("Sign Up for new Employee");
        setBounds(200,15,800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		double w = getWidth();
		int h = (int)getHeight();

        JLabel l1 = new JLabel("Registration");
		l1.setBounds((int)(((1.5 * w ) - 150 )/2),(h - 650 ),150,100);
		l1.setFont(f2);
		l1.setForeground(DARK_GREEN);        
		add(l1);


        JLabel l2 = new JLabel("Designation");
		l2.setBounds((int)(((1.5 * w ) - 470 )/2),((h - 350 )/2),260,30);
		l2.setFont(f3);
		l2.setForeground(Color.GRAY);        
		add(l2);


        JLabel l3 = new JLabel("Username");
		l3.setBounds((int)(((1.5 * w ) - 470 )/2),((h - 250 )/2),260,30);		
		l3.setFont(f3);
		l3.setForeground(Color.GRAY);        
		add(l3);

        JLabel l4 = new JLabel("Password");
		l4.setBounds((int)(((1.5 * w ) - 470 )/2),((h - 150 )/2),260,30);		
		l4.setFont(f3);
		l4.setForeground(Color.GRAY);         
		add(l4);

        String array[] = {"Manager","Cashier"};
		
		DefaultListCellRenderer lRenderer = new DefaultListCellRenderer();
		lRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
		
        cmb1 = new JComboBox(array);
		cmb1.setRenderer(lRenderer);
		cmb1.setBounds((int)((w * .5 - 200 )/2),(h - 550 ),200,50);;
		//cmb1.setBackground(LIGHT_GREEN);
		cmb1.setForeground(Color.BLACK);
		cmb1.setFont(f1);
		cmb1.setEditable(false);
        cmb1.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 350 )/2),250,40);
        add(cmb1);


        t1 = new JTextField();
        t1.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 250 )/2),250,40);
		t1.setFont(f1);
        add(t1);

        p1 = new JPasswordField();
        p1.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 150 )/2),250,40);
		p1.setFont(f1);
		p1.setEchoChar('$');
        add(p1);

        p2 = new JPasswordField();
		p2.setBounds((int)(((1.5 * w ) - 210 )/2),((h - 50 )/2),250,40);
		p2.setFont(f1);
		p2.setEchoChar('$');
		add(p2);

		JLabel l5 = new JLabel("Confirm Password");
		l5.setBounds((int)(((1.5 * w ) - 470 )/2),((h - 50 )/2),260,30);		
		l5.setFont(f3);
		l5.setForeground(Color.GRAY);         
		add(l5);

        bt1 = new JButton("Submit");
        bt1.setBounds((int)(((1.5 * w)  - 193 )/2),400,193,50);
		bt1.setFont(f1);
		bt1.setCursor(crsr);
		bt1.setBackground(DARK_GREEN);
        bt1.setForeground(Color.WHITE);
        bt1.addActionListener(this);
        add(bt1);

		bt2 = new JButton("Back");
        bt2.setBounds((int)(((1.5 * w)  - 193 )/2),470,193,50);
		bt2.setFont(f1);
		bt2.setCursor(crsr);
		bt2.setBackground(DARK_GREEN);
		bt2.setForeground(Color.WHITE);
        bt2.addActionListener(this);
        add(bt2);


    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == bt1){
            String susername = t1.getText();
            String spassword = p1.getText();
            String confirmPassword = p2.getText();
            String sdesignation = (String) cmb1.getSelectedItem();
            String username = susername;
            String test = spassword;

         
                if(spassword.equals(confirmPassword)==false){
                    JOptionPane.showMessageDialog(null,"Password don't matched");
                    t1.setText("");
                    p1.setText("");
                    p2.setText("");

                    return;
                }
                if(username.length() == 0){
                    JOptionPane.showMessageDialog(null,"Please Enter username");
                    return;
                }
    
                if(test.length() == 0){
                    JOptionPane.showMessageDialog(null,"Please Enter password");
                    return;
                }
    
                if(username.length() > 20){
                    JOptionPane.showMessageDialog(null,"Username is too long");
                    return;
                }
                if(test.length() > 	20){
                    JOptionPane.showMessageDialog(null,"Password is too long");
                    return;
                }
    
                String upperCaseChars = "(.*[A-Z].*)";
                if (!test.matches(upperCaseChars ))
                {
                        System.out.println("Password must have atleast one uppercase character");
                        JOptionPane.showMessageDialog(null,"Password must have atleast one uppercase character");
                        return;
                }
                String lowerCaseChars = "(.*[a-z].*)";
                if (!test.matches(lowerCaseChars ))
                {
                        System.out.println("Password must have atleast one lowercase character");
                        JOptionPane.showMessageDialog(null,"Password must have atleast one lowercase character");
                        return;
                }
                String numbers = "(.*[0-9].*)";
                if (!test.matches(numbers ))
                {
                        System.out.println("Password must have atleast one number");
                        JOptionPane.showMessageDialog(null,"Password must have atleast one number");
                        return;
                }
                String specialChars = "(.*[@,#,$,%].*$)";
                if (!test.matches(specialChars ))
                {
                        System.out.println("Password must have atleast one special character among @#$%");
                        JOptionPane.showMessageDialog(null,"Password must have atleast one special character among @#$%");
                        return;
                }
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Login();
				
        }
		
		if(ae.getSource() == bt2){
			this.setVisible(false);
			new Login();
		}
    }
	
	 public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Signup();
            }
        });
    }

    
}