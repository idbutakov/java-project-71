package hexlet.code;

public final class Difference implements Comparable<Difference> {
    public enum Status {
        ADDED, REMOVED, MODIFIED, UNCHANGED
    }

    private final String key;
    private final Status status;
    private final Object value1;
    private final Object value2;

    public Difference(String key, Status status, Object value1, Object value2) {
        this.key = key;
        this.status = status;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }

    @Override
    public int compareTo(Difference other) {
        return this.key.compareTo(other.key);
    }
}
