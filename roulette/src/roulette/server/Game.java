/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import roulette.communication.PlayerProxy;
import roulette.communication.Server;

/**
 *
 * @author POOP
 */
public class Game 
{
    private double playerStartMoney = 200;

	// Pošto je samo jedna igra predviðena, mogao bi da se koristi
	// uzorak Unikat (singleton). Pomoæu tog uzorka se lako može
	// proširiti funkcionalnost da podrži veæi (ali kontrolisan)
	// broj igara.
    
    // Metoda kojom se prikljucuje nov igrac u igru
	// Metoda vraæa iznos koji je dodeljen igraèu
    public double newPlayer(PlayerProxy pp)
    {
        Player p = new Player(pp, playerStartMoney, this);
        // Dodati igraèa u kolekciju igraèa koju pamti Game
        return playerStartMoney;
    }
    
    // Metoda koja sluzi za "broadcast" - slanje poruke svim igracima
    public void sendMessageToAllPlayers(String message)
    {
        for(Player p : players)
            p.reportMessage(message);
    }
    
    public static void main(String []args)
    {
        try 
        {
            // Napravi igru i sve što je potrebno da bi se ona pokrenula i odvijala
        }
        catch (SocketException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
