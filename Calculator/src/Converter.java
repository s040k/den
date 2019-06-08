class Converter extends Calculator { //Перед выполнением действия процессора калькулятора ему нужно преобразовать комманду в свою систему счисления, в данном случае в арабаскую нумерацию)

    Operation getOperation(String operationLine) throws CalculatorException {

        if (onlyOneOperation(operationLine)) {
            if (operationLine.contains(String.valueOf(operationChars[0]))) {
                return Operation.ADDITION;
            } else if (operationLine.contains(String.valueOf(operationChars[1]))) {
                return Operation.SUBTRACTION;
            } else if (operationLine.contains(String.valueOf(operationChars[2]))) {
                return Operation.MULTIPLICATION;
            } else if (operationLine.contains(String.valueOf(operationChars[3]))) {
                return Operation.DIVISION;
            }

        } else {
            throw new CalculatorException("Слишком много операторов, достаточно одного.");
        }
        return null;
    }

    private boolean onlyOneOperation(String opertationLine) throws CalculatorException {
        int operationsCount = 0;
        for (Character x : operationChars) {
            if (opertationLine.contains(x.toString())) {
                operationsCount++;
            }
            if (!repeatingOperation(opertationLine, x)) {
                return false;
            }
        }
        if (operationsCount == 1) {
            return true;
        } else {
            if (operationsCount > 1) {
                throw new CalculatorException("Слишком много операторов, достаточно одного.");
            } else {
                throw new CalculatorException("Слишком мало операторов, добавьте хотя бы один.");
            }

        }
    }

    private boolean repeatingOperation(String operationLine, char operation) {
        int countOperation = 0;
        for (Character x : operationLine.toCharArray()) {
            if (x.equals(operation)) countOperation++;
        }
        if (countOperation > 1) {
            return false;
        }
        return true;

    }

    NumMode getMode(String operationLine) //Определяем, какой набор чисел у данной операции, находя совпадения строки операции с библиотеками чисел
    {
        boolean containsArabNum = false;
        boolean containsRomanNum = false;

        for (Character x : arabChars) {
            if (operationLine.contains(x.toString())) {
                containsArabNum = true;
                break;
            }
        }
        for (Character x : romanChars) {
            if (operationLine.contains(x.toString())) {
                containsRomanNum = true;
                break;
            }
        }

        if ((containsArabNum && containsRomanNum) || (!containsArabNum && !containsRomanNum)) {
            return null;
        } else {
            if (containsArabNum) return NumMode.ARABIC;
            else return NumMode.ROMAN;
        }

    }

    int[] getNums(String operationline, Operation operation, NumMode numMode) throws CalculatorException {
        int[] result = new int[2];
        String numA, numB;


        String operationChar = String.valueOf(operationChars[operation.ordinal()]);
        numA = operationline.split("[" + operationChar + "]")[0];
        numB = operationline.split("[" + operationChar + "]")[1];
        if (numA.isEmpty() || numB.isEmpty()) {
            throw new CalculatorException("Подсказка, операция получится удачной, если справа и слева от оператора будут присутствовать цифры. Попробуй еще.");
        }
        numA = getNum(numA, numMode);
        numB = getNum(numB, numMode);

        if (numMode.equals(NumMode.ROMAN)) {
            numA = replaceRomanToArab(numA);
            numB = replaceRomanToArab(numB);
        }

        result[0] = Integer.parseInt(numA);
        result[1] = Integer.parseInt(numB);

        return result;
    }

    private String replaceRomanToArab(String num) //В библеотеках чисел, их индексы совпадают. Так что перевести их с римского на арабский можно, сопоставив их индексы в массивах
    {
        for (int i = 0; i < arabNum.length; i++) {
            if (num.equals(romanNum[i])) {
                return arabNum[i];
            }
        }
        return null;
    }

    private String getNum(String numLine, NumMode numMode) throws CalculatorException //Удаляем все пробелы и получаем только число.
    {
        if (checkOnlyNum(numLine, numMode)) return numLine.replaceAll(" ", "");
        else {
            return null;
        }
    }

    private boolean checkOnlyNum(String numLine, NumMode numMode) throws CalculatorException//Проверяем одно число ли в строке, считая число таковым, если оно окружено пробелами.
    {
        String[] nums = new String[2];
        if (numMode == NumMode.ARABIC) {
            nums = arabNum;
        }
        if (numMode == NumMode.ROMAN) {
            nums = romanNum;
        }

        int countNum = 0;
        String numLineAddSpace = " " + numLine + " ";
        for (String x : nums) {
            if (numLineAddSpace.contains(" " + x + " ")) {
                countNum++;
            }
        }
        if (countNum == 1) {
            return true;
        } else {
            throw new CalculatorException("Требуются только числа от 1 до 10 или от I до X");
        }
    }

    String inRoman(int num) throws CalculatorException {
        if (num <= 0) throw new CalculatorException("В римских цифрах вроде как не было нуля и отрицательных чисел");
        int dec = num / 10;
        if (dec == 0) {
            return romanNum[num - 1];
        } else if (dec == 1) {
            return "X" + romanNum[num - 10 - 1];
        } else if (dec == 2) {
            return "XX" + romanNum[num - 20 - 1];
        } else if (dec == 3) {
            return "XXX" + romanNum[num - 30 - 1];
        } else if (dec == 4) {
            return "XL" + romanNum[num - 40 - 1];
        } else if (dec == 5) {
            return "L" + romanNum[num - 50 - 1];
        } else if (dec == 6) {
            return "LX" + romanNum[num - 60 - 1];
        } else if (dec == 7) {
            return "LXX" + romanNum[num - 70 - 1];
        } else if (dec == 8) {
            return "LXXX" + romanNum[num - 80 - 1];
        } else if (dec == 9) {
            return "XC" + romanNum[num - 90 - 1];
        } else {
            return "C";
        }

    }

}
