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
import java.util.NoSuchElementException;

import roulette.common.Bet;
import roulette.communication.CommunicationCommands;
//import roulette.common.TableWheel;

/**
 *
 * @author POOP
 */

public class Croupier implements Runnable
{
	static final double LOW=1;
	static final double HIGH=8;
	static class Elem implements Comparable<Elem>{
		Player p;
		Bet b;
		Elem(Player p,Bet b){
			this.p=p; this.b=b;
			}
		@Override
		public int compareTo(Elem o) {
			if (p.id>o.p.id) return 1;
			else if (p.id<o.p.id) return -1;
			return 0;
		}
	} 
	private LinkedList<Elem> list=new LinkedList<Elem>();
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
	
	private synchronized void pay_out(int winning){
		
		Collections.sort(list);
		try{

			Player last=list.getFirst().p;
			double amount=0;
				
			for (Elem temp: list)
			{
			Player p=temp.p;
			amount+=temp.b.win(winning);
			if (temp.p!=last){
				p.reportMessage(CommunicationCommands.WIN+" "+amount);
				amount=0;
				System.out.println("KRUPIJE ISPLACUJE !");//////
				}
			last=p;
		}if (last!=null)  last.reportMessage(CommunicationCommands.WIN+" "+amount);
		} catch(NoSuchElementException nsee){}
		list.clear();
	}
	
	public synchronized boolean check_state(){//stanje !!!
		return _table.allow_bets;
	}
	protected void tellThem(boolean PYB)
	{
		if (PYB)_game.sendMessageToAllPlayers(CommunicationCommands.PLACE_BETS);
		else  _game.sendMessageToAllPlayers(CommunicationCommands.NO_BETS);
	}
    public void run()
    {
    	_table.setCroupier(this);
    	while(!Thread.interrupted())
    	{
    		
    		//u javi protected daje pristup samo u okviru paketa i izvedenih klasa.
    		//Player nema u interfejsu spin, stoga ljudi ne mogu da zavrte. . sad ako neko izvede iz te klase, to je problem
    		//ali ako napravimo da klasa bude finalna, kao sto jesmo, nema izvodjenja iz nje i problem je resen
    		//pretpostavka je da u paket roulette.server ne moze bilo ko da ubacuje klase
    		
    		_table.spin(LOW+Math.random()*(HIGH-LOW)); 
    		
     		_table.waitToSpin();
    		
    		int win_number=_table.get_winner();
    		
    		_game.sendMessageToAllPlayers(CommunicationCommands.WINNER+" "+win_number);
   
    		pay_out(win_number); 
    		
    		
    		
    		try{Thread.sleep(2000);}catch(InterruptedException ie){}
    		
    		
    	}
    }
    public String player_bet(Player p,Bet b) //pb
    {
    	String state=_table.state();
    	if (state!=CommunicationCommands.NO_BETS)
    	{
    		addBet(p,b);
    	}
    	return state;
    }
    

}
