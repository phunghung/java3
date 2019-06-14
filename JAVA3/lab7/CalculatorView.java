package baitap.lab7;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorView {
    JFrame frame;
    JTextField firstNumber = new JTextField(10);
    JLabel additionLabel = new JLabel("+");
    JTextField secondNumber = new JTextField(10);
    JButton calculateButton = new JButton("Calculate");
    JTextField calcSolution = new JTextField(10);
    
    
    private void prepareGUI() {
        frame = new JFrame("Bai2");
        frame.setSize(720, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        JPanel calcPanel = new JPanel();
        calcPanel.add(firstNumber);
        calcPanel.add(additionLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(calculateButton);
        calcPanel.add(calcSolution);
        calcSolution.setEditable(false);
        
        frame.add(calcPanel);
    }

    public CalculatorView() {
        prepareGUI();
    }
    
    public int getFirstNumber() {
        return Integer.parseInt(firstNumber.getText());
    }
    
    public int getSecondNumber() {
        return Integer.parseInt(secondNumber.getText());
    }
    
    public int getCalcSolution() {
        return Integer.parseInt(calcSolution.getText());
    }
    
    public void setCalcSolution(int solution) {
        calcSolution.setText(Integer.toString(solution));
    }
    
    void addCalculateListener(ActionListener listenForCalcButton) {
        calculateButton.addActionListener(listenForCalcButton);
    }
    
    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }
}
