package euler_0001;

import java.util.concurrent.Callable;

public class EulerTask1 implements Callable<Integer> {
    private int start;
    private int end;

    public EulerTask1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }

        return sum;
    }
}
