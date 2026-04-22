import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BuildingEvent {
    private EventType type;
    private int id;
    private String source;
    private int priority;
    private LocalDateTime timestamp;
    private String message;

    public BuildingEvent(EventType type, int id, String source, int priority, String message) {
        this.type = type;
        this.id = id;
        this.source = source;
        this.priority = priority;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public boolean isCritical() {
        return type.equals(EventType.SMOKE_ALERT) || type.equals(EventType.WATER_LEAK);
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "[%s] Event #%d | Source: %s | Type: %s | Priority: %d | Message: %s",
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")), id, source, type.name(), priority, message);
    }

    public EventType getType() {
        return type;
    }

    public String getSource() {
        return source;
    }
}
