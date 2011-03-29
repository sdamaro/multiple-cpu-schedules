import java.util.*;

public abstract class Scheduler {
	boolean stillRunning;
	int sizeOfQueue = 20;
	int currentTime = 0;
	Vector <int> waitTimes;
	Vector <int> turnaroundTimes;
	private Vector <Process> queue;
	
	public abstract void Run();

	public abstract void addProcesses();
	
}