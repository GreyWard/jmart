package MichaelHardityaJmartFA;
import java.util.*;
import java.util.function.Function;
public class ObjectPoolThread<T> extends Thread {
	private boolean exitSignal;
	private Vector<T> objectPool;
	private Function<T,Boolean> routine;
	public ObjectPoolThread (String name, Function<T,Boolean> routine) {
		this.routine = routine;
	}
	public ObjectPoolThread(Function<T,Boolean> routine) {
		this.routine = routine;
	}
	public void add (T object) {
		objectPool.add(object);
	}
	public void exit() {
		this.exitSignal = true;
	}
	public void run() {
		
	}
	public int size() {
		return objectPool.size();
	}
}
