/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.common;

import roulette.communication.CommunicationCommands;

/**
 *
 * @author POOP
 */
public class Passe extends Bet
{
	public Passe(int amount) {
		super(amount);
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.PASSE + " " + Integer.toString(getAmount());
	}

	@Override
	public int win(int number) {
		return number <= 36 && number > 18 ? getAmount()*2 : 0;
	}
}
