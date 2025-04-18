package constants;

public enum Session {
    USER("user");
    private final String name;
    private Session(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
