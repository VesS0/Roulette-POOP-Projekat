package roulette.common;

import roulette.communication.CommunicationCommands;

public class Impair extends Bet {

	public Impair(int amount) {
		super(amount);
	}

	public static boolean isImpair(int number) {
		return number != 0 && number % 3 == 0;
	}
	
	@Override
	public String getMessage() {
		return CommunicationCommands.IMPAIR + " " + Integer.toString(getAmount());
	}

	@Override
	public int win(int number) {
		return isImpair(number) ? getAmount()*2 : 0;
	}

}
