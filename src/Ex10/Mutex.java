package Ex10;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class Mutex implements Lock, Serializable {

	private static final long serialVersionUID = -2610620120776425857L;
	// Our internal helper class
	   private static class Sync extends AbstractQueuedSynchronizer {
		 
	    
		   // Acquire the lock if state is zero
	     public boolean tryAcquire(int acquires) {
	    	 if (hasQueuedPredecessors()) {
	    		 return false;
	    	 }
	    	 if (compareAndSetState(0, 1)) {
	    		 setExclusiveOwnerThread(Thread.currentThread());
	    		 return true;
	    	 }
	    	 return false;
	     }

	     // Release the lock by setting state to zero
	     protected boolean tryRelease(int releases) {
	       setExclusiveOwnerThread(null);
	       setState(0);
	       return true;
	     }

	   }

	   private final Sync sync = new Sync();

	   public boolean isQueued(Thread t) {
		   return sync.isQueued(t);
	   }
	   public void lock()                { sync.acquire(0); }
	   public boolean tryLock()          { return sync.tryAcquire(0); }
	   public void unlock()              { sync.release(0); }
	   public Condition newCondition()   { return null; }
	   public void lockInterruptibly() throws InterruptedException {
	     sync.acquireInterruptibly(1);
	   }
	   public boolean tryLock(long timeout, TimeUnit unit)
	       throws InterruptedException {
	     return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	   }
	   
	   
	 }