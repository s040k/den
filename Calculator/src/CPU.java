 class CPU extends Calculator {
     CPU() {
     }

     int calculate(int a, int b, Operation operation) throws CalculatorException {
        if(operation.equals(Operation.ADDITION)){return addition(a,b);}
        else if(operation.equals(Operation.SUBTRACTION)){return subtraction(a,b);}
        else if(operation.equals(Operation.MULTIPLICATION)){return multiplication(a,b);}
        else if(operation.equals(Operation.DIVISION)){return division(a,b);}
        else return 0;

    }

    private int addition(int a, int b) throws CalculatorException {
        if(a>10||b>10||a<=0||b<=0){throw new CalculatorException("Требуются только числа от 1 до 10 или от I до X");}
        return a+b;
    }

    private int subtraction(int a, int b) throws CalculatorException {
        if(a>10||b>10||a<=0||b<=0){throw new CalculatorException("Требуются только числа от 1 до 10 или от I до X");}
        return a-b;
    }

    private int multiplication(int a, int b) throws CalculatorException {
        if(a>10||b>10||a<=0||b<=0){throw new CalculatorException("Требуются только числа от 1 до 10 или от I до X");}
        return a*b;
    }

    private int division(int a, int b) throws CalculatorException { /*На ноль делить нельзя, но и 0 запрещенная цифра*/
        if(a>10||b>10||a<=0||b<=0){throw new CalculatorException("Требуются только числа от 1 до 10 или от I до X");}
        return a/b;
    }
}
