import java.util.LinkedList;
import java.util.Queue;

public class EventBuffer {
    private final Queue<BuildingEvent> queue = new LinkedList<>();
    private final int capacity;

    public EventBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(BuildingEvent event) {
        while (queue.size() >= capacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " waits, because buffer is full.");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.offer(event);
        notifyAll();
    }

    public synchronized BuildingEvent take() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waits, because buffer is empty.");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        BuildingEvent event = queue.poll();
        notifyAll();

        return event;
    }
}
