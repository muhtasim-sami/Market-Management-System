package management.validation;
import java.util.*;
import javax.swing.JOptionPane;
import management.employee.EmployeeManagement;
import management.product.ProductManagement;
import user.*;

public class Security {
    private DBManager DBManager;
    
    public Security(DBManager DBManager) {
        this.DBManager = DBManager;
    }
    
    public boolean checkUsername(String username) {
        List<String> data = DBManager.readFile();
        for (String line : data) {
            if (line.startsWith("User:")) {
                String[] parts = line.substring(6).split(", ");
                // Skip checking if there aren't enough parts
                if (parts.length < 3) continue;
                
                String storedUsername = parts[1].trim();
                if (storedUsername.equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPassword(String password) {
        List<String> data = DBManager.readFile();
        for (String line : data) {
            if (line.startsWith("User:")) {
                String[] parts = line.substring(6).split(", ");
                // Skip checking if there aren't enough parts
                if (parts.length < 3) continue;
                
                String storedPassword = parts[2].trim();
                if (storedPassword.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCredentials(String username, String password) {
        List<String> data = DBManager.readFile();
        for (String line : data) {
            if (line.startsWith("User: ")) {
                String[] parts = line.substring(6).split(", ");
                //if (parts.length < 3) continue;
                
                String storedUsername = parts[1].trim();
                String storedPassword = parts[2].trim();
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void writeUser(String[] userData) {
        List<String> userDataList = new ArrayList<>(Arrays.asList(userData));
        DBManager.writeFile(userDataList);
    }

    public void afterLogin(String username, String password) {
        List<String> data = DBManager.readFile();
        for (String line : data) {
            if (line.startsWith("User: ")) {
                String[] parts = line.substring(6).split(", ");
                //if (parts.length < 4) continue;
                
                String storedUsername = parts[1].trim();
                String storedPassword = parts[2].trim();
                String role = parts[3].trim();
                
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    switch (role) {
                        case "Employee Manager":
                            new EmployeeManagement();
                            break;
                        case "Shop Manager":
                            new ProductManagement();
                            break;
                        case "Vendor":
							new VendorView(new DBManager());
							break;
                    }
                    break;
                }
            }
        }
    }
	public boolean isValid(String username, String email, String test) {
		if(username.length() == 0) {
			JOptionPane.showMessageDialog(null, "Please Enter username");
			return false;
		}
		if(username.length() > 20) {
			JOptionPane.showMessageDialog(null, "Username is too long");
			return false;
		}

		if(email.length() == 0) {
			JOptionPane.showMessageDialog(null, "Please Enter email");
			return false;
		}
		if(!email.contains("@") || (!email.contains(".")) || (!email.endsWith(".com"))) {
			JOptionPane.showMessageDialog(null, "Invalid email");
			return false;
		}
		String[] emailParts = email.split("@");
		if(emailParts.length != 2) {
			JOptionPane.showMessageDialog(null, "Invalid email format");
			return false;
		}
		if(emailParts[0].length() == 0) {
			JOptionPane.showMessageDialog(null, "Email username cannot be empty");
			return false;
		}
		if(emailParts[1].length() == 0) {
			JOptionPane.showMessageDialog(null, "Email domain cannot be empty");
			return false;
		}
		if(email.contains(" ")) {
			JOptionPane.showMessageDialog(null, "Email cannot contain spaces");
			return false;
		}
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		if(!email.matches(emailRegex)) {
			JOptionPane.showMessageDialog(null, "Invalid email characters");
			return false;
		}

		if(test == "") {
			JOptionPane.showMessageDialog(null, "Please Enter password");
			return false;
		}
		if(test.length() > 20) {
			JOptionPane.showMessageDialog(null, "Password is too long");
			return false;
		}
		String upperCaseChars = "(.*[A-Z].*)";
		if(!test.matches(upperCaseChars)) {
			System.out.println("Password must have atleast one uppercase character");
			JOptionPane.showMessageDialog(null, "Password must have atleast one uppercase character");
			return false;
		}
		String lowerCaseChars = "(.*[a-z].*)";
		if(!test.matches(lowerCaseChars)) {
			System.out.println("Password must have atleast one lowercase character");
			JOptionPane.showMessageDialog(null, "Password must have atleast one lowercase character");
			return false;
		}
		String numbers = "(.*[0-9].*)";
		if(!test.matches(numbers)) {
			System.out.println("Password must have atleast one number");
			JOptionPane.showMessageDialog(null, "Password must have atleast one number");
			return false;
		}
		String specialChars = "(.*[@,#,$,%].*$)";
		if(!test.matches(specialChars)) {
			System.out.println("Password must have atleast one special character among @#$%");
			JOptionPane.showMessageDialog(null, "Password must have atleast one special character among @#$%");
			return false;
		}
		
		return true;
	}
}