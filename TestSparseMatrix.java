import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestSparseMatrix {
	
	SparseMatrix sparsemat_empty;
	SparseMatrix sparsemat_full;

	@Before
	public void setUp() throws Exception {
		sparsemat_empty = new SparseMatrix(10,10);
		sparsemat_full = new SparseMatrix(4,4);
		sparsemat_full.setValue(1, 0, 0);
		sparsemat_full.setValue(2, 0, 1);
		sparsemat_full.setValue(3, 0, 2);
		sparsemat_full.setValue(4, 0, 3);
		sparsemat_full.setValue(5, 1, 0);
		sparsemat_full.setValue(6, 1, 1);
		sparsemat_full.setValue(7, 1, 2);
		sparsemat_full.setValue(8, 1, 3);
		sparsemat_full.setValue(9, 2, 0);
		sparsemat_full.setValue(10, 2, 1);
		sparsemat_full.setValue(11, 2, 2);
		sparsemat_full.setValue(12, 2, 3);
		sparsemat_full.setValue(13, 3, 0);
		sparsemat_full.setValue(14, 3, 1);
		sparsemat_full.setValue(15, 3, 2);
		sparsemat_full.setValue(16, 3, 3);
		
	}

	@After
	public void tearDown() throws Exception {
		sparsemat_full = null;
	}
	
	@Test
	public void testgetValue(){
		assertTrue(sparsemat_full.getValue(0,0)==1);
		assertTrue(sparsemat_full.getValue(0,1)==2);
		assertTrue(sparsemat_full.getValue(1,0)==5);
		assertTrue(sparsemat_full.getValue(1,1)==6);
	}
	
	@Test
	public void testsetValue(){
		sparsemat_full.setValue(30, 0, 0);
		assertTrue(sparsemat_full.getValue(0,0)==30);
		sparsemat_full.setValue(1, 3, 3);
		assertTrue(sparsemat_full.getValue(3,3)==1);
	}

	@Test
	public void testprintLong() {
		System.out.println("starting print long test");
		sparsemat_full.printLong();
	}
	
	@Test
	public void testprintcolvalues(){
		//sparsemat_full.printColValues(0);
		//sparsemat_full.printColValues(1);
		//sparsemat_full.printColValues(2);
	}

}
