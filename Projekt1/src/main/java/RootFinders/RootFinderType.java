package RootFinders;

public enum RootFinderType {
    BISECTION("Bisection"),
    NEWTON("Newton"),
    REGULAFALSI("Fegula Falsi"),
    FIXEDPOINT("Fixed Point"),
    SECANT("Secant");

    private String label;

    RootFinderType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
