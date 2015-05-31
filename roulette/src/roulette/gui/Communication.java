package roulette.gui;
import roulette.server.*;

import java.util.LinkedHashMap;
import java.util.PriorityQueue;

import roulette.server.Croupier;

public class Communication {
	public static Communication com;
	private PriorityQueue pq;
	private Croupier cr;
	private NewJFrame njf;
	private static CommunicatorOnly c=new CommunicatorOnly();
	public static class CommunicatorOnly{
		private CommunicatorOnly(){}
	}
	public void hello(Croupier crp)
	{
		cr=crp;
	}
	public void reset()
	{
		if (njf!=null) njf.resetTop();
	}
	public void send(PriorityQueue p,Croupier crp)
	{
		if (crp==null) cr=crp;
		pq=p;
		synchronized(this){notify();}
	}
	public void removeID(int id)
	{
		if (cr!=null) cr.removeID(id,c);
	}
	public PriorityQueue receive(NewJFrame nj) throws InterruptedException
	{
			if (njf==null) njf=nj;
			while (pq==null) synchronized(this){ wait();}
			PriorityQueue retQue=pq;
			pq=null;
			return retQue;
	}
	public synchronized void resetPlayers(LinkedHashMap Players)
	{
		njf.resetPlayers(Players);
	}
	public static Communication getCom()
	{
		if (com==null) com=new Communication();
		return com;
	}
	public void tellWinner(int win,Croupier crp)
	{
		if (crp.equals(cr))
		{
			njf.tellWinner(win,this);
		}
	}
	public void interrupts(NewJFrame jfr)
	{
		System.out.println("PREKINUO IZ COMMUNICATIONA");
		if (njf.equals(jfr))
			if (cr!=null) cr.interrupts(this);
			
	}
}
