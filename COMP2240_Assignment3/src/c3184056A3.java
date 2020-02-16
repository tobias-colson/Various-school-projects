//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.List;
import java.util.LinkedList;

public class c3184056A3 {

	public static void main(String[] args) {
		
		//load processes from file
		List<Process> processes = new LinkedList<Process>(); //Linked list of processes
		List<Process> processes2 = new LinkedList<Process>(); //Linked list of processes again, necessary to create a proper clone
		int maxFrames = Integer.parseInt(args[0]); //Command argument for pages
		int quantum = Integer.parseInt(args[1]); //Same but for time slice
		
		for (int i = 2; i < args.length; i++) {	//For each argument in the command line
			String path = args[i]; //Get the text file path
			Process p = Process.parseFile(path, i - 1); //Creates a new process object and initialises it using a function in Process
			Process p2 = Process.parseFile(path, i - 1); //Create another object because Java
			if (p != null) {	//If p exists
				processes.add(p);	//Add the process to the list
				processes2.add(p2); //Add it to the second list
			}
		}
   
		if (!processes.isEmpty()) { //There are processes in the list
			
			RoundRobin scheduler = new RoundRobin(quantum); //Creates a new instance of the Round Robin scheduler for controlling time allocation
			FixedAllocation fixed = new FixedAllocation(maxFrames, false); //Set the maximum number of frames and the boolean for the replacement type
			
			System.out.println("LRU - Fixed:"); //Formatting
			
			OperatingSystem os = new OperatingSystem(scheduler, fixed); //Starts a new Operating system instance, using the scheduler and scheme above
			os.run(processes); //Run the os? Uses the processes list we cloned
			
			System.out.print(os.print()); //A toString() for the process list
			
			System.out.println("\n-----------------------------------------------------");

			fixed = new FixedAllocation(maxFrames, true);
    		
			System.out.println("\nClock - Fixed:"); //More printing
    		
			os = new OperatingSystem(scheduler, fixed); //Restart the os
    		os.run(processes2); //Run again
    		
    		System.out.println(os.print());
		}
	}
}