import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Welcome! Please only use integers when typing the initial two coordinates, in the format \"(x, y)\".\nEnter coordinate 1: ");
        String c1 = scan.nextLine();
        int x1 = Integer.parseInt(c1.substring(c1.indexOf("(") + 1, c1.indexOf(",")));
        int y1 = Integer.parseInt(c1.substring(c1.indexOf(" ") + 1, c1.indexOf(")")));
        System.out.print("Enter coordinate 2: ");
        String c2 = scan.nextLine();
        int x2 = Integer.parseInt(c2.substring(c2.indexOf("(") + 1, c2.indexOf(",")));
        int y2 = Integer.parseInt(c2.substring(c2.indexOf(" ") + 1, c2.indexOf(")")));

        if (x1 == x2) {
            System.out.println("\nThese points are on a vertical line: x = " + x1);
            System.exit(0);
        }

        LinearEquation equation = new LinearEquation(x1, y1, x2, y2);
        System.out.println("\n----- Line info -----");
        System.out.println(equation.lineInfo());

        System.out.print("\nEnter a value for x (can be decimal): ");
        double solveX = Double.parseDouble(scan.nextLine());
        System.out.println("\nCoordinate for x: " + equation.coordinateForX(solveX));
    }
}