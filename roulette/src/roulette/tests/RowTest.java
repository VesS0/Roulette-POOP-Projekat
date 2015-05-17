package roulette.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import roulette.common.*;

public class RowTest {

	@Test
	public void testGetMessage() throws ParameterOutOfBoundsException {

		Row rowBet = new Row(2, 250);
		
		assertEquals("ROW_2 250", rowBet.getMessage());
	}

	@Test
	public void testRow() {

		Row rowBet;
		
		try {
			
			rowBet = new Row(55, 250);
			
			
		} catch (ParameterOutOfBoundsException e) {
			
			try {
				rowBet = new Row(2, 250);
				
				assertNotNull(rowBet);
			} catch (ParameterOutOfBoundsException e1) {
				
				fail("Error in row bet check");
			}
			
		}
	}

}
