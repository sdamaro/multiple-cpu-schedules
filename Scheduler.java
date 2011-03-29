import java.util.*;

public abstract class Scheduler {
	int sizeOfQueue = 20;
	private Vector <Process> queue;
	
	public abstract void Run();

	public abstract void addProcesses();
	
}