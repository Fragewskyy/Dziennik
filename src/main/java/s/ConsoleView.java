package s;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView implements View.View {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String readString(String label) {
        System.out.println(label+": ");
        return scanner.nextLine();
    }

    @Override
    public int readInt(String label) {

        int value;
        while (true) {
            System.out.print(label+": ");
            try {
                value = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong choice. Try again!");
                scanner.next();
            }

        }

        return value;
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }
}
