package management.employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import management.validation.DBManager;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back;
    DBManager DBManager;

    public ViewEmployee() {
        DBManager = new DBManager();
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20, 20, 250, 20);
        add(searchlbl);
        
        cemployeeId = new Choice();
        cemployeeId.setBounds(300, 20, 150, 20);
        add(cemployeeId);
        
        // Populate employee IDs from the database
        List<String> employeeData = DBManager.readEmployeeData();
        for (String data : employeeData) {
            String[] details = data.split(",");
            cemployeeId.add(details[0]); // Assuming the first column is employee ID
        }
        
        table = new JTable();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(20, 70, 120, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(160, 70, 120, 20);
        print.addActionListener(this);
        add(print);
        
        update = new JButton("Update");
        update.setBounds(300, 70, 120, 20);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBounds(440, 70, 120, 20);
        back.addActionListener(this);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String empId = cemployeeId.getSelectedItem();
            List<String> employeeData = DBManager.readEmployeeData();
            String[] columnNames = {"Employee ID", "Name", "Father's Name", "DOB", "Salary", "Address", "Phone", "Email", "Education", "Designation", "Another Number"};
            String[][] data = new String[1][11];
            
            for (String dataLine : employeeData) {
                String[] details = dataLine.split(",");
                if (details[0].equals(empId)) {
                    data[0] = details;
                    break;
                }
            }
            
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else {
            setVisible(false);
            new EmployeeManagement();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new ViewEmployee();
            }
        });
    }
}
