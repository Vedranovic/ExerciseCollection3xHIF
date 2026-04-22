import java.util.concurrent.Callable;

public class HappyNumberTask implements Callable<Integer> {
    private int start;
    private int end;
    private int count;

    public HappyNumberTask(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
