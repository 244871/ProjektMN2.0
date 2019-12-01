package Utils;

import RootFinders.IRootFinder;
import RootFinders.KeplerEquation;


import java.util.ArrayList;

public class Planet {
    private String name;
    private double distance;
    private double revolution;
    private double eccentricity;
    private ArrayList<Coordinates> trajectory;

    private IRootFinder rootFinder;

    public String getRootFinderName() {
        return rootFinderName;
    }

    private String rootFinderName;


    public Planet(String name, double distance, double revolution, double eccentricity, IRootFinder rootFinder) {
        this.name = name;
        this.distance = distance;
        this.revolution = revolution;
        this.eccentricity = eccentricity;
        this.rootFinder = rootFinder;
        rootFinderName = rootFinder.getClass().getSimpleName();

        this.trajectory = new ArrayList<>();
    }

    public ArrayList<Coordinates> getTrajectory() {
        if(trajectory.size() < 1){
            trajectory = Trajectory.calculateTrajectory(this, rootFinder, new KeplerEquation(eccentricity));
        }

        return trajectory;
    }

    public String getPlanetTrajectoryCsv(){
        StringBuilder sb = new StringBuilder();
        for (Coordinates coord: trajectory
             ) {
            String line = coord.getX() + "," + coord.getY() + ";\n";
            sb.append(line);
        }
        return sb.toString();
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getRevolution() {
        return revolution;
    }

    public void setRevolution(double revolution) {
        this.revolution = revolution;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
