//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.List;

public abstract class ReplacePage {
 
	private final int maxFrames; //The max number of frames available to the algorithm

	//Constructor
	public ReplacePage(int maxFrames) {
		this.maxFrames = maxFrames;
	}

	//Getter
	public int getMaxFrames() {
		return this.maxFrames;
	}

	//Removes a page dependent on implementation in the extending class
	public abstract Page getPageToRemove(List<Page> pages);
	
}

