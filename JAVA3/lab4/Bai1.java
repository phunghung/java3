package baitap.lab4;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class Bai1 extends JFrame implements ActionListener {

    ImageIcon icon = new ImageIcon("icon.png");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu mFile = new JMenu("A Menu");
    private JMenuItem itemAnotherMenu = new JMenuItem("Another Menu");
    private JMenuItem itemTextonly = new JMenuItem("A Text-only menu item");
    private JMenuItem itemTextandIcon = new JMenuItem("Both text and icon", icon);
    private JMenuItem itemIcon = new JMenuItem(icon);

    public Bai1() {
        // thêm các mục menu con vào menu cha là File
        mFile.add(itemTextonly);
        mFile.add(itemTextandIcon);
        mFile.add(itemIcon);
        
        JRadioButton radiobtn1 = new JRadioButton("A radio button menu item", true);
        JRadioButton radiobtn2 = new JRadioButton("Another one", false);
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(radiobtn1);
        bgroup.add(radiobtn2);
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, (BoxLayout.Y_AXIS)));
        radioPanel.add(radiobtn1);
        radioPanel.add(radiobtn2);
        mFile.add(radioPanel);
        
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, (BoxLayout.Y_AXIS)));
        JCheckBox checkBox1 = new JCheckBox("A check box menu item");
        JCheckBox checkBox2 = new JCheckBox("Another one");
        checkboxPanel.add(checkBox1);
        checkboxPanel.add(checkBox2);
        mFile.add(checkboxPanel);
        
        JMenu subMenu = new JMenu("Submenu");
        JMenuItem item2 = new JMenuItem("Another Menu");
        JMenuItem item1 = new JMenuItem("An item in the submenu");
        subMenu.add(item1);
        subMenu.add(item2);
        mFile.add(subMenu);
        mFile.addSeparator();

        // Thêm các mục menu cha vào menu chính của Frame
        menuBar.add(mFile);
        menuBar.add(itemAnotherMenu);
        
        // Thêm menu chính vào Frame
        this.setJMenuBar(menuBar);
        // Vì đây là JMenuBar nên khi đặt menu phải dùng phương thức setJMenuBar

        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        itemAnotherMenu.addActionListener(this);
        itemTextonly.addActionListener(this);
        itemTextandIcon.addActionListener(this);
        itemIcon.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == itemAnotherMenu) {
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn Another Menu", "Thông báo", JOptionPane.CLOSED_OPTION);
        }

        if (e.getSource() == itemTextonly) {
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn Text only ", "Thông báo", JOptionPane.CLOSED_OPTION);
        }

        if (e.getSource() == itemTextandIcon) {
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn Tex tand Icon ", "Thông báo", JOptionPane.CLOSED_OPTION);
        }

        if (e.getSource() == itemIcon) {
            JOptionPane.showMessageDialog(null, "Bạn vừa chọn Icon ", "Thông báo", JOptionPane.CLOSED_OPTION);
        }
    }

    public static void main(String[] args) {
        new Bai1();
    }
}
