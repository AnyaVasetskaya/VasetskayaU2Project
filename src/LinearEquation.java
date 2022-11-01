import java.text.DecimalFormat;

public class LinearEquation {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private static final DecimalFormat df = new DecimalFormat("0.00");



    public LinearEquation(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }



    public double distance() {
        return roundedToHundredth(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }



    public double yIntercept() { return roundedToHundredth(y1 - x1 * slope()); }



    public double slope() { return (y2 - y1 * 1.0)/(x2 - x1) == 0 ? 0.0 : roundedToHundredth((y2 - y1 * 1.0)/(x2 - x1)); }



    /*All the extra methods below (before the equation() method) are essentially just methods
      that help the legibility of the equation() method and make it much shorter and easier to understand when
      it's split up into separate methods like this, instead of just being one confusing, long collection of if-else statements.*/
    private boolean isSlopeInt() {
        return ((slope()) * 10) % 10 == 0;
    }

    /*Creates the first part of an equation with a whole number slope*/
    private String intSlopeEqPrefix() {
        return slope() == 1 ? "y = x"
                : slope() == -1 ? "y = -x"
                : slope() == 0 ? "y ="
                : "y = " + (int) (slope()) + "x";
    }

    /*Creates the second part of an equation
      with a whole number slope based on the y-intercept.*/
    private String intSlopeEqSuffix() {
        double yInt = yIntercept();
        double yIntAbs = Math.abs(yInt);
        if (yInt == 0)
            return slope() == 0 ? " 0" : "";
        else if (yInt > 0)
            return slope() == 0 ? "" + (int) yInt : " + " + yInt;
        else
            return " -" + (slope() == 0 ? (int) yIntAbs : " " + yIntAbs);

    }

    //Adds the two parts together to create an equation (with an integer slope)
    private String intSlopeEquation() {
        return intSlopeEqPrefix() + intSlopeEqSuffix();
    }

    //Checks if both the numerator and denominator of a fractional slope are negative
    /*This is used in the method after this one, that eliminates double negatives
      in case of a fractional slope (e.g. -5/-4 becomes 5/4)*/
    private boolean isNumDenomNeg() {
        return  (y2 - y1) < 0 && (x2 - x1) < 0;
    }

    /*Creates the first part of an equation with a fractional slope*/
    private String nonIntSlopeEqPrefix() {
        if (isNumDenomNeg()) return "y = (" + Math.abs(y2 - y1) + "/" + Math.abs(x2 - x1) + ")x";
        else if ((x2 - x1) < 0) return "y = (-" + (y2 - y1) + "/" + Math.abs(x2 - x1) + ")x";
        else return "y = (" + (y2 - y1) + "/" + (x2 - x1) + ")x";
    }

    /*Creates the second part of an equation
      with a fractional slope based on the y-intercept.*/
    private String nonIntSlopeEqSuffix() {
        if (yIntercept() == 0) return "";
        else if (yIntercept() > 0) return " + " + yIntercept();
        else return " - " + Math.abs(yIntercept());
    }

    //Adds the two parts together to create an equation (with a fractional slope)
    private String nonIntSlopeEquation() {
        return nonIntSlopeEqPrefix() + nonIntSlopeEqSuffix();
    }

    //Uses the previous 8 methods to concisely create an equation for every possible situation of pairs of coordinates
    public String equation() {
        return isSlopeInt() ? intSlopeEquation() : nonIntSlopeEquation();
    }



    public String coordinateForX(double x) {
        double y = roundedToHundredth(slope() * x + yIntercept());
        return "(" + x + ", " + y + ")";
    }



    public double roundedToHundredth(double toRound) { return Double.parseDouble(df.format(toRound)); }



    public String lineInfo() {
        return ("The two points are: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")"
                + "\nThe equation of the line between these points is: " + equation()
                + "\nThe slope of the line is: " + slope()
                + "\nThe y-intercept of the line is: " + yIntercept()
                + "\nThe distance between the two points is: " + distance());
    }

}
