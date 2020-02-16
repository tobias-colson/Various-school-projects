package PA2;
/**
  * @desc class holds methods to modify/view point values 
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
import java.lang.*;
import java.util.*;
public abstract class PlanarShape implements Comparable<PlanarShape>
{
	//Abstract declarations for methods to be declared in subclasses
	public abstract String toString();
	public abstract double area();
	public abstract double originDistance();
	
	// Desc:    Overrides the compareTo method from Comparable
    // Inputs:  N/A
    // Return:  Int representing equality of shapes
	@Override
	public int compareTo(PlanarShape other)
	{
		if (this.area() < other.area()) //If area of first shape is smaller than second
		{
			return 1;
		}
		else if (Math.abs((this.area() - other.area()) / other.area()) < 0.0005) //Checks whether areas are within 0.05% of each other, considered equal if they are
		{
			if (Math.abs(this.originDistance()) > Math.abs(other.originDistance())) //Checks for difference in distance to origin to determine order
			{
				return 1;
			}
			else if (Math.abs(this.originDistance()) < Math.abs(other.originDistance()))
			{
				return -1;
			}
			else 
			{
				return 0;
			}
		}
		else
		{
			return -1;
		}
	}
}