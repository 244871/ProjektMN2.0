package RootFinders;

public class FixedPoint extends RootFinder implements IRootFinder {
    @Override
    public double findRoot(IEquation equation) {
        return calculate(equation);
    }


    private double calculate(IEquation equation){

        double a = -20; //xl
        double c = 20; //xu

        if (equation.calculateEquation(a) * equation.calculateEquation(c) >= 0)
        {
            return 0;
        }


        while (calculateEpsilonA(c,a) >= epsilon)
        {
            // Find middle point
            c = equation.calculateEquation(a)+a;

            // Check if middle point is root
            if (equation.calculateEquation(c) == 0.0)
                break;

                // Decide the side to repeat the steps
            else if (equation.calculateEquation(c)*equation.calculateEquation(a) < 0)
                a = c;
            else
                break;
        }
        return c;
    }
}
