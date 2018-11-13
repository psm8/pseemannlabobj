package pl.edu.agh.kis.java2015.domain;

import pl.edu.agh.kis.java2015.Exceptions.HeapUnderflowException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap <T extends Comparable<T>> implements Comparator<T> {

	private int heapSize = 0;
	private ArrayList<T> tab = new ArrayList<>();

	@Override
	public int compare(T x, T y) {
		if(x.compareTo(y)<0){
			return -1;
		}
		if(x.compareTo(y)>0){
			return 1;
		}
		return 0;
	}

	public void insert(T value) {
		int currentIndex = heapSize;
		int parentIndex = parentIndex(currentIndex);
		tab.add(value);
		while( isChildGreaterThanParent(currentIndex, parentIndex) ) {
			swapElements(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = parentIndex(currentIndex);
		}
		heapSize++;
	}

	public boolean isChildGreaterThanParent(int currentIndex, int parentIndex) {
		return biggerThan(tab.get(currentIndex),tab.get(parentIndex));
	}

	public void swapElements(int currentIndex, int parentIndex) {
		T parentValue = parentValue(currentIndex);
		T currentValue = tab.get(currentIndex);
		tab.set(parentIndex, currentValue);
		tab.set(currentIndex, parentValue);
	}

	public T parentValue(int currentIndex) {
		T parentValue = tab.get(parentIndex(currentIndex));
		return parentValue;
	}

	public int parentIndex(int currentIndex) {
		return (currentIndex - 1)/2;
	}

	public int size() {
		return heapSize ;
	}

	public T top() { return tab.get(0); }

	public void setSize(int size){
		this.heapSize = size;
	}

	public int getLeftChildIndex(int parentIndex){
		return 2*(parentIndex+1)-1;
	}

	public int getRightChildIndex(int parentIndex){
		return 2*(parentIndex+1);
	}

	public void maxHeapify(int i){
		int l = getLeftChildIndex(i);
		int r = getRightChildIndex(i);
		int largest;
		if(l < size() && biggerThan(tab.get(l), tab.get(i))){
			largest = l;
		}else{largest = i;}
		if(r < size() && biggerThan(tab.get(r), tab.get(largest))){
			largest = r;
		}
		if(largest != i){
			swapElements(largest,i);
			maxHeapify(largest);
		}

	}

	public T extractMax() throws HeapUnderflowException {
		if(size()<1){throw new HeapUnderflowException("heapUnderflowException");}
		T max = this.top();
		if(this.size() > 1) {
			tab.set(0, tab.get(size() - 1));
		}
		setSize(this.size()-1);
		maxHeapify(0);
		return max;
	}

	public void deleteMax() throws HeapUnderflowException{
		if(size()<1){throw new HeapUnderflowException("heapUnderflowException");}
		tab.set(0,tab.get(size()-1));
		setSize(this.size()-1);
		maxHeapify(0);
	}

	public void replece(T value) throws HeapUnderflowException{
		if(size()<1){throw new HeapUnderflowException("heapUnderflowException");}
		tab.set(0,value);
		maxHeapify(0);
	}

	public Heap merge(Heap heap2) throws HeapUnderflowException {
		Heap mergedHeap = new Heap();
		if (size()+heap2.size() < 1) {
			throw new HeapUnderflowException("heapUnderflowException");
		}
		int size = this.size();
		for (int i = 0; i < size; i++) {
			mergedHeap.insert(this.extractMax());
		}
		int size2 = heap2.size();
		for (int i = 0; i < size2; i++) {
			mergedHeap.insert(heap2.extractMax());

		}
		return mergedHeap;
	}
	public void meld(Heap heap2) throws HeapUnderflowException {
		if (size()+heap2.size() < 1) {
			throw new HeapUnderflowException("heapUnderflowException");
		}
		int size = heap2.size();
		for (int i = 0; i < size; i++) {
			this.insert((T)heap2.extractMax());
		}
	}

	private boolean biggerThan(T arg1, T arg2){
		if(compare(arg1,arg2) > 0){
			return true;
		}else{return false;}
	}
}
