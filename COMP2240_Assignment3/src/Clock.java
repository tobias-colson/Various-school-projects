//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.List;

public class Clock extends ReplacePage {
	
	//Constructor that simply grabs the maxFrames value used in declaring the abstract
	public Clock(int maxFrames) {
		super(maxFrames);
	}

	@Override
	public Page getPageToRemove(List<Page> pages) {
		
		Page removedPage = null; //A blank page

		while (removedPage == null) { //While we haven't found a page to remove
			for (Page page : pages) {
				
				if (page.getReferenceBit() == false) { //If reference bit is 0
					removedPage = page; //Page to be removed is equal to this page
					break; //Exit loop
				}

				page.setReferenceBit(!page.getReferenceBit()); //Switch the value of the reference bit as we cycle through the pages
			}
		}

		return removedPage; //Return the page to remove from memory
	}
}
