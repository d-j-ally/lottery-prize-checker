import java.util.Arrays;

/*
    Calculates a lottery prize given the lottery type, winning ticket, and player ticket.
*/

public class TicketPrizeCalculator {

    public static void main(String[] args) {
     
        try {
            if (args.length != 3) {
                throw new IllegalArgumentException("Please provide exactly 3 arguments");
            }
    
            int[] winningNumbers, playerNumbers;

            try {
                winningNumbers = parseNumbers(args[1]);          
                playerNumbers = parseNumbers(args[2]);  
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("All lottery number arguments must be integers");
            }

            
            LotteryPrize prize;
    
            switch (args[0]) {
                case "JavaMillions":
                    prize = new JavaMillions();
                    break;
    
                case "SpringLotto":
                    prize = new SpringLotto();
                    break;
                default:
                    throw new IllegalArgumentException("First argument must be the name of a valid lottery");
            }

            System.out.println("Calculating Lottery Winners v1.0.0 completed by Daniel Ally");

            prize.verifyNumbers(winningNumbers);
            prize.verifyNumbers(playerNumbers);
            int prizeClass = prize.calculateClass(winningNumbers, playerNumbers);
            prize.calculatePrize(prizeClass);
            
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }

    }

    private static int[] parseNumbers(String numbers) throws NumberFormatException {
        String[] tokens = numbers.split(",");
        int[] parsed = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
        return parsed;
    }
}