package PA2;
/**
  * @desc class is a linked list holding planarshape objects
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
import java.util.*;
public class LinkedList<PlanarShape>  implements Iterable<PlanarShape>
{
	private Node<PlanarShape> sentinel;
	
    // Desc:    Constructor for LinkedList objects
    // Inputs:  N/A
    // Return:  N/A
	public LinkedList()
	{
		sentinel = new Node<PlanarShape>(null);
		sentinel.setNext(sentinel); //Sets next and previous references to sentinel
		sentinel.setPrevious(sentinel);
	}
	
    // Desc:    Adds a new PlanarShape object into a Node and inserts that Node into the LinkedList
    // Inputs:  PlanarShape object, Node<PlanarShape> n
    // Return:  N/A
	public void add(PlanarShape object, Node<PlanarShape> n) 
	{
		Node<PlanarShape> shape = new Node<PlanarShape>(object);
		Node<PlanarShape> temp = n.getPrevious();
		shape.setNext(n);
		n.setPrevious(shape);		 
		shape.setPrevious(temp); 
		temp.setNext(shape);
	}
	
    // Desc:    Adds a new PlanarShape object into a Node and calls add()
    // Inputs:  PlanarShape object
    // Return:  N/A
	public void prepend (PlanarShape object)
	{
		Node<PlanarShape> temp = sentinel.getNext();
		add(object, temp);
	}
	
    // Desc:    Adds a new PlanarShape object into a Node and calls add()
    // Inputs:  PlanarShape object
    // Return:  N/A
	public void append (PlanarShape object)
	{
		Node<PlanarShape> temp = sentinel;
		add(object, temp);
	}
	
    // Desc:    Override of the iterator method of iterable interface
    // Inputs:  N/A
    // Return:  Iterator<PlanarShape>
	public Iterator<PlanarShape> iterator()
	{
        Iterator<PlanarShape> iter = new Iterator<PlanarShape>() 
        {
            private Node<PlanarShape> nextNode = sentinel.getNext();
            
            public boolean hasNext()
            {
                return nextNode.getData() != null;
            }
            
            public PlanarShape next()
            {
                if (this.hasNext())
                {
                    PlanarShape object = nextNode.getData();
                    nextNode = nextNode.getNext();
                    return object;
                }
                else
                {
                    throw new NoSuchElementException();
                }
            }
            
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
        return iter;
	}   
}