package MichaelHardityaJmartFA;
import java.io.*;
import java.util.*;

public class Algorithm{
	private Algorithm() {
		
	}
	public static <T> int count(T[] array,T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return count(iterator,pred);
	}
	public static <T> int count(Iterable<T> iterable, T value) {
		Predicate<T> pred = value::equals;
		return count(iterable, pred);
	}
	public static <T> int count(Iterator<T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return count(iterator,pred);
	}
	public static <T> int count(T[] array,Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return count(iterator,pred);
	}
	public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return count(iterator,pred);
	}
	public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
		int counter=0;
		while(iterator.hasNext()){
			if (pred.predicate(iterator.next())) {
				counter++;
			}
		}
		return counter;
	}
	public static <T> int find(T[] array,T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return count(iterator,pred);
	}
	public static <T> int find(Iterable<T> iterable, T value) {
		Predicate<T> pred = value::equals;
		return count(iterable, pred);
	}
	public static <T> int find(Iterator<T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return count(iterator,pred);
	}
	public static <T> int find(T[] array,Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return count(iterator,pred);
	}
	public static <T> int find(Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return count(iterator,pred);
	}
	public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
		while(iterator.hasNext()) {
			T x= iterator.next();
			if (pred.predicate(x)) {
				return x;
			}
		}
		return null;
	}
}
