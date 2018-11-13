package pl.edu.agh.kis.java2015.domain;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.edu.agh.kis.java2015.Exceptions.HeapUnderflowException;

public class HeapTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void insert0intoNewHeap_topIs0() {
		
		Heap<Double> heap = new Heap();

		heap.insert(0.0);
		assertEquals("size should be 1",1,heap.size());
		assertEquals(0,heap.top(),0.001);
		assertEquals(1,heap.size());
	}
	
	@Test
	public void insert0AndThen2intoNewHeap_topIs2() {
		
		Heap<Double> heap = new Heap();
		heap.insert(0.0);
		heap.insert(2.0);
		
		assertEquals("size should be 2",2,heap.size());
		assertEquals(2,heap.top(),0.001);
	}
	
	@Test
	public void insert0And2And3And5And6intoNewHeap_topIs6() {
		
		Heap<Double> heap = new Heap();
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(5.0);
		heap.insert(6.0);
		
		assertEquals(6,heap.top(),0.001);
	}

	@Test
	public void ExtractMax5Times_TopIs5320_SizeIs5() throws HeapUnderflowException {

		Heap<Double> heap = new Heap();
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(5.0);
		heap.insert(6.0);

		assertEquals(6,heap.extractMax(),0.001);
		assertEquals(5,heap.extractMax(),0.001);
		assertEquals(3,heap.extractMax(),0.001);
		assertEquals(2,heap.extractMax(),0.001);
		assertEquals(0,heap.extractMax(),0.001);
		assertEquals("size should be 0",0,heap.size());
	}

	@Test
	public void DeleteMax5Times_SizeIs0() throws HeapUnderflowException{

		Heap<Double> heap = new Heap();
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(5.0);
		heap.insert(6.0);
		heap.deleteMax();
		assertEquals(5,heap.top(),0.001);
		heap.deleteMax();
		assertEquals(3,heap.top(),0.001);
		heap.deleteMax();
		assertEquals(2,heap.top(),0.001);
		heap.deleteMax();
		assertEquals(0,heap.top(),0.001);
		heap.deleteMax();

		assertEquals("size should be 0",0,heap.size());

	}

	@Test
	public void RepleceWith4_TopIs5ThenTopIs4()  throws HeapUnderflowException{

		Heap<Double> heap = new Heap();
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(5.0);
		heap.insert(6.0);
		heap.replece(4.0);
		assertEquals(5, heap.top(), 0.001);
		heap.replece(1.0);
		assertEquals(4, heap.top(), 0.001);
	}

	@Test
	public void MergeHeaps5and3Elements_SizeIs8TopIs9() throws HeapUnderflowException{

		Heap<Double> heap = new Heap();
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(5.0);
		heap.insert(6.0);
		Heap<Double> heap2 = new Heap();
		heap2.insert(8.0);
		heap2.insert(7.0);
		heap2.insert(9.0);
		Heap<Double> heap3 = heap.merge(heap2);
		assertEquals(9, heap3.top(), 0.001);
		assertEquals("size should be 8",8, heap3.size());
	}

	@Test
	public void MeldHeaps5and3Elements_SizeIs8TopIs9() throws HeapUnderflowException{

		Heap<Double> heap = new Heap();
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(5.0);
		heap.insert(6.0);
		Heap<Double> heap2 = new Heap();
		heap2.insert(8.0);
		heap2.insert(7.0);
		heap2.insert(9.0);
		heap.meld(heap2);
		assertEquals(9, heap.top(), 0.001);
		assertEquals("size should be 8",8, heap.size());
	}

	@Test
	public void extractMaxThrowsHeapUnderflowException() throws HeapUnderflowException{
		thrown.expect(HeapUnderflowException.class);
		thrown.expectMessage("heapUnderflowException");
		Heap<Double> heap = new Heap();
		heap.extractMax();
	}

	@Test
	public void deleteMaxThrowsHeapUnderflowException() throws HeapUnderflowException{
		thrown.expect(HeapUnderflowException.class);
		thrown.expectMessage("heapUnderflowException");
		Heap<Double> heap = new Heap();
		heap.deleteMax();
	}

	@Test
	public void repleceMaxThrowsHeapUnderflowException() throws HeapUnderflowException{
		thrown.expect(HeapUnderflowException.class);
		thrown.expectMessage("heapUnderflowException");
		Heap<Double> heap = new Heap();
		heap.replece(1.0);
	}

	@Test
	public void mergeThrowsHeapUnderflowException() throws HeapUnderflowException{
		thrown.expect(HeapUnderflowException.class);
		thrown.expectMessage("heapUnderflowException");
		Heap<Double> heap = new Heap();
		Heap<Double> heap2 = new Heap();
		heap.merge(heap2);
	}

	@Test
	public void meldThrowsHeapUnderflowException() throws HeapUnderflowException{
		thrown.expect(HeapUnderflowException.class);
		thrown.expectMessage("heapUnderflowException");
		Heap<Double> heap = new Heap();
		Heap<Double> heap2 = new Heap();
		heap.meld(heap2);
	}

	@Test
	public void IntegerHeapTest() throws HeapUnderflowException{
		Heap<Integer> heap = new Heap();
		heap.insert(0);
		heap.insert(2);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);
		Heap<Integer> heap2 = new Heap();
		heap2.insert(8);
		heap2.insert(7);
		heap2.insert(9);
		heap.meld(heap2);
		assertEquals(9, heap.top(), 0.001);
		assertEquals("size should be 8",8, heap.size());
		heap.replece(1);
		assertEquals(8, heap.top(), 0.001);
		assertEquals(8,heap.extractMax(),0.001);
		assertEquals(7,heap.extractMax(),0.001);
		assertEquals(6,heap.extractMax(),0.001);
		assertEquals("size should be 5",5, heap.size());
	}
}
