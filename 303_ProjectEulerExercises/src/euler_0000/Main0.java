package euler_0000;

import java.util.concurrent.*;

public class Main0 {
    private static final int NUM_THREAD = 16;
    private static final int MAX = 904_000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREAD);
        CompletionService<Long> cs = new ExecutorCompletionService<>(executor);

        cs.submit(new EulerTask0(1, MAX));

        Future<Long> result = cs.take();

        System.out.println(result.get());

        executor.shutdown();
    }
}
