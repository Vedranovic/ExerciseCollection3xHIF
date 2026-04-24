import java.util.concurrent.*;

public class Main {
    private static final int NUM_THREADS = 16;
    private static final int START = 1;
    private static final int END = 1_000_000;
    private static final int NUM_PER_TASK = 100;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        int start = START;
        int end;
        int numTasks = (int) Math.ceil((double) (END - START) / NUM_PER_TASK);
        int result = 0;

        for (int i = 0; i < numTasks; i++) {
            end = i == numTasks - 1 ? END : start + NUM_PER_TASK - 1;
            cs.submit(new HappyNumberTask(start, end));
            start += NUM_PER_TASK;
        }

        for (int i = 0; i < numTasks; i++) {
            result += cs.take().get();
        }

        System.out.println(result);

        executor.shutdown();
    }
}
