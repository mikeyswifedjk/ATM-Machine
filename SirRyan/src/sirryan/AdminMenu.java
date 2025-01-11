/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import java.util.logging.Level;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class AdminMenu {
    JFrame frame = new JFrame();
    JButton createAcc = new JButton();
    JButton updateAcc = new JButton();
    JButton deleteAcc = new JButton();
    JButton searchAcc = new JButton();
    JButton withdraw = new JButton();
    JButton transfer = new JButton();
    JButton deposit = new JButton();
    JButton locked = new JButton();
    JButton report = new JButton();
    JButton exit = new JButton();
    JButton customertransact = new JButton();
    ImageIcon image = new ImageIcon("pic6.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    public static Connection getConnection() {
        Connection conn;
        String url = "jdbc:mysql://localhost:3306/atm";
        String username = "root";
        String password = "ybizamaika";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return conn;
    }
    
    AdminMenu() {
        createAcc.setBounds(130, 50, 250, 50);
        createAcc.setText("CREATE ACCOUNT");
        createAcc.setFocusable(false);

        transfer.setBounds(430, 290, 250, 50);
        transfer.setText("TRANSFER");
        transfer.setFocusable(false);

        withdraw.setBounds(430, 210, 250, 50);
        withdraw.setText("WITHDRAW");
        withdraw.setFocusable(false);

        deposit.setBounds(430, 130, 250, 50);
        deposit.setText("DEPOSIT");
        deposit.setFocusable(false);

        updateAcc.setBounds(130, 290, 250, 50);
        updateAcc.setText("UPDATE ACCOUNT");
        updateAcc.setFocusable(false);

        deleteAcc.setBounds(130, 370, 250, 50);
        deleteAcc.setText("DELETE ACCOUNT");
        deleteAcc.setFocusable(false);

        searchAcc.setBounds(130, 130, 250, 50);
        searchAcc.setText("CUSTOMER DETAILS");
        searchAcc.setFocusable(false);

        locked.setBounds(130, 450, 250, 50);
        locked.setText("LOCKED ACCOUNTS");
        locked.setFocusable(false);

        customertransact.setBounds(130, 210, 250, 50);
        customertransact.setText("CUSTOMER TRANSACTION");
        customertransact.setFocusable(false);
        
        report.setBounds(430, 50, 250, 50);
        report.setText("PRINT REPORT");
        report.setFocusable(false);
        
        exit.setBounds(430, 370, 250, 50);
        exit.setText("EXIT");
        exit.setFocusable(false);
        
//        createAcc.setBounds(130, 50, 250, 50);
//        createAcc.setText("CREATE ACCOUNT");
//        createAcc.setFocusable(false);
//
//        transfer.setBounds(430, 290, 250, 50);
//        transfer.setText("TRANSFER");
//        transfer.setFocusable(false);
//
//        withdraw.setBounds(430, 210, 250, 50);
//        withdraw.setText("WITHDRAW");
//        withdraw.setFocusable(false);
//
//        deposit.setBounds(430, 130, 250, 50);
//        deposit.setText("DEPOSIT");
//        deposit.setFocusable(false);
//
//        updateAcc.setBounds(130, 290, 250, 50);
//        updateAcc.setText("UPDATE ACCOUNT");
//        updateAcc.setFocusable(false);
//
//        deleteAcc.setBounds(130, 370, 250, 50);
//        deleteAcc.setText("DELETE ACCOUNT");
//        deleteAcc.setFocusable(false);
//
//        searchAcc.setBounds(130, 130, 250, 50);
//        searchAcc.setText("CUSTOMER DETAILS");
//        searchAcc.setFocusable(false);
//
//        locked.setBounds(430, 50, 250, 50);
//        locked.setText("LOCKED ACCOUNTS");
//        locked.setFocusable(false);
//
//        customertransact.setBounds(130, 210, 250, 50);
//        customertransact.setText("CUSTOMER TRANSACTION");
//        customertransact.setFocusable(false);
//                
//        exit.setBounds(430, 370, 250, 50);
//        exit.setText("EXIT");
//        exit.setFocusable(false);

        label.setBounds(0, 0, 800, 570);
        label.add(report);
        label.add(customertransact);
        label.add(createAcc);
        label.add(updateAcc);
        label.add(deleteAcc);
        label.add(searchAcc);
        label.add(transfer);
        label.add(deposit);
        label.add(withdraw);
        label.add(locked);
        label.add(exit);

        frame.add(label);
        frame.setSize(800, 570);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        // action listeners
        createAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CreateAccount();
            }
        });

        updateAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UpdateAccount(true);
            }
        });

        searchAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search();
            }
        });

        deleteAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DeleteAccount();
            }
        });

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Transfer(true);
            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Deposit(true);
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Withdraw(true);
            }
        });

        locked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LockedAccounts();
            }
        });

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new Report();
            } 
        });
        
//        report.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {                     
//                Connection conn = Database.getConnection();
//                JasperDesign design = JRXmlLoader.load("src//sirryan//report//DayTransactionReport.jrxml");
//                String query = "SELECT * FROM transactions";
//                JRDesignQuery update = new JRDesignQuery();
//                update.setText(query);
//                design.setQuery(update);
//                
//                JasperReport customerReport = JasperCompileManager.compileReport(design);               
//                JasperPrint print = JasperFillManager.fillReport(customerReport, null, conn);
//                JasperViewer.viewReport(print, false);
//                }
//                catch(JRException ex){
//                    Logger.getLogger(AdminMenu.class.getName()).log(Level.SEVERE, null, ex);                   
//                }
//            }
//        });
        

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login();
            }
        });
        
        customertransact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerTransaction();
            }
        });
    }
}
