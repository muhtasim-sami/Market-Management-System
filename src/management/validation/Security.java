package management.validation;
import java.util.*;
import management.employee.EmployeeManagement;
import management.product.ProductManagement;

public class Security {
    private DBConnection dbConnection;
    
    public Security(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public boolean checkUsername(String username) {
        List<String> data = dbConnection.readFile();
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
        List<String> data = dbConnection.readFile();
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
        List<String> data = dbConnection.readFile();
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
        dbConnection.writeFile(userDataList);
    }

    public void afterLogin(String username, String password) {
        List<String> data = dbConnection.readFile();
        for (String line : data) {
            if (line.startsWith("User: ")) {
                String[] parts = line.substring(6).split(", ");
                //if (parts.length < 4) continue;
                
                String storedUsername = parts[1].trim();
                String storedPassword = parts[2].trim();
                String role = parts[3].trim();
                
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    switch (role) {
                        case "Shop Manager":
                            new EmployeeManagement();
                            break;
                        case "Vendor":
                        case "Cashier":
                            new ProductManagement();
                            break;
                    }
                    break;
                }
            }
        }
    }
}