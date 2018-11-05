package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	// ******** add your code below *********
	/**
	 * compare to monom's by power
	 * @param m1
	 * @param m2
	 * @return three options negative, zero, positive
	 */
	public int compare(Monom m1, Monom m2) {
		return -(m1.get_power()-m2.get_power());									//negative: m1 > m2, zero m1 = m2, positive m1 < m2.
	}
	

}
