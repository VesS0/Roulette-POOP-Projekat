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
	public String getMesage() {
		return CommunicationCommands.PASSE + " " + Integer.toString(super.getAmount());
	}
}
