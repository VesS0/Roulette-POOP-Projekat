package roulette.common;

import roulette.communication.CommunicationCommands;

public class Row extends Bet {

	private final int m_row;
	
	public Row(int row, int amount) throws ParameterOutOfBoundsException {
		super(amount);
		
		if (row < 1 && row > 12) 
			throw new ParameterOutOfBoundsException("row can be between 1 and 12");
		
		m_row = row;
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.ROW + Integer.toString(m_row) 
				+ " " + Integer.toString(getAmount());
	}

}
