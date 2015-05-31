package roulette.common;

import roulette.communication.CommunicationCommands;

public class Pair extends Bet {

	public Pair(int amount) {
		super(amount);
	}
	
	public static boolean isPair(int number) {
		return number != 0 && number % 2 == 0;
	}
	
	@Override
	public String getMessage() {
		return CommunicationCommands.PAIR + " " + Integer.toString(getAmount());
	}

	@Override
	public int win(int number) {
		return isPair(number) ? getAmount()*2 : 0;
	}

}
