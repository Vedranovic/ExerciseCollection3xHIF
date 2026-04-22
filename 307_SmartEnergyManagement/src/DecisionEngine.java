public class DecisionEngine implements Runnable {
    private final Buffer batteryQueue;
    private final Buffer dataBuffer;
    private final Buffer heatingQueue;
    private final Buffer dumpQueue;
    private final Buffer evQueue;

    public DecisionEngine(Buffer batteryQueue, Buffer dataBuffer, Buffer heatingQueue, Buffer dumpQueue, Buffer evQueue) {
        this.batteryQueue = batteryQueue;
        this.dataBuffer = dataBuffer;
        this.heatingQueue = heatingQueue;
        this.dumpQueue = dumpQueue;
        this.evQueue = evQueue;
    }

    @Override
    public void run() {

    }

    private void decide(EnergyData data) {

    }

    private double round(double value) {


        return 0;
    }
}
