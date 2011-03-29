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
		while (inactive.get(0).startTime >= currentTime) {
		   queue.add(inactive.get(0));
		   System.out.println("[time " + currentTime + "ms] Process " + inactive.get(0).ID + " created (requiring " + inactive.get(0).neededCPUTime + "ms CPU time, priority " + inactive.get(0).priority + ")");
		   inactive.remove(0);
		}
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