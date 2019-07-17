import java.util.ArrayList;

public class SpringLotto implements LotteryPrize {

    private final static int POOL_ONE_LOWER = 1;
    private final static int POOL_ONE_UPPER = 36;

    private ArrayList<Integer> winners;

    @Override
    public int calculateClass(int[] poolNumbers, int[] playerNumbers) {

        int poolOneMatches = 0;
        winners = new ArrayList<Integer>();

        for (int i = 0; i < poolNumbers.length; i++) {
            if (poolNumbers[i] == playerNumbers[i]) {
                poolOneMatches++;
                winners.add(poolNumbers[i]);
            }
        }

        int result;

        switch (poolOneMatches) {
            case 1:
                result = 6;
                break;
            case 2:
                result = 5; 
                break;
            case 3:
                result = 4;      
                break;
            case 4:
                result = 3;      
                break;
            case 5:
                result = 2;      
                break;
            case 6:
                result = 1;
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
                result = 500000;
                break;
            case 2:
                result = 2000;
                break;
            case 3:
                result = 300;
                break;
            case 4:
                result = 50;
                break;
            case 5:
                result = 12;
                break;
            case 6:
                result = 3;
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

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < POOL_ONE_LOWER || numbers[i] > POOL_ONE_UPPER) {
                throw new IllegalArgumentException("Pool one numbers must within the range: " 
                + POOL_ONE_LOWER + "-" + POOL_ONE_UPPER);
            }
            for (int j = i+1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j] && i != j) {
                    throw new IllegalArgumentException("Numbers within each pool cannot be the same");
                }
            }
        }
    }

}