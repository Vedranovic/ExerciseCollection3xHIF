import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

public class PerfectNumberTask implements Callable<Integer> {
    private int start;
    private int end;

    public PerfectNumberTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (i == sumOfDividers(i)) {
                    return i;
                }
            }
        }

        throw new NoSuchElementException();
    }

    private static int sumOfDividers(int num) {
        int sum = 0;

        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }

        return sum;
    }
}
