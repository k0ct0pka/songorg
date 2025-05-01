package constants;

public enum Session {
    USER("user"), RECOMMENDED_SONGS_FOR_USER("recommendedSongsForUser");
    private final String name;
    private Session(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
