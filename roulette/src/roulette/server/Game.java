/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedHashMap;

import roulette.common.Bet;
import roulette.communication.CommunicationCommands;
import roulette.communication.PlayerProxy;
import roulette.communication.Server;
import roulette.gui.Communication;
import roulette.gui.Communication.CommunicatorOnly;

import java.io.IOException;
import java.lang.Object;
import java.util.Hashtable;
/**
 *
 * @author POOP
 */
public class Game 
{
	private LinkedHashMap<Integer,Player> players=new LinkedHashMap<Integer,Player>();
	private int numOfPlayers;
	private int maxNum;
    private double playerStartMoney = 200;
    private int id=1;
    private Communication com;
    private Croupier croupier;
    private Table table;
    private Thread croupierThread;
    private Thread tableThread;
	// Pošto je samo jedna igra predviðena, mogao bi da se koristi
	// uzorak Unikat (singleton). Pomoæu tog uzorka se lako može
	// proširiti funkcionalnost da podrži veæi (ali kontrolisan)
	// broj igara.
    
    // Metoda kojom se prikljucuje nov igrac u igru
	// Metoda vraæa iznos koji je dodeljen igraèu
    protected boolean getState(){return croupier.check_state();}
    protected synchronized String player_bet(Player p, Bet b){ return croupier.player_bet(p, b);}
    protected Table getTable(){return table;}
    public Game(int num,int _playerStartMoney,int timeToSpin)
    {
    	table=new Table();
    	croupier=new Croupier(this,timeToSpin);
    	maxNum=num;
    	playerStartMoney=_playerStartMoney;
    }
    protected void start()
    {
    	croupierThread=new Thread(croupier);
    	tableThread=new Thread(table);
    	croupierThread.start();
    	tableThread.start();
    }
    private void refresh()
    {
    	if (com!=null) com.resetPlayers(players);
    }
    public void interrupts(Croupier c)
    {
    	if (croupier.equals(c))
    	{
    		System.out.println("PREKINUO IZ GAMEA");
    		croupierThread.interrupt();
    		tableThread.interrupt();
    	}
    }
    public synchronized double newPlayer(PlayerProxy pp,String name) //s
    {
    	if (maxNum==numOfPlayers) playerStartMoney=0;
    	else
    	{
    		System.out.println("Dodajem Novog Igraca!");
    		Player p = new Player(pp, playerStartMoney, this,name);
    		numOfPlayers++;
    		//Dodati igraèa u kolekciju igraèa koju pamti Game
    		players.put(id,p);
    		p.set(id++);
    		refresh();
    	}
        return playerStartMoney;
    }
    
    // Metoda koja sluzi za "broadcast" - slanje poruke svim igracima
    public void sendMessageToAllPlayers(String message)
    {
    	
        for(int key: players.keySet())
        {
          Player p=players.get(key);
        	  if (p!=null) {
        		  if (message.equals(CommunicationCommands.PLACE_BETS)) {p.resetBets(); refresh();}
        		  p.reportMessage(message);
        	  }
        }
       
    }
    public void deleteMember(int id,CommunicatorOnly c)
    {
    	quit(id);
    }
    public synchronized void quit(int players_id)//
    {
    	System.out.println("REMOVEUJEM");//nije usao uopste.
    	players.remove(players_id);
    	numOfPlayers--;
    	refresh();
    	 for(int key: players.keySet())
         {
           Player p=players.get(key);
           System.out.println(p+"  "+key);
         }
    }
    protected void sendComm(Communication comm)
    {
    	com=comm;
    }
    public static void g_main(String []args)
    {
        try 
        {
        	//System.out.println("Please enter a port and max number of players");
        	//int port=(new Scanner(System.in)).nextInt();
        	//int num=(new Scanner(System.in)).nextInt();
        	int port=Integer.parseInt(args[1]);
        	System.out.println("PORT!:" +port);
        	int num=Integer.parseInt(args[2]);
        	System.out.println("BROJ!:" +num);
        	int startingMoney=Integer.parseInt(args[3]);
        	System.out.println("POCETNA SUMA!:" +startingMoney);
        	
        	int timeToSpin=Integer.parseInt(args[4]);
        	System.out.println("VREME SPINA!:" +timeToSpin);
        	
            Game game=new Game(num,startingMoney,timeToSpin);
            Server server=new Server(game,port);
            Thread serverThread=new Thread(server);
            serverThread.start();
          
            game.start();
            
        	
        }
        catch (SocketException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
