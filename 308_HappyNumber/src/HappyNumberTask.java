import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class HappyNumberTask implements Callable<Integer> {
    private int start;
    private int end;
    private int count;

    public HappyNumberTask(int start, int end) {
        this.start = start;
        this.end = end;
        this.count = 0;
    }

    @Override
    public Integer call() {
        List<Integer> oldNumbers = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            oldNumbers.clear();

            int current = i;

            while (current != 1 && !oldNumbers.contains(current)) {
                oldNumbers.add(current);

                current = String.valueOf(current)
                        .chars()
                        .map(c -> c - '0')
                        .map(digit -> digit * digit)
                        .sum();
            }

            if (current == 1) {
                count++;
            }
        }

        System.out.println("Finished Task: " + Thread.currentThread().getName());

        return count;
    }
}
