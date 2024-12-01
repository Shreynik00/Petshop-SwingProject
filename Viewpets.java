import java.util.*;
import javax.swing.*;
import java.sql.*;
public class Viewpets extends JFrame {
    String data[][],head[]={"name","qty","cost","gender"};
    int size=0;
    ResultSet rs;
    Connection c=null;
    Statement stmt;
    JTable jt;
    JScrollPane sp;
    public Viewpets()
    {
        setTitle("View pets");
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
            String query="SELECT * from pet ;";
            stmt=c.createStatement();
            rs=stmt.executeQuery(query);   
            while(rs.next())
            {
                size++;
            }
            rs.close();
            data=new String[size][4];
            rs=stmt.executeQuery(query);
            int x=0;
            while(rs.next())
            {
                data[x][0]=rs.getString("name");
                data[x][1]=rs.getString("qty")+"";
                data[x][2]=rs.getString("cost")+"";
                data[x][3]=rs.getString("gender");
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

}
