import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by adam on 10.05.2018.
 */
public class RPNCalculator {

    Stack<Double> stack;

    public enum Operands {
        PLUS("+"),
        MINUS("-"),
        DIVIDE("/"),
        MULTIPLY("*"),
        FACTORIAL("!"),
        LOG("log"),
        SIN("sin"),
        COS("cos"),
        TG("tg"),
        POW("pow");

        private String operand;

        Operands(String operand) {
            this.operand = operand;
        }

        public String operand() {
            return operand;
        }
    }

    public enum Operation {
        PLUS,
        MINUS,
        DIVIDE,
        MULTIPLY,
        FACTORIAL,
        LOG,
        SIN,
        COS,
        TG,
        POW;

        double calculate(double x, double y) {
            switch (this) {
                case PLUS:
                    return x + y;
                case MINUS:
                    return x - y;
                case MULTIPLY:
                    return x * y;
                case DIVIDE:
                    return x / y;
                case POW:
                    return x / y;
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }

        double calculate(double x) {
            switch (this) {
                case FACTORIAL:
                    return factorial(x);
                case LOG:
                    return Math.log(x);
                case SIN:
                    return Math.sin(x);
                case COS:
                    return Math.cos(x);
                case TG:
                    return Math.tan(x);
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }
    }

    public void calculate() {
        try {
            stack = new Stack<Double>();
            Scanner sc = new Scanner(System.in);
            String[] line = sc.nextLine().split(" ");

            for (String t : line) {
                if (t.equals(Operands.PLUS.operand())) {
                    stack.push(Operation.PLUS.calculate(getFromStack(), getFromStack()));
                } else if (t.equals(Operands.MINUS.operand())) {
                    stack.push(Operation.MINUS.calculate(getFromStack(), getFromStack()));
                } else if (t.equals(Operands.DIVIDE.operand())) {
                    stack.push(Operation.DIVIDE.calculate(getFromStack(), getFromStack()));
                } else if (t.equals(Operands.MULTIPLY.operand())) {
                    stack.push(Operation.MULTIPLY.calculate(getFromStack(), getFromStack()));
                } else if (t.equals(Operands.FACTORIAL.operand())) {
                    stack.push(Operation.FACTORIAL.calculate(getFromStack()));
                } else if (t.equals(Operands.LOG.operand())) {
                    stack.push(Operation.LOG.calculate(getFromStack()));
                } else if (t.equals(Operands.SIN.operand())) {
                    stack.push(Operation.SIN.calculate(getFromStack()));
                } else if (t.equals(Operands.COS.operand())) {
                    stack.push(Operation.COS.calculate(getFromStack()));
                } else if (t.equals(Operands.TG.operand())) {
                    stack.push(Operation.TG.calculate(getFromStack()));
                } else if (t.equals(Operands.POW.operand())) {
                    stack.push(Operation.POW.calculate(getFromStack(), getFromStack()));
                } else {
                    stack.push(Double.parseDouble(t));
                }
            }
            System.out.println(stack.peek());
        } catch (EmptyStackException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private double getFromStack() {
        return stack.pop();
    }

    public static Double factorial(Double n) {
        if (n == 0.0) {
            return 1.0;
        } else {
            return (n * factorial(n - 1.0));
        }
    }
}