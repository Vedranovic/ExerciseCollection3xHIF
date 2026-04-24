public class Main {
    public static void main(String[] args) throws InterruptedException {
        RawContentBuffer rawContentBuffer = new RawContentBuffer(5);
        ReviewBuffer reviewBuffer = new ReviewBuffer(5);

        Thread producer1 = new Thread(new PostProducer(rawContentBuffer, 6, "Feed-A"), "Producer-1");
        Thread producer2 = new Thread(new PostProducer(rawContentBuffer, 6, "Feed-B"), "Producer-2");
        Thread producer3 = new Thread(new PostProducer(rawContentBuffer, 6, "Feed-C"), "Producer-3");
        Thread filter = new Thread(new ContentFilter(rawContentBuffer, reviewBuffer ,3));
        Thread moderator = new Thread(new Moderator(reviewBuffer));

        filter.start();
        moderator.start();
        producer1.start();
        producer2.start();
        producer3.start();

        filter.join();
        moderator.join();

        System.out.println();
        System.out.println("Program finished.");
    }
}
