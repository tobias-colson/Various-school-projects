//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.*;

public class Frame {

	private Process process; //The frame's associated process
	private List<Page> pages; //The frame's list of pages
	private int maximumPages; //How many pages the frame handles
	
	//Constructor
	public Frame(Process process) {
		this.process = process; //Sets the frame's process
		this.pages = new ArrayList<Page>(); // Creates a new list of pages for the current process
	}
	
	public boolean instructionLoaded(int instruction) { //Is the instruction currently loaded
		for (Page page: pages) { //For all pages in this frame
			if (page.getInstruction() == instruction && page.isLoaded()) { //If the argument instruction is in the list and that page is loaded currently
				return true; //It's loaded
			}
		}
	    return false; //It's not
	}

	//Is the number of pages greater than the max allowed
	public boolean hasMaxPages() {
		return pages.size() >= maximumPages;
	}
	
	//Add a new page to the list
	public void addPage(Page page) {
		pages.add(page);
	}
	
	//Get the page containing this instruction
	public Page getPage(int instruction) {
		for (Page page: pages) {
			if (page.getInstruction() == instruction) {
				return page;	
			}
		}
		return null;
	}
	
	//Does the page containing the instruction exist
	public boolean hasPage(int instruction) {
		return getPage(instruction) != null;
	}

	//Getters and setters
	public Process getProcess() {
		return process;
	}
  
	public void setProcess(Process process){
		this.process = process;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages){
		this.pages = pages;
 	}
	
	public int getMaximumPages() {
		return maximumPages;
	}
  
	public void setMaximumPages(int maximumPages){
		this.maximumPages = maximumPages;
	}

	//Override of equals for comparing processes or pages
	@Override
	public boolean equals(Object other) {
		
		//For processes
		if (other instanceof Process) { //If other is a process type
			Process o = (Process)other; //Cast other to a process to ensure type correctness
			return getProcess() == o; //Return their equality
		}

		//For pages
		if (other instanceof Page) { //Same as process
			Page o = (Page)other; //Ditto
			return process == o.getProcess() && pages.contains(o); //Processes are the same and the page is in the list
		}

		//Default otherwise the function doesn't compile as it may not return
		return this == other;
	}
}