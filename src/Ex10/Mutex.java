package Ex10;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

class Mutex {

	// Our internal helper class
	private static class Sync extends AbstractQueuedSynchronizer {

		private static final long serialVersionUID = 7804611243523785643L;

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

	public void lock()   { sync.acquire(0); }
	public void unlock() { sync.release(0); }

}