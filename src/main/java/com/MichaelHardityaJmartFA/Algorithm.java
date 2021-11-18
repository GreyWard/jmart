package com.MichaelHardityaJmartFA;
import java.util.*;
/** melakukan beberapa operasi perbandingan menggunakan generic
 * menggunakan Iterator, Iterable, dan Predicate
*@author MichaelHarditya*/
public class Algorithm{
	private Algorithm() {
		
	}
	public static <T> List<T> collect (T[] array, T value){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return collect(iterator,pred);
	}
	public static <T> List<T> collect (Iterable<T> iterable, T value){
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return collect(iterator,pred);
	}
	public static <T> List<T> collect (Iterator<T> iterator, T value){
		Predicate<T> pred = value::equals;		
		return collect(iterator,pred);
	}
	public static <T> List<T> collect (T[] array, Predicate<T> pred){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return collect(iterator,pred);
	}
	public static <T> List<T> collect (Iterable<T> iterable, Predicate<T> pred){
		Iterator<T> iterator = iterable.iterator();
		return collect(iterator,pred);
	}
	public static <T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
		List<T> list = new ArrayList<T>();
		T check;
		while (iterator.hasNext()) {
			check = iterator.next();
			if (pred.predicate(check)) {
				list.add(check);
			}
		}
		return list;
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
	public static <T> boolean exists(T[] array,T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return exists(iterator,pred);
	}
	public static <T> boolean exists(Iterable<T> iterable, T value) {
		Predicate<T> pred = value::equals;
		return exists(iterable, pred);
	}
	public static <T> boolean exists(Iterator<T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return exists(iterator,pred);
	}
	public static <T> boolean exists(T[] array,Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return exists(iterator,pred);
	}
	public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return exists(iterator,pred);
	}
	public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
		while(iterator.hasNext()) {
			T x= iterator.next();
			if (pred.predicate(x)) {
				return true;
			}
		}
		return false;
	}
	public static <T> T find(T[] array,T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return find(iterator,pred);
	}
	public static <T> T find(Iterable<T> iterable, T value) {
		Predicate<T> pred = value::equals;
		return find(iterable, pred);
	}
	public static <T> T find(Iterator<T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return find(iterator,pred);
	}
	public static <T> T find(T[] array,Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return find(iterator,pred);
	}
	public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return find(iterator,pred);
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
	public static <T extends Comparable<? super T>> T max(T first, T second) {
		if (first.compareTo(second)<0) {
			return first;
		}
		else {
			return second;
		}
	}
	public static <T extends Comparable<? super T>> T max(T[] array) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return max(iterator);
	}
	public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
		Iterator<T> iterator = iterable.iterator();
		return max(iterator);
	}
	public static <T extends Comparable<? super T>> T max(Iterator<T> iterator) {
		T max = null;
		while(iterator.hasNext()) {
			T x= iterator.next();
			T y= iterator.next();
			max = max(x,y);
		}
		return max;
	}
	public static <T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator) {
		if (first.compareTo(second)<0) {
			return first;
		}
		else {
			return second;
		}
	}
	public static <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return max(iterator);
	}
	public static <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator) {
		Iterator<T> iterator = iterable.iterator();
		return max(iterator);
	}
	public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
		T max = null;
		while(iterator.hasNext()) {
			T x= iterator.next();
			T y= iterator.next();
			max = max(x,y);
		}
		return max;
	}
	public static <T extends Comparable<? super T>> T min(T first, T second) {
		if (second.compareTo(first)<0) {
			return first;
		}
		else {
			return second;
		}
	}
	public static <T extends Comparable<? super T>> T min(T[] array) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return min(iterator);
	}
	public static <T extends Comparable<? super T>> T min(Iterable<T> iterable) {
		Iterator<T> iterator = iterable.iterator();
		return min(iterator);
	}
	public static <T extends Comparable<? super T>> T min(Iterator<T> iterator) {
		T min = null;
		while(iterator.hasNext()) {
			T x= iterator.next();
			T y= iterator.next();
			min = min(x,y);
		}
		return min;
	}
	public static <T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator) {
		if (second.compareTo(first)<0) {
			return first;
		}
		else {
			return second;
		}
	}
	public static <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return min(iterator);
	}
	public static <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator) {
		Iterator<T> iterator = iterable.iterator();
		return min(iterator);
	}
	public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
		T min = null;
		while(iterator.hasNext()) {
			T x= iterator.next();
			T y= iterator.next();
			min = min(x,y);
		}
		return min;
	}
	public static <T> List<T> paginate (T[] array, int page, int pageSize, Predicate<Product> pred){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return paginate(iterator,page,pageSize,pred);
    }
	public static <T> List<T> paginate (Iterable<T> iterable, int page, int pageSize, Predicate<Product> pred){
		Iterator<T> iterator = iterable.iterator();
		return paginate(iterator,page,pageSize,pred);
    }
	public static <T> List<T> paginate (Iterator<T> iterator, int page, int pageSize, Predicate<Product> pred){
	    	List<T> paginatedList = new ArrayList<T>();
	    	int x = 0;
	    	int start = page * pageSize;
	    	int end = start + pageSize;
			while(iterator.hasNext()) {
				T check = iterator.next();
				if (x>=start && x < end) {
				paginatedList.add(check);
				}
				x++;
			}
			return paginatedList;
	    }
}
