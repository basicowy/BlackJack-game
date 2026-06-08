package blackjack;

import java.io.InputStream;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this(System.in);
    }
    public InputHandler(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine();
                return val;
            } else {
                String bad = scanner.next();
                System.out.println("Invalid input: " + bad);
            }
        }
    }

    public Move getMoveInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNext()) {
                // no token, continue loop
                scanner.nextLine();
                continue;
            }
            String token = scanner.next();
            String up = token.trim().toUpperCase();
            switch (up) {
                case "H":
                case "HIT":
                    return Move.HIT;
                case "S":
                case "STAND":
                    return Move.STAND;
                case "D":
                case "DOUBLE":
                    return Move.DOUBLE;
                default:
                    System.out.println("Invalid move: " + token);
            }
        }
    }
}

