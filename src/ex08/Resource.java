package ex08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Resource {
	private int threadsActive = 0;
	private List<Task> waitingTasks = new LinkedList<>();
	
	public void enter(Task task) {
		synchronized(this) {
			System.out.println(task+" asked permission to execute function");
			while (threadsActive > 0 || (waitingTasks.size() > 0 && !waitingTasks.get(0).equals(task))) {
				try {
					if (!waitingTasks.contains(task)) {
						waitingTasks.add(task);
					}
					wait();
				} catch (InterruptedException e) {}
			}
			waitingTasks.remove(task);
			threadsActive++;
		}
		
		execute(task);
		leave(task);
	}
	
	
	private synchronized void leave(Task task) {
		System.out.println(task+" stopped execution on resource");
		threadsActive--;
		
		notifyAll();
	}
	
	// critical region
	private void execute(Task task) {
		System.out.println("\n"+task+" is accessing the resource\n");
		// simulating work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
}
