import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Addpet extends JFrame implements ActionListener {
    JTextField tn ,tmo,tnw,tg;
    JLabel lm,ln,lmo,lnw,lg;
    JButton b;
    String data[][],head[]={"name","qty","cost","gender"},sql="";
    int size=0,i,k,f=0;
    ResultSet rs;
    Connection c=null;
    Statement stmt;
   private PreparedStatement pst;
    JTable jt;
    JScrollPane sp;
  public  Addpet()
    {
        setTitle("Add pet");
        ln=new JLabel("Pet Name"); 
        ln.setBounds(10,10,250,20);
        tn=new JTextField(15);
        tn.setBounds(100,10,150,20);
     
        lmo=new JLabel("qty");
        lmo.setBounds(200,700,350,20);
        tmo=new JTextField(10);
        tmo.setBounds(200,300,350,20);
     
        lnw = new JLabel("cost");
        lnw.setBounds(10,10,250,20);
        tnw=new JTextField(10);
        tnw.setBounds(100,10,150,20);

        lg = new JLabel("gender");
        lg.setBounds(10,10,250,20);
        tg=new JTextField(10);
        tg.setBounds(100,10,150,20);

       

        lm = new JLabel("");
        lm.setBounds(10,10,250,20);

        b= new JButton("ADD Pet");
        b.setBounds(10,10,250,20);
        b.addActionListener(this);

        add(lm);
        add(ln);
        add(tn);
        add(lmo);
        add(tmo);

        add(lg);
        add(tg);
        add(lnw);
        add(tnw);
        add(b);
         
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:shop.db");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        
try{ 

String query= "SELECT * FROM pet;";
stmt= c.createStatement();
rs=stmt.executeQuery (query);
 while(rs.next())
 { 
size++;

}
rs.close();
data = new String[size][4];
rs= stmt.executeQuery(query);
int x=0; 
while(rs.next())
{ 
data[x][0]=rs.getString("name");
data[x][1]=rs.getInt("qty")+""; 
data[x][2]=rs.getFloat("cost")+"";
data[x][3]=rs.getString("gender");
x++;
}
jt=new JTable(data, head);
sp=new JScrollPane (jt);
add(sp);
setSize(950,458);
setLayout(new FlowLayout());
setVisible(true);
setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    }
    catch (Exception e)
    {
        System.out.println(e);
    }
}

public void actionPerformed (ActionEvent e)
{
String nn=tn.getText();//name
 String nq=tmo.getText();//qty
  String nc=tnw.getText(); //cost
  String ng =tg.getText();//gender
  if(nn.isEmpty())
  {

   lm.setText("Enter pet name");
  }
else if(nq.isEmpty())
{ 
lm.setText("Enter pet qty");
}
else if(nc.isEmpty())
{ 
lm.setText("Enter pet cost");
}
else if(ng.isEmpty())
{ 
lm.setText("Enter pet gender");
}
else
{ 

 try{
     String sql = " INSERT INTO pet ( name , qty , cost , gender) VALUES(?,?,?,?); ";
  PreparedStatement pst=c.prepareStatement(sql); 
    
    pst.setString(1,nn);
     pst.setString(2,nq);
     pst.setString(3,nc);     
     pst.setString(4,ng);
    pst.execute();
}
catch(Exception r)
{
    r.printStackTrace();
    System.out.println(r);
}
 } 
}
  public static void main(String[] args) {
    Addpet a = new Addpet();
  }
}
