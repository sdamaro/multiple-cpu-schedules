import java.util.*;

public abstract class Scheduler {
	boolean stillRunning = true;
	int sizeOfQueue = 20;
	int currentTime = 0;
	Vector <int> waitTimes;
	Vector <int> turnaroundTimes;
	private Vector <Process> queue;
	private Vector <Process> inactive;
	
	public abstract void Run();
	public void checkNewProcesses() { 
		while (inactive.get(0).startTime >= currentTime) {
		   queue.add(inactive.get(0));
		   System.out.println("[time " + currentTime + "ms] Process " + inactive.get(0).ID + " created (requiring " + inactive.get(0).neededCPUTime + "ms CPU time, priority " + inactive.get(0).priority + ")");
		   inactive.remove(0);
		}
	public abstract void addProcesses(Vector <Process> p) {
		inactive = p;
		Collections.sort(inactive, new startTimeComparator());
	}
	public abstract void contextSwitch();
}