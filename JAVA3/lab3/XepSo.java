package baitap.lab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class XepSo extends JFrame implements ActionListener{
    private JPanel panelnumber, mainPanel, paneLabel, paneStart;
    private JLabel lbl;
    private JButton btn;
    private String btnName[] = {"15", "14", "13", "12",
        "11", "10", "9", "8",
        "7", "6", "5", "4",
        "3", "2", "1", "",};



    public XepSo() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 330);
        setLocationRelativeTo(null);
        setResizable(false);
        add(createmainFrame());
    }

    /*Hàm tạo panel chính*/
    public JPanel createmainFrame() {
//        String s = JOptionPane.showInputDialog("Ten nguoi choi: ");
//        JOptionPane.showMessageDialog(null, "Welcom " + s);
//        setTitle(s);
        mainPanel = new JPanel(new BorderLayout(4, 4));
        mainPanel.add(createLabel(), BorderLayout.NORTH);
        mainPanel.add(createButtonofNumber(), BorderLayout.CENTER);
        mainPanel.add(createButtonBatDau(), BorderLayout.SOUTH);
        mainPanel.setBackground(Color.white);
        
        return mainPanel;
    }

    /*Khởi tạo jbutton*/
    private JButton myButton(String btnname) {
        btn = new JButton(btnname);
        btn.setBackground(Color.white);
        btn.setForeground(Color.darkGray);
        btn.addActionListener(this);
        return btn;

    }
    /*Add các jbutton vào panel*/

    public JPanel createButtonofNumber() {
        panelnumber = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < btnName.length; i++) {
            btn = myButton(btnName[i]);
            panelnumber.add(btn);
            //btn.setEnabled(false);
        }
        panelnumber.setBackground(Color.white);
        return panelnumber;
    }
    
    public JPanel createButtonBatDau() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        paneStart = new JPanel(layout);
        paneStart.add(myButton("Bat Dau"));
        paneStart.setBackground(Color.white);
        return paneStart;
    }

    /* Khởi tạo JLabel */
    private JPanel createLabel() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        paneLabel = new JPanel(layout);        
        lbl = new JLabel("TRO CHOI XEP O SO");
        lbl.setBackground(Color.WHITE);
        lbl.setForeground(Color.DARK_GRAY);
        paneLabel.add(lbl);
        return paneLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand() == "Bat Dau") {
//            JOptionPane.showMessageDialog(null, "OK");
//        }
//        String button = "";
//        String sSelected = ((JToggleButton) e.getSource()).getText(); 
//        ((JToggleButton) e.getSource()).setText(""); 
    }

    public static void main(String[] args) {
        new XepSo().setVisible(true);
    }
}
