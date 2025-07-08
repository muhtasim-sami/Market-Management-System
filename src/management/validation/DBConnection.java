package management.validation;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

    public class DBConnection {
    private static final String DB_URL = "jdbc:oracle:thin:@CreedFanatic:1521:XE"; // Update as needed
    private static final String DB_USER = "system";
    private static final String DB_PASS = "admin";

    private String path = System.getProperty("user.dir");  
    private String db = (path.substring(0, path.length() - 3) + "DB\\db.txt");

    public String getDB() { return db; }

	public Connection getOracleConnection() throws SQLException, ClassNotFoundException {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		}

    // New: Example method to read from Oracle DB
    public List<String> readFromOracle(String query) {
        List<String> results = new ArrayList<>();
        try (Connection conn = getOracleConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i));
                    if (i < columnCount) row.append(", ");
                }
                results.add(row.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // New: Example method to write to Oracle DB
    public void writeToOracle(String insertSql) {
        try (Connection conn = getOracleConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insertSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> readFile() {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getDB()))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeFile(List<String> content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getDB()))) {
            for (String line : content) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
