package management.validation;

import java.util.*;
import management.employee.EmployeeManagement;
import management.product.ProductManagement;

public class Security {
    
    private List<List<String>> data = new ArrayList<>();
    private DBConnection dbConnection;

    public Security(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
    }
    
    public void readData() {
        data = new ArrayList<>();
        List<String> lines = dbConnection.readFile(dbConnection.getUserPassData());
        for (String line : lines) {
            String[] values = line.split(" , ");
            data.add(Arrays.asList(values));
        }
    }

    public boolean checkUsername(String username) {
        readData(); // Ensure data is read before checking
        for (List<String> row : data) {
            if (row.get(0).trim().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String password) {
        readData(); // Ensure data is read before checking
        for (List<String> row : data) {
            if (row.get(1).trim().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCredentials(String username, String password) {
        readData(); // Ensure data is read before checking
        for (List<String> row : data) {
            if (row.get(0).trim().equals(username) && row.get(1).trim().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public void writeUser(String[] userData) {
        List<String> content = new ArrayList<>();
        content.add(String.join(" , ", userData));
        dbConnection.writeFile(dbConnection.getUserPassData(), content);
    }

    public void afterLogin(String username, String password) {
        for (List<String> row : data) {
            if (row.get(0).trim().equals(username) && row.get(1).trim().equals(password)) {
                switch (row.get(2).trim()) {
                    case "Shop Manager":
                        new EmployeeManagement();
                        break;
                    case "Vendor":
                    case "Cashear":
                        new ProductManagement();
                        break;
                }
            }
        }
    }
}