package finalproject;

import java.sql.Connection;
import finalproject.db.Database;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JButton;
import static javax.swing.text.html.HTML.Tag.SELECT;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Report {

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
    
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel();
    JButton daily = new JButton();
    JButton weekly = new JButton();
    JButton monthly = new JButton();
    JButton back = new JButton("BACK");
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel background = new JLabel("", image, JLabel.CENTER);

    Report() {

        label1.setBounds(140, 8, 650, 90);
        label1.setText("JJTM TRANSACTION REPORT");
        label1.setFont(new Font(null, Font.BOLD, 40));
        label1.setForeground(Color.WHITE);
        
        daily.setBounds(50, 110, 250, 50);
        daily.setText("TODAY'S TRANSACTION REPORT");
        daily.setFocusable(false);
        
        weekly.setBounds(310, 110, 250, 50);
        weekly.setText("WEEKLY TRANSACTION REPORT");
        weekly.setFocusable(false);
        
        monthly.setBounds(570, 110, 250, 50);
        monthly.setText("MONTHLY TRANSACTION REPORT");
        monthly.setFocusable(false);
        
        back.setBounds( 700, 190, 110, 30);
        back.setFocusable(false);
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);

        background.add(label1);
        background.add(daily);
        background.add(weekly);
        background.add(monthly);
        background.add(back);
        background.setBounds(0, 0, 870, 270);

        frame.add(background);
        frame.setSize(870, 270);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        daily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = Database.getConnection();
                    JasperDesign design = JRXmlLoader.load("src//sirryan//report//DayTransactionReport.jrxml");
                    String query = "SELECT * FROM transactions WHERE DATE_FORMAT(curdate() , \"%M %e &Y\") = DATE_FORMAT(datetime, \"%M %e &Y\")"; 
                    JRDesignQuery update = new JRDesignQuery();
                    update.setText(query);
                    design.setQuery(update);

                    JasperReport customerReport = JasperCompileManager.compileReport(design);
                    JasperPrint print = JasperFillManager.fillReport(customerReport, null, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(AdminMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        weekly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = Database.getConnection();
                    JasperDesign design = JRXmlLoader.load("src//sirryan//report//WeeklyTransactionReport.jrxml");
                    String query = "SELECT * FROM transactions WHERE dayofweek(datetime) BETWEEN 1 AND  7 having week(datetime) = week(curdate());"; 
                    JRDesignQuery update = new JRDesignQuery();
                    update.setText(query);
                    design.setQuery(update);

                    JasperReport customerReport = JasperCompileManager.compileReport(design);
                    JasperPrint print = JasperFillManager.fillReport(customerReport, null, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(AdminMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        monthly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = Database.getConnection();
                    JasperDesign design = JRXmlLoader.load("src//sirryan//report//MonthlyTransactionReport.jrxml");
                    String query = "SELECT * FROM transactions WHERE MONTHNAME(CURDATE()) =  DATE_FORMAT(datetime, \"%M\");"; 
                    JRDesignQuery update = new JRDesignQuery();
                    update.setText(query);
                    design.setQuery(update);

                    JasperReport customerReport = JasperCompileManager.compileReport(design);
                    JasperPrint print = JasperFillManager.fillReport(customerReport, null, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(AdminMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        back.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new AdminMenu();
            }
        });
    }
}
