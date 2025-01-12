
import javax.swing.*;

public class Security{
	private String[] su = {"ahnaf","sami","rabby"};
	private String[] sp = {"abcd","efgh","ijkl"};
	private String[] ds = {"Shop owner","Vendor","Coustomer"};
	private int person;
	
	public void usernameCheck(String s){
		boolean bu[] = new boolean [su.length];
		boolean bUser = false;
		if ( s.isEmpty() ) {
			JOptionPane.showMessageDialog (null,"Enter your username ");
		}
			
		for (int i = 0 ; i < su.length; i++){
			if ( s.equals(sp[i])){
				bu[i] = true;
			}
			bUser = bUser | bu[i];
		}
		if (bUser) {
			JOptionPane.showMessageDialog (null,"username is correct ");
		}
		else{
			JOptionPane.showMessageDialog (null,"username is incorrect ");
		}	
	}
	public void passwordCheck(String s){
		boolean bp[] = new boolean [sp.length];
		boolean bPass = false;
		if ( s.isEmpty() ) {
			JOptionPane.showMessageDialog (null,"Enter your password ");
		}
			
		for (int i = 0 ; i < sp.length; i++){
			if ( s.equals(sp[i])){
				bp[i] = true;
			}
			bPass = bPass | bp[i];
		}
		if (bPass) {
			JOptionPane.showMessageDialog (null,"password is correct ");
		}
		else{
			JOptionPane.showMessageDialog (null,"password is incorrect ");
		}	
	}
	public boolean usernameAndPasswordCheck(String s1, String s2){
		boolean bu[] = new boolean [su.length];
		boolean bp[] = new boolean [su.length];
		boolean bup[] = new boolean [su.length];
		boolean bUser = false;
		boolean bPass = false;
		boolean bUserPass = false;
		if (s1.isEmpty() ) {
			JOptionPane.showMessageDialog (null,"Enter your username");
		}
		if ( s2.isEmpty() ) {
			JOptionPane.showMessageDialog (null,"Enter your password ");
		}
			
		for (int i = 0 ; i < su.length; i++){
			
			if (s1.equals(su[i]) &  s2.equals(sp[i])){
				bup[i] = true;
				person = i;
			}
			bUserPass = bUserPass | bup[i];
			
			if (s1.equals(su[i])){
				bu[i] = true;
			}
			bUser = bUser | bu[i];
			
			if ( s2.equals(sp[i])){
				bp[i] = true;
			}
			bPass = bPass | bp[i];
		}
		if (bPass == false) {
			JOptionPane.showMessageDialog (null,"password is incorrect ");
		}	
		else if (bUser == false) {
			JOptionPane.showMessageDialog (null,"username is incorrect ");
		}
		else if (bUserPass) {
			if (ds[person].equals("Shop owner")){
				new EmployeeManagement();
			}
			else if (ds[person].equals("Vendor")){
				new ProductManagement();
			}
			return true;
			
		}	
		return false;
	}
}


