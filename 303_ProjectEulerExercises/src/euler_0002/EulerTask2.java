package euler_0002;

public class EulerTask2 {
    public static int sumEvenFibonacci() {
        int firstValue = 1;
        int secondValue = 2;
        int sumEven = 0;

        while (firstValue < 4_000_000) {
            if (firstValue % 2 == 0) {
                sumEven += firstValue;
            }

            int thirdValue = firstValue + secondValue;
            firstValue = secondValue;
            secondValue = thirdValue;
        }

        return sumEven;
    }
}
