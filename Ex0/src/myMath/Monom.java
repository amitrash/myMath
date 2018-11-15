
package myMath;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where b is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 * @author Matti & Amit
 */
public class Monom implements function{
	
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
		if(b<0) {
			throw new RuntimeException("ERR: Power is real number (none negative) "+b);
		}
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	// ***************** add your code below **********************
	/**
	 * default constractor
	 */
	public Monom() {
		this.set_coefficient(0);
		this.set_power(1);
	}
	/**
	 * creats a monom from string
	 * @param m
	 */
	public Monom(String m) {
		if(m==null) {																		//null expression can't be monom
			throw new RuntimeException("ERR: Monom cannote be null " + m);					
		}
		if(m.charAt(0)=='+') {																//now we get the start of a new monom
			m=m.substring(1);
		}
		m=m.replace(" ", "");																//spaces eraser
		m=m.replace('y', 'x');																//'y' be readen like 'x'
		m=m.toLowerCase();																	//turns 'X' to 'x'
		int nonMinus = m.indexOf('^') +1;													//gets the index after '^'
		if((m.charAt(nonMinus) == '-') && (nonMinus != 0)) {								//if after the '^' there is a minus throw
			throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)1 " + m);
		}
		if(!m.contains("x")) {																//checks if there is 'x' in the string
			try {
				this.set_coefficient(Double.parseDouble(m));								//all of the string is the coefficient
				this.set_power(0);															
			}
			catch(Exception e) {
				throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)" + m);
			}
		}
		else {
			 if(m.indexOf("x") == 0 && m.contains("^")){									//if the coefficient is '1'
			   	 try {
			   		this.set_coefficient(1);
			   		this.set_power(Integer.parseInt(m.substring(m.indexOf('x')+2)));		//checks what is the power
					}
					catch(Exception e){
						throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)" + m);
					}
			 }
			 else if(m.indexOf("x") == m.length()-1 && m.contains("*")){					//if 'x' is in the end the power is '1'
			   	 try {
			   		this.set_coefficient(Integer.parseInt(m.substring(0,m.indexOf('x')-1)));//checks what is the coefficient
			 		this.set_power(1);
					}
					catch(Exception e){
						throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)" + m);
					}
			 }
			 else if(m.contains("*") && m.contains("^") && m.charAt(m.indexOf('x')-1)=='*' &&  m.charAt(m.indexOf('x')+1)=='^') { //checks if the '*' and '^' in a right place
					try {
						this.set_coefficient(Integer.parseInt(m.substring(0, m.indexOf('x')-1))); //sets the monom
			 			this.set_power(Integer.parseInt(m.substring(m.indexOf('x')+2)));
					}
					catch(Exception e){
						throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)" + m);
					}
			 }
			 else if(m.length()==1) {														//if the length of the string is 1 
				  this.set_coefficient(1);													//coefficient is 1
			      this.set_power(1);														//power is 1 and we got just 'x'
			 }
			 else if(!m.contains("*")&&m.contains("^")&& m.charAt(m.indexOf('x')+1)=='^'){	//if the string don't contains '*'
				 try {
						this.set_coefficient(Integer.parseInt(m.substring(0, m.indexOf('x')))); 
			 			this.set_power(Integer.parseInt(m.substring(m.indexOf('x')+2)));
					}
					catch(Exception e){
						throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)" + m);
					}
			 }
			 else if(!m.contains("*") && m.indexOf("x") == m.length()-1) {					//if the string don't contains '*' and power = 1
				  try {
						this.set_coefficient(Integer.parseInt(m.substring(0, m.indexOf('x')))); 
			 			this.set_power(1);
					}
					catch(Exception e){
						throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)" + m);
					}
			 }
			 else {
				 throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)" + m);
			 }
		}
	}	
	/**
	 * get coefficient
	 * @return
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * get power
	 * @return
	 */
	public int get_power() {
		return this._power;
	}
	/**
	 * use value of 'x' to calculate f(x)
	 * @param value of f(x)
	 */
	public double f(double x) {
		if(this.get_coefficient()==0){												
			return 0;
		}
		else if(this.get_power()==0) {
			return this.get_coefficient();
		}
		return ((this.get_coefficient()*Math.pow(x, this.get_power())));
	}
	/**
	 * derivative of a monom
	 */
	public void derivative() {
		int b=this.get_power()-1;
		double a=this.get_coefficient()*this.get_power();
		this._power=b;
		this._coefficient=a;
	}
	/**
	 * adds a monom to another monom just if the power is equal
	 * @param ot
	 */
	public void add(Monom ot) {
		if(ot.get_power()==this.get_power()) {
			this._coefficient=ot.get_coefficient()+this.get_coefficient();
		}
	}
	/**
	 * multiply two monoms
	 * @param ot
	 */
	public void multiply(Monom ot) {
		double a=(ot.get_coefficient())*(this.get_coefficient());
		int b=ot.get_power()+this.get_power();
		this._coefficient=a;
		this._power=b;
	}
	/**
	 * to string metod
	 * @return
	 */
	public String toString() {
		return " [" + _coefficient + "*X^" + _power + "]";
	}
	//****************** Private Methods and Data *****************
	
	
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}
	
	private double _coefficient; // 
	private int _power; 
	
}
