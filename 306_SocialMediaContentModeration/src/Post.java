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
        return new Post(-1, "Revan", "Das Wort das Marc halt immer sagt", -1, true);
    }

    public boolean needsManualReview() {
        return toxicScore >= 60;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", text='" + text + '\'' +
                ", toxicScore=" + toxicScore +
                ", poisonPill=" + poisonPill +
                '}';
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
