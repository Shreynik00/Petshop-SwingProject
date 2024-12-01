import javax.swing.*;
public class Petshop extends JFrame {
    JLabel e;
    public Petshop()
    {
        setTitle("Pet shop");
        ImageIcon icon = new ImageIcon("welcome.jpeg");
        e = new JLabel(icon);
        add(e);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700,500);
        try{
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        dispose();
      Login ob = new Login();
    }
  public static void main(String[] args) {
     Petshop p = new Petshop();
  }
}