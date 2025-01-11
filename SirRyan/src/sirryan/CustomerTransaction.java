/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import finalproject.Login;
import static finalproject.Search.col;
import static finalproject.Search.model;
import finalproject.UserMenu;
import finalproject.db.Database;
import finalproject.db.Transaction;
import finalproject.db.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maikaordonez
 */
public class CustomerTransaction {
    JFrame frame = new JFrame("TRANSACTION DETAILS");
    static String[] col = {"Serial No.","Account No.","Transfer to Account ID","Date and Time","Type", "Amount"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header1 = new JLabel("CUSTOMER TRANSACTIONS");
    JLabel accId = new JLabel("ACCOUNT NO:");
    JButton btnSearch = new JButton();
    JButton btnBack = new JButton("Back");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);
    JTextField txtAccId = new JTextField();
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel background = new JLabel("", image, JLabel.CENTER);
    
    CustomerTransaction(){
        
        String[][] tableData = new String[Database.transactions().size()][6];
        for (int i = 0; i < Database.transactions().size(); i++) {
            tableData[i] = Database.transactions().get(i).toAllArray();
        }
        
        model.setDataVector(tableData, col);
        
        header1.setBounds(170, 30, 500, 50);
        header1.setFont(new Font("Times New Roman", Font.BOLD, 35));
        header1.setForeground(Color.WHITE);
        background.add(header1);

        accId.setBounds(100, 110, 150, 20);
        accId.setFont(new Font(null, Font.BOLD, 15));
        accId.setForeground(Color.WHITE);
        background.add(accId);
        
        txtAccId.setBounds(220, 110, 300, 20);
        background.add(txtAccId);
        
        btnSearch.setBounds(535, 110, 100, 20);
        btnSearch.setText("SEARCH");
        btnSearch.setFocusable(false);
        background.add(btnSearch);

        btnBack.setBounds(590, 420, 110, 20);
        background.add(btnBack);

        sp.setBounds(100, 150, 600, 250);
        jt.setModel(model);
        background.add(sp);

        background.setBounds(0,0,800,500);
        
        frame.add(background);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        txtAccId.addActionListener(getActionListener());
        btnSearch.addActionListener(getActionListener());

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminMenu();
                frame.dispose();
            }
        });
    }
        
        private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accId = txtAccId.getText();

                if (accId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an account ID.");
                    return;
                }

                if (accId.length() != 8) {
                    JOptionPane.showMessageDialog(null, "Account ID must be 8 digits.");
                    return;
                }

                User user = Database.getUser(accId);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                    return;
                }

                ArrayList<Transaction> userTransactions = Database.getUserTransactions(accId);
                
                if (userTransactions == null || userTransactions.size() == 0) {
                JOptionPane.showMessageDialog(null, "No transactions found.");
                frame.dispose();
                new CustomerTransaction();
                return;
                }
                
                String[][] tableData = new String[userTransactions.size()][6];
                for (Transaction t : userTransactions) {
                    tableData[userTransactions.indexOf(t)] = t.toAllArray();
                }
                model.setDataVector(tableData, col);
            }
        };
        
    }
}