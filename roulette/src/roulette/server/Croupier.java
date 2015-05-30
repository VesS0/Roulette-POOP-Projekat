/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.Collections;

import roulette.common.Bet;
import roulette.communication.CommunicationCommands;
//import roulette.common.TableWheel;

/**
 *
 * @author POOP
 */

public class Croupier implements Runnable
{
	static final double LOW=100;
	static final double HIGH=10000;
	static class Elem implements Comparable<Elem>{
		Player p;
		Bet b;
		Elem(Player p,Bet b){ this.p=p; this.b=b;}
		@Override
		public int compareTo(Elem o) {
			if (p.id>o.p.id) return 1;
			else if (p.id<o.p.id) return -1;
			return 0;
		}
	}
	private LinkedList<Elem> list;
	private Game _game;
	private Table _table;
	
	public Croupier(Game game){
		_game=game;
		_table=_game.getTable();
	}
	private void addBet(Player p, Bet b)
	{
		list.add(new Elem(p,b));
	}
	
	private void pay_out(int winning){
		Collections.sort(list);
		Player last=list.getFirst().p;
		
		for (Elem temp: list)
		{
			Player p=temp.p;
			double amount= temp.b.win(winning);
			if (temp.p!=last)
				p.reportMessage(CommunicationCommands.WIN+" "+amount);
			last=p;

		}
	}
	
	public boolean check_state(){//stanje !!!
		return _table.allow_bets;
	}
    public void run()
    {
    	while(!Thread.interrupted())
    	{
    		
    		//u javi protected daje pristup samo u okviru paketa i izvedenih klasa.
    		//Player nema u interfejsu spin, stoga ljudi ne mogu da zavrte. . sad ako neko izvede iz te klase, to je problem
    		//ali ako napravimo da klasa bude finalna, kao sto jesmo, nema izvodjenja iz nje i problem je resen
    		//pretpostavka je da u paket roulette.server ne moze bilo ko da ubacuje klase
    		_table.spin(LOW+Math.random()*(HIGH-LOW)); 
			
    		_game.sendMessageToAllPlayers(CommunicationCommands.PLACE_BETS);
    		while(check_state()) try {synchronized(this){wait();}} catch(InterruptedException ie){}
    		_game.sendMessageToAllPlayers(CommunicationCommands.NO_BETS);
    		try{synchronized(this){wait();}} catch(InterruptedException ie){}
    		int win_number=_table.get_winner();
    		_game.sendMessageToAllPlayers(CommunicationCommands.WINNER+" "+win_number);
    		pay_out(win_number);
    		try{Thread.sleep(2000);}catch(InterruptedException ie){}
    	}
    }
    public String player_bet(Player p,Bet b) //pb
    {
    	String state= _table.state();
    	if (state!=CommunicationCommands.NO_BETS)
    	{
    		addBet(p,b);
    	}
    	return state;
    }
    

}
