class Calculator {
    private Output output;
    private Input input;
    private Converter converter;
    private CPU cpu;

    Calculator() {
    }

    Calculator(Output output, Input input, Converter converter, CPU cpu) {
        this.output = output;
        this.input = input;
        this.converter = converter;
        this.cpu = cpu;
    }


    private Operation operation;
    private NumMode numMode;

    private int result;


    char[] arabChars = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    char[] romanChars = new char[]{'I', 'V', 'X'};
    char[] operationChars = new char[]{'+', '-', '*', '/'};
    char[] otherChars = new char[]{' '};


    String[] arabNum = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] romanNum = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    void onCalculate() {
        try {
            output.displayMessage("Введите операцию");
            String operationLine;
            operationLine = input.getOperationLine();
            operation = converter.getOperation(operationLine);
            numMode = converter.getMode(operationLine);
            int[] nums = converter.getNums(operationLine, operation, numMode);
            int arabNumA = nums[0];
            int arabNumB = nums[1];
            result = cpu.calculate(arabNumA, arabNumB, operation);
        } catch (CalculatorException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    enum NumMode {
        ARABIC,
        ROMAN
    }

    public enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION
    }

    void getResult() throws CalculatorException {
        if (numMode.equals(NumMode.ROMAN)) {
            output.displayMessage(converter.inRoman(result));
        } else {
            output.displayMessage(String.valueOf(result));
        }
    }

}

