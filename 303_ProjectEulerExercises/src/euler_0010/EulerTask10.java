package euler_0010;
import java.util.concurrent.Callable;

public class EulerTask10 implements Callable<Integer> {
    private int start;
    private int end;

    public EulerTask10(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }

        return sum;
    }

    private boolean isPrime(int number) {
        boolean isPrime = true;

        if (number < 2) {
            return false;
        }

        for (int i = 2; i * i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}
