package management.employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import management.validation.DBManager;
import management.validation.OracleDB;


public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tffname, tfaddress, tfphone, tfaadhar, tfemail, tfsalary, tfdesignation, lblname, lbldob, lblaadhar;
    JLabel lblempId;
    JButton add, back;
    String empId;
    JComboBox tfeducation;
    DBManager DBManager;
	private String path = System.getProperty("user.dir");  
	private String background = (path.substring(0, path.length() - 3) + "pic\\Background.jpg");

    public UpdateEmployee(String empId) {
        this.empId = empId;
        DBManager = new DBManager();
        //getContentPane().setBackground(Color.WHITE);
        setLayout(null);
		
		JLabel l = new JLabel(new ImageIcon(background));
		setContentPane(l);

        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        lblname = new JTextField();
        lblname.setBounds(200, 150, 150, 30);
        add(lblname);

        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(400, 150, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        add(tffname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);

        lbldob = new JTextField();
        lbldob.setBounds(200, 200, 150, 30);
        add(lbldob);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(400, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(600, 200, 150, 30);
        add(tfsalary);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(400, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(400, 300, 150, 30);
        labeleducation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeleducation);

        String courses[] = {"BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        tfeducation = new JComboBox(courses);
        tfeducation.setBounds(600, 300, 150, 30);
        add(tfeducation);

        JLabel labeldesignation = new JLabel("Designation");
        labeldesignation.setBounds(50, 350, 150, 30);
        labeldesignation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(200, 350, 150, 30);
        add(tfdesignation);

        JLabel labelaadhar = new JLabel("Another Number");
        labelaadhar.setBounds(400, 350, 150, 30);
        labelaadhar.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelaadhar);

        lblaadhar = new JTextField();
        lblaadhar.setBounds(600, 350, 150, 30);
        add(lblaadhar);

        JLabel labelempId = new JLabel("Employee id");
        labelempId.setBounds(50, 400, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempId);

        lblempId = new JLabel(empId);
        lblempId.setBounds(200, 400, 150, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblempId);

        add = new JButton("Update Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        // Populate fields with existing data
        List<String> employeeData = DBManager.readEmployeeData();
        for (String data : employeeData) {
            String[] details = data.split(",");
            if (details[0].equals(empId)) {
                for (int i = 0; i < details.length; i++) {
                    System.out.println("Detail " + i + ": " + details[i]);
                }
                if (details.length >= 11) {
                    lblname.setText(details[1]);
                    tffname.setText(details[2]);
                    lbldob.setText(details[3]);
                    tfsalary.setText(details[4]);
                    tfaddress.setText(details[5]);
                    tfphone.setText(details[6]);
                    tfemail.setText(details[7]);
                    tfeducation.setSelectedItem(details[8]);
                    tfdesignation.setText(details[9]);
                    lblaadhar.setText(details[10]);
                } else {
                    JOptionPane.showMessageDialog(this, "Employee data is incomplete for ID: " + empId + "\nData: " + data);
                }
                break;
            }
        }

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = lblname.getText();
            String fname = tffname.getText();
            String dob = lbldob.getText();
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) tfeducation.getSelectedItem();
            String designation = tfdesignation.getText();
            String aadhar = lblaadhar.getText();

            String updatedDetails = name + "," + fname + "," + dob + "," + salary + "," + address + "," + phone + "," + email + "," + education + "," + designation + "," + aadhar;

            System.out.println("Employee ID: " + empId);
            System.out.println("Updated Employee Data: " + updatedDetails);
            
            try {
                DBManager.updateEmployeeData(empId,updatedDetails);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error updating employee details: " + e.getMessage());
                e.printStackTrace();
                return;
            }

            JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
            setVisible(false);
            new EmployeeManagement();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new EmployeeManagement();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdateEmployee("");
            }
        });
    }
}