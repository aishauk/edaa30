package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
	
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	
	@Before
	public void setUp() {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}
	
	@After
	public void tearDown() {		//efter testet, töm
		q1 = null;
		q2 = null;
	}



	@Test
	public void testTwoEmpty() {
		q1.append(q2);
		assertEquals("incorrect size q1", 0, q1.size());
		 
	}
	/**
	 * Test tomma kö q2, konkateneras till icke-tom kö
	 */
	@Test
	public void testNonEmptyAppendEmp() {
		
		for(int i=1; i<6;i++) {
			q1.offer(i);
		}
		q1.append(q2);
		assertEquals("incorrect size" , 5, q1.size());
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		
			assertEquals("poll returns incorrect element", null, q1.poll());
			assertEquals("q2 tömmer inte", 0, q2.size());
			assertEquals("q2 tömmer inte", null, q2.peek());
			
			
		
	}
	
	
	/**
	 * Test icke-tom kö som konkateneras till tom kö
	 */
	@Test
	public void testEmptyAppendNonEmpty() {
		for(int i=1; i<=5;i++) {
			q2.offer(i);
		}
		q1.append(q2);
		assertEquals( "size  ",5, q1.size());
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		
			assertEquals("poll returns incorrect element", null, q1.poll());

			assertEquals("q2 toma inte", 0, q2.size());
			assertEquals("q2 toma inte", null, q2.peek());
	}
	
	/**
	 * Test två icke-tomma köer 
	 */
	@Test
	public void testNonEmpty() {
		for(int i=1; i<=5;i++) {
			q1.offer(i);
		}
		for(int i=6; i<=10;i++) {
			q2.offer(i);
		}
		q1.append(q2);
		assertEquals( "size  ",10, q1.size());
		for (int i = 1; i <= 10; i++) {
			int k = q1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		
			assertEquals("poll returns incorrect element", null, q1.poll());

			assertEquals("q2 tömmer inte", 0, q2.size());
			assertEquals("q2 tömmer inte", null, q2.peek());
			
	}
	
	/**
	 * Testar icke-tom kö som konkateneras till tom kö
	 */
	@Test
	public void testAppendSelf() {
		for(int i=1; i<=5;i++) {
			q1.offer(i);
		}
		try {
			q1.append(q1);
		} catch (IllegalArgumentException e)  {
			// successful test
		}
		
	}
}
