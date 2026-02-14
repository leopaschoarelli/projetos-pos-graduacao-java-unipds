import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws MyException {

        Produto p = new Produto();
        p.setId(0);
        p.setNome(null);
        p.setPreco(10);

        System.out.println(p);

        /*
        try (Scanner scanner = new Scanner(System.in)) {
            int a, b, r;

            System.out.println("Digite a: ");
            a = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite b: ");
            b = scanner.nextInt();

            r = a / b;

            System.out.println("Valor de r: " + r);
        } catch (ArithmeticException e) {
            System.out.println("Erro - Divisão por zero!");
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Erro - Você deveria digitar números inteiros");
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            System.out.println("Erro!");
        }
         */

    }
}
