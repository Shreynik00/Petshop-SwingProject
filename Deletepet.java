import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class Deletepet extends JFrame implements ActionListener {
    JTextField tn;
    JLabel ln,lm;
    JButton b;
    String data[][],head[]={"name","qty","cost","gender",},sql="";
    int size=0,i,k,f=0;
    ResultSet rs;
    PreparedStatement ps;
    Connection c =null;
    Statement stmt;
    JTable jf;
    JScrollPane sp;
    String query;
     public Deletepet() 
    {
        setTitle("Delete Pet");
        tn=new JTextField(20);
        tn.setBounds(100,10,150,20);

        ln= new JLabel("Pet name");
        ln.setBounds(10,10,250,20);
        lm= new JLabel("");
        lm.setBounds(10,10,250,20);

        b=new JButton("Delete Pets");
        b.setBounds(10,20,120,50);
        b.addActionListener(this);
        add(ln);
        add(lm);
        add(tn);
        add(b);
        try{
            Class.forName("org.sqlite.JDBC");
            c=DriverManager.getConnection("jdbc:sqlite:shop.db");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        try{
             query="SELECT * FROM pet;";
            stmt=c.createStatement();
            rs=stmt.executeQuery(query);
            while(rs.next()){
                size++;
            }
            
             data= new String[size][4];
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
             jf= new JTable(data,head);
             sp= new JScrollPane(jf);
             add(sp);
             setSize(550,650);
             setLayout(new FlowLayout());
             setVisible(true);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
            }
         catch (Exception o) {
            System.out.println(o);
            System.exit(0);
        }
        

    }
    public void actionPerformed( ActionEvent e)
    {
        String na= tn.getText();
        if(na.isEmpty())
        {
            lm.setText("Enter name pls");
        }
        else{
            f=0;
            for(i=0;i<size;i++)
            {
                for(k=0;k<4;k++)
                {
                    if(data[i][k].equals(na))
                    {
                        f=1;
                    }
                }
            }
            if(f==1)
            {
                try{
                    sql="DELETE FROM  pet WHERE name=?;";
                    PreparedStatement ps=c.prepareStatement(sql);
                    ps.setString(1,na);
                    ps.execute();
                    lm.setText("Pet:"+na+""+"Deleted");
                   // ln.setText("na");
                }
            catch (Exception c) {
                System.out.println(c);
                System.exit(0);
            }
            }
            else{
                lm.setText("Pet:"+na+""+"Not Found");
            }
        }
    }
    public static void main(String[] args) {
        Deletepet d = new Deletepet();
    }
}
