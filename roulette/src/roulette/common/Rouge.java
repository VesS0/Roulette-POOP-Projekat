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
	public static int[] rouges = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,32,34,36};
	
	public static boolean isRouge(int val) { 
		for(int i = 0; i < rouges.length; i++) {
			if (rouges[i] == val) {
				return true;
			}
		}
		return false;
	}
	
	public Rouge(int amount) {
		super(amount);
	}

	@Override
	public String getMessage() {
		return CommunicationCommands.ROUGE + " " + Integer.toString(getAmount());
	}

	@Override
	public int win(int number) {
		return isRouge(number) ? getAmount()*2 : 0;
	}
}
