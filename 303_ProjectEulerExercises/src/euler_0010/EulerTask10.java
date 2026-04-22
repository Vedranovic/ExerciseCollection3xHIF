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
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            boolean isPrime = false;

            for (int j = 2; j < i; j++) {
                if (i % j != 0) {
                    isPrime = true;
                    break;
                }
            }

            if (isPrime) {
                sum += i;
            }
        }

        return sum;
    }
}
