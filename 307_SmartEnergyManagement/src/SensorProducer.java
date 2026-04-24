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
        for (int i = 0; i < count; i++) {
            dataBuffer.put(new EnergyData(
                    nextId(),
                    random.nextInt(26),
                    random.nextInt(2, 13),
                    random.nextInt(20, 101),
                    random.nextBoolean()));

            try {
                Thread.sleep(random.nextInt(300, 701));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        dataBuffer.close();
        System.out.println("SensorProducer done and DataQueue closed.");
    }

    private synchronized static int nextId() {
        return nextId++;
    }
}
