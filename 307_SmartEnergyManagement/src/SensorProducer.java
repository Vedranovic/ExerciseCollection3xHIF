import java.util.Random;

public class SensorProducer implements Runnable {
    private final Buffer dataBuffer;
    private static int nextId = 1;
    private final int count;
    private final Random random;

    public SensorProducer(Buffer dataBuffer, int count) {
        this.dataBuffer = dataBuffer;
        this.count = count;
        this.random = new Random();
    }

    @Override
    public void run() {

    }
}
