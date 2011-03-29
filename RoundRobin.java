public class RoundRobin extends Scheduler {
	int timeSlice = 100;
	
	@Override
	public void Run() {
		while (stillRunning) {
			currentTime +=1;
			if (queue.get(0).neededCPUTime == 0) {
				queue.remove(0);
				continue;
			}
			if (currentTime % timeSlice == 0)
				contextSwitch();
		}
	}
	
	@Override
	public void addProcesses() {
		queue.add(p);
	}
	
	@Override
	public void contextSwitch() {
		
	}
}