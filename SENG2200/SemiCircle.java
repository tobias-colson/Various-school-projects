package PA2;
/**
  * @desc class holds methods to modify/view semi circle objects
  * implements methods to calculate area, distance to origin and toString
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
import java.util.*;
import java.lang.*;
public class SemiCircle extends PlanarShape
{
	//Private member variables and array declaration
	private double areaSemi;
	private double originDist;
	Point[] semiPoints;
	
	// Desc:    Creates a formatted string from array for output, with specified output length on area
    // Inputs:  N/A
    // Return:  Formatted String
	public String toString()
	{
		String points = "";
		points = points + semiPoints[0] + " " + semiPoints[1] + " ";
		String semiString = "SEMI = [ " + points + "]" + ": " + String.format("%5.2f", areaSemi);
		return semiString;
	}
	
	// Desc:    Calculates area of a semi circle and stores in member variable "areaSemi"
    // Inputs:  N/A
    // Return:  areaSemi
	public double area()
	{
		double radius = Math.sqrt((Math.pow(semiPoints[1].getPointX() - semiPoints[0].getPointX(), 2) + (Math.pow(semiPoints[1].getPointY() - semiPoints[0].getPointY(), 2))));

		areaSemi = Math.PI * Math.pow(radius, 2);
		areaSemi = areaSemi / 2; //Divides sum by 2 for actual area
		return areaSemi;
	}
	
	// Desc:    Calculates distance to origin for both points and extremities of semi circle and determines the closest of them
    // Inputs:  N/A
    // Return:  double originDist
	public double originDistance()
	{
		double slope = (semiPoints[0].getPointY() - semiPoints[1].getPointY()) / (semiPoints[0].getPointX() - semiPoints[1].getPointX());
		double radius = Math.sqrt((Math.pow(semiPoints[1].getPointX() - semiPoints[0].getPointX(), 2) + (Math.pow(semiPoints[1].getPointY() - semiPoints[0].getPointY(), 2))));
		double originTemp, originTemp2, originTemp3; //Stores temporary value of calculation to be compared against stored value
		double tempX1;
		double tempX2;
		double tempY1;
		double tempY2;
		
		tempX1 = semiPoints[0].getPointX() + Math.sqrt(Math.pow(radius, 2) / (1 + (1 / Math.pow(slope, 2))));
		tempX2 = semiPoints[0].getPointX() - Math.sqrt(Math.pow(radius, 2) / (1 + (1 / Math.pow(slope, 2))));
		tempY1 = semiPoints[0].getPointY() - ((1 / slope) * (tempX1 - semiPoints[0].getPointX()));
		tempY2 = semiPoints[0].getPointY() - ((1 / slope) * (tempX2 - semiPoints[0].getPointX()));
		
		originDist = Math.sqrt((Math.pow(semiPoints[0].getPointX(), 2) + Math.pow(semiPoints[0].getPointY(), 2))); //Initialises originDist with distance of first point using Pythagoras
		originTemp = Math.sqrt((Math.pow(semiPoints[1].getPointX(), 2) + Math.pow(semiPoints[1].getPointY(), 2))); //Does the same for originTemp
		originTemp2 = Math.sqrt((Math.pow(tempX1, 2)) + (Math.pow(tempY1, 2)));
		originTemp3 = Math.sqrt((Math.pow(tempX2, 2)) + (Math.pow(tempY2, 2)));
		if (originDist > originTemp) //Checks whether newly calculated value is smaller than current stored value
		{
			originDist = originTemp; //If it is stores temp value in member variable
		}
		else if (originDist > originTemp2)
		{
			originDist = originTemp;
		}
		else if (originDist > originTemp3)
		{
			originDist = originTemp;
		}
		return originDist;
	}
	
	// Desc:    Constructor for semi circle objects, initialises array of Points
    // Inputs:  Point array
    // Return:  N/A
	public SemiCircle(Point point[])
	{
		semiPoints = point;
		areaSemi = 0;
		originDist = 0;
	}	
}