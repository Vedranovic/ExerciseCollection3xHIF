import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Buffer dataBuffer = new Buffer(6, "DataQueue");
        Buffer batteryQueue = new Buffer(4, "BatteryQueue");
        Buffer evQueue = new Buffer(4, "EVQueue");
        Buffer heatingQueue = new Buffer(4, "HeatingQueue");
        Buffer dumpQueue = new Buffer(4, "DumpQueue");

        Thread sensorProducer = new Thread(new SensorProducer(dataBuffer, 16));
        Thread decisionEngine = new Thread(new DecisionEngine(batteryQueue, dataBuffer, heatingQueue, dumpQueue, evQueue));
        executorService.submit(new ActionConsumer(batteryQueue, "BatteryController"));
        executorService.submit(new ActionConsumer(evQueue, "EVController"));
        executorService.submit(new ActionConsumer(heatingQueue, "HeatingController"));
        executorService.submit(new ActionConsumer(dumpQueue, "DumpController"));

        sensorProducer.start();
        decisionEngine.start();

        sensorProducer.join();
        decisionEngine.join();
        executorService.shutdown();

        System.out.println("Program ended.");
    }
}
