import java.util.*;

public abstract class Scheduler {
	boolean stillRunning = true;
	int sizeOfQueue = 20;
	int currentTime = 0;
	Vector <int> waitTimes = new Vector <int>();
	Vector <int> turnaroundTimes = new Vector <int>();
	private Vector <Process> queue = new Vector <Process>();
	private Vector <Process> inactive = new Vector <Process>();
	
	public abstract void Run();
	public abstract void checkNewProcesses();
	public abstract void addProcesses(Vector <Process> p) {
		inactive.addAdd(p);
		Collections.sort(inactive, new startTimeComparator());
	}
	public abstract void contextSwitch();
	public void addProcess(Process p){
	
	}
}