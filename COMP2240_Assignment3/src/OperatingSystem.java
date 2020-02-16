//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.*;

public class OperatingSystem {

	private static final int loadPage = 6; //Constant time needed to load a page
	private List<Frame> frames = new ArrayList<Frame>(); //List of all the frames available to the OS
	private final RoundRobin scheduler; //The scheduler used
	private final FixedAllocation fixed; //The allocation type

	public OperatingSystem(RoundRobin scheduler, FixedAllocation fixed) { //Constructor
		this.scheduler = scheduler;
		this.scheduler.setOperatingSystem(this); //Set the scheduler's OS to this one
		this.fixed = fixed;
		this.fixed.setFrames(frames); //Equal to the value passed in as a command line argument
    }
	
	
	public void run(List<Process> processes) {
		initFrames(processes); //Sets the list of frames to the list of processes
		scheduler.start(processes); //Call the start function of the round robin scheduler
		while (scheduler.isRunning()) { //While scheduler has it's running value set to true
			tick(); //Do a system tick
		}
	}

	public void initFrames(List<Process> processes) {
		for (Process p: processes) { //For all processes in the list
			Frame pf = new Frame(p); //Create a new process frame using the process
			p.setFrame(pf); //Sets the process' frame to the newly created one, so now the frame and process are linked
			frames.add(pf); //Add the frame to the list stored in the OS
		}
	}

	public boolean isPaged(Process process, int instruction) { //Checks whether an instruction of a process is paged
		Frame frame = frames.get(frames.indexOf(process)); //Gets the frame containing the process
		boolean paged = frame.instructionLoaded(instruction); //Sets the boolean to true if the instruction is found in the list of pages of the process
		return paged; //Return the boolean
	}

	public void loadInstruction(Process process, int instruction) {
		
		Frame frame = frames.get(frames.indexOf(process)); //Gets the frame containing the process
		
		if (!frame.hasPage(instruction)) { //Check whether the frame has a page containing the instruction
			
			fixed.allocatePages(frame); //Allocate a set number of pages to a process, up to 50

			Page page = new Page(process, instruction); //Create the page for the instruction
			page.setTicksToWait(loadPage); //Set the number of ticks the page needs to wait before being loadable
			frame.addPage(page); //Add the page to the process' frame
		}
	}

	public void tick() {
		
		for (Frame frame: frames) { //For all frames
			for (Page page: frame.getPages()) { //For all pages in the frame
				if (!page.isLoaded()) { //If page isn't currently loaded, i.e is blocked or running
					int ticksLeft = page.getTicksToWait() - 1; //Reduce the number of ticks left to wait
					page.setTicksToWait(ticksLeft); //Set the page's tick left waiting
					page.setLastTick(scheduler.getCurrentTick()); //Set the last tick of the page to the current tick
          
					if (ticksLeft == 0) { //If the process is now ready to run
						page.getProcess().setState("Ready"); //Set its state to ready
					}
				}
			}
		}
		scheduler.tick(); //Call the tick function for the scheduler which updates the current tick and other stuff
	}
	
	public void instructionExecution(Process process, int instruction, int currentTick) { //When an instruction is executed
		
		Frame frame = frames.get(frames.indexOf(process)); //Get the process frame

		Page page = frame.getPage(instruction); //Get the page for the given instruction
		
		page.setLastTick(currentTick); 	//Update last seen by the page tick
	}

	public String print() { //Returns a formatted string
		String result = "PID  Turnaround Time  # Faults  Fault Times\n"; //The headings
		for (Frame frame: this.frames) { //For all frames
			Process process = frame.getProcess(); //Get the process out of the frame
			result += String.format("%-3d  %-15d  %-8d ", process.getId(), process.getCompletionTime(), process.getPageFaults().size()); //Create a formatted string
			result += " {"; 
			for (int i = 0; i < process.getPageFaults().size(); i++) { //For the size of the process' page fault list
				if (i != process.getPageFaults().size() - 1) { //If not the last in the list
					result += String.format("%d, ", process.getPageFaults().get(i)); //Add the page fault time to the string
				}
				else {
					result += String.format("%d", process.getPageFaults().get(i)); //The final one doesn't have the comma
				}
			}
			result += "}\n"; //Finishes the line and goes to the next line
		}
		return result; //Return the full string for output
	}
}