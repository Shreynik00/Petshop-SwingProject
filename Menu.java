import javax.swing.*;
import java.awt.event.*;
public class Menu  extends JFrame implements ActionListener{
    JMenuBar mb;
    JMenu m1,m2,m3;
    JMenuItem i1,i2,i3,i4,i5,i6,i7;
    public Menu()
    {
        setTitle("Pet -Menu");
       
        JLabel label = new JLabel();
        add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400,500);
        i1=new JMenuItem("ADD PET");
        i2=new JMenuItem("VIEW PETS");
        i3=new JMenuItem("DELETE PETS");
        i4=new JMenuItem("MODIFY PETS");
        i5=new JMenuItem("EXIT");
        i6=new JMenuItem("SELL PETS");
        i7=new JMenuItem("SELL REPORT");

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);
      
        
        mb=new JMenuBar();
        m1=new JMenu("pets");
        m2=new JMenu("sell");
        m3=new JMenu("Report");
        m1.add(i1);
        m1.add(i2);
        m1.add(i3);
        m1.add(i4);
        m1.add(i4);
        m1.add(i5);
        m2.add(i6);
        m3.add(i7);
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        add(mb);
        setJMenuBar(mb);
    }   
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==i1)
        {
          new Addpet();
        }
        if(e.getSource()==i2)
        {
           new Viewpets();
        }
        if(e.getSource()==i3)
        {
            new Deletepet();
        }
        if(e.getSource()==i4)
        {
            new Modifypet();
        }
        if(e.getSource()==i5)
        {
           System.exit(0);
        }
        if(e.getSource()==i6)
        {
           new Sellpets();
        }
        if(e.getSource()==i7)
        {
          new Sellreport();
        }
    }
   
  
 
}
