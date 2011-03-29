/*
 *	CSCI-4210: Operating Systems
 *     Project 1: Process Scheduling
 *
 *     Ken Bellows and Bethany Clark
 */ 

import java.util.*;

class CPUTimeComparator implements Comparator {
	
	public int compare(Object p1, Object p2) {
		int t1 = ((Process)p1).neededCPUTime;
		int t2 = ((Process)p2).neededCPUTime;
		
		if (t1 < t2) return 1;
		else return -1;
	}

}