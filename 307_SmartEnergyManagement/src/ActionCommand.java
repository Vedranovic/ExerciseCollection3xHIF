public class ActionCommand extends BufferItem {
    private String target;
    private String message;

    public ActionCommand(String target, String message) {
        this.target = target;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ActionCommand{" +
                "target='" + target + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
