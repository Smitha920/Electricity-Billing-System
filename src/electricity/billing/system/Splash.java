package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame {
    Splash(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/img1.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(1800,800, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);
        setSize(1800,850);
        setLocation(0,0);
        setVisible(true);
        try{
            Thread.sleep(3000L);
            setVisible(false);
            new Login();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Splash();
    }
}
