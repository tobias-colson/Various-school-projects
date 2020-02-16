//Tobias Colson 3184056
//COMP2240 Assignment 3

import java.util.*;
import java.io.File;
import java.util.Scanner;

public class Process {

	private String state; //Holds the state of the process, either Ready, Blocked or Finished
	private Queue<Integer> instructions; //A queue of instructions in the process
	private List<Integer> pageFaults; //List holding every page fault experienced by the process
	private int id; //The process id
	private int completionTime = -1; //Completion time of the process
	private Frame frame; //The frame holding the process

	//Constructor
	public Process(Queue<Integer> instructions, int id) {
		this.instructions = instructions;
		this.id = id;
		this.pageFaults = new ArrayList<>();
		this.state = "Ready";
	}
	
	public static Process parseFile(String filePath, int pid) {
		try {
			File f = new File(filePath); //Sets the file path equal to the argument provided in command line
			Scanner s = new Scanner(f); //The file scanner

			List<String> lines = new LinkedList<String>(); //A list of all the lines in the file
			while (s.hasNextLine()) { //While the file has more lines
				lines.add(s.nextLine()); //Add the line to the list
			}
			
			Queue<Integer> instructions = new LinkedList<Integer>(); //Initialise the queue of instructions
			
			for (int i = 0; i < lines.size(); i++) { //For all lines in the file
				if (lines.get(i).equals("begin")) { //If the line is begin do nothing
					
				}
				else if (lines.get(i).equals("end")) { //If the line is end break the loop
					break;
				}
				else { //Else add the number to the instructions queue
					instructions.offer(Integer.parseInt(lines.get(i)));
				}
			}
      
			s.close(); //Close the scanner
			return new Process(instructions, pid); //Returns the process 
		}
		catch (Exception e) {
			System.out.println(e.getMessage()); //If it breaks, print why
			return null; //Return nothing
		}
	}

	//Grab the next instruction
	public void executeInstruction() {
		instructions.poll();
	}

	//If there are no more instructions in the queue, the process is done
	public boolean isComplete() {
		return this.instructions.isEmpty(); 
	}

	//Check for the next instruction in the queue
	public int nextInstruction() {
		return this.instructions.peek();
	}
	
	//Add a new page fault at the tick provided as an argument
	public void addPageFault(int tick) {
		pageFaults.add(tick);
	}

	//Get the frame of this process
	public Frame getFrame() {
	  return this.frame;
	}

	//Set the frame of this process
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}
  
	public void setId(int id){
		this.id = id;
	}

	public String getState() {
		return state;
	}
  
	public void setState(String state){
		this.state = state;
	}

	public int getCompletionTime() {
		return completionTime;
	}
  
	public void setCompletionTime(int completionTime){
	  this.completionTime = completionTime;
	}

	public List<Integer> getPageFaults() {
		return pageFaults;
	}
	
	//Used to check whether a frame is the one used by this process
	@Override
	public boolean equals(Object other) { 
		if (other instanceof Frame) {
			return other.equals(this); 
		}
		return this == other;
	}
}