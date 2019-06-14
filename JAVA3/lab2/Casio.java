package baitap.lab2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Casio extends JFrame implements ActionListener {

    private double b;
    private JPanel panelcalculator, mainPanel, panelOUTPUT;
    private JTextArea textarea;
    private String btnName[] = {"1", "2", "3", "/", "sqrt",
        "4", "5", "6", "*", "%",
        "7", "8", "9", "-", "1/x",
        "0", "+/-", "C", "+", "=",};

    private JButton btn;
    private double a = 0;
    private int casenumber = 0;
    private double result = 0;

    public Casio() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 330);
        setLocationRelativeTo(null);
        setResizable(false);
        add(createmainFrame());
    }

    /*Hàm tạo panel chính*/
    public JPanel createmainFrame() {
        setTitle("Calculator");

        mainPanel = new JPanel(new BorderLayout(4, 5));
        mainPanel.add(createButtonofCalculator(), BorderLayout.CENTER);
        mainPanel.add(createTextArea(), BorderLayout.NORTH);
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

    public JPanel createButtonofCalculator() {
        panelcalculator = new JPanel(new GridLayout(4, 5));
        for (int i = 0; i < btnName.length; i++) {
            panelcalculator.add(myButton(btnName[i]));
        }
        panelcalculator.setBackground(Color.white);
        return panelcalculator;

    }

    /* Khởi tạo jtextarea */
    private JPanel createTextArea() {
        panelOUTPUT = new JPanel(new GridLayout(1, 1));
        textarea = new JTextArea(3, 3);
        textarea.setBackground(Color.WHITE);
        textarea.setForeground(Color.DARK_GRAY);
        panelOUTPUT.add(textarea);
        textarea.setEditable(false);
        return panelOUTPUT;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
// Ở đây chúng ta sẽ bắt sự kiến cho từng nút
        if (e.getActionCommand() == "1") {
            textarea.append("1");
        }
        if (e.getActionCommand() == "2") {
            textarea.append("2");
        }
        if (e.getActionCommand() == "3") {
            textarea.append("3");
        }
        if (e.getActionCommand() == "4") {
            textarea.append("4");
        }
        if (e.getActionCommand() == "5") {
            textarea.append("5");
        }
        if (e.getActionCommand() == "6") {
            textarea.append("6");
        }
        if (e.getActionCommand() == "7") {
            textarea.append("7");
        }
        if (e.getActionCommand() == "8") {
            textarea.append("8");
        }
        if (e.getActionCommand() == "9") {
            textarea.append("9");
        }
        if (e.getActionCommand() == "0") {
            textarea.append("0");
        }
        if (e.getActionCommand() == ".") {
            textarea.append(".");
        }
        if (e.getActionCommand() == "+/-")//Dấu âm (negative)
        {
            textarea.append("-");
        }

        if (e.getActionCommand() == "+") {
            a = Double.parseDouble(textarea.getText());
            casenumber = 1;
            textarea.setText("");
        }
        if (e.getActionCommand() == "-") {
            a = Double.parseDouble(textarea.getText());
            casenumber = 2;
            textarea.setText("");
        }

        if (e.getActionCommand() == "*") {
            a = Double.parseDouble(textarea.getText());
            casenumber = 3;
            textarea.setText("");
        }

        if (e.getActionCommand() == "/") {
            a = Double.parseDouble(textarea.getText());
            casenumber = 4;
            textarea.setText("");
        }

        if (e.getActionCommand() == "%") {
            a = Double.parseDouble(textarea.getText());
            casenumber = 5;
            textarea.setText("");
        }

        if (e.getActionCommand() == "1/x") {
            a = Double.parseDouble(textarea.getText());
            result = 1/a;
            textarea.setText(new Double(result).toString());
        }

        if (e.getActionCommand() == "sqrt") {
            a = Double.parseDouble(textarea.getText());
            result = Math.sqrt(a);
            textarea.setText(new Double(result).toString());
        }

        if (e.getActionCommand() == "=") {
            b = Double.parseDouble(textarea.getText());
            switch (casenumber) {
                case 1:
                    result = a + b;
                    break;

                case 2:
                    result = a - b;
                    break;

                case 3:
                    result = a * b;
                    break;

                case 4:
                    result = a / b;
                    break;
                case 5:
                    result = a % b;
                    break;

                default:
                    result = 0;
            }
            textarea.setText(new Double(result).toString());
        }

        if (e.getActionCommand() == "C") {
            textarea.setText("");
        }

    }

    public static void main(String[] args) {
        new Casio().setVisible(true);
    }
}
