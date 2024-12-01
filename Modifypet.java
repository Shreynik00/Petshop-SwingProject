import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Modifypet extends JFrame implements ActionListener {
    JTextField tn ,tmo,tnw;
    JLabel lm,ln,lmo,lnw;
    JButton b;
    String data[][],head[]={"name","qty","cost","gender"},sql="";
    int size=0,i,k,f=0;
    ResultSet rs;
    Connection c=null;
    Statement stmt;
 
    JTable jt;
    JScrollPane sp;
  public  Modifypet ()
    {
        setTitle("Modifypets");
        ln=new JLabel("Pet Name"); 
        ln.setBounds(10,10,250,20);
        tn=new JTextField(15);
        tn.setBounds(100,10,150,20);
     
        lmo=new JLabel("Field to modify");
        lmo.setBounds(10,10,250,20);
        tmo=new JTextField(10);
        tmo.setBounds(10,10,250,20);
     
        lnw = new JLabel("New Value");
        lnw.setBounds(10,10,250,20);
        tnw=new JTextField(10);
        tnw.setBounds(100,10,150,20);

       

        lm = new JLabel("");
        lm.setBounds(10,10,250,20);

        b= new JButton("Modify Pets");
        b.setBounds(10,10,250,20);
        b.addActionListener(this);

        add(lm);
        add(ln);
        add(tn);
        add(lmo);
        add(tmo);

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
setSize(850,600);
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
String na=tn.getText();
 String nm=tmo.getText();
  String nv=tnw.getText(); 
  if(na.isEmpty())
  {

   lm.setText("Empty");
  }
else if(nm.isEmpty())
{ 
lm.setText("Enter field");
}
else if(nv.isEmpty())
{ 
lm.setText("Enter a new value");
}
else
{ 
f=0;
for (i=0;i<size; i++)
{ 
  for (k=0;k<3; k++)
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
    if(nm.equals("qty"))
    { 
     String sql = "UPDATE pet SET qty=? WHERE name=?; ";
  PreparedStatement ps=c.prepareStatement(sql); 
     int v=Integer.parseInt(nv);
    ps.setInt(1,v);
     ps.setString(2,na);
     ps.executeUpdate();
    }


    else if(nm.equals("cost"))
    { 
      String sql = "UPDATE pet SET cost=? WHERE name=?;";
   PreparedStatement ps=c.prepareStatement(sql);
    float p=Float.parseFloat(nv);
    ps.setFloat (1,p); 
    ps.setString(2,na);
    ps.executeUpdate();
    }
    else if(nm.equals("gender"))
    {
     String sql = "UPDATE pet SET gender=? WHERE name = ?;";
     PreparedStatement    ps=c.prepareStatement (sql);
      ps.setString(1, nv);
       ps.setString(2,na);
       ps.executeUpdate();
    }
    
    lm.setText("PET" +""+na+""+" UPDATED !!!");
    tn.setText("");
    tmo.setText("");
    tnw.setText("");
}
catch (Exception r) {

    System.out.println(r);
}
 }
 else{
    lm.setText("Pet"+""+na+""+"Not found");
    tn.setText("");
    tmo.setText("");
    tnw.setText("");
 }
}
}
public static void main(String[] args) {
    Modifypet ob= new Modifypet();
}
}
