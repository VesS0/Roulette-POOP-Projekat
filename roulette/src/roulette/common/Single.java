package roulette.common;

import roulette.communication.CommunicationCommands;

public class Single extends Bet {

	private int m_number;
	
	public Single(int number, int amount) throws ParameterOutOfBoundsException {
		super(amount);
		
		if (number < 0 && number > 36)
			throw new ParameterOutOfBoundsException("number can be between 0 and 36");
		
		m_number = number;
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.SINGLE + Integer.toString(m_number) 
				+ " " + Integer.toString(getAmount());
	}

	@Override
	public int win(int number) {
		return number == m_number ? getAmount()*36 : 0;
	}

}
