package Ex09;

import java.util.Random;


public class PriorityTask implements Runnable, Comparable<PriorityTask> {
	private int id;
	private Resource resource;
	private int priority;
	
	public PriorityTask(int id, Resource resource) {
		this.id = id;
		this.resource = resource;
	}
	
	
	@Override
	public String toString() {
		return "Task no: "+id+" with priority: "+priority;
	}
	
	@Override
	public int compareTo(PriorityTask o) {
		return o.priority-priority;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
			// generates a random priority (higher is more important)
			priority = rand.nextInt(100);
			resource.enter(priority);
			execute();
			resource.leave();
		}
	}
	
	
	private void execute() {
		// simulating work
		try {
			System.out.println("\n"+this+" is accessing the resource\n");
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
	
	
	public int getPriority() {
		return priority;
	}
}
