package roulette.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import roulette.common.*;

public class SingleTest {

	@Test
	public void testGetMessage() throws ParameterOutOfBoundsException {
		
		Single singleBet = new Single(2, 250);

		assertEquals("SINGLE_2 250", singleBet.getMessage());
	}

	@Test
	public void testSingle() {
		
		Single singleBet;
		
		try {
			
			singleBet = new Single(55, 250);
			
			
		} catch (ParameterOutOfBoundsException e) {
			
			try {
				singleBet = new Single(2, 250);
				
				assertNotNull(singleBet);
			} catch (ParameterOutOfBoundsException e1) {
				
				fail("Error in single bet check");
			}
			
		}
	}

}
