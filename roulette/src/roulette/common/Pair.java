package roulette.common;

import roulette.communication.CommunicationCommands;

public class Pair extends Bet {

	public Pair(int amount) {
		super(amount);
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.PAIR + " " + Integer.toString(getAmount());
	}

}
