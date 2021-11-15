package MichaelHardityaJmartFA;
import java.util.*;
import java.util.function.Function;
public class ObjectPoolThread<T> extends Thread {
	private boolean exitSignal = false;
	private Vector<T> objectPool;
	private Function<T,Boolean> routine;
	public ObjectPoolThread (String name, Function<T,Boolean> routine) {
		Thread thread = new Thread(name);
		thread.start();
		this.routine = routine;
	}
	public ObjectPoolThread(Function<T,Boolean> routine) {

	}
	public synchronized void add (T object) {
		objectPool.add(object);
	}
	public synchronized void exit() {
		this.exitSignal = true;
	}
	public void run() {
		try {
		for (T iterate : objectPool) {
			if (routine.apply(iterate)) {
				Thread.sleep(50);
			}
			if (exitSignal == true) {
				break;
			}
		}
		}catch (InterruptedException e) {
			System.out.println("Thread interrupted.");
		}
	}
	public int size() {
		return objectPool.size();
	}
}
