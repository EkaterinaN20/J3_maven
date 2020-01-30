import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lambra {
    public static  void main(String[] args) {
        try {
            System.out.println("Введите цифру с плавающей точкой, например, 5.0");
            BufferedReader readA = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите еще одну цифру с плавающей точкой, например, 5.0");
            BufferedReader readB = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите знак + - или *");
            BufferedReader readOp = new BufferedReader(new InputStreamReader(System.in));
            String lineA = readA.readLine();
            String lineB = readB.readLine();
            String lineOp = readOp.readLine();
            Double a = Double.parseDouble(lineA);
            Double b = Double.parseDouble(lineB);
            if (lineOp.equals("+")) {
                Operationable op = (x, y) -> a + b;
                double result = op.calculate(a, b);
                System.out.println(result);
            }
            if (lineOp.equals("-")) {
                Operationable op = (w, s) -> a - b;
                double result = op.calculate(a, b);
                System.out.println(result);
            }
            if (lineOp.equals("*")) {
                Operationable op = (y, z) -> a * b;
                double result = op.calculate(a, b);
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println("Вы ввели неверно");
        } catch (NumberFormatException e) {
            System.out.println("Вы цифры неверно");
        }

        }
    }
