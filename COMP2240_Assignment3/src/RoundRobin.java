//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.Collection;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class RoundRobin {
	private int quantum = 3; //Time slice
	private boolean isRunning;	//Scheduler state
	private Process current; //Currently running process
	private int currentTick; //The current "tick" of the system, i.e the time run
	private OperatingSystem OS; //An operating system object

	private Queue<Process> readyQueue; //Queue of processes waiting
	private int timeSlice; //Holds how much time the process has left to run

	public RoundRobin(int quantum) {
		this.quantum = quantum; //Sets the time quantum provided as a command line argument
		readyQueue = new LinkedList<>(); //Makes a new linked list when round robin is called
		timeSlice = quantum; //Sets the time slice left to run to the quantum
	}

  	public boolean allBlocked(Collection<Process> list) {
  		for (Process p: list) { //For all processes
  			if (!p.getState().equals("Blocked")) { //If their state isn't equal to blocked
  				return false; //Return false as not all processes are blocked
  			}
  		}
  		return true; //Otherwise if they are, return true
  	}

  	public Process getNextProcess() {
  		
  		Process process = readyQueue.peek(); //Initialised to the head of the queue
  		
  		for (Process p: readyQueue) { //For all processes
  			if (p.getState().equals("Ready")) { //If the process is ready to run
  				process = p; //Set process to p
  				break; //Break from the loop
  			}
  		}

  		readyQueue.remove(process); //Remove the process from the ready queue

  		return process; //Return it
  	}
  
  	public void start(List<Process> newProcesses) {
  		isRunning = true; //Sets the boolean for running to true
  		currentTick = -1; //Current tick is -1 as it begins processing at 0
  	
  		for (Process process: newProcesses) { //For all processes
			readyQueue.add(process); //Add the processes to the ready queue
		}
  		
  		if (!readyQueue.isEmpty()) { //If there are processes waiting to be run
			current = readyQueue.poll(); //Remove the head of the queue and make current process equal to it
		}
  	}

  	public void tick() { 
  		currentTick++; //Increment the current system tick
  		
  		if (current != null) { //Current process exists
			
			while (current.getState().equals("Ready")) { //While processes are ready to run
				
				int nextInstruction = current.nextInstruction(); //Get next instruction
				
				if (OS.isPaged(current, nextInstruction)) { //Check if instruction is paged
					
					current.executeInstruction(); //Execute it, which removes it from the queue
					timeSlice--; //Decrement the time slice

					OS.instructionExecution(current, nextInstruction, currentTick); //Handles the execution of the instruction by updating run values
					break; //Leave loop
				}
				else {
					current.addPageFault(currentTick); //Add page fault for current process at the current tick

					OS.loadInstruction(current, current.nextInstruction()); //Load the instruction
					current.setState("Blocked"); //Set the current process' state to blocked

					readyQueue.offer(current); //Add current back into the queue at the tail

					timeSlice = quantum; //Reset the time slice value to a full quantum
					current = getNextProcess(); //Get the next ready process in the queue and make it the current process
				}
			}

			if (current.isComplete()) {  //Process has finished
				current.setCompletionTime(getCurrentTick() + 1); //Set the completion time of the process
				current.setState("Finished"); //Set the process' state to finished
				current = null; //Current is set to null
			}

			else if (timeSlice == 0) { //If the current process has run for a quantum without completing
				if (readyQueue.isEmpty() || allBlocked(readyQueue)) { //If the ready queue has nothing waiting or all the processes are blocked waiting
					timeSlice = quantum; //Reset the quantum and run the current process again
				}
				else {
					readyQueue.offer(current); //Return the current to the queue
					current = null; //Null the current
				}
			}
		}

		if (current == null && !readyQueue.isEmpty()) { //If there's no current process and the ready queue has processes waiting
			current = getNextProcess(); //Get the next waiting
			timeSlice = quantum; //Reset the time slice left
		}
		else if (readyQueue.isEmpty() && current == null) { //Else if current is null and there's nothing waiting
			isRunning = false; //Stop the scheduler
		}
  	}

  	public boolean isRunning() { //Boolean for whether the scheduler is running
  		return isRunning;
  	}

  	//Getters and setters
  	public Process getCurrentProcess() {
  		return current;
  	}
  
  	public void setCurrentProcess(Process currentProcess){
  		this.current = currentProcess;
  	}

  	public int getCurrentTick() {
  		return currentTick;
  	}

  	public OperatingSystem getOperatingSystem() {
  		return OS;
  	}
  
  	public void setOperatingSystem(OperatingSystem system){
  		this.OS = system;
  	}
}

