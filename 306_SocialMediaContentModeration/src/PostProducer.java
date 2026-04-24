import java.util.Random;

public class PostProducer implements Runnable {
    private final RawContentBuffer rawBuffer;
    private static int nextId = 1;
    private final int postCount;
    private final String producerName;
    private static final String[] USERS = {"anna", "max", "christschn01", "Simon_HaDe", "Vedranovic"};
    private static final String[] TEXTS = {"Nice day today!", "This is completely unfair!", "You fucking piece of shit"};
    private final Random random;

    public PostProducer(RawContentBuffer rawBuffer, int postCount, String producerName) {
        this.rawBuffer = rawBuffer;
        this.postCount = postCount;
        this.producerName = producerName;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < postCount; i++) {
            try {
                Thread.sleep(random.nextInt(200, 701));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String text = "[" + producerName + "] " + TEXTS[random.nextInt(TEXTS.length)];

            rawBuffer.put(new Post(
                    nextId(),
                    USERS[random.nextInt(USERS.length)],
                    text,
                    random.nextInt(100),
                    false));
        }

        rawBuffer.put(Post.poisonPill());
        System.out.println(Thread.currentThread().getName() + "has produced all posts.");
    }

    private static int nextId() {
        return nextId++;
    }
}
