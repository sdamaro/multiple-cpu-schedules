import java.util.*;

public class MainApp {
	
	@SuppressWarnings("unchecked")
	public static void main(String [] args) {
		Vector <Process> queue1 = new Vector <Process>();
		int numProcesses = 20;
		
		for (int i=1; i<=numProcesses; i++) {
			queue1.add(new Process(i, 0));
		}
		
		// Make 3 copies of the queue, since the queue is altered permanently by each Scheduler
		Vector <Process> queue2 = (Vector <Process>)queue1.clone();
		//Vector <Process> queue3 = (Vector <Process>)queue1.clone();
		//Vector <Process> queue4 = (Vector <Process>)queue1.clone();
		
		Simulate(new RoundRobin(), queue2);
		Simulate(new FirstComeFirstServe(), queue1);
		
		//Simulate(new ShortestJobFirst(), queue3);
		//Simulate(new PreemptivePriority(), queue4);
		
	}
	
	static void Simulate(Scheduler s, Vector <Process> v) {
		s.addProcesses(v);
		s.Run();
	}
	
}