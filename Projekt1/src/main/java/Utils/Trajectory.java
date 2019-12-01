package Utils;

import RootFinders.IEquation;
import RootFinders.IRootFinder;
import RootFinders.Parameters;

import java.util.ArrayList;

public class Trajectory {

    public static ArrayList<Coordinates> calculateTrajectory(Planet planet, IRootFinder rootFinder, IEquation equation){
        ArrayList<Coordinates> trajectory = new ArrayList<>();

        double M = Trajectory.calculateNewM( planet.getRevolution(),0);
        int i = 0;
        while(planet.getRevolution() >= i){
            equation.updateParameter(M);
            double E = rootFinder.findRoot(equation);

            double posX = planet.getDistance() * Math.cos(E - planet.getEccentricity());
            double posY = planet.getDistance() * Math.sqrt(1 - Math.pow(planet.getEccentricity(), 2))*Math.sin(E);

            trajectory.add(new Coordinates(posX, posY));
            M = Trajectory.calculateNewM(planet.getRevolution(), Parameters.step * i);
            i++;
        }

        return trajectory;
    }

    private static double calculateNewM(double T, double t0){
        return 2*Math.PI / T * t0;
    }

}
