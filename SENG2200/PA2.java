package PA2;
/**
  * @desc main class creates LinkedList and SortedList and populates them with PlanarShape objects
  * outputs unsorted and sorted lists
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
import java.io.*;
import java.util.*;
public class PA2
{
	public static void main(String[] args) throws IOException //Throws exception if file doesn't exist
	{
		LinkedList<PlanarShape> shape = new LinkedList<PlanarShape>(); //New linked list of planar shape objects
		SortedList<PlanarShape> shapeSorted = new SortedList<PlanarShape>();
		Point[] pointArray;
		Point newPoint;
		char shapeType;
		PlanarShape newShape;
		String filePath = args[0]; //Stores text file path from argument
		
		FileReader fileIn = new FileReader(filePath); //Filepath given from argument
		
		Scanner file = new Scanner(fileIn); //Scanner for reading text file
		
		while (file.hasNext()) //Checks for next value in file
		{
			if (file.hasNext("P")) //Checks for sentinel value denoting polygon
			{
				shapeType = 'P';
				file.next(); //Goes to next value
				
				pointArray = new Point[file.nextInt() + 1]; //Takes next int in file and uses it to initialise new shapegon object
				for (int i = 0; i < pointArray.length - 1; i++) //Checks for next double existing
				{
					newPoint = new Point(file.nextDouble(),file.nextDouble()); //Sets X to next double
					pointArray[i] = newPoint;
				}
				pointArray[pointArray.length - 1] = pointArray[0];
				newShape = ShapeFactory.createShape(shapeType, pointArray, 0);
				newShape.area();
				newShape.originDistance();
				shape.append(newShape);
			}
			else if (file.hasNext("C")) //Circle case
			{
				shapeType = 'C';
				file.next(); //Goes to next value
				
				pointArray = new Point[1]; //Initialise array
				newPoint = new Point(file.nextDouble(),file.nextDouble()); //Sets point
				pointArray[0] = newPoint;
				double radius = file.nextDouble();
				newShape = ShapeFactory.createShape(shapeType, pointArray, radius);
				newShape.area();
				newShape.originDistance();
				shape.append(newShape);
			}
			else if (file.hasNext("S")) //Semi Circle case
			{
				shapeType = 'S';
				file.next(); //Goes to next value
				
				pointArray = new Point[2]; //Initialises new Point array
				newPoint = new Point(file.nextDouble(),file.nextDouble()); //Sets first point
				pointArray[0] = newPoint;
				newPoint = new Point(file.nextDouble(),file.nextDouble()); //Sets second point
				pointArray[1] = newPoint;
				newShape = ShapeFactory.createShape(shapeType, pointArray, 0);
				newShape.area();
				newShape.originDistance();
				shape.append(newShape);
			}
		}
		file.close();
		
		Iterator<PlanarShape> itr1 = shape.iterator();
		while(itr1.hasNext())
		{
			PlanarShape object = itr1.next();
			System.out.println(object);	
			shapeSorted.insertInOrder(object);
		}
		
		System.out.println("");
		
		Iterator<PlanarShape> itr3 = shapeSorted.iterator();
		while(itr3.hasNext())
		{
			System.out.println(itr3.next());
		}
		System.exit(0);
	}
}

// Desc:    Shape Factory Class with createShape method
// Inputs:  char c, Point[] p
// Return:  PlanarShape myShape 
class ShapeFactory
{
	public static PlanarShape createShape (char c, Point[] p, double r)
	{
		PlanarShape myShape;

		switch (c)
		{
		case 'P':
			myShape = new Polygon(p);
			break;
		case 'C':
			myShape = new Circle(p, r);
			break;
		case 'S':
			myShape = new SemiCircle(p);
			break;
		default:
			myShape = null;
			break;
		}

		return myShape;
	}
}