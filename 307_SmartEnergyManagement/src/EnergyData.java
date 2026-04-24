public class EnergyData extends BufferItem {
    private int id;
    private double pvPower;
    private double houseLoad;
    private int batterySoc;
    private boolean carConnected;

    public EnergyData(int id, double pvPower, double houseLoad, int batterySoc, boolean carConnected) {
        this.id = id;
        this.pvPower = pvPower;
        this.houseLoad = houseLoad;
        this.batterySoc = batterySoc;
        this.carConnected = carConnected;
    }

    public double surplus() {
        return pvPower - houseLoad;
    }

    @Override
    public String toString() {
        return "EnergyData{" +
                "id=" + id +
                ", pvPower=" + pvPower +
                ", houseLoad=" + houseLoad +
                ", batterySoc=" + batterySoc +
                ", carConnected=" + carConnected +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getBatterySoc() {
        return batterySoc;
    }

    public boolean isCarConnected() {
        return carConnected;
    }
}
