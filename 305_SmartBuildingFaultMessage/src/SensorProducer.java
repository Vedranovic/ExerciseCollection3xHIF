import java.util.Random;

public class SensorProducer implements Runnable {
    private final EventType[] possibleTypes;
    private final EventBuffer buffer;
    private static int nextId = 1;
    private final String source;
    private final int numberOfEvents;
    private final Random random;

    public SensorProducer(int numberOfEvents, String source, EventBuffer buffer, EventType[] possibleTypes) {
        this.numberOfEvents = numberOfEvents;
        this.source = source;
        this.buffer = buffer;
        this.possibleTypes = possibleTypes;
        this.random = new Random();
    }

    private static int getNextId() {
        return nextId++;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfEvents; i++) {
            try {
                Thread.sleep(random.nextInt(400, 1001));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            EventType type = possibleTypes[random.nextInt(possibleTypes.length)];
            BuildingEvent event = new BuildingEvent(type,
                    getNextId(),
                    source,
                    determinePriority(type),
                    createMessage(type));

            buffer.put(event);
            System.out.println(Thread.currentThread() + " creates " + event);
        }

        System.out.println(Thread.currentThread() + " created all events.");
    }

    private int determinePriority(EventType type) {
        return switch (type) {
            case MOTION -> 4;
            case DOOR_OPEN -> 5;
            case TEMPERATURE_WARNING -> 6;
            case WATER_LEAK -> 9;
            case SMOKE_ALERT -> 10;
        };
    }

    private String createMessage(EventType type) {
        return switch (type) {
            case MOTION -> "Motion recognized.";
            case DOOR_OPEN -> "Door opened.";
            case TEMPERATURE_WARNING -> "Temperature not in range.";
            case WATER_LEAK -> "Water leak detected.";
            case SMOKE_ALERT -> "Smoke detected.";
        };
    }
}
