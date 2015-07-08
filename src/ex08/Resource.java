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
	private List<Task> tasks = new LinkedList<>();
	
	public void enter(Task task) {
		synchronized(this) {
			System.out.println(task+" asked permission to execute function");
			while (threadsActive > 0 || (tasks.size() > 0 && !tasks.get(0).equals(task))) {
				try {
					if (!tasks.contains(task)) {
						tasks.add(task);
					}
					wait();
				} catch (InterruptedException e) {}
			}
			tasks.remove(task);
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
	
	
	private void execute(Task task) {
		System.out.println(task+" is accessing the resource");
		// simulating work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
}
