package RootFinders;

public abstract class RootFinder {
    public double epsilon;

    public RootFinder() {
        this.epsilon = 0.01;
    }

    public RootFinder(double epsilon) {
        this.epsilon = epsilon;
    }

    protected double calculateEpsilonA(double xn, double xn1){
        return ((xn-xn1)/xn)*100;
    }

}
