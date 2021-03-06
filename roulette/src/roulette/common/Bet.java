
package roulette.common;

/**
 *
 * @author POOP
 */
public abstract class Bet 
{
	private final int m_amount;
	
	public Bet(int amount) {
		m_amount = amount;
	}

	public int getAmount() {
		return m_amount;
	}
	
	public abstract String getMessage();
	public abstract int win(int number);
	
	public final String toString() {
		return getMessage();
	}
	
	public final boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
}
