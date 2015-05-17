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
public class Rouge extends Bet
{
	public Rouge(int amount) {
		super(amount);
	}

	@Override
	public String getMesage() {
		return CommunicationCommands.ROUGE + " " + Integer.toString(getAmount());
	}
}
