import java.util.Scanner;

class Input extends Calculator { //Ввод предаставляет собой кнопку "=" на калькулторе. После нажатия на которую данные отправляются на обработку


    String getOperationLine() throws CalculatorException {
        Scanner scanner = new Scanner(System.in);
        String operationLine = scanner.nextLine();
        if (operationLine.isEmpty()) {
            throw new CalculatorException("Вы ничего не ввели");
        }
        if (checkLine(operationLine)) { //Проверяем не глючат ли кнопки калькулятора, может они сбоят и вводят не разрешенные знаки.
            return operationLine;
        }
        return null;
    }

    private boolean checkLine(String operationLine) throws CalculatorException { //Проверим каждый символ отдельно

        for (int i = 0; i < operationLine.length(); i++) {
            if (!checkChar(operationLine.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkChar(char c) throws CalculatorException { //Проверяем, находится ли данный символ в библиотеке разрешенный символов
        for (Character x : arabChars) {
            if (x == c) {
                return true;
            }
        }
        for (Character x : romanChars) {
            if (x == c) {
                return true;
            }
        }
        for (Character x : operationChars) {
            if (x == c) {
                return true;
            }
        }
        for (Character x : otherChars) {
            if (x == c) {
                return true;
            }
        }

        throw new CalculatorException("Присутствуют запрещенные символы");

    }
}
