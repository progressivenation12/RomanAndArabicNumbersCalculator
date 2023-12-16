import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String inputExpression = scanner.nextLine();

        System.out.println(calc(inputExpression));
    }

    public static String calc(String input) throws Exception {
        Converter converter = new Converter();
        String[] operatorExpressionArray = {"+", "-", "*", "/"};

        String currentOperatorExpression = " ";

        for (String s : operatorExpressionArray) {
            if (input.contains(s)) {
                if (s.equals("+") || s.equals("*")) {
                    currentOperatorExpression = String.format("\\%s", s);
                } else {
                    currentOperatorExpression = s;
                }
                break;
            }
        }

        if (currentOperatorExpression.equals(" ")) {
            throw new Exception("Incorrect operator entered");
        }

        String[] operands = input.replace(" ", "").toUpperCase().split(currentOperatorExpression);

        if (converter.isRomanNumber(operands[0]) == converter.isRomanNumber(operands[1]) && operands.length == 2) {
            boolean isRoman = converter.isRomanNumber(operands[0]);
            int firstNumber;
            int secondNumber;

            if (isRoman) {
                if ((converter.romanToInt(operands[0]) < 1 || converter.romanToInt(operands[1]) < 1) || (converter.romanToInt(operands[0]) > 10 || converter.romanToInt(operands[1]) > 10)) {
                    throw new Exception("The numbers must be from 1 to 10 inclusive");
                } else {
                    firstNumber = converter.romanToInt(operands[0]);
                    secondNumber = converter.romanToInt(operands[1]);
                }

                return converter.intToRoman(Integer.parseInt(getResultExpression(currentOperatorExpression, firstNumber, secondNumber)));
            } else {
                if ((Integer.parseInt(operands[0]) < 1 || Integer.parseInt(operands[1]) < 1) || (Integer.parseInt(operands[0]) > 10 || Integer.parseInt(operands[1]) > 10)) {
                    throw new Exception("The numbers must be from 1 to 10 inclusive");
                } else {
                    firstNumber = Integer.parseInt(operands[0]);
                    secondNumber = Integer.parseInt(operands[1]);
                }

                return getResultExpression(currentOperatorExpression, firstNumber, secondNumber);
            }
        } else {
            throw new Exception("Incorrect mathematical operation format");
        }
    }

    private static String getResultExpression(String currentOperatorExpression, int firstNumber, int secondNumber) throws Exception {
        switch (currentOperatorExpression) {
            case "\\+" -> {
                return String.valueOf(firstNumber + secondNumber);
            }
            case "-" -> {
                return String.valueOf(firstNumber - secondNumber);
            }
            case "\\*" -> {
                return String.valueOf(firstNumber * secondNumber);
            }
            case "/" -> {
                return String.valueOf(firstNumber / secondNumber);
            }
            default -> throw new Exception("Incorrect operator entered");
        }
    }
}
