package baitap.lab7;

public class Bai2 {
    public static void main(String[] args) {
        CalculatorView objView = new CalculatorView();
        CalculatorModel objModel = new CalculatorModel();
        CalculatorController objController = new CalculatorController(objView, objModel);
    }
}
