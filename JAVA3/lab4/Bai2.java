package baitap.lab4;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
 
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
 
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Bai2 extends JFrame implements ActionListener{
    private JMenuBar menuBar = new JMenuBar();
    private JMenu mFile = new JMenu("File");
    private JMenuItem itemNew = new JMenuItem("New(N)");
    private JMenuItem itemOpen = new JMenuItem("Open(O)");
    private JMenuItem itemSave = new JMenuItem("Save(S)");
    private JMenuItem itemSaveAs = new JMenuItem("Save As(A)");
 
    public Bai2(){
        // thêm các mục menu con vào menu cha là File
        mFile.add(itemNew);
        mFile.add(itemOpen);
        mFile.add(itemSave);
        mFile.add(itemSaveAs);

        // Tạo phím tắt cho các menu tương ứng
        itemNew.setMnemonic(KeyEvent.VK_N);
        itemOpen.setMnemonic(KeyEvent.VK_O);
        itemSave.setMnemonic(KeyEvent.VK_S);
        itemSaveAs.setMnemonic(KeyEvent.VK_A);
 
        // Thêm các mục menu cha vào menu chính của Frame
        menuBar.add(mFile);
        

        // Thêm menu chính vào Frame
        this.setJMenuBar(menuBar);
        // Vì đây là JMenuBar nên khi đặt menu phải dùng phương thức setJMenuBar
 
        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        itemNew.addActionListener(this);
        itemOpen.addActionListener(this);
        itemSave.addActionListener(this);
        itemSaveAs.addActionListener(this);
    }
 
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == itemNew ){
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn New ", "Thông báo", JOptionPane.CLOSED_OPTION);
        }
 
        if(e.getActionCommand().equalsIgnoreCase("Open") ){
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn Open ", "Thông báo", JOptionPane.CLOSED_OPTION);
        }
 
        if(e.getActionCommand().equalsIgnoreCase("Save") ){
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn Save ", "Thông báo", JOptionPane.CLOSED_OPTION);
        }
 
        if(e.getActionCommand().equalsIgnoreCase("Save As") ){
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn Save As", "Thông báo", JOptionPane.CLOSED_OPTION);
        }
    }
 
    public static void main(String[] args) {
        new Bai2();
    }
}
