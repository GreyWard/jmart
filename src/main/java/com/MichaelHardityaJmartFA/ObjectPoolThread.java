package com.MichaelHardityaJmartFA;
import java.util.Vector;
import java.util.function.Function;
/**
 * Thread to work a pool of objects
 * @author Michael Harditya
 *
 * @param <T> can be any data
 */
public class ObjectPoolThread<T> extends Thread {
	private boolean exitSignal = false;
	private Vector<T> objectPool = new Vector<T>();
	private Function<T,Boolean> routine;
	/**
	 * Creating a {@link Thread} with specified name
	 * @param name Thread name
	 * @param routine a {@link Function} to be worked at the pool
	 */
	public ObjectPoolThread (String name, Function<T,Boolean> routine) {
		super(name);
		this.routine = routine;
	}
	/**
	 * Creating a {@link Thread}
	 * @param routine a {@link Function} to be worked at the pool
	 */
	public ObjectPoolThread(Function<T,Boolean> routine) {
		this.routine = routine;
	}
	/**
	 * Adding new object to the pool
	 * @param object the object to be added
	 */
	public synchronized void add (T object) {
		objectPool.add(object);
	}
	/**
	 * Killing the {@link Thread}
	 */
	public synchronized void exit() {
		this.exitSignal = true;
	}
	/**
	 * Runs the {@link Thread}, the {@link Function} specified by the constructor 
	 * will start working on the objects in the pool. If there is no object in the pool, 
	 * the Thread will go into Waiting state. A new object added will wake the Thread.
	 * Thread will stop after {@code exit()} is called
	 */
	public void run() {
		while (this.exitSignal == false) {
			for (T iter : this.objectPool) {
				while (this.routine.apply(iter) != true);
				if (routine.apply(iter) == true) {
					objectPool.remove(iter);
				}
				synchronized(this) {
					while(this.size() == 0) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if (this.size() != 0) {
						this.notify();
					}
				}
			}
		}
	}
	/**
	 * Checking the Thread pool size
	 * @return objectPool size
	 */
	public int size() {
		return objectPool.size();
	}
}