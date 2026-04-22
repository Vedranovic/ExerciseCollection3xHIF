public class Main {
    public static void main(String[] args) throws InterruptedException {
        Statistics statistics = new Statistics();
        EventBuffer buffer = new EventBuffer(5);

        Thread sensor1 = new Thread(
                new SensorProducer(
                        8,
                        "Motion Detection EG",
                        buffer,
                        new EventType[]{EventType.MOTION}), "Sensor-1"
        );
        Thread sensor2 = new Thread(
                new SensorProducer(
                        8,
                        "Door sensor main entrance",
                        buffer,
                        new EventType[]{EventType.DOOR_OPEN}), "Sensor-2"
        );
        Thread sensor3 = new Thread(
                new SensorProducer(
                        8,
                        "Smoke detector utility room",
                        buffer,
                        new EventType[]{EventType.SMOKE_ALERT, EventType.TEMPERATURE_WARNING}), "Sensor-3"
        );
        Thread sensor4 = new Thread(
                new SensorProducer(
                        8,
                        "Water sensor cellar",
                        buffer,
                        new EventType[]{EventType.WATER_LEAK, EventType.TEMPERATURE_WARNING}), "Sensor-4"
        );

        Thread consumer = new Thread(new ControlCenterConsumer(buffer, statistics, 32),"Control Center");

        sensor1.start();
        sensor3.start();
        sensor2.start();
        sensor4.start();
        consumer.start();

        sensor1.join();
        sensor3.join();
        sensor2.join();
        sensor4.join();
        consumer.join();

        statistics.printReport();
    }
}
