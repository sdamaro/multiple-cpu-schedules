/*
 *	CSCI-4210: Operating Systems
 *     Project 1: Process Scheduling
 *
 *     Ken Bellows and Bethany Clark
 */ 

import java.util.*;

public class Process {

	public int ID;
	public int neededCPUTime;
	public int priority;
	public int startTime;
	public int lastTimeAccessed;
	public int turnaroundTime;
	public int waitTime;
	
	private int initialNeeded;
	
	
	public Process(int ID, int startTime) {
		this.ID		     = ID;
		this.startTime   = startTime;
		neededCPUTime    = 500 + (int)(Math.round(Math.random() * 9500));
		initialNeeded	 = neededCPUTime;
		priority 	     = (int)(Math.round(Math.random() * 4));
		turnaroundTime 	 = 0;
		waitTime	   	 = 0;
		lastTimeAccessed = 0;
	}
	
	public void reset() {
		neededCPUTime    = initialNeeded;
		turnaroundTime   = 0;
		waitTime	   	 = 0;
		lastTimeAccessed = 0;
	}
}