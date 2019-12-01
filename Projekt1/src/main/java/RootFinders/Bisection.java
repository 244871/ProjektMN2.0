package RootFinders;

public class Bisection extends RootFinder implements IRootFinder {

    public Bisection() {
    }

    public Bisection(double epsilon) {
        super(epsilon);
    }

    @Override
    public double findRoot(IEquation equation) {
        return calculate(equation);
    }


    private double calculate(IEquation equation){

        double a = -20; //przyjmujemy parametry początkowe
        double b = 20;

        if (equation.calculateEquation(a) * equation.calculateEquation(b) >= 0)
        {
            return 0;
        }


        double c=a;
        while (b-a >= epsilon)
        {

            // Find middle point
            c = (a+b)/2;

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
