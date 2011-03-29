/*
 *	CSCI-4210: Operating Systems
 *     Project 1: Process Scheduling
 *
 *     Ken Bellows and Bethany Clark
 */ 

import java.util.*;

public class RoundRobin extends Scheduler {
	int timeSlice   = 100;
	int runningTime = 0;
	
	@Override
	public void Run() {
		checkNewProcesses();
		while (!(queue.isEmpty() && inactive.isEmpty())) {
			if (!queue.isEmpty()){
				//new process is entering the system. declaring the wait time and outputting to screen.
				runningTime = 1;
				queue.get(0).waitTime += currentTime - queue.get(0).lastTimeAccessed;
				waitTimes.add(queue.get(0).waitTime);
				if (queue.get(0).lastTimeAccessed == 0) System.out.println("[time " + currentTime + "ms] Process " + queue.get(0).ID + " accessed CPU for the first time (wait time  " + queue.get(0).waitTime + "ms)");
				
				//incrementing each millisecond at a time for the breadth of the current process
				while (queue.get(0).neededCPUTime > 0){
					//decrement remaining time, increment elapsed time
					queue.get(0).neededCPUTime--;
					runningTime++;
					currentTime++;
					if (runningTime % timeSlice == 0 && queue.size() > 1) {
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
			turnaroundTimes.add(currentTime - p.startTime);
			System.out.println("[time " + currentTime + "ms] Process " + p.ID + " terminated (turnaround time " + (currentTime - p.startTime) + "ms, wait time " + p.waitTime + "ms)");
			currentTime += contextSwitchOverhead;
		}
		else {
			p.lastTimeAccessed = currentTime;
			queue.add(p);
			System.out.println("[time " + currentTime + "ms] Context switch (swapped out process " + p.ID + " for process " + queue.get(0).ID + "; " + p.neededCPUTime + "ms left for " + p.ID + ")");
			currentTime += contextSwitchOverhead;
		}
	}
}