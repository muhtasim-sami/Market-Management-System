package management.validation;

import java.io.*;
import java.util.*;

public class DBConnection {
    private String db = "D://java//MarketManagementSystem//DB//db.txt";

    public String getDB() { return db; }

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

    private String generateNextId(String prefix, String entityType) {
        List<String> data = readFile();
        int maxId = 0;

        for (String line : data) {
            if (line.startsWith(entityType)) {
                String[] parts = line.substring(entityType.length()).split(", ");
                if (parts.length > 0) {
                    String idPart = parts[0].trim();
                    if (idPart.startsWith(prefix)) {
                        try {
                            int currentId = Integer.parseInt(idPart.substring(prefix.length()));
                            maxId = Math.max(maxId, currentId);
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }

        return prefix + String.format("%03d", maxId + 1);
    }

    private String generateNextProductId(String shopId) {
        List<String> data = readFile();
        int maxProductNumber = 0;
        String productPrefix = shopId.substring(1) + "P"; 

        for (String line : data) {
            if (line.startsWith("Product:")) {
                String[] parts = line.substring(9).split(", ");
                String productId = parts[0].trim();
                if (productId.startsWith(productPrefix)) {
                    try {
                        int productNumber = Integer.parseInt(productId.substring(productPrefix.length()));
                        maxProductNumber = Math.max(maxProductNumber, productNumber);
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }

        return productPrefix + String.format("%03d", maxProductNumber + 1);
    }

    private String getShopIdByName(String shopName) {
        List<String> data = readFile();
        for (String line : data) {
            if (line.startsWith("Shop:")) {
                String[] parts = line.substring(6).split(", ");
                if (parts.length >= 2 && parts[1].equals(shopName)) {
                    return parts[0];
                }
            }
        }
        return null;
    }

	private void addDataInOrder(String prefix, String newEntry) {
		List<String> dbData = readFile();
		List<String> newData = new ArrayList<>();
		boolean added = false;
		
		if (prefix.equals("Shop: ")) {
			newData.addAll(dbData);
        
			if (!dbData.isEmpty() && !dbData.get(dbData.size() - 1).trim().isEmpty()) {
				newData.add("");
			}			
			newData.add(prefix + newEntry);
			writeFile(newData);
			return;
		}
		
		if (dbData.isEmpty()) {
			newData.add(prefix + newEntry);
			writeFile(newData);
			return;
		}

		int lastIndex = -1;
		for (int i = 0; i < dbData.size(); i++) {
			String line = dbData.get(i);
			if (line.startsWith(prefix)) {
				lastIndex = i;
			}
		}

		for (int i = 0; i < dbData.size(); i++) {
			if (i == lastIndex + 1 && !added) {
				newData.add(prefix + newEntry);
				added = true;
			}
			newData.add(dbData.get(i));
		}

		if (!added) {
			newData.add(prefix + newEntry);
		}

		writeFile(newData);
	}
    public void addUserData(List<String> userData) {
        String id = generateNextId("U", "User:");
        List<String> newData = new ArrayList<>();
        newData.add(id);
        newData.addAll(userData);
        String newUser = String.join(", ", newData);
        addDataInOrder("User: ", newUser);
    }

    public void addShopData(List<String> shopData) {
        String id = generateNextId("S", "Shop:");
        List<String> newData = new ArrayList<>();
        newData.add(id);
        newData.addAll(shopData);
        String newShop = String.join(", ", newData);
        addDataInOrder("Shop: ", newShop);
    }

    public void addProductData(List<String> productData, String shopName) {
        String shopId = getShopIdByName(shopName);
        if (shopId == null) {
            System.out.println("Error: Shop " + shopName + " not found. Please create the shop first.");
            return;
        }

        String productId = generateNextProductId(shopId);
        List<String> newData = new ArrayList<>();
        newData.add(productId);
        newData.addAll(productData);
        String newProduct = String.join(", ", newData);
        
        List<String> dbData = readFile();
        List<String> newDbData = new ArrayList<>();
        boolean added = false;
        String currentShopName = "";
        int lastProductIndex = -1;

        for (int i = 0; i < dbData.size(); i++) {
            String line = dbData.get(i);
            
            if (line.startsWith("Shop:")) {
                String[] shopParts = line.substring(6).split(", ");
                currentShopName = shopParts[1];
            }
            
            if (currentShopName.equals(shopName) && line.startsWith("Product:")) {
                lastProductIndex = i;
            }
        }

        currentShopName = "";
        for (int i = 0; i < dbData.size(); i++) {
            String line = dbData.get(i);
            
            if (line.startsWith("Shop:")) {
                String[] shopParts = line.substring(6).split(", ");
                currentShopName = shopParts[1];
            }

            if (!added && currentShopName.equals(shopName)) {
                if (lastProductIndex == -1 && line.startsWith("Shop:")) {
                    newDbData.add(line);
                    newDbData.add("Product: " + newProduct);
                    added = true;
                    continue;
                } else if (i == lastProductIndex) {
                    newDbData.add(line);
                    newDbData.add("Product: " + newProduct);
                    added = true;
                    continue;
                }
            }
            
            newDbData.add(line);
        }

        if (!added) {
            newDbData.add("Product: " + newProduct);
        }

        writeFile(newDbData);
    }

    public void addEmployeeData(List<String> employeeData) {
        String id = generateNextId("E", "Employee:");
        List<String> newData = new ArrayList<>();
        newData.add(id);
        newData.addAll(employeeData);
        String newEmployee = String.join(", ", newData);
        addDataInOrder("Employee: ", newEmployee);
    }
	
	public List<String> readProductData() {
		List<String> products = new ArrayList<>();
		List<String> data = readFile();
		for (String line : data) {
			if (line.startsWith("Product:")) {
				String productParts = line.substring(9);
				products.add(productParts);
			}
		}
		return products;
	}

	public List<String> readEmployeeData() {
		List<String> employees = new ArrayList<>();
		List<String> data = readFile();
		for (String line : data) {
			if (line.startsWith("Employee:")) {
				String employeeParts = line.substring(10);
				employees.add(employeeParts);
			}
		}
		return employees;
	}
	
	public List<String> readShopAndProductData() {
		List<String> shopAndProducts = new ArrayList<>();
		List<String> data = readFile();
		
		for (String line : data) {
			if (line.startsWith("Shop:") || line.startsWith("Product:")) {
				shopAndProducts.add(line);
			}
		}
		
		return shopAndProducts;
	}
	
	public void updateProductData(String productId, String newData) {
		List<String> allData = readFile();
		List<String> updatedData = new ArrayList<>();
		
		for (String line : allData) {
			if (line.startsWith("Product:")) {
				String[] parts = line.substring(9).split(", ");
				if (parts[0].equals(productId)) {
					updatedData.add("Product: " + productId + ", " + newData);
				} else {
					updatedData.add(line);
				}
			} else {
				updatedData.add(line);
			}
		}
		
		// Write back to file
		writeFile(updatedData);
	}

	public void updateEmployeeData(String employeeId, String newData) {
		List<String> allData = readFile();
		List<String> updatedData = new ArrayList<>();
		
		for (String line : allData) {
			if (line.startsWith("Employee:")) {
				String[] parts = line.substring(10).split(", ");
				if (parts[0].equals(employeeId)) {
					updatedData.add("Employee: " + employeeId + "," + newData);
				} else {
					updatedData.add(line);
				}
			} else {
				updatedData.add(line);
			}
		}
		
		// Write back to file
		writeFile(updatedData);
	}
	
	public void removeProductData(String productId) {
		List<String> allData = readFile();
		List<String> updatedData = new ArrayList<>();
		
		for (String line : allData) {
			if (line.startsWith("Product:")) {
				String[] parts = line.substring(9).split(", ");
				if (!parts[0].equals(productId)) {
					updatedData.add(line);
				}
			} else {
				updatedData.add(line);
			}
		}
		
		// Write back to file
		writeFile(updatedData);
	}

	public void removeEmployeeData(String employeeId) {
		List<String> allData = readFile();
		List<String> updatedData = new ArrayList<>();
		
		for (String line : allData) {
			if (line.startsWith("Employee:")) {
				String[] parts = line.substring(10).split(", ");
				if (!parts[0].equals(employeeId)) {
					updatedData.add(line);
				}
			} else {
				updatedData.add(line);
			}
		}
		
		// Write back to file
		writeFile(updatedData);
	}

	public void removeShopData(String shopId) {
		List<String> allData = readFile();
		List<String> updatedData = new ArrayList<>();
		List<String> productsToRemove = new ArrayList<>();
		
		// First, find all products associated with this shop
		for (String line : allData) {
			if (line.startsWith("Product:")) {
				String[] parts = line.substring(9).split(", ");
				if (parts.length > 2 && parts[2].equals(shopId)) {
					productsToRemove.add(parts[0]);
				}
			}
		}
		
		// Then remove the shop and its associated products
		for (String line : allData) {
			if (line.startsWith("Shop:")) {
				String[] parts = line.substring(6).split(", ");
				if (!parts[0].equals(shopId)) {
					updatedData.add(line);
				}
			} else if (line.startsWith("Product:")) {
				String[] parts = line.substring(9).split(", ");
				if (!productsToRemove.contains(parts[0])) {
					updatedData.add(line);
				}
			} else {
				updatedData.add(line);
			}
		}
		
		// Write back to file
		writeFile(updatedData);
	}

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
    }
}

/*

public void updateShopData(String shopId, String newData) {
    List<String> allData = readFile();
    List<String> updatedData = new ArrayList<>();
    List<String> productsToRemove = new ArrayList<>();
    
    // First pass: identify all products belonging to this shop
    for (String line : allData) {
        if (line.startsWith("Product:")) {
            String[] parts = line.substring(9).split(", ");
            if (parts.length > 2 && parts[2].equals(shopId)) {
                productsToRemove.add(parts[0]);  // Store product IDs to remove
            }
        }
    }
    
    // Second pass: update shop data and remove associated products
    for (String line : allData) {
        if (line.startsWith("Shop:")) {
            String[] parts = line.substring(6).split(", ");
            if (parts[0].equals(shopId)) {
                updatedData.add("Shop: " + shopId + ", " + newData);
            } else {
                updatedData.add(line);
            }
        } else if (line.startsWith("Product:")) {
            String[] parts = line.substring(9).split(", ");
            boolean shouldKeep = true;
            for (String productId : productsToRemove) {
                if (parts[0].equals(productId)) {
                    shouldKeep = false;
                    break;
                }
            }
            if (shouldKeep) {
                updatedData.add(line);
            }
        } else {
            updatedData.add(line);
        }
    }
    
    writeFile(updatedData);
}
		// Update product
		updateProductData("P001", "P001, NewName, NewPrice, NewQuantity");

		// Update employee
		updateEmployeeData("E001", "E001, NewName, NewPosition, NewSalary");
		
        // Example usage with automatic ID generation
        List<String> newShopData = Arrays.asList("MyShop", "Location1", "Owner1");
        dbConnection.addShopData(newShopData);
        
        List<String> newProductData1 = Arrays.asList("Product1", "19.99", "100");
        dbConnection.addProductData(newProductData1, "MyShop");
        
        List<String> newProductData2 = Arrays.asList("Product2", "29.99", "50");
        dbConnection.addProductData(newProductData2, "MyShop");
        
        List<String> newUserData = Arrays.asList("TestUser", "test@email.com", "password123");
        dbConnection.addUserData(newUserData);
        
        List<String> newEmployeeData = Arrays.asList("John Doe", "Father Doe", "1995-01-01", 
            "60000", "123 Street", "555-1234", "john@example.com", "BSc", "Manager", "123456789");
        dbConnection.addEmployeeData(newEmployeeData);
        List<String> newShopData = Arrays.asList("301", "Shop1", "Location1", "Owner1");
        dbConnection.addShopData(newShopData);
        
        List<String> newProductData1 = Arrays.asList("401", "Product1", "19.99", "100");
        dbConnection.addProductData(newProductData1, "Shop1");
        
        List<String> newProductData2 = Arrays.asList("402", "Product2", "29.99", "50");
        dbConnection.addProductData(newProductData2, "Shop1");
		
        List<String> newUserData1 = Arrays.asList("201", "TestUser1", "test1@email.com", "password123");
        dbConnection.addUserData(newUserData1);
        
        List<String> newProductData1 = Arrays.asList("401", "TestProduct", "19.99", "100");
        dbConnection.addProductData(newProductData1);
        
        List<String> newProductData2 = Arrays.asList("301", "TestProduct", "19.99", "100");
        dbConnection.addProductData(newProductData2);
	
        List<String> newProductData3 = Arrays.asList("201", "TestProduct", "19.99", "100");
        dbConnection.addProductData(newProductData3);
        
        List<String> newUserData2 = Arrays.asList("202", "TestUser2", "test2@email.com", "password456");
        dbConnection.addUserData(newUserData2);

        List<String> newEmployeeData = Arrays.asList("107", "Test Employee", "TestFather Doe", "1995-01-01", "60000", "222 Test St", "555-8888", "test.employee@example.com", "BSc", "Tester", "890123456789");
        dbConnection.addEmployeeData(newEmployeeData);
		*/