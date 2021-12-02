package com.MichaelHardityaJmartFA;
import java.util.*;
/** Basic algorithm to take and modify for generic data types such as Iterator and Iterable
 * also works for Array type
 * 
*@author MichaelHarditya*/
public class Algorithm{
	private Algorithm() {
		
	}
	/**
	 * collecting a set of array data, filtered with the value parameter
	 * @param <T> can be any data type
	 * @param array the data that is going to be collected in ArrayList type
	 * @param value the filter to choose which data is going to be collected
	 * @return a filtered ArrayList data
	 */
	public static <T> List<T> collect (T[] array, T value){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return collect(iterator,pred);
	}
	/**
	 * collecting a set of Iterable data, filtered with the value parameter
	 * @param <T> can be any data type
	 * @param iterable the data that is going to be collected in Iterable type
	 * @param value the filter to choose which data is going to be collected
	 * @return a filtered ArrayList data
	 */
	public static <T> List<T> collect (Iterable<T> iterable, T value){
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return collect(iterator,pred);
	}
	/**
	 * collecting a set of Iterator data, filtered with the value parameter
	 * @param <T> can be any data type
	 * @param iterator the data that is going to be collected in Iterator type
	 * @param value the filter to choose which data is going to be collected
	 * @return a filtered ArrayList data
	 */
	public static <T> List<T> collect (Iterator<T> iterator, T value){
		Predicate<T> pred = value::equals;		
		return collect(iterator,pred);
	}
	/**
	 * collecting a set of array data, filtered with the a lambda operation
	 * @param <T> can be any data type
	 * @param array the data that is going to be collected in ArrayList type
	 * @param pred the filter to choose which data is going to be collected in lambda operation
	 * @return a filtered ArrayList data
	 */
	public static <T> List<T> collect (T[] array, Predicate<T> pred){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return collect(iterator,pred);
	}
	/**
	 * collecting a set of Iterable data, filtered with a lambda operation
	 * @param <T> can be any data type
	 * @param iterable the data that is going to be collected in Iterable type
	 * @param pred the filter to choose which data is going to be collected in lambda operation
	 * @return a filtered ArrayList data
	 */
	public static <T> List<T> collect (Iterable<T> iterable, Predicate<T> pred){
		Iterator<T> iterator = iterable.iterator();
		return collect(iterator,pred);
	}
	/**
	 * collecting a set of Iterator data, filtered with a lambda operation
	 * @param <T> can be any data type
	 * @param iterator the data that is going to be collected in Iterator type
	 * @param pred the filter to choose which data is going to be collected in lambda operation
	 * @return a filtered ArrayList data
	 */
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
	/**
	 * counting a set of ArrayList data, filtered with the value parameter
	 * @param <T> can be any data type
	 * @param array the data that is going to be counted in ArrayList type
	 * @param value the filter to choose which data is going to be counted
	 * @return counted objects, in integer
	 */
	public static <T> int count(T[] array,T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return count(iterator,pred);
	}
	/**
	 * counting a set of Iterable data, filtered with the value parameter
	 * @param <T> can be any data type
	 * @param iterable the data that is going to be counted in Iterable type
	 * @param value the filter to choose which data is going to be counted
	 * @return counted objects, in integer
	 */
	public static <T> int count(Iterable<T> iterable, T value) {
		Predicate<T> pred = value::equals;
		return count(iterable, pred);
	}
	/**
	 * counting a set of Iterator data, filtered with the value parameter
	 * @param <T> can be any data type
	 * @param iterator the data that is going to be counted in Iterator type
	 * @param value the filter to choose which data is going to be counted
	 * @return counted objects, in integer
	 */
	public static <T> int count(Iterator<T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return count(iterator,pred);
	}
	/**
	 * counting a set of ArrayList data, filtered with a lambda operation
	 * @param <T> can be any data type
	 * @param array the data that is going to be counted in ArrayList type
	 * @param pred the filter to choose which data is going to be counted in lambda operation
	 * @return counted objects, in integer
	 */
	public static <T> int count(T[] array,Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return count(iterator,pred);
	}
	/**
	 * counting a set of Iterable data, filtered with a lambda operation
	 * @param <T> can be any data type
	 * @param array the data that is going to be counted in Iterable type
	 * @param pred the filter to choose which data is going to be counted in lambda operation
	 * @return counted objects, in integer
	 */
	public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return count(iterator,pred);
	}
	/**
	 * counting a set of Iterator data, filtered with a lambda operation
	 * @param <T> can be any data type
	 * @param array the data that is going to be counted in Iterator type
	 * @param pred the filter to choose which data is going to be counted in lambda operation
	 * @return counted objects, in integer
	 */
	public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
		int counter=0;
		while(iterator.hasNext()){
			if (pred.predicate(iterator.next())) {
				counter++;
			}
		}
		return counter;
	}
	/**
	 * check if an object with specified value is on the dataset (ArrayList type)
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in ArrayList type
	 * @param value the filter to be checked inside the data
	 * @return a boolean, true if it is on the data
	 */
	public static <T> boolean exists(T[] array,T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return exists(iterator,pred);
	}
	/**
	 * check if an object with specified value is on the dataset (Iterable type)
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterable type
	 * @param value the filter to be checked inside the data
	 * @return a boolean, true if it is on the data
	 */
	public static <T> boolean exists(Iterable<T> iterable, T value) {
		Predicate<T> pred = value::equals;
		return exists(iterable, pred);
	}
	/**
	 * check if an object with specified value is on the dataset (Iterator type)
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterator type
	 * @param value the filter to be checked inside the data
	 * @return a boolean, true if it is on the data
	 */
	public static <T> boolean exists(Iterator<T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return exists(iterator,pred);
	}
	/**
	 * check if an object with specified predicate is on the dataset (ArrayList type)
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in ArrayList type
	 * @param pred the filter to be checked inside the data in lambda operation
	 * @return a boolean, true if it is on the data
	 */
	public static <T> boolean exists(T[] array,Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return exists(iterator,pred);
	}
	/**
	 * check if an object with specified predicate is on the dataset (Iterable type)
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterable type
	 * @param pred the filter to be checked inside the data in lambda operation
	 * @return a boolean, true if it is on the data
	 */
	public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return exists(iterator,pred);
	}
	/**
	 * check if an object with specified predicate is on the dataset (Iterator type)
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterator type
	 * @param pred the filter to be checked inside the data in lambda operation
	 * @return a boolean, true if it is on the data
	 */
	public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
		while(iterator.hasNext()) {
			T x= iterator.next();
			if (pred.predicate(x)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * collects a data from a set of data (ArrayList type) which matches a certain value
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in ArrayList type
	 * @param value the certain value that describe the collected data
	 * @return a data in T type, null if not found
	 */
	public static <T> T find(T[] array,T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return find(iterator,pred);
	}
	/**
	 * collects a data from a set of data (Iterable type) which matches a certain value
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterable type
	 * @param value the certain value that describe the collected data
	 * @return a data in T type, null if not found
	 */
	public static <T> T find(Iterable<T> iterable, T value) {
		Predicate<T> pred = value::equals;
		return find(iterable, pred);
	}
	/**
	 * collects a data from a set of data (Iterator type) which matches a certain value
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterator type
	 * @param value the certain value that describe the collected data
	 * @return a data in T type, null if not found
	 */
	public static <T> T find(Iterator<T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return find(iterator,pred);
	}
	/**
	 * collects a data from a set of data (ArrayList type) which matches a certain value
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in ArrayList type
	 * @param pred a lambda operation to define the collected data
	 * @return a data in T type, null if not found
	 */
	public static <T> T find(T[] array,Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return find(iterator,pred);
	}
	/**
	 * collects a data from a set of data (Iterable type) which matches a certain value
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterable type
	 * @param pred a lambda operation to define the collected data
	 * @return a data in T type, null if not found
	 */
	public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return find(iterator,pred);
	}
	/**
	 * collects a data from a set of data (Iterator type) which matches a certain value
	 * @param <T> can be any type
	 * @param array the data that is going to be checked in Iterator type
	 * @param pred a lambda operation to define the collected data
	 * @return a data in T type, null if not found
	 */
	public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
		while(iterator.hasNext()) {
			T x= iterator.next();
			if (pred.predicate(x)) {
				return x;
			}
		}
		return null;
	}
	/**
	 * find the biggest value between two data
	 * @param <T> can be any type
	 * @param first the first data
	 * @param second the second data
	 * @return the biggest data between first and second
	 */
	public static <T extends Comparable<? super T>> T max(T first, T second) {
		if (first.compareTo(second)<0) {
			return first;
		}
		else {
			return second;
		}
	}
	/**
	 * find the biggest value in a set of data (in ArrayList type)
	 * @param <T> can be any type
	 * @param array a set of data that is going to be checked in ArrayList type
	 * @return the biggest data in the set
	 */
	public static <T extends Comparable<? super T>> T max(T[] array) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return max(iterator);
	}
	/**
	 * find the biggest value in a set of data (in Iterable type)
	 * @param <T> can be any type
	 * @param array a set of data that is going to be checked in Iterable type
	 * @return the biggest data in the set
	 */
	public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
		Iterator<T> iterator = iterable.iterator();
		return max(iterator);
	}
	/**
	 * find the biggest value in a set of data (in Iterator type)
	 * @param <T> can be any type
	 * @param array a set of data that is going to be checked in Iterator type
	 * @return the biggest data in the set
	 */
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
