package roulette.common;

import roulette.communication.CommunicationCommands;

public class Noir extends Bet {

	public Noir(int amount) {
		super(amount);
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.NOIR + " " + Integer.toString(getAmount());
	}

}
