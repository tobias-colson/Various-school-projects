//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.List;

public class LRU extends ReplacePage {
	
	//Constructor
	public LRU(int maxFrames) {
		super(maxFrames);
	}

	@Override
  	public Page getPageToRemove(List<Page> pages) {
	  
	  	Page removedPage = null; //The page that will be removed
    
	  	for (Page page: pages) { //For all pages
	  		if (removedPage == null || (page.getLastTick() < removedPage.getLastTick() && page.isLoaded())) { //If the page doesn't exist or is loaded and has the lowest last tick, i.e least recent use
	  			removedPage = page; //Set the page to be removed
	  		}
	  	}
	  	return removedPage; //Return the page to be removed
  	}
}
