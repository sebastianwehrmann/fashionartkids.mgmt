package fashionartkids.mgmt.model.talent;

public enum Gender {
    MALE("männlich"),
    FEMALE("weiblich"),
    DIVERSE("divers");

    public final String displayValue;

    Gender(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
