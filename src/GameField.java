import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class GameField {

    static List<String> options = new ArrayList<>();

    public GameField() throws NoSuchAlgorithmException, InvalidKeyException {
        initGame();
    }

    public void initGame() throws NoSuchAlgorithmException, InvalidKeyException {
        String computerMove = computerMove();
        String secretKey = new Hmac().getKey();
        System.out.println("HMAC:\n" + new Hmac().hmac(computerMove));
        System.out.println("Available moves:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + " - " + options.get(i));
        }
        System.out.println("0 - exit\n? - help");
        System.out.print("Enter your move: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        switch (choice) {
            case ("0"):
                System.out.println("exit");
                break;
            case ("?"):
                new Table(options, options.size()).drawTable();
                initGame();
                break;
            default:
                String playerMove = playerMove(Integer.parseInt(choice));
                System.out.print("Your move: " + playerMove);
                System.out.println("\nComputer move: " + computerMove);
                System.out.println(new Rules().whoWin(options.indexOf(playerMove), options.indexOf(computerMove), options.size()));
                System.out.println("HMAC Key:\n" + secretKey);
                System.out.println();
                initGame();
                break;
        }
    }

    public String playerMove(int move) {
        return options.get(move - 1);
    }

    public String computerMove() {
        SecureRandom random = new SecureRandom();
        int move = random.nextInt(options.size());
        return options.get(move);
    }



    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String error = "the arguments entered are incorrect.\nExample: rock paper scissors lizard spock (at least three and an odd number)\nValues should not be repeated.";
        Set isValid = new HashSet();
        for (String arg : args) {
            if (!isValid.add(arg)) {
                System.out.println(error);
                return;
            }
        }
        if (args.length <3 || (args.length % 2) == 0 ) {
            System.out.println(error);
            return;
        } else {
            for (int i = 0; i < args.length; i++) {
                options.add(i, args[i]);
            }
        }
        new GameField();
    }
}