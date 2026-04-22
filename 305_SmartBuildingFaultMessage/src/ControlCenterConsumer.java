public class ControlCenterConsumer implements Runnable {
    private final EventBuffer buffer;
    private final Statistics statistics;
    private final int numberOfEventsToProcess;

    public ControlCenterConsumer(EventBuffer buffer, Statistics statistics, int numberOfEventsToProcess) {
        this.buffer = buffer;
        this.statistics = statistics;
        this.numberOfEventsToProcess = numberOfEventsToProcess;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfEventsToProcess; i++) {
            BuildingEvent event = buffer.take();
            System.out.println(Thread.currentThread().getName() + " processed: " + event);
            processEven(event);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Thread.currentThread().getName() + " processed all events.");
    }

    private void processEven(BuildingEvent event) {
        statistics.record(event);

        if (event.isCritical()) {
            System.out.println(">>> ALARM! Critical event found: " + event.getType() + " at " + event.getSource());
        }
    }
}
