/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import roulette.communication.CommunicationCommands;

/**
 *
 * @author POOP
 */
public final class Table implements Runnable
{
	static final double DEGREE_PER_NUMBER=9.37;
	private int[] wheel={26,3,35,12,28,7,29,18,22,9,31,14,20,1,33,16,24,5,10,23,8,30,11,36,13,27,6,34,17,25,2,21,4,19,15,32,0};
	private int last_number=-1;
	private boolean spin;
	protected boolean allow_bets=true;
	private double w;
	
	
    public void run()
    {
    	while(!Thread.interrupted())
    	{
    		try{
    		synchronized(this){
    			while(!spin) wait();
    		}
       		double _old=w,_new=w,rotation=0;;
    		long time=0;
    		
    		while(_new>(0.01*w))
    		{
    			Thread.sleep(10); 
    			time+=10;
    			_new=_old*0.99;
    			rotation+=_old*time;
    			if((_old=_new)<0.2*w){ allow_bets=false; notifyAll();}
    		}
    		
    		double degree=(int) (rotation-Math.floor(rotation)+(Math.floor(rotation)%360));
    		last_number=wheel[(int) Math.floor(degree/DEGREE_PER_NUMBER)];
    		
    		allow_bets=true;
    		notify();
    		}
    		catch(InterruptedException ie){}
    		spin=false;
    	}
    }  
    
    public String state(){
    	if (allow_bets) return CommunicationCommands.PLACE_BETS;
    	else return CommunicationCommands.NO_BETS;
    }
    protected void spin(double w){spin=true; this.w=w;} //
    public int get_winner(){return last_number;}
	
}
