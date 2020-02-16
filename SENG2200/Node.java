package PA2;
/**
  * @desc class holds methods to modify/view node object variables
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
public class Node<PlanarShape>
{
	//Private member variables storing node references and data
	private PlanarShape data;
	private Node<PlanarShape> next;
	private Node<PlanarShape> previous;
	
	// Desc:    Constructor for creating a new node without references to other nodes
    // Inputs:  Polygon object as data
    // Return:  N/A
	public Node(PlanarShape object)
	{
		data = object;
		next = null;
		previous = null;
	}
	
	// Desc:    Constructor for creating a new node with references to other nodes
    // Inputs:  Polygon object as data, next and previous node references
    // Return:  N/A
	public Node(PlanarShape newData, Node<PlanarShape> next1, Node<PlanarShape> previous1)
	{
		data = newData;
		next = next1;
		previous = previous1;
	}
	
	// Desc:    Getters and setters for pointX and pointY variables
    // Inputs:  Polygon for data, Node references for next and previous (setters)
    // Return:  Data, next and previous references (getters)
	public Node getNext()
	{
		return next;
	}
	
	public Node getPrevious()
	{
		return previous;
	}
	
	public PlanarShape getData()
	{
		return data;
	}
	
	public void setData(PlanarShape object)
	{
		data = object;
	}
	
	public void setNext(Node n)
	{
		next = n;
	}
	
	public void setPrevious(Node p)
	{
		previous = p;
	}
}