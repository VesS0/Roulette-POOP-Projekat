package roulette.common;

import roulette.communication.CommunicationCommands;

public class Column extends Bet {

	private final int m_column;
	
	public Column(int column, int amount) throws ParameterOutOfBoundsException {
		super(amount);
		
		if (column < 1 && column > 3)
			throw new ParameterOutOfBoundsException("row can be between 1 and 12");
		
		m_column = column;
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.COLUMN + Integer.toString(m_column) 
				+ " " + Integer.toString(getAmount());
	}

}
