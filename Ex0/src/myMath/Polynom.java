package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.Comparator;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 * @author Matti & Amit
 */
public class Polynom implements Polynom_able{

	// ********** add your code below ***********
	/**
	 * default constractor with ArrayList
	 */
	public Polynom() {
		this.polynom = new ArrayList<Monom>();
	}
	/**
	 * copy constractor
	 * @param p1
	 */
	public Polynom(Polynom_able p1) {
		this.polynom = new ArrayList<Monom>();												
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext()) {																//insert for the old polynom to the new one
			this.polynom.add(iter.next());
		}
		polynom.sort(this.sort);															//sort by power
	}
	/**
	 * string constractor 
	 * @param p1
	 */
	public Polynom(String p1) {
		if(p1==null || p1=="") {															//null can't be a polynom
			throw new RuntimeException("ERR: Monom shape is a*x^b where b is a real number and a is an integer (summed a none negative)");
		}
		this.polynom = new ArrayList<Monom>();
		p1=p1.toLowerCase();																//turns 'X' to 'x'
		for(int i=p1.length()-1;i>1;i--) {
			if(p1.charAt(i)=='+') {															//finds the monoms places
				Monom m1=new Monom(p1.substring(i+1, p1.length()));							//creat the monom
				this.add(m1);																//adds to the new polynom
				p1=p1.substring(0, i);														//cuts the string
				i=p1.length()-1;
			}
			if( p1.charAt(i)=='-') {														//for a minus expression
				Monom m1=new Monom(p1.substring(i, p1.length()));							//creat the monom
				this.add(m1);																//adds to the new polynom
				p1=p1.substring(0, i);														//cuts the string
				i=p1.length()-1;
			}
		}
		if(p1.indexOf('+')>0) {																//if the string still containes plus 
			String str1=p1.substring(p1.indexOf('+'));										
			p1=p1.substring(0, p1.indexOf('+'));
			if(str1!=null) {
				Monom m1=new Monom(str1);													//creat the monom
				this.add(m1);																//adds to the new polynom
			}
		}
		else if(p1.indexOf('-')>0) {														//if the string still containes minus
			String str2=p1.substring(p1.indexOf('-'));
			p1=p1.substring(0, p1.indexOf('-'));
			if(str2!=null) {
				Monom m1=new Monom(str2);													//creat the monom
				this.add(m1);																//adds to the new polynom
			}
		}
		Monom m1=new Monom(p1);																//the last monom
		this.add(m1);
	}
	/**
	 * add a polynom to a polymon
	 * @param p1
	 */
	public void add(Polynom_able p1){
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext()) {
			this.add(iter.next());
		}
	}
	/**
	 * add a monom to a polynom
	 * @param m1
	 */
	public void add(Monom m1){
		if(m1.get_coefficient()==0) {														//if the coefficient is zero ignore
			return;
		}
		Iterator<Monom> iter=polynom.iterator();											
		while(iter.hasNext()){																//loop on all the polynom		
			Monom m2 = iter.next();							
			if(m2.get_power()==m1.get_power()) {											
				iter.remove();																//remove this monom from the polynom
				m2.add(m1);																	//add m1 to m2 
				if(m2.get_coefficient()!=0) {												//if it's not a zero monom add to the polynom
					polynom.add(m2);
					return;
				}
				else {
					return;	
				}
			}
		}
			polynom.add(m1);																//if this monom have a different power
		polynom.sort(this.sort);															//sort by power
	}
	/**
	 * substract a polynom from a polymon
	 * @param p1
	 */
	public void substract(Polynom_able p1){
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext()){																//loop on all the polynom
			Monom m1=iter.next();															
			Monom m2 =new Monom(-1*(m1.get_coefficient()),m1.get_power());					//multiply the coefficient in -1
			this.add(m2);																	//add to the polymon
		}
	}
	/**
	 * multiply two polynoms
	 * @param p1
	 */
	public void multiply(Polynom_able p1){
		Polynom newPolynom=new Polynom();
		Iterator<Monom> iter1=polynom.iterator();
		while(iter1.hasNext()) {	
			Monom m =iter1.next();															//creat a monom in the iterators place and remove it from the polynom
			iter1.remove();																	
			Iterator<Monom> iter2=p1.iteretor();
			while(iter2.hasNext()) {
				Monom m2=new Monom(m);														//creat a monom 
				m2.multiply(iter2.next());													//multiply two monoms and insert to the new polynom
				newPolynom.add(m2);
			}
		}
		this.add(newPolynom);
	}
	/**
	 * check if two polynoms are equal
	 * @param p1
	 * @return true or false
	 */
	public boolean equals (Polynom_able p1){
		Polynom p=new Polynom();
		p.add(p1);																			//insert the polynom to a new polynom to make sure it's sorted
		Iterator<Monom> iter1=polynom.iterator();
		Iterator<Monom> iter2=p.iteretor();
		
		while(iter1.hasNext() || iter2.hasNext()) {
			if(!iter1.hasNext() && iter2.hasNext()) {										//one of the polynoms is come to an end
				return false;																//not equal
			}
			if(!iter2.hasNext()&&iter1.hasNext()) {											//second polynom is come to an end
				return false;																//not equal
			}
			Monom m1=iter1.next();						
			Monom m2=iter2.next();
			if(m1.get_coefficient()!=m2.get_coefficient()||m1.get_power()!=m2.get_power()) {//check the coefficient and power
				return false;																//if the coefficient and power isn't equal
			}
			
		}
		return true;																		//if we got here they are equal
	}
	/**
	 * check polynom = zero
	 * @return true or false
	 */
	public boolean isZero() {
		Iterator<Monom> iter=this.polynom.iterator();
		while(iter.hasNext()) {
			if(iter.next().get_coefficient()!=0) {											//if the coefficient isn't equals zero it's false
				return false;
			}
		}
		return true;																		//if we got here it's true
	}
	/**
	 * copy polynom
	 * @return
	 */
	public Polynom_able copy() {
		Polynom p1=new Polynom();
		Iterator<Monom> iter=this.polynom.iterator();
		while(iter.hasNext()) {
			p1.add(iter.next());															//insert monom's to the new polynom
		}
		return (Polynom_able)p1;															//cast to Polynom_able
	}
	/**
	 * derivative to the polynom
	 * @return
	 */
	public Polynom_able derivative(){
		Polynom p1=new Polynom();
		Iterator<Monom> iter=this.polynom.iterator();
		while(iter.hasNext()) {
			Monom m=iter.next();
			iter.remove();
			m.derivative();																	//send to derivative of Monom
			p1.add(m);
		}
		this.add(p1);
		return this;
		
	}
	/**
	 * gives the value of 'x'
	 * f(x) > eps
	 * between x0, x1 ... x0<'x'<x1
	 * @param x0 start
	 * @param x1 end
	 * @param eps epsilon
	 * @return
	 */
	public double root(double x0, double x1, double eps) {
		double mid = (x0 + x1)/2;
		if(Math.abs(f(mid)) < eps) {
			return mid;
		}
		if(mid > 0 && x0 < 0) {
			return root(x0, mid, eps);
		}
		else {
			return root(mid, x1, eps);
		}
	}
	/**
	 * returns the value of f(x)
	 * @param x
	 */
	public double f(double x) {
		Iterator<Monom> iter=this.polynom.iterator();
		double y=0;
		while(iter.hasNext()) {
			y+=iter.next().f(x);															//doing f(x) for every monom and sums all in 'y'
		}
		return y;
	}
	/**
	 * Compute Riemann's Integral over this Polynom
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @param x0 start
	 * @param x1 end
	 * @param eps epsilon
	 */
	public double area(double x0,double x1, double eps){
		eps= Math.abs(eps);																	//|eps|
		double area=0;						
		while(x0<=x1) {
			area+= Math.abs(f(x0)*eps);														//sum of the rectangle
			x0+=eps;																		//x0 getting closer to x1 every time by eps
		}
		return area;
	}
	/**
	 * to string metod
	 * @return the polynom as a string
	 */
	public String toString() {
		return "Polynom = [" + polynom + "]";
	}
	/**
	 * creeating an iterator for Polynom_able
	 * @return Polynom_able as an iterator
	 */
	public Iterator<Monom> iteretor(){
		Iterator<Monom> iter=polynom.iterator();											//the same that we use in Polynom
		return iter;
	}	
	
	///////////////////////////////////private//////////////////////////////
	
	private ArrayList<Monom> polynom; 
	private final Comparator<Monom> sort = new  Monom_Comperator();
	
	
	
	
	
	
	
	
}	
	

