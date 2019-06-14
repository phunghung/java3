package baitap.lab7;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

public class Bai1 implements ActionListener{
    JFrame frame;
    private JList lst1, lst2;
    private DefaultListModel dt1, dt2;
    private JButton bt1, bt2;
    
    
    public Bai1() {
        prepareGUI();
    }
    
    private void prepareGUI() {
        frame = new JFrame("Bai1");
        frame.setSize(720, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        frame.getContentPane().setLayout(new FlowLayout());
        String dsMonHoc[] = {"Pascal", "Python", "Java", "Visual Basic", "C", "C++", "C#"};
        
        dt1 = new DefaultListModel();
        dt2 = new DefaultListModel();
        
        for(int i = 0; i < dsMonHoc.length; i++) {
            dt1.addElement(dsMonHoc[i]);
        }
        
        lst1 = new JList(dt1);
        lst2 = new JList(dt2);
        bt1 = new JButton(">");
        bt1.addActionListener(this);
        bt2 = new JButton("<");
        bt2.addActionListener(this);
        
        frame.getContentPane().add(lst1);
        frame.getContentPane().add(bt1);
        frame.getContentPane().add(bt2);
        frame.getContentPane().add(lst2);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bt1) {
            Object s1 = lst1.getSelectedValue();
            dt2.addElement(s1);
            dt1.removeElement(s1);
        }
        else {
            Object s2 = lst2.getSelectedValue();
            dt1.addElement(s2);
            dt2.removeElement(s2);
        }
    }
    
    public static void main(String[] args) {
        new Bai1();
    }
}
