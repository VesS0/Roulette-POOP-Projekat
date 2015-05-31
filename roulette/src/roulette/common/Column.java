package roulette.common;

import roulette.communication.CommunicationCommands;

public class Column extends Bet {

	private final int m_column;

	public static int getColumn(int number) {
		for(int i = 1; i <= 3; i++) {
			if((number - i) % 3 == 0) {
				return i;
			}
		}
		
		return 0;
	}
	
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

	@Override
	public int win(int number) {
		return getColumn(number) == m_column ? getAmount()*12 : 0;
	}

}
