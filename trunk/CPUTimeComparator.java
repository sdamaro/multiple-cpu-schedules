class CPUTimeComparator implements Comparator {
	
	public int compare(Object p1, Object p2) {
		t1 = ((Process)p1).neededCPUTime;
		t2 = ((Process)p2).neededCPUTime;
		
		if (t1 < t2) return 1;
		else return -1;
	}

}