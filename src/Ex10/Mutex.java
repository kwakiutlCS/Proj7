package Ex10;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

class Mutex extends AbstractQueuedSynchronizer {

	private static final long serialVersionUID = -1775794736267209361L;

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



	public void lock()                { acquire(0); }
	public void unlock()              { release(0); }

}