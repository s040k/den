

public class Main {

    public static void main(String[] args) throws CalculatorException {
        Calculator calculator = new Calculator(new Output(), new Input(), new Converter(), new CPU());
        while (true) {
            calculator.onCalculate();
            calculator.getResult();
        }
    }
}
