import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Queue<BufferItem> bufferItems;
    private int capacity;
    private String name;
    private boolean closed = false;

    public Buffer(int capacity, String name) {
        this.bufferItems = new LinkedList<>();
        this.capacity = capacity;
        this.name = name;
    }

    public synchronized void put(BufferItem item) {
        while (bufferItems.size() >= capacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " waits: " + name + " full!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        bufferItems.offer(item);
        System.out.println(Thread.currentThread().getName() + " -> " + name + " added: " + item.toString());
        notifyAll();
    }

    public synchronized BufferItem take() {
        if (closed) {
            return null;
        }

        while (bufferItems.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waits: " + name + " empty!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        BufferItem item = bufferItems.poll();
        notifyAll();

        return item;
    }

    public void close() {
        closed = true;
    }
}
