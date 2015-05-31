package roulette.common;

import roulette.communication.CommunicationCommands;

public class Noir extends Bet {

	public Noir(int amount) {
		super(amount);
	}
	
	public static boolean isNoir(int number) {
		return !(Rouge.isRouge(number) || number == 0);
	}
	
	@Override
	public String getMessage() {
		return CommunicationCommands.NOIR + " " + Integer.toString(getAmount());
	}

	@Override
	public int win(int number) {
		return isNoir(number) ? getAmount()*2 : 0;
	}

}
