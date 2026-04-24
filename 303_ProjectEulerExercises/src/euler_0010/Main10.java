package euler_0010;

import java.util.concurrent.*;

public class Main10 {
    private static final int NUM_TREADS = 16;
    private static final int START_VALUE = 1;
    private static final int END_VALUE = 2_000_000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_TREADS);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        int start = START_VALUE;
        int end;
        int sum = 0;

        for (int i = 0; i < NUM_TREADS; i++) {
            end = i == NUM_TREADS - 1 ? END_VALUE : start + END_VALUE / NUM_TREADS - 1;
            cs.submit(new EulerTask10(start, end));
            start = end + 1;
        }

        for (int i = 0; i < NUM_TREADS; i++) {
            sum += cs.take().get();
        }

        System.out.println(sum);

        executor.shutdown();
    }
}
