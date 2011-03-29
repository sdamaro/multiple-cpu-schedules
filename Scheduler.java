import java.util.*;

public abstract class Scheduler {

	private Vector <Process> queue;
	
	public abstract void Run();

	public abstract void addProcess();
	
}