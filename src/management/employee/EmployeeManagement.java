package management.employee;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import frame.*;

public class EmployeeManagement extends JFrame implements ActionListener {

    JButton view, add, update, remove, logout;

    public EmployeeManagement() {
        Font f1 = new Font("Arial", Font.BOLD, 18);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 = new Font("Arial", Font.BOLD, 13);

        Color DARK_GREEN = new Color(0, 153, 0);

        Cursor crsr = new Cursor(Cursor.HAND_CURSOR);
        LineBorder lineBorder = new LineBorder(Color.black, 1, true);

        setVisible(true);
        setBounds(200, 15, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        double w = getWidth();
        int h = (int) getHeight();

        JLabel heading = new JLabel("Employee Management");
        heading.setBounds((int) (((1.5 * w) - 350) / 2), (h - 650), 350, 100);
        heading.setFont(f2);
        heading.setForeground(DARK_GREEN);
        add(heading);

        add = new JButton("Add Employee");
        add.setBounds((int) (((1.5 * w) - 193) / 2), 180, 230, 50);
        add.setFont(f1);
        add.setBackground(DARK_GREEN);
        add.setForeground(Color.WHITE);
        add.setCursor(crsr);
        add.addActionListener(this);
        add(add);

        view = new JButton("View Employees");
        view.setBounds((int) (((1.5 * w) - 193) / 2), 260, 230, 50);
        view.setFont(f1);
        view.setBackground(DARK_GREEN);
        view.setForeground(Color.WHITE);
        view.setCursor(crsr);
        view.addActionListener(this);
        add(view);

        update = new JButton("Update Employee");
        update.setBounds((int) (((1.5 * w) - 193) / 2), 340, 230, 50);
        update.setFont(f1);
        update.setBackground(DARK_GREEN);
        update.setForeground(Color.WHITE);
        update.setCursor(crsr);
        update.addActionListener(this);
        add(update);

        remove = new JButton("Remove Employee");
        remove.setBounds((int) (((1.5 * w) - 193) / 2), 420, 230, 50);
        remove.setFont(f1);
        remove.setBackground(DARK_GREEN);
        remove.setForeground(Color.WHITE);
        remove.setCursor(crsr);
        remove.addActionListener(this);
        add(remove);

        logout = new JButton("Logout");
        logout.setBounds((int) (((1.5 * w) - 193) / 2), 500, 230, 50);
        logout.setFont(f1);
        logout.setBackground(DARK_GREEN);
        logout.setForeground(Color.WHITE);
        logout.setCursor(crsr);
        logout.addActionListener(this);
        add(logout);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveEmployee();
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new Login();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagement();
            }
        });
    }
}
