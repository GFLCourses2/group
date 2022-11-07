package executor.service.model;

public class Step {
    private String action;
    private String value;

    public Step() {}

    public Step(String action, String value) {
        this.action = action;
        this.value = value;

    }

    public String getAction() {
        return action;
    }

    public String getValue() {
        return value;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Step step = (Step)o;
        return this.action.equals(step.action) && this.value.equals(step.value);

    }
    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result += value != null ? value.hashCode() : 0;
        return 31 * result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"action\":\"" + action + "\"" +
                ",\"value\":\"" + value + "\"" +
                "}";
    }
}