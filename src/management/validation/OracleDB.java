package management.validation;

import java.sql.*;
import java.util.List;

public class OracleDB {
    private static final String DB_URL = "jdbc:oracle:thin:@CreedFanatic:1521:XE"; // Update as needed
    private static final String DB_USER = "system";
    private static final String DB_PASS = "admin";

    public Connection getOracleConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    // Helper method to execute SQL with try-catch
    private void executeUpdate(String sql) {
        try (Connection conn = getOracleConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error executing SQL: ");
            e.printStackTrace();
        }
    }

    // --- EMPLOYEE ---
    public void addEmployeeToOracle(List<String> employeeData) {
        String sql = "INSERT INTO EMPLOYEE (EMPLOYEEID, FULLNAME, FATHERNAME, DATEOFBIRTH, salary, address, CONTACTNUMBER, email, QUALIFICATION, designation, NID) VALUES ('"
                + escapeSql(employeeData.get(0)) + "', '" + escapeSql(employeeData.get(1)) + "', '" + escapeSql(employeeData.get(2)) + "', '" + escapeSql(employeeData.get(3)) + "', '" + escapeSql(employeeData.get(4)) + "', '" + escapeSql(employeeData.get(5)) + "', '" + escapeSql(employeeData.get(6)) + "', '" + escapeSql(employeeData.get(7)) + "', '" + escapeSql(employeeData.get(8)) + "', '" + escapeSql(employeeData.get(9)) + "', '" + escapeSql(employeeData.get(10)) + "')";
        executeUpdate(sql);
    }

    public void updateEmployeeInOracle(String id, List<String> employeeData) {
        String sql = "UPDATE EMPLOYEE SET FULLNAME ='" + escapeSql(employeeData.get(1)) +
                "', FATHERNAME='" + escapeSql(employeeData.get(2)) +
                "', DATEOFBIRTH=TO_DATE('" + escapeSql(employeeData.get(3)) + "', 'YYYY-MM-DD')" +
                ", SALARY='" + escapeSql(employeeData.get(4)) +
                "', address='" + escapeSql(employeeData.get(5)) +
                "', CONTACTNUMBER='" + escapeSql(employeeData.get(6)) +
                "', email='" + escapeSql(employeeData.get(7)) +
                "', QUALIFICATION='" + escapeSql(employeeData.get(8)) +
                "', designation='" + escapeSql(employeeData.get(9)) +
                "', NID='" + escapeSql(employeeData.get(10)) +
                "' WHERE EMPLOYEEID ='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    public void deleteEmployeeFromOracle(String id) {
        String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    // --- USER ---
    public void addUserToOracle(List<String> userData) {
        String sql = "INSERT INTO users (USERID, username, password, role) VALUES ('"
                + escapeSql(userData.get(0)) + "', '" + escapeSql(userData.get(1)) + "', '" + escapeSql(userData.get(2)) + "', '" + escapeSql(userData.get(3)) + "')";
        executeUpdate(sql);
    }

    public void updateUserInOracle(String id, List<String> userData) {
        String sql = "UPDATE users SET USERNAME='" + escapeSql(userData.get(1)) + "', PASSWORD='" + escapeSql(userData.get(2)) + "', ROLE='" + escapeSql(userData.get(3)) + "' WHERE USERID='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    public void deleteUserFromOracle(String id) {
        String sql = "DELETE FROM users WHERE USERID='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    // --- PRODUCT ---
    public void addProductToOracle(List<String> productData) {
        String sql = "INSERT INTO PRODUCT (PRODUCTID, PRODUCTNAME, CATEGORY, QUANTITY, PRICE, SHOPID) VALUES ('"
                + escapeSql(productData.get(0)) + "', '" + escapeSql(productData.get(1)) + "', '" + escapeSql(productData.get(2)) + "', '" + escapeSql(productData.get(3)) + "', '" + escapeSql(productData.get(4)) + "', '" + escapeSql(productData.get(5)) + "')";
        executeUpdate(sql);
    }

    public void updateProductInOracle(String id, List<String> productData) {
        List<String> shopData = new DBConnection().readFromOracle("SELECT SHOPID FROM PRODUCT WHERE PRODUCTID = '" + escapeSql(id) + "'");
        String sql = "UPDATE PRODUCT SET PRODUCTNAME='" + escapeSql(productData.get(1)) +
                "', CATEGORY='" + escapeSql(productData.get(2)) +
                "', QUANTITY='" + escapeSql(productData.get(3)) +
                "', PRICE='" + escapeSql(productData.get(4)) +
                "', SHOPID='" + escapeSql(shopData.get(0)) +
                "' WHERE PRODUCTID='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    public void deleteProductFromOracle(String id) {
        String sql = "DELETE FROM PRODUCT WHERE PRODUCTID ='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    // --- SHOP ---
    public void addShopToOracle(List<String> shopData) {
        String sql = "INSERT INTO SHOP (SHOPID, SHOPNAME, ADDRESS) VALUES ('"
                + escapeSql(shopData.get(0)) + "', '" + escapeSql(shopData.get(1)) + "', '" + escapeSql(shopData.get(2)) + "')";
        executeUpdate(sql);
    }

    public void updateShopInOracle(String id, List<String> shopData) {
        String sql = "UPDATE SHOP SET SHOPNAME ='" + escapeSql(shopData.get(1)) + "', ADDRESS ='" + escapeSql(shopData.get(2)) + "' WHERE SHOPID ='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    public void deleteShopFromOracle(String id) {
        String sql = "DELETE FROM SHOP WHERE SHOPID='" + escapeSql(id) + "'";
        executeUpdate(sql);
    }

    public void decreaseProductQuantityInOracle(String productId, int quantity) {
        String sql = "UPDATE PRODUCT SET quantity = quantity - " + quantity + " WHERE PRODUCTID = '" + escapeSql(productId) + "'";
        executeUpdate(sql);
    }

    public void increaseProductQuantityInOracle(String productId, int quantity) {
        String sql = "UPDATE PRODUCT SET quantity = quantity + " + quantity + " WHERE PRODUCTID = '" + escapeSql(productId) + "'";
        executeUpdate(sql);
    }

    private String escapeSql(String value) {
        return value == null ? null : value.replace("'", "''");
    }
}

