import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Sellpets extends JFrame implements ActionListener {
    JTextField tpn, tpg, tpq, tcn;
    JLabel lpn, lpg, lpq, lcn, lm;
    JButton b;
    String data[][], head[] = {"name", "qty", "cost", "gender"}, sql = "";
    Date date;
    int size = 0, i, k, f = 0, q, aq, a;
    float cost;

    ResultSet rs;
    Connection c = null;
    Statement stmt;
    PreparedStatement ps;
    JTable jt;
    JScrollPane sp;

    public Sellpets() {
        setTitle("SELL PETS");

        
        tpn = new JTextField(20);
        tpg = new JTextField(10);
        tpq = new JTextField(10);
        tcn = new JTextField(10);
        lpn = new JLabel("PET - NAME");
        lpg = new JLabel("PET GENDER");
        lpq = new JLabel("QUANTITY");
        lcn = new JLabel("CUSTOMER");
        lm = new JLabel("");
        b = new JButton("Sell");

      
        lpn.setBounds(10, 10, 250, 20);
        tpn.setBounds(100, 10, 150, 20);
        lpg.setBounds(10, 40, 250, 20);
        tpg.setBounds(100, 40, 150, 20);
        lpq.setBounds(10, 70, 250, 20);
        tpq.setBounds(100, 70, 150, 20);
        lcn.setBounds(10, 100, 250, 20);
        tcn.setBounds(100, 100, 150, 20);
        lm.setBounds(10, 130, 300, 50);
        b.setBounds(10, 160, 100, 30);

      
        add(lpn);
        add(tpn);
        add(lpg);
        add(tpg);
        add(lpq);
        add(tpq);
        add(lcn);
        add(tcn);
        add(b);
        add(lm);

      
        b.addActionListener(this);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:shop.db");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        try {
            String query = "SELECT * FROM pet;";
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                size++;
            }
            rs.close();
            data = new String[size][4];
            rs = stmt.executeQuery(query);
            int x = 0;
            while (rs.next()) {
                data[x][0] = rs.getString("name");
                data[x][1] = rs.getString("qty") + "";
                data[x][2] = rs.getString("cost") + "";
                data[x][3] = rs.getString("gender");
                x++;
            }
            jt = new JTable(data, head);
            sp = new JScrollPane(jt);
            add(sp);
            setSize(550, 650);
            setLayout(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception u) {
            System.out.println(u);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String pn = tpn.getText();
        String pg = tpg.getText();
        String pq = tpq.getText();
        String cn = tcn.getText();
        if (pn.isEmpty()) {
            lm.setText("Enter pet Name");
        } else if (pg.isEmpty()) {
            lm.setText("Enter Gender");
        } else if (pq.isEmpty()) {
            lm.setText("Enter Quantity");
        } else if (cn.isEmpty()) {
            lm.setText("Enter Customer name");
        } else {
            f = 0;
            aq = Integer.parseInt(pq);
            for (i = 0; i < size; i++) {
                q = Integer.parseInt(data[i][1].trim());
                cost = Float.parseFloat(data[i][2]);
                if (data[i][0].equals(pn) && data[i][3].equals(pg) && q >= aq) {
                    f = 1;
                    break;
                }
            }
            if (f == 1) {
                try {
                    a = q - aq;
                    if (a > 0) {
                        sql = "UPDATE pet SET qty=? WHERE name=?;";
                        ps = c.prepareStatement(sql);
                        ps.setInt(1, a);
                        ps.setString(2, pn);
                        ps.execute();
                    } else {
                        sql = "DELETE FROM pet WHERE name=?;";
                        ps = c.prepareStatement(sql);
                        ps.setString(1, pn);
                        ps.execute();
                    }
                    sql = "INSERT INTO sell VALUES (?, ?, ?, ?, ?, ?);";
                    ps = c.prepareStatement(sql);
                    cost = cost * aq;
                    date = new Date();
                    
                    ps.setString(1, pn);
                    ps.setString(2, pg);
                    ps.setFloat(3, cost);
                    ps.setInt(4, aq);
                    ps.setString(5, cn);
                    ps.setString(6, date.toString().substring(0, 10));
                    ps.execute();
                    ps.close();
                    c.close();
                } catch (Exception n) {
                    System.out.println(n);
                }
                lm.setText("Pet " + pn + " found and sold");
            } else {
                lm.setText("Pet " + pn + " not found");
            }
            tpn.setText("");
            tpq.setText("");
            tpg.setText("");
            tcn.setText("");
        }
    }

   public static void main(String[] args) {
    Sellpets ov = new Sellpets();
   }
}
