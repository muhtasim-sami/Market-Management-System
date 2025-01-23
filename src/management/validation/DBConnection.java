package management.validation;

import java.io.*;
import java.util.*;

public class DBConnection {
    //public DBConnection(){}
    public String userPass = "D://java//MarketManagementSystem//DB//user.txt";
    public String shopData = "D://java//MarketManagementSystem//DB//shops.txt";
    public String productData = "D://java//MarketManagementSystem//DB//products.txt";

    public List<String> readFile(String filePath) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeFile(String filePath, List<String> content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : content) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
