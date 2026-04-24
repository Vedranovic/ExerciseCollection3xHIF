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
        BufferItem raw;

        while ((raw = queue.take()) != null) {
            execute((ActionCommand) raw);

            try {
                Thread.sleep(random.nextInt(300, 801));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void execute(ActionCommand command) {
        System.out.println(actorName + " executes data " + ++processed + ": " + command.getMessage());
    }
}
