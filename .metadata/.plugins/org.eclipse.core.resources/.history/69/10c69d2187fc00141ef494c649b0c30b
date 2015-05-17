/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.common;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author POOP
 */
public class Bets 
{
    private static ArrayList<Bet> bets = new ArrayList<>();
    
	private Bets() { }
	
    static {
        bets.add( new Manque() );
        bets.add( new Passe() );
        bets.add( new Rouge() );
    }
    
    public static Bet decodeBet(String str)
    {
        String []parts = str.split("_");
        for(Bet b : bets)
        {
            if(b.getClass().getName().toUpperCase().equals(parts[0]))
            {
                try 
                {
					Bet bb = null; 
					// Sledeæa linija je namerno zakomentarisana zato što
					// klasa Bet nema javnu metodu clone().
                    // bb = b.clone();						// Na mestu gde se trži stvaranje ovakvog objekta
															// treba postaviti iznos uloga
					
					// Ovako može da funkcioniše samo za one uloge koji
					// nemaju dodatne parametre. 
					// Za one uloge koji imaju dodatne parametre, poput Single uloga, potrebno
					// je obezbediti postavljanje tih parametara pre return.
                    return bb;
                } 
                catch (Exception ex) 
                {
                } 
            }
        }
        return null;
    }
}
