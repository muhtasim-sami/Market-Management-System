# Market Management System

A Java-based desktop application for managing products, employees, shops, and billing in a market environment. The system uses file storage and Oracle Database for persistent data management.

## Features

- **Product Management:** Add, update, delete, and manage product inventory.
- **Employee Management:** Add, update, and manage employee records.
- **Shop Management:** Manage shop details and assignments.
- **Billing System:** Generate bills, update product quantities, and handle payments.
- **Oracle Database Integration:** Synchronize data with an Oracle database.
- **User Authentication:** Manage users and roles.

## Technologies Used

- Java (Swing for GUI)
- Oracle Database (JDBC)
- File-based data storage
- MVC design pattern

## Setup

1. **Clone the repository:**
    ```sh
    git clone https://github.com/muhtasim-sami/Market-Management-System
    cd MarketManagementSystem
    ```

2. **Configure Oracle Database:**
    - Update the Oracle connection settings in `src/management/validation/OracleDB.java`:
      ```java
      private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
      private static final String DB_USER = "system";
      private static final String DB_PASS = "admin";
      ```
    - Make sure your Oracle DB is running and accessible.

3. **Compile the project:**

- **Windows users:**  
  Go to the `run` directory and double-click `run.bat` to compile and run the project.

- **Linux/macOS users:**  
  Go to the `run` directory, make the script executable, and run `run.sh` in your terminal:
  ```sh
  chmod +x run.sh
  ./run.sh
  ```

  > **Note:**  
  > The provided `run.sh` script uses Unix line endings and the correct classpath separator (`:`), so it works on both Linux and macOS.


## Usage

- Launch the application and log in.
- Use the menu to manage products, employees, shops, and billing.
- All changes are saved to both files and the Oracle database.

## Notes

- Ensure Oracle JDBC driver (`ojdbc11.jar`) is in the `lib` directory.
- Update database credentials as needed.
- Data files are stored in the project directory.

## License

This project is for educational purposes.

---

