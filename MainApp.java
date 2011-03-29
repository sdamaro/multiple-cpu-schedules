/*
 *	CSCI-4210: Operating Systems
 *     Project 1: Process Scheduling
 *
 *     Ken Bellows and Bethany Clark
 */ 

import java.util.*;

public class MainApp {
	
	@SuppressWarnings("unchecked")
	public static void main(String [] args) {
		Vector <Process> queue = new Vector <Process>();
		int numProcesses = 20;
		
		for (int i=1; i<=numProcesses; i++) {
			queue.add(new Process(i, 0));
		}
		
		Simulate(new FirstComeFirstServe(), queue);
		Simulate(new RoundRobin(), queue);
		//Simulate(new ShortestJobFirst(), queue);
		//Simulate(new PreemptivePriority(), queue);
		
	}
	
	static void Simulate(Scheduler s, Vector <Process> v) {
		s.addProcesses(v);
		s.Run();
		for (Process p : v) {
			p.reset();
		}
	}
	
}