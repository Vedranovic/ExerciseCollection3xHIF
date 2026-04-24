package euler_0012;

import java.math.BigInteger;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main12 {
    public static final int START = 1;
    public static final int END = 100;
    public static final int NUM_OF_THREADS = 16;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREADS);
        CompletionService<BigInteger> cs = new ExecutorCompletionService<>(executor);
        int start = START;
        int end;

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            end = i == NUM_OF_THREADS - 1 ? END : start - END / NUM_OF_THREADS - 1;
            cs.submit(new EulerTask12(start, end));
            start = end + 1;
        }
    }
}
