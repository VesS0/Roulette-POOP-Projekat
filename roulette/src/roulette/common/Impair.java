package roulette.common;

import roulette.communication.CommunicationCommands;

public class Impair extends Bet {

	public Impair(int amount) {
		super(amount);
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.IMPAIR + " " + Integer.toString(getAmount());
	}

}
