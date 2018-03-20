import java.util.Scanner;
import java.util.InputMismatchException;

class WrongOperator extends Exception {
    WrongOperator(String e) {
        super(e);
    }
}

class TryToUseZero extends Exception {
    TryToUseZero(String e) {
        super(e);
    }
}

class TryToUseZeroInRadical extends Exception {

    TryToUseZeroInRadical(String e) {
        super(e);
    }
}

public class First {
    public static void main(String[] args) {
        System.out.println("Введите операнд a(оператор)операнд b");
        System.out.println("Возможные операторы \n'*'-умножение\n'/'-деление\n'+'-сложение\n'-'-вычетание\n'^'-возведение a в степень b\n'|'-корень степени b");

        try {
            InputInformation inputInformation =new InputInformation();
            inputInformation.input();
            Calculator calculator = new Calculator(inputInformation);
            System.out.println(calculator.getResult());
        } catch (WrongOperator e) {
            e.printStackTrace();
        } catch (TryToUseZero e) {
            e.printStackTrace();
        } catch (TryToUseZeroInRadical e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Формат ввода не соответствует");
        }
    }
}

class Calculator extends InputInformation {
    private double result;
    private double a;
    private double b;
    private String operator;

    Calculator(InputInformation obj) {
        this.a = obj.a;
        this.b = obj.b;
        this.operator = obj.operator;
    }

    public double getResult() throws WrongOperator, TryToUseZero, TryToUseZeroInRadical {
        switch (operator) {
            case "*":
                Multiplication multiplication = new Multiplication();
                result = multiplication.multiplication(a, b);
                break;
            case "/":
                Division division = new Division();
                result = division.devision(a, b);
                break;
            case "+":
                Addition additor = new Addition();
                result = additor.addition(a, b);
                break;
            case "-":
                Subtraction subtraction = new Subtraction();
                result = subtraction.substraction(a, b);
                break;
            case "|":
                Radical radical = new Radical();
                result = radical.radical(a, b);
                break;
            case "^":
                Power power = new Power();
                result = power.power(a, b);
                break;
            default:
                throw new WrongOperator("Неверный оператор");
        }
        return result;
    }
}

class InputInformation {
    String operator = null;
    double b;
    double a;

    public void input() throws WrongOperator, TryToUseZero, TryToUseZeroInRadical {
        Scanner in = new Scanner(System.in);
        a = in.nextDouble();
        in.nextLine();
        operator = in.nextLine();
        b = in.nextDouble();

    }
}

class Division {
    public double devision(double a, double b) throws TryToUseZero {
        if (b == 0) throw new TryToUseZero("Нельзя делить на ноль");
        return a / b;
    }
}

class Multiplication {
    public double multiplication(double a, double b) {
        return a * b;
    }
}

class Radical {
    public double radical(double a, double b) throws TryToUseZeroInRadical {
        if (a == 0) throw new TryToUseZeroInRadical("Не существует корня из нуля");
        return Math.pow(a, 1 / b);
    }
}

class Subtraction {
    public double substraction(double a, double b) {
        return a - b;
    }
}

class Addition {
    public double addition(double a, double b) {
        return a + b;
    }
}

class Power {
    public double power(double a, double b) {
        return Math.pow(a, b);
    }
}