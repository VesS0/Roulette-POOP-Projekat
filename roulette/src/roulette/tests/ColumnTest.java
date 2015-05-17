package roulette.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import roulette.common.*;

public class ColumnTest {

	@Test
	public void testGetMessage() throws ParameterOutOfBoundsException {
		
		Column columnBet = new Column(2, 250);
		
		assertEquals("COLUMN_2 250", columnBet.getMessage());
	}

	@Test
	public void testColumn() {
		Column columnBet;
		
		try {
			
			columnBet = new Column(55, 250);
			
			
		} catch (ParameterOutOfBoundsException e) {
			
			try {
				columnBet = new Column(2, 250);
				
				assertNotNull(columnBet);
			} catch (ParameterOutOfBoundsException e1) {
				
				fail("Error in Column bet check");
			}
			
		}
		
	}

}
