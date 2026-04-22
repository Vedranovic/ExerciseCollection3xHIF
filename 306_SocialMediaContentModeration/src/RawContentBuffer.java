import java.util.LinkedList;
import java.util.Queue;

public class RawContentBuffer {
    private Queue<Post> queue;
    private int capacity;

    public RawContentBuffer(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void put(Post post) {
        while (queue.size() >= capacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " waits, raw buffer full!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.offer(post);
        System.out.println(Thread.currentThread().getName() + " -> RAW in: " + post.toString());
        notifyAll();
    }

    public synchronized Post take() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waits, raw buffer empty!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Post post = queue.poll();
        System.out.println(Thread.currentThread().getName() + " -> RAW in: " + post.toString());

        return post;
    }
}
