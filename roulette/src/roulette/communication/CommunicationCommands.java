/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.communication;

/**
 *
 * @author POOP
 */
public class CommunicationCommands 
{
    private CommunicationCommands() { }
    
    // main client server messages
    public static final String JOIN_MESSAGE = "JOIN";
    public static final String WELCOME_MESSAGE = "WELCOME";
    public static final String QUIT_MESSAGE = "QUIT";
    public static final String QUIT_RESPONSE = "BYE";
    public static final String STATE_REQUEST = "STATE";
    
    // other messages 
    public static final String MANQUE = "MANQUE";
    public static final String PASSE = "PASSE";
    public static final String ROUGE = "ROUGE";
    public static final String NOIR = "NOIR";
    public static final String PAIR = "PAIR";
    public static final String IMPAIR = "PAIR";
    public static final String SINGLE = "SINGLE_";
    public static final String COLUMN = "COLUMN_";
    public static final String ROW = "ROW_";
}
