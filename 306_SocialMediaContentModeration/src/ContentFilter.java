public class ContentFilter implements Runnable {
    private final RawContentBuffer rawBuffer;
    private final ReviewBuffer reviewBuffer;
    private final int producerCount;
    private int poisonPillsSeen = 0;
    private int autoApproved = 0;
    private int forwardedToReview = 0;

    public ContentFilter(RawContentBuffer rawBuffer, ReviewBuffer reviewBuffer, int producerCount) {
        this.rawBuffer = rawBuffer;
        this.reviewBuffer = reviewBuffer;
        this.producerCount = producerCount;
    }

    @Override
    public void run() {
        while (poisonPillsSeen != producerCount) {
            Post post = rawBuffer.take();

            if (post.isPoisonPill()) {
                poisonPillsSeen++;

                if (poisonPillsSeen == producerCount) {
                    System.out.println("FILTER is shutting down and send termination signal to moderation");
                    reviewBuffer.put(Post.poisonPill());
                    printReport();
                }
                continue;
            }

            process(post);
        }
    }

    private void process(Post post) {
        System.out.println("FILTER checks: " + post.toString());

        if (post.needsManualReview()) {
            forwardedToReview++;
            System.out.println("FILTER -> critical post, forwarding to moderator");
            reviewBuffer.put(post);
        } else {
            autoApproved++;
            System.out.println("FILTER -> automatically approved: " + post.getId());
        }
    }

    private void printReport() {
        System.out.format("""
                === Filter Statistics ===
                Automatically approved: %d
                Forwarded to moderation: %d
                """, autoApproved, forwardedToReview);
        System.out.println();
    }
}
