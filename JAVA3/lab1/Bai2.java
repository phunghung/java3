package baitap.lab1;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bai2 {
    public static void main(String[] args) { 
     JFrame f = new JFrame(); 
     try { 
      f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Koala.jpg"))))); 
     } catch (IOException e) { 
      e.printStackTrace(); 
     } 
     f.pack(); 
     f.setVisible(true); 
     f.setSize(500, 300);
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
}
