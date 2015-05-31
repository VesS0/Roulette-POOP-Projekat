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
    public static final String BALANCE="BALANCE";
    
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
    
    // server messages
    public static final String PLACE_BETS="PYB";
    public static final String NO_BETS="RNVP";
    public static final String BET_MESSAGE="BET";
    public static final String REJECT_RESPONCE="REJECT";
    public static final String BUSY_RESPONCE="BUSY";
    public static final String NO_MONEY_RESPONCE="FUND";
    public static final String POSITIVE_RESPONCE="ACCEPT";
    public static final String WINNER="WINNUMBER";
    public static final String WIN="WIN";
    public static final String ERROR="ERROR MESSAGE";
}
