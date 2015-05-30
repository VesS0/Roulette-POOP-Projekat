/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import roulette.common.Bet;
import roulette.common.Bets;
import roulette.communication.CommunicationCommands;
import roulette.communication.PlayerProxy;
/**
 *
 * @author POOP
 */
public class Player implements Runnable
{
    private Thread playerThread = new Thread(this);
    private PlayerProxy playerProxy;
	private double amount_available;
	private Game myGame;
	protected int id;
    
    public Player(PlayerProxy _playerProxy, double _startMoney, Game _game)
    {
    	playerProxy = _playerProxy;
   		amount_available=_startMoney;
   		myGame=_game;
        playerThread.start();
    }
    protected void set(int idd)
    {
    	id=idd;
    }
    public void state(){
    	String string;
    	if (myGame.getState())
    	string=CommunicationCommands.PLACE_BETS;
    	else
    	string=CommunicationCommands.NO_BETS;
    	reportMessage(string);
    }
    
    public void run()
    {
    	while(true)
    	{
    		try{
    		String msg=playerProxy.receive();
    		String[] command= msg.split(" ");
    		if (command.length==2 && command[0]==CommunicationCommands.QUIT_MESSAGE && Integer.parseInt(command[1])==id)
    		{
    			System.out.println("Player"+id+" quits");
    			quitGame();
    		}
    		else
    			if(command.length==4 && command[0]==CommunicationCommands.BET_MESSAGE)
    			{
    				if (Integer.parseInt(command[3])>amount_available) reportMessage(CommunicationCommands.NO_MONEY_RESPONCE);
    				Bet bet= Bets.decodeBet(command[2]+"_"+command[3]); //razdvojen KOD i AMOUNT donjom crticom.
    				reportMessage(myGame.player_bet(this,bet));
    				amount_available-=Integer.parseInt(command[3]);
    			}
    			else 
    				if (command.length==2 && command[0]==CommunicationCommands.STATE_REQUEST) state();
    					else
    						if (command.length==2 && command[0]==CommunicationCommands.BALANCE) 
    							reportMessage(amount_available+" ");
    						else reportMessage(CommunicationCommands.ERROR);
    		
        // napraviti ciklus u kojem nit ceka da PlayerProxy dostavi poruku
        // pozivom metode PlayerProxy.receive()
        
        // zatim analizirati sadrzaj poruke (koja komanda je u pitanju)
        // i postupiti saglasno primljenoj poruci
    		}catch (InterruptedException ie){}
    	}
    }

    
    public void quitGame()
    {
    	synchronized(this){myGame.quit(id);}//////
        // U ovoj metodi igrac javlja klasi Game da napusta igru
        // A klasa Game onda tog igraca uklanja iz spiska igraca
        // (vodeci racuna da se mozda upravo u tom trenutku neki
        // drugi igrac prijavljuje - dakle treba obezbediti
        // medjusobnu iskljucivost niti kod pristupa)
    }
    
    // Metoda kojom neki akter sa serverske strane (na primer - krupije)
    // salje klijentu poruku o nekom dogadjaju (na primer - dozvoljeno
    // ulaganje) 
    
    public void reportMessage(String message)
    {
        try 
        {
        	String s[]=message.split(" ");
        	if (s.length==2 && s[0]==CommunicationCommands.WIN)
        	{
        		amount_available+=Integer.parseInt(s[1]);
        	}
            playerProxy.send(message);
        } 
        catch (IOException ex) 
        {
            // Razmisliti: mozda nije pravo mesto za hvatanje ovog izuzetka,
            // jer onaj deo koda iz kojeg se klijentu nesto signalizira nece
            // imati obavestenje da signalizacija nije uspela.
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
