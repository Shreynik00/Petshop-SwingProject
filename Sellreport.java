
import java.util.*;
import javax.swing.*;
import java.sql.*;
public class Sellreport extends JFrame {
    String data[][],head[]={"Pet","gender","cost","qty","customer","date"};
    int size=0;
    ResultSet rs;
    Connection c=null;
    Statement stmt;
    JTable jt;
    JScrollPane sp;
    public Sellreport()
    {
        setTitle("Sales report ");
        try{
             Class.forName("org.sqlite.JDBC");
        c= DriverManager.getConnection("jdbc:sqlite:shop.db");
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
        try{
            String query="SELECT * from sell ;";
            stmt=c.createStatement();
            rs=stmt.executeQuery(query);   
            while(rs.next())
            {
                size++;
            }
            rs.close();
            data=new String[size][6];
            rs=stmt.executeQuery(query);
            int x=0;
            while(rs.next())
            {
                data[x][0]=rs.getString("name");
                data[x][1]=rs.getString("gender")+"";
                data[x][2]=rs.getString("cost")+"";
                data[x][3]=rs.getString("qty")+"";
                data[x][4]=rs.getString("customer")+"";
                data[x][5]=rs.getString("date");
                x++;
            } 
            jt=new JTable(data,head);
            jt.setBounds(30,40,400,900);
            sp=new JScrollPane(jt);
            add(sp);
            setSize(400,500) ;
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                        
        }
        catch (Exception u)
        {
            System.out.println(u);
        }

    }
 public static void main(String[] args) {
    Sellreport s = new Sellreport();
 }
}
