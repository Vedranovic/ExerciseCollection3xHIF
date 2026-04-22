import java.util.Locale;

public class Post {
    private int id;
    private String userName;
    private String text;
    private int toxicScore;
    private boolean poisonPill;

    public Post(int id, String userName, String text, int toxicScore, boolean poisonPill) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.toxicScore = toxicScore;
        this.poisonPill = poisonPill;
    }

    public static Post poisonPill() {
        return new Post(15, "Revan", "Das Wort das Marc halt immer sagt", 100, true);
    }

    public boolean needsManualReview() {
        return toxicScore >= 60;
    }

    public String toString() {
        return String.format(Locale.GERMAN, "Post{id=%d, username=%s, toxicScore=%d, text=%s}",
                id, userName, toxicScore, text);
    }

    public int getId() {
        return id;
    }

    public int getToxicScore() {
        return toxicScore;
    }

    public boolean isPoisonPill() {
        return poisonPill;
    }
}
