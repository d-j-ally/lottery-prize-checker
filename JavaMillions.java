import java.util.ArrayList;

public class JavaMillions implements LotteryPrize {

    private final static int POOL_ONE_LOWER = 1;
    private final static int POOL_ONE_UPPER = 49;
    private final static int POOL_TWO_LOWER = 1;
    private final static int POOL_TWO_UPPER = 9;

    private ArrayList<Integer> winners;
    private int poolTwoWinner;

    @Override
    public int calculateClass(int[] poolNumbers, int[] playerNumbers) {

        int poolOneMatches = 0;
        boolean poolTwoMatch;

        winners = new ArrayList<Integer>();

        for (int poolIndex = 0; poolIndex < poolNumbers.length-1; poolIndex++) {
            for (int playerIndex = 0; playerIndex < playerNumbers.length-1; playerIndex++) {
                if (poolNumbers[poolIndex] == playerNumbers[playerIndex]) {
                    poolOneMatches++;
                    winners.add(poolNumbers[poolIndex]);
                }
            }
        }

        if (poolNumbers[poolNumbers.length-1] == playerNumbers[playerNumbers.length-1]) {
            poolTwoMatch = true;
            poolTwoWinner = poolNumbers[poolNumbers.length-1];
        } else {
            poolTwoMatch = false;
            poolTwoWinner = 0;
        }

        int result;

        switch (poolOneMatches) {
            case 2:
                result = poolTwoMatch ?  7 : 8; 
                break;
            case 3:
                result = poolTwoMatch ?  5 : 6;      
                break;
            case 4:
                result = poolTwoMatch ?  3 : 4;      
                break;
            case 5:
                result = poolTwoMatch ?  1 : 2;      
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }

    public int calculatePrize(int prizeClass) {

        int result;

        switch (prizeClass) {
            case 1:
                result = 10000000;
                break;
            case 2:
                result = 100000;
                break;
            case 3:
                result = 10000;
                break;
            case 4:
                result = 500;
                break;
            case 5:
                result = 100;
                break;
            case 6:
                result = 30;
                break;
            case 7:
                result = 5;
                break;
            case 8:
                result = 2;
                break;
            default:
                result = 0;
                break;
        }

        if (result > 0) {
            System.out.printf("This ticket won a prize class of %d and amount of %d.\n", prizeClass, result);

            String winningMessage = "Matched numbers ";
    
            for (Integer num : winners) {
                winningMessage += num + ", ";
            }

            winningMessage += "from pool 1";
            
            if (poolTwoWinner != 0) {
                winningMessage += " and number " + poolTwoWinner + " from pool 2.";
            } else {
                winningMessage += ".";
            }

            System.out.println(winningMessage);

        } else {
            System.out.println("This ticket did not win a prize.");
        }


        return result;
    }

    public void verifyNumbers(int[] numbers) throws IllegalArgumentException {

        if (numbers.length != 6) {
            throw new IllegalArgumentException("Lottery draw entries must consist of exactly 6 numbers");
        }

        for (int i = 0; i < numbers.length-1; i++) {
            if (numbers[i] < POOL_ONE_LOWER || numbers[i] > POOL_ONE_UPPER) {
                throw new IllegalArgumentException("Pool one numbers must within the range: " 
                + POOL_ONE_LOWER + "-" + POOL_ONE_UPPER);
            }
            for (int j = i+1; j < numbers.length-1; j++) {
                if (numbers[i] == numbers[j] && i != j) {
                    throw new IllegalArgumentException("Numbers within each pool cannot be the same");
                }
            }
        }

        if (numbers[numbers.length-1] < POOL_TWO_LOWER || numbers[numbers.length-1] > POOL_TWO_UPPER) {
            throw new IllegalArgumentException("Pool two numbers must within the range: " 
            + POOL_TWO_LOWER + "-" + POOL_TWO_UPPER);
        }
    }

}