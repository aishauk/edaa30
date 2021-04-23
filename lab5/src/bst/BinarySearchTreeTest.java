package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> b;

	@BeforeEach
	void setUp() throws Exception {
		b=new BinarySearchTree<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		b=null;
	}
	
	@Test
	void testEmpty() {
		//assertEquals testar om höjd och size är lika med 0 (då funkar den), annars skrivs texten ut
		assertEquals("empty BinarySearchTree height test", 0, b.height());
		assertEquals("size returns incorrect result", 0, b.size());
	} 
	
	@Test
	void testAdd() {
		for (int i=1; i <=5; i++) {
			b.add(i);
		}
		
		assertEquals("size incorrect", 5, b.size());		//kollar size
		assertEquals("height incorrect", 5,  b.height());	//kollar height
	} 
	
	
	
	@Test
	void testAddOlika() {
		b.add(7);
		b.add(3);
		b.add(11);
		b.add(1);
		b.add(5);
		b.add(9);
		b.add(13);
		b.add(3);
		
		assertEquals("size incorrect", 7, b.size());		//kollar size
		assertEquals("height incorrect", 3,  b.height());	//kollar height

	}

}
