
public class FirstComeFirstServe extends Scheduler {
	@Override
	public void Run(){
	
	
	}

	@Override
	public void addProcesses(){
		for (int i = 0; i < sizeOfQueue; i++) {
			queue.add(new Process(i));
		}
	}
}
