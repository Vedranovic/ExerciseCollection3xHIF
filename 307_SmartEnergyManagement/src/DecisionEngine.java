import java.math.BigDecimal;
import java.math.RoundingMode;

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
        BufferItem raw;

        while ((raw = dataBuffer.take()) != null) {
            EnergyData item = (EnergyData) raw;
            System.out.println("ENGINE analyzes: " + item);

            decide(item);
        }

        batteryQueue.close();
        heatingQueue.close();
        dumpQueue.close();
        evQueue.close();

        System.out.println("DecisionEngine done and all action queues are closed.");
    }

    private void decide(EnergyData data) {
        double surplus = round(data.surplus());

        if (surplus <= 0) {
            dumpQueue.put(new ActionCommand("DUMP",
                    "data " + data.getId() + ": no surplus."));
        } else if (data.getBatterySoc() < 80) {
            batteryQueue.put(new ActionCommand("BATTERY",
                    "data " + data.getId() + ": charge battery with surplus " + surplus + " kW"));
        } else if (data.isCarConnected() && surplus >= 4.0) {
            evQueue.put(new ActionCommand("EV",
                    "data " + data.getId() + ": heating started with surplus " + surplus + " kW"));
        } else if (surplus >= 2.0) {
            heatingQueue.put(new ActionCommand("HEATING",
                    "data " + data.getId() + ": charge battery with surplus " + surplus + " kW"));
        } else {
            dumpQueue.put(new ActionCommand("DATA",
                    "data " + data.getId() + ": small surplus " + surplus + " kW"));
        }
    }

    private double round(double value) {
        return new BigDecimal(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
