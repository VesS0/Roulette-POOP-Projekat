package roulette.common;

import roulette.communication.CommunicationCommands;

public class Single extends Bet {

	private int m_number;
	
	public Single(int number, int amount) {
		super(amount);
		m_number = number;
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.SINGLE + Integer.toString(m_number) 
				+ " " + Integer.toString(getAmount());
	}

}
