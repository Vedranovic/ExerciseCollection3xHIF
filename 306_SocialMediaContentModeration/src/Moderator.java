import java.util.Random;

public class Moderator implements Runnable {
    private final ReviewBuffer reviewBuffer;
    private int approved = 0;
    private int rejected = 0;
    private final Random random;

    public Moderator(ReviewBuffer reviewBuffer) {
        this.reviewBuffer = reviewBuffer;
        this.random = new Random();
    }

    @Override
    public void run() {
        Post post = null;

        do {
            post = reviewBuffer.take();

            if (post.isPoisonPill()) {
                printReport();
                System.out.println("MODERATOR is shutting down.");
                continue;
            }

            moderate(post);
        } while (!post.isPoisonPill());
    }

    private void moderate(Post post) {
        try {
            Thread.sleep(random.nextInt(400, 901));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (post.getToxicScore() < 85) {
            approved++;
            System.out.println("MODERATOR -> post " + post.getId() + " approved");
        } else {
            rejected++;
            System.out.println("MODERATOR -> post " + post.getId() + " rejected");
        }
    }

    private void printReport() {
        System.out.format("""
                === Moderation Statistics ===
                Approved: %d
                Rejected: %d""", approved, rejected);
        System.out.println();
    }
}
