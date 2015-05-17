package roulette.common;

import roulette.communication.CommunicationCommands;

public class Column extends Bet {

	private final int m_column;
	
	public Column(int column, int amount) {
		super(amount);
		
		m_column = column;
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.COLUMN + Integer.toString(m_column) 
				+ " " + Integer.toString(getAmount());
	}

}
