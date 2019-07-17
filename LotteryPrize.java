public interface LotteryPrize {

    // Should accept 2 arrays with 6 elements each and return the prize class. 0 is no prize
    public int calculateClass(int[] poolNumbers, int[] playerNumbers);

    // Derives the prize amount from the prize class
    public int calculatePrize(int prizeClass);

    // Ensures that numbers are within the ranges set for that lottery
    public void verifyNumbers(int[] numbers) throws IllegalArgumentException;

}