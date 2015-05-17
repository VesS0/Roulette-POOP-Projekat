package roulette.common;

import roulette.communication.CommunicationCommands;

public class Row extends Bet {

	private final int m_row;
	
	public Row(int row, int amount) {
		super(amount);
		m_row = row;
	}

	@Override
	public String getMesage() {
		return CommunicationCommands.ROW + Integer.toString(m_row) 
				+ " " + Integer.toString(getAmount());
	}

}
