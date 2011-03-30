/*
 *	CSCI-4210: Operating Systems
 *     Project 1: Process Scheduling - Part 1
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
		
		System.out.println("FIRST COME FIRST SERVE SIMULATION:");
		Simulate(new FirstComeFirstServe(), queue);
		System.out.println("\n\n\nROUND ROBIN SIMULATION:");
		Simulate(new RoundRobin(), queue);
		System.out.println("\n\n\nSHORTEST JOB FIRST SIMULATION:");
		Simulate(new ShortestJobFirst(), queue);
		System.out.println("\n\n\nPREMPTIVE PRIORITY SIMULATION:");
		Simulate(new PreemptivePriority(), queue);
		
	}
	
	static void Simulate(Scheduler s, Vector <Process> v) {
		s.addProcesses(v);
		s.Run();
		for (Process p : v) {
			p.reset();
		}
	}
	
}