 import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener {
    JTextField tu,tp;
    String in=null,ps=null;
    JLabel lu,lp,lm;
    JButton b;
    ImageIcon e;
    Connection c =null;
    Statement stmt;
    ResultSet rs;
    public Login()
    {
        setTitle("Login");
        e=new ImageIcon("login.jpeg");///login phtot
        tu=new JTextField();
        tu.setBounds(100,10,150,20);
        tp=new JTextField();
        tp.setBounds(100,50,150,20);
        lu=new JLabel("User name");
        lu.setBounds(10,10,250,20);
        lp= new JLabel("Password");
        lp.setBounds(10,50,250,20);
        lm= new JLabel("");
        lm.setBounds(100,80,250,20);
        b= new JButton(e);
        b.setBounds(50,100,170,70);
        b.addActionListener(this);
        add(b);
        add(tu);
        add(lu);
        add(tp);
        add(lp);
        add(lm);
        setSize(700,400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    try{
        Class.forName("org.sqlite.JDBC");
        c=DriverManager.getConnection("jdbc:sqlite:shop.db");
    }
    catch (Exception e)
    {
        System.out.println("error-"+e);
        System.exit(0);

    }
} 
public void actionPerformed( ActionEvent e)
{
 in=tu.getText();
 ps=tp.getText();
 if(in.isEmpty())
 {
    lm.setText("Enter username");
 }
 else if(ps.isEmpty())
 {
    lm.setText("Enter password");
 }
 else
{
    try{ 
    String query="SELECT * from valid";
    stmt=c.createStatement();
    rs=stmt.executeQuery(query);
    String a=rs.getString("name");
    String b=rs.getString("pass");
    if(a.equals(in)&& b.equals(ps))
    {
        lm.setText("Welcome"+in);
        stmt.close();
        c.close();
        setVisible(false);
       Menu m = new Menu();

    }
    else{
        if(!a.equals(in)){ 
        lm.setText("Wrong user name");
        }
        if( !b.equals(ps))
        {
            lm.setText("Wrong password");
        }
}
    }
    catch (Exception u)
    {
        System.out.println(u);
    }
 }
}
}

