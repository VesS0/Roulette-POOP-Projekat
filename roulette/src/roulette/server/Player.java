/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import roulette.communication.PlayerProxy;

/**
 *
 * @author POOP
 */
public class Player implements Runnable
{
    private Thread playerThread = new Thread(this);
    private PlayerProxy playerProxy;
    
    public Player(PlayerProxy _playerProxy, double _startMoney, Game _game)
    {
        playerProxy = _playerProxy;
        playerThread.start();
    }
       
    public void run()
    {
        // napraviti ciklus u kojem nit ceka da PlayerProxy dostavi poruku
        // pozivom metode PlayerProxy.receive()
        
        // zatim analizirati sadrzaj poruke (koja komanda je u pitanju)
        // i postupiti saglasno primljenoj poruci
    }

    
    public void quitGame()
    {
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
