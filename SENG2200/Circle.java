package PA2;
/**
  * @desc class holds methods to modify/view circle objects
  * implements methods to calculate area, distance to origin and toString
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
import java.util.*;
import java.lang.*;
public class Circle extends PlanarShape
{
	//Private member variables and array declaration
	private double radius;
	private double areaCircle;
	private double originDist;
	Point[] circlePoints;
	
	// Desc:    Creates a formatted string from array for output, with specified output length on area
    // Inputs:  N/A
    // Return:  Formatted String
	public String toString()
	{
		String point = ""; 
		point = point + circlePoints[0] + " ";
		String circleString = "CIRC = [ " + point + radius + " ]" + ": " + String.format("%5.2f", areaCircle);
		return circleString;
	}
	
	// Desc:    Calculates area of a circle and stores in member variable "areaCircle"
    // Inputs:  N/A
    // Return:  double areaCircle
	public double area()
	{
		areaCircle = 0;
		areaCircle = Math.PI * Math.pow(radius, 2); 
		return areaCircle;
	}
	
	// Desc:    Calculates distance to origin from closest point of circle
    // Inputs:  N/A
    // Return:  double originDist
	public double originDistance()
	{
		originDist = (Math.sqrt((Math.pow(circlePoints[0].getPointX(), 2) + Math.pow(circlePoints[0].getPointY(), 2))) - radius);
		return originDist;
	}
	
	// Desc:    Constructor for circle objects, initialises array of Points
    // Inputs:  Point array, double r
    // Return:  N/A
	public Circle(Point point[], double r)
	{
		circlePoints = point;
		radius = r;
		areaCircle = 0;
		originDist = 0;
	}
	
}