package PA2;
/**
  * @desc class holds methods to modify/view point values 
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
public class Point
{
	//Private member variables storing double values for coordinates
	private double pointX;
	private double pointY;
	
	// Desc:    Creates formatted string from point variables for output
    // Inputs:  N/A
    // Return:  Formatted string
	public String toString()
	{
		String pointString = "(" + String.format("%4.2f", pointX) + "," + String.format("%4.2f", pointY) + ")";
		return pointString;
	}
	
	// Desc:    Getters and setters for pointX and pointY variables
    // Inputs:  doubles for pointX and pointY (setters)
    // Return:  pointX and pointY (getters)
	public void setPointX(double x)
	{
		pointX = x;
	}
	
	public void setPointY(double y)
	{
		pointY = y;
	}
	
	public double getPointX()
	{
		return pointX;
	}
	
	public double getPointY()
	{
		return pointY;
	}
	
	// Desc:    Parameterised constructor for Point class
    // Inputs:  doubles for pointX and pointY
    // Return:  N/A
	public Point(double x, double y)
	{
		pointX = x;
		pointY = y;
	}
}