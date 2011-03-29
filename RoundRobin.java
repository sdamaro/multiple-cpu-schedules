/*
 *	CSCI-4210: Operating Systems
 *     Project 1: Process Scheduling
 *
 *     Ken Bellows and Bethany Clark
 */ 

import java.util.*;

public class RoundRobin extends Scheduler {
	int timeSlice = 100;
	
	@Override
	public void Run() {
		checkNewProcesses();
		while (!(queue.isEmpty() && inactive.isEmpty())) {
			if (!queue.isEmpty()){
				//new process is entering the system. declaring the wait time and outputting to screen.
				queue.get(0).waitTime += currentTime - queue.get(0).lastTimeAccessed;
				waitTimes.add(queue.get(0).waitTime);
				if (queue.get(0).turnaroundTime == 0) System.out.println("[time " + currentTime + "ms] Process " + queue.get(0).ID + " accessed CPU for the first time (wait time  " + queue.get(0).waitTime + "ms)");
				
				//incrementing each millisecond at a time for the breadth of the current process
				while (queue.get(0).neededCPUTime > 0){
					//decrement remaining time, increment elapsed time
					queue.get(0).neededCPUTime--;
					queue.get(0).turnaroundTime++;
					currentTime++;
					if (queue.get(0).turnaroundTime % timeSlice == 0) {
						break;
					}
					checkNewProcesses();
					//System.out.println("current: "+ currentTime + "\tturnaround: " + queue.get(0).turnaroundTime);
				}
				
				//finishing up the process by defining the turnaround times and output
				
				contextSwitch();
				
			}
			else{
				currentTime++;
				checkNewProcesses();
			}
		}
	}
	
	@Override
	void checkNewProcesses() {
		while (!inactive.isEmpty() && inactive.get(0).startTime >= currentTime) {
			queue.add(inactive.get(0));
			System.out.println("[time " + currentTime + "ms] Process " + inactive.get(0).ID + " created (requiring " + inactive.get(0).neededCPUTime + "ms CPU time, priority " + inactive.get(0).priority + ")");
			inactive.remove(0);
		}
	}
	
	
	
	@Override
	void contextSwitch(){
		Process p = queue.remove(0);
		if (p.neededCPUTime <= 0) {
			turnaroundTimes.add(p.turnaroundTime);
			System.out.println("[time " + currentTime + "ms] Process " + p.ID + " terminated (turnaround time " + p.turnaroundTime + "ms, wait time " + p.waitTime + "ms)");
		}
		else {
			queue.add(p);
			System.out.println("[time " + currentTime + "] Context switch (swapped out process " + p.ID + " for process " + queue.get(0).ID + "; " + p.neededCPUTime + "ms left for " + p.ID + ")");
			currentTime += contextSwitchOverhead;
		}
	}
}