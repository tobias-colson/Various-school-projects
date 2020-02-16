//Tobias Colson 3184056
//COMP2240 Assignment 3

public class Page {

	private Process process; //The associated process of the page
	private int instruction; //The instruction in the page
	private int ticksToWait; //The ticks left before the page is loaded
	private int lastTick; //What the last tick of processing seen by the page is
	private boolean referenceBit; //Used in clock scheme

	public Page(Process process, int instruction) {
		this.process = process;
		this.instruction = instruction;
		this.ticksToWait = -1; //Not equal to 0
		this.lastTick = 0;
		this.referenceBit = true;
	}

	public boolean isLoaded() { //Is the page loaded
		return ticksToWait == 0; //Does it have any system ticks left to wait
	}
  
	//Getters and setters
	public int getLastTick() { 
		return lastTick;
	}
  
	public void setLastTick(int lastTick){
		this.lastTick = lastTick;
	}

	public boolean getReferenceBit() {
	  return referenceBit;
	}
  
 	public void setReferenceBit(boolean referenceBit){
 		this.referenceBit = referenceBit;
 	}

 	public Process getProcess() {
 		return process;
 	}
  
 	public void setProcess(Process process){
 		this.process = process;
 	}

 	public int getInstruction() {
 		return instruction;
 	}
  
 	public void setInstruction(int instruction){
	  this.instruction = instruction;
 	}

 	public int getTicksToWait() {
 		return ticksToWait;
 	}
  
 	public void setTicksToWait(int ticksTillLoaded){
 		this.ticksToWait = ticksTillLoaded;
 	}

 	//Used to check whether a frame or process has this page
 	@Override
 	public boolean equals(Object other) {
  	 if (other instanceof Frame) {
  		 return other.equals(this);
  	 }

  	 if (other instanceof Page) {
  		 Page o = (Page)other;
  		 return process == o.process && instruction == o.instruction && ticksToWait == o.ticksToWait;
  	 }
  	 return false;
 	}
}