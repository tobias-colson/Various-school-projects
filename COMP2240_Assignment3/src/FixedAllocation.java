//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.List;

/**
 * Allocates a fixed number of pages to each process
 * that does not change over the course of execution
 * using a local replacement strategy
 */
public class FixedAllocation {

	private List<Frame> frames; //List of processframes

	private final int maxFrames; //Maximum number of frames allowed
	private ReplacePage replaceType; //An abstract object that is implemented as either type at runtime
	
	//Constructor
	public FixedAllocation(int maxFrames, boolean type) {
		this.maxFrames = maxFrames;
		this.setReplacementScheme(type);
	}

	public void allocatePages(Frame frame) { //Allocates a set number of pages to a frame
		if (frame.getMaximumPages() == 0) { //If the frame has maximumPages equal to 0 
			int processPages = maxFrames / frames.size(); //Set the number of pages per process to an even slice of frames
			frame.setMaximumPages(processPages); //The max pages to that frame
		}

		if (frame.hasMaxPages()) { //If the frame has more pages in its list than frames allocated
			Page page = replaceType.getPageToRemove(frame.getPages()); //Find a page to remove from the frame determined by the algorithm in question
			frame.getPages().remove(page); //Remove that page from the list
		}
 	}
	private void setReplacementScheme(Boolean type) { //Simply checks whether the replacement scheme should be LRU or clock based on the boolean provided
		if (type) {
			replaceType = new Clock(maxFrames);
		}
		else {
			replaceType = new LRU(maxFrames);
		}
	}
	
	//Used to fill the frame list used in allocation
	public void setFrames(List<Frame> frames){
		this.frames = frames;
	}
}