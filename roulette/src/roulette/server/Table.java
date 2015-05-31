/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette.server;

import java.util.NoSuchElementException;

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
	private Croupier myCroupier;
	private volatile boolean spin;
	private volatile boolean spinMe;
	protected volatile boolean allow_bets=true;
	private double w;
	protected void setCroupier(Croupier c){myCroupier=c;}
	protected synchronized void waitToSpin() throws InterruptedException
	{
		while(!spinMe)
			synchronized(this)
			{
				wait();
			}
	}
	//private synchronized void spinIT()
	//{
	//	spinMe=true;
	//	notifyAll();
	//	System.out.println("U SPINITUU!!!");
	//}
    public void run()
    {
    	spinMe=true;
    	try{
    	while(!Thread.interrupted())
    	{
    	
    		while (!spin) synchronized(this){ wait();}
    		spin=false;
    		
       		double _old=w,_new=w,rotation=0;;
    		long time=0;
    		boolean wasNotIn=true;
    		while(_new>(0.01*w))
    		{
    			Thread.sleep(10); 
    			time+=10;
    			_new=_old*0.99;
    			rotation+=_old*time;
    			_old=_new;
    			rollTheWheel(_old);
    			if(_old<0.2*w && wasNotIn) { allow_bets=false; myCroupier.tellThem(allow_bets);wasNotIn=false;}
    		}
    		double degree=(int) (rotation-Math.floor(rotation)+(Math.floor(rotation)%360));
    		last_number=wheel[(int) Math.floor(degree/DEGREE_PER_NUMBER)%37];
    		System.out.println("WINNER"+last_number);
    		spinMe=true;
    		allow_bets=true;
    		myCroupier.tellThem(allow_bets);
    		synchronized(this){notify();}
    		}
    		
    	}catch(InterruptedException ie){}
    }  
    private void rollTheWheel(double degree)
    {
    	//roulette.gui.imageWheel.roll(degree);
    }
    public synchronized String state(){
    	if (allow_bets) return CommunicationCommands.PLACE_BETS;
    	else return CommunicationCommands.NO_BETS;
    }
    protected synchronized void spin(double w){spin=true; this.w=w;	spinMe=false; notify();} //
    public int get_winner(){return last_number;}
	
}
