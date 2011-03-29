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
	public void Pause() { stillRunning = false; }
	public abstract void addProcess(Process p);
	public abstract void contextSwitch();
}