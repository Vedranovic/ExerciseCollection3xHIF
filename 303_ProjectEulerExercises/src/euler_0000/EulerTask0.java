package euler_0000;

import java.util.concurrent.Callable;

public class EulerTask0 implements Callable<Long> {
    private int start;
    private int end;

    public EulerTask0(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() throws Exception {
        long sumOdd = 0;

        for (long i = start; i <= end; i++) {
            if ((i * i) % 2 != 0) {
                sumOdd += (i * i);
            }
        }

        return sumOdd;
    }
}
