public class Process {

	public int ID;
	public int neededCPUTime;
	public int priority;
	
	public int turnaroundTime;
	public int waitTime;
	
	public Process(int i) {
		ID 			   = i;
		neededCPUTime  = 500 + (int)(Math.round(Math.random() * 9500));
		priority 	   = (int)(Math.round(Math.random() * 4));
		turnaroundTime = 0;
		waitTime	   = 0;
	}
	
	
}