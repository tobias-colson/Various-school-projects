package PA2;
/**
  * @desc class holds methods to modify/view polygon objects
  * implements methods to calculate area, distance to origin and comesBefore(custom comparable)
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
import java.util.*;
import java.lang.*;
public class Polygon extends PlanarShape
{
	//Private member variables and array declaration
	private int numSides;
	private double areaPoly;
	private double originDist;
	Point[] polyPoints;
	
	// Desc:    Creates a formatted string from array for output, with specified output length on area
    // Inputs:  N/A
    // Return:  Formatted String
	public String toString()
	{
		numSides = polyPoints.length;
		String points = "";
		for (int i = 0; i < numSides; i++)
		{
			points = points + polyPoints[i] + " ";
		}
		String polyString = "POLY = [ " + points + "]" + ": " + String.format("%5.2f", areaPoly);
		return polyString;
	}
	
	// Desc:    Calculates area of a polygon and stores in member variable "areaPoly"
    // Inputs:  N/A
    // Return:  double areaPoly
	public double area()
	{
		areaPoly = 0;
		numSides = polyPoints.length - 1;
		for (int i = 0; i < numSides; i++) //For loop to run through all points in array
		{
			areaPoly = (areaPoly + ((polyPoints[i+1].getPointX() - polyPoints[i].getPointX()) * (polyPoints[i+1].getPointY() + polyPoints[i].getPointY()))); //Sums (X+1 - X) * (Y+1 - Y) for all values of X and Y
		}
		areaPoly = areaPoly / 2; //Divides sum by 2 for actual area
		return areaPoly;
	}
	
	// Desc:    Calculates distance to origin for all points in array and determines the closest of them
    // Inputs:  N/A
    // Return:  double originDist
	public double originDistance()
	{
		numSides = polyPoints.length - 1;
		double originTemp; //Stores temporary value of calculation to be compared against stored value
		originDist = Math.sqrt((Math.pow(polyPoints[0].getPointX(), 2) + Math.pow(polyPoints[0].getPointY(), 2))); //Initialises originDist with distance of first point using Pythagoras
		for (int i = 1; i < numSides + 1; i++)
		{
			originTemp = Math.sqrt((Math.pow(polyPoints[i].getPointX(), 2) + Math.pow(polyPoints[i].getPointY(), 2))); //Does the same for originTemp
			
			if (originDist > originTemp) //Checks whether newly calculated value is smaller than current stored value
			{
				originDist = originTemp; //If it is stores temp value in member variable
			}
		}
		return originDist;
	}
	
	// Desc:    Constructor for polygon objects, initialises array of Points
    // Inputs:  Point array
    // Return:  N/A
	public Polygon(Point point[])
	{
		polyPoints = point;
		areaPoly = 0;
		originDist = 0;
	}
}