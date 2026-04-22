import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private int totalEvents;
    private int criticalEvents;
    private Map<String, Integer> eventsBySource = new HashMap<>();
    private Map<EventType, Integer> eventsByType = new HashMap<>();

    public void record(BuildingEvent event) {
        if (event.isCritical()) {
            criticalEvents++;
        }

        eventsBySource.put(event.getSource(), eventsBySource.getOrDefault(event.getSource(), 0) + 1);
        eventsByType.put(event.getType(), eventsByType.getOrDefault(event.getType(), 0) + 1);

        totalEvents++;
    }

    public void printReport() {
        StringBuilder printSources = new StringBuilder();
        StringBuilder printTypes = new StringBuilder();

        for (Map.Entry<String, Integer> entry : eventsBySource.entrySet()) {
            printSources.append(String.format("%s: %d\n", entry.getKey(), entry.getValue()));
        }

        for (Map.Entry<EventType, Integer> entry : eventsByType.entrySet()) {
            printTypes.append(String.format("%s: %d\n", entry.getKey(), entry.getValue()));
        }

        System.out.format("""
                ========== STATISTIC ==========
                Amount of all events: %d
                Critical events: %d
               \s
                Events per source:
                    - %s
                   \s
                Events per type:
                    - %s
                ================================""", totalEvents, criticalEvents, printSources, printTypes);
    }
}
