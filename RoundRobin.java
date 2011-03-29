public class RoundRobin extends Scheduler {
	int timeSlice = 100;
	
	@Override
	public void Run() {
		stillRunning = true;
		while (stillRunning) {
			currentTime +=1;
			if (queue.get(0).neededCPUTime == 0) {
				contextSwitchDone();
				continue;
			}
			if (currentTime % timeSlice == 0) {
				contextSwitch();
			}
		}
	}
	
	@Override
	public void checkNewProcess() {
		queue.add(p);
	}
	
	
	
	@Override
	public void contextSwitch(){
		Process p = queue.remove(0);
		queue.add(p);
		println("Context switch (swapped out process) " + p.ID + " for process " + queue.get(0).ID + ")");
		currentTime += 9;
	}
	public void contextSwitchDone() {
		Process p = queue.remove(0);
		println("Context switch (swapped out process) " + p.ID + " for process " + queue.get(0).ID + ")");
	}
}