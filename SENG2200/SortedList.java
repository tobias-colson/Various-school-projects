package PA2;
/**
  * @desc class extends linkedlist as a sorted variation
  * @author Tobias Colson C3184056
  *	6/05/2018
*/
import java.util.*;
public class SortedList<PlanarShape extends Comparable<PlanarShape>> extends LinkedList<PlanarShape> implements Iterable<PlanarShape>
{
	private Node<PlanarShape> sentinel; //The sentinel node, used to create the lists and override the iterator                                                                                                                                                                                                       
	
    // Desc:    Constructor for a new SortedList object, with sentinel circularly referencing itself
    // Inputs:  N/A
    // Return:  N/A
	public SortedList()
	{
		sentinel = new Node(null);
		sentinel.setNext(sentinel); //Sets next and previous references to sentinel
		sentinel.setPrevious(sentinel);
	}
	
    // Desc:    Inserts a new PlanarShape object into the list in order according to the compareTo function
    // Inputs:  PlanarShape object
    // Return:  N/A
	public void insertInOrder(PlanarShape object)
	{ 
		Node<PlanarShape> current = sentinel.getNext();
		if (current.getData() == null)
		{
			add(object, current);
			return;
		}
		else if (current.getData() != null)
		{
			while (current.getData() != null)
			{
				if (object.compareTo(current.getData()) > 0)
				{
					add(object, current);
					current = sentinel;
					return;
				}
				else if (object.compareTo(current.getData()) <= 0)
				{
					current = current.getNext();
				}
			}
			add(object, current);
		}
	}
	
    // Desc:    Override of the iterator method of iterable interface
    // Inputs:  N/A
    // Return:  Iterator<PlanarShape>
	public Iterator<PlanarShape> iterator()
	{
        Iterator<PlanarShape> iter = new Iterator<PlanarShape>() 
        {
            private Node<PlanarShape> nextNode = sentinel.getNext();    //Node object used in function overrides
            
            public boolean hasNext() //Returns a boolean for whether nextNode contains non-NULL data
            {
                return nextNode.getData() != null;
            }
            
            public PlanarShape next()   //Returns the PlanarShape object stored in nextNode
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
            
            public void remove()    //Returns an exception if called
            {
                throw new UnsupportedOperationException();
            }
        };
        return iter;
	}   
}
