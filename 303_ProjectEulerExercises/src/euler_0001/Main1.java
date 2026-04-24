package euler_0001;

import java.util.concurrent.*;

public class Main1 {
    private static final int NUM_THREADS = 16;
    private static final int START_VALUE = 1;
    private static final int END_VALUE = 999;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        int sum = 0;
        int start = START_VALUE;
        int end;

        for (int i = 0; i < NUM_THREADS; i++) {
            end = i == NUM_THREADS - 1 ? END_VALUE : start + END_VALUE / NUM_THREADS - 1;
            cs.submit(new EulerTask1(start, end));
//            System.out.format("[%d, %d]\n", start, end);
            start = end + 1;
        }

        for (int i = 0; i < NUM_THREADS; i++) {
             sum += cs.take().get();
        }

        System.out.println(sum);

        executor.shutdown();
    }
}
