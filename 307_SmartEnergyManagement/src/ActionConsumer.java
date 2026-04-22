import java.util.Random;

public class ActionConsumer implements Runnable {
    private final Buffer queue;
    private final String actorName;
    private int processed = 0;
    private final Random random;

    public ActionConsumer(Buffer queue, String actorName) {
        this.queue = queue;
        this.actorName = actorName;
        this.random = new Random();
    }

    @Override
    public void run() {

    }

    private void execute(ActionCommand command) {

    }
}
