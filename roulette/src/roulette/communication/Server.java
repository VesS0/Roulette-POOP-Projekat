/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.communication;

import java.io.IOException;
import java.net.SocketException;
import java.util.Hashtable;
import roulette.server.Game;

/**
 *
 * @author POOP
 */
public class Server extends SocketCommunicator implements Runnable
{
    private Hashtable<Integer, PlayerProxy> connectedPlayers = new Hashtable<>();
    private Thread serverThread = new Thread(this);
    private int clientID;
    private Game game;
        
    public Server(Game _game) throws SocketException
    {
        super(SERVER_PORT);
        game = _game;
        serverThread.start();
    }
    
    public void run()
    {
        System.out.println("Game server started...");
        while( ! serverThread.interrupted() )
        {
            try
            {
                String message = receive();
				// Debug:
                // System.out.println("Server received: " + message);
                processMessage(message);
            }
            catch(IOException e) {  }
        }
        System.out.println("... game server ended.");
    }
    
    private void processMessage(String message) throws IOException
    {
        
        // Bilo bi dobro najpre proveriti da li je poznata komanda.
        // Ako nije - poslati izvoru nepoznate komande poruku da je nastala greska 
        
        // Bilo bi dobro nekako grupisati komande - na primer po kategoriji:
        // komande konekcije i komande igraca
        // Onda bi ova metoda prvo pitala nesto poput
        
        // if( connectionCommands.containCommandStartingWith(message) )
        // { ... obradi komandu konekcije ... }
        
        // if( playerCommands.containCommandStartingWith(message) )
        // { ... izdvoj ID igraca, obradi ostatak komande ... }
        
        if( message.equals(CommunicationCommands.JOIN_MESSAGE) )
        {
            PlayerProxy pp = new PlayerProxy(this, receivePacket.getAddress(), receivePacket.getPort());
            clientID++;
            connectedPlayers.put(clientID, pp);
            double playerStartMoney = game.newPlayer(pp);
            pp.send(CommunicationCommands.WELCOME_MESSAGE + " " + clientID + " " + playerStartMoney );
        }
        else
        if( message.startsWith(CommunicationCommands.QUIT_MESSAGE) )
        {
            // poruka se razdvaja na delove, a kao separator se koristi razmak
            String []parts = message.split(" ");
            // Trebalo bi proveriti da li se poruka sastoji od vise delova
            Integer id = Integer.parseInt(parts[1]);
            PlayerProxy pp = connectedPlayers.get(id);
            if( pp != null )
            {
                // Ovde treba javiti igracu, putem PlayerProxy objekta,
                // da treba da napusti igru
            }
        }
    }
}
