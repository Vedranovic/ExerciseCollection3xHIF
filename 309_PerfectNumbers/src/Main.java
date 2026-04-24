import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static final int NUM_THREADS = 16;
    private static final int START = 1000;
    private static final int END = 1_000_000;
    private static final int BLOCK = 50;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        int start = START;
        int end;
        int numOfTasks = (int) Math.ceil((double) (END - START) / BLOCK);
        List<PerfectNumberTask> tasks = new ArrayList<>();

        for (int i = 0; i < numOfTasks; i++) {
            end = i == numOfTasks - 1 ? END : start + BLOCK - 1;
            PerfectNumberTask task = new PerfectNumberTask(start, end);
            tasks.add(task);

            start = end + 1;
        }

        int result = executor.invokeAny(tasks);

        System.out.println(result);

        executor.shutdown();
    }
}
