package fashionartkids.mgmt.model.talent;

public enum EyeColor {
    BLUE("Blau"),
    GREEN("Grün"),
    BROWN("Braun");

    public final String displayValue;

    EyeColor(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
