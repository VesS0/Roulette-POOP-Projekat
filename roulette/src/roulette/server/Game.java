/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import roulette.common.Bet;
import roulette.communication.CommunicationCommands;
import roulette.communication.PlayerProxy;
import roulette.communication.Server;

import java.io.IOException;
import java.lang.Object;
import java.util.Hashtable;
/**
 *
 * @author POOP
 */
public class Game 
{
	private Hashtable<Integer,Player> players=new Hashtable<Integer,Player>();
	private int numOfPlayers;
	private int maxNum;
    private double playerStartMoney = 200;
    private int id=1;
    private Croupier croupier;
    private Table table;
	// Pošto je samo jedna igra predviðena, mogao bi da se koristi
	// uzorak Unikat (singleton). Pomoæu tog uzorka se lako može
	// proširiti funkcionalnost da podrži veæi (ali kontrolisan)
	// broj igara.
    
    // Metoda kojom se prikljucuje nov igrac u igru
	// Metoda vraæa iznos koji je dodeljen igraèu
    protected boolean getState(){return croupier.check_state();}
    protected synchronized String player_bet(Player p, Bet b){ return croupier.player_bet(p, b);}
    protected Table getTable(){return table;}
    public Game(int num)
    {
    	table=new Table();
    	croupier=new Croupier(this);
    	maxNum=num;
    }
    protected void start()
    {
    	Thread croupierThread=new Thread(croupier);
    	Thread tableThread=new Thread(table);
    	croupierThread.start();
    	tableThread.start();
    }
    public double newPlayer(PlayerProxy pp) //s
    {
    	if (maxNum==numOfPlayers) playerStartMoney=0;
    	else
    	{
    		Player p = new Player(pp, playerStartMoney, this);
    		numOfPlayers++;
    		//Dodati igraèa u kolekciju igraèa koju pamti Game
    		players.put(id,p);
    		p.set(id++);
    	}
        return playerStartMoney;
    }
    
    // Metoda koja sluzi za "broadcast" - slanje poruke svim igracima
    public void sendMessageToAllPlayers(String message)
    {
        for(int i=0;i<id;i++)
        {
          Player p=players.get(i);
        	  if (p!=null) p.reportMessage(message);
        }
    }
    public synchronized void quit(int players_id)//
    {
    	players.remove(players_id);numOfPlayers--;
    }
    public static void main(String []args)
    {
        try 
        {
        	System.out.println("Please enter a port and max number of players");
        	int port=(new Scanner(System.in)).nextInt();
        	int num=(new Scanner(System.in)).nextInt();
            Game game=new Game(num);
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
