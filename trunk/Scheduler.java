import java.util.*;
import java.text.NumberFormat;

public abstract class Scheduler {
	boolean stillRunning = true;
	int sizeOfQueue = 20;
	int currentTime = 0;
	Vector <int> waitTimes = new Vector <int>();
	Vector <int> turnaroundTimes = new Vector <int>();
	private Vector <Process> queue = new Vector <Process>();
	private Vector <Process> inactive = new Vector <Process>();
	
	public abstract void Run();
	public abstract void checkNewProcesses();
	public abstract void contextSwitch();
	public void addProcesses(Vector <Process> p) {
		inactive.addAdd(p);
		Collections.sort(inactive, new startTimeComparator());
	}
	public abstract void contextSwitch();
	
	//this function finds the min, max, and ave values for the wait and turnaround times to correct precision
	public void printMMMstats(){
		float wtmin = 1000000, wtmax = 0, wttotal = 0;
		float ttmin = 1000000, ttmax = 0, tttotal = 0;
		for (int i = 0; i < waitTimes.size(); i++){
			if(waitTimes.get(i) < wtmin) wtmin = waitTimes.get(i);
			if(waitTimes.get(i) > wtmax) wtmax = waitTimes.get(i);
			if(turnaroundTimes.get(i) < ttmin) ttmin = turnaroundTimes.get(i);
			if(turnaroundTimes.get(i) > ttmax) ttmax = turnaroundTimes.get(i);
			wttotal += waitTimes.get(i);
			tttotal += turnaroundTimes.get(i);
		}
		wttotal = wttotal/waitTimes.size();
		tttotal = tttotal/waitTimes.size();
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(3);
		nf.setMinimumFractionDigits(3);
		System.out.println("Wait Times:");
		System.out.print("	 Minimum: " + nf.format(wtmin));
		System.out.print("	 Maximum: " + nf.format(wtmax));
		System.out.print("	 Average: " + nf.format(wttotal) + "\n");
		System.out.println("Turnaround Times:");
		System.out.print("	 Minimum: " + nf.format(ttmin));
		System.out.print("	 Maximum: " + nf.format(ttmax));
		System.out.print("	 Average: " + nf.format(tttotal) + "\n");
	}
}