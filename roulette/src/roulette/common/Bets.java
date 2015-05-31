/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author POOP
 */
public class Bets 
{
    private static Map<String, Class<?>> m_bets = new HashMap<String, Class<?>>();
    
    // immutable for public use
    public final static Map<String, Class<?>> BetMap;
    
	private Bets() { }
    
	private static void registerBet(Class<?> ... bets) {
		for(Class<?> bet : bets) {
			m_bets.put(bet.getSimpleName().toLowerCase(), bet);
		}
	}
	
	static {
		registerBet(Column.class,
				Impair.class,
				Manque.class,
				Noir.class,
				Pair.class,
				Passe.class,
				Rouge.class,
				Row.class,
				Single.class);
		
		
		BetMap = Collections.unmodifiableMap(m_bets);
	}
	
	
	// usable by both client and server!
    public static Bet decodeBet(String str)
    {
        String[] parts = str.replace('_', ' ').split(" ");
        if(parts.length > 1) {
        	
        	parts[0] = parts[0].toLowerCase();
        	
        	if (m_bets.containsKey(parts[0])) {
        		// we found correct class to construct!
        		final Class<?> _class = m_bets.get(parts[0]);
        		
        		if (_class == null)
        			return null;
        		
        		final Object instance;
        		
        		try {
	        		if(parts.length > 2) {
						
	        			instance = _class.getConstructor(int.class, int.class).newInstance(Integer.parseInt(parts[1]),
									Integer.parseInt(parts[2]));
						
	        		} else {
	        		
	        			instance = _class.getConstructor(int.class).newInstance(Integer.parseInt(parts[1]));
	        			
	        		}
        		} catch (Exception e) {
					e.printStackTrace();
					
					return null;
				}
        		
        		return (Bet)instance;
        	} 
        }
        
        return null;
    }
}
