package RootFinders;

public class RegulaFalsi extends RootFinder implements IRootFinder {
    @Override
    public double findRoot(IEquation equation) {
        return calculate(equation);
    }

    private double calculate(IEquation equation){

        double a = -20; //xl
        double b = 20; //xu

        if (equation.calculateEquation(a) * equation.calculateEquation(b) >= 0)
        {
            return 0;
        }

        double c = a;
        while (calculateEpsilonA(b,c) >= epsilon)
        {
            // Find middle point
            c = (equation.calculateEquation(b)*(a-b))/(equation.calculateEquation(a)-equation.calculateEquation(b));

            // Check if middle point is root
            if (equation.calculateEquation(c) == 0.0)
                break;

                // Decide the side to repeat the steps
            else if (equation.calculateEquation(c)*equation.calculateEquation(a) < 0)
                b = c;
            else
                a = c;
        }
        return c;
    }
}
