package RootFinders;

public class KeplerEquation implements IEquation{
    private double M = 0.0;
    private double eccentricity;

    public KeplerEquation(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    @Override
    public double calculateEquation(double E) {
        return M + eccentricity * Math.sin(E) - E;
    }

    @Override
    public double calculateDerivativeValue(double E) {
       return  (1-  eccentricity  *Math.cos(E));
    }

    @Override
    public void updateParameter(double M) {
        this.M = M;
    }
}
