package myMath;

public class Test {
	public static void main(String[] args) {
		
		////////////Monom test////////////
		
		Monom m1 = new Monom();								//default constractor m1 = 0*x^1
		Monom m2 = new Monom(1,2);							//constractor that gets coefficient and power m2 = 1*x^2
		Monom m3 = new Monom("-2*x^3");						//string constractor m3 = -2*x^3
		Monom m4 = new Monom(m2);							//copy constractor m4 = m2 = 1*x^2
		Monom m5 = new Monom(3,0);							//m5 = 3*x^0 = 3
		Monom m6 = new Monom(3,4);							//m6 = 3*x^4
		
		double ans = m3.f(-2);								//m3 get value at x = -2 ... -2*(-2)^3 = 16
		System.out.println("the value of f(x): " + ans);							
		
		m3.derivative();									//derivative of m3 = -6*x^2
		System.out.println("m3 after derivative:" + m3);					
		
		m2.add(m4);											//adds to m4 the Monom m2 ... new m2 = 1*x^2 + 1*x^2 = 2*x^2
		System.out.println("m2 after adding:" + m2);
		
		m4.multiply(m6);									//multiplies m4 and m6 ... new m4 = 1*x^2 * 3*x^4 = 3*x^6
		System.out.println("m4 after multiply:" + m4);
		
		//////////Polynom test////////////
		
		Monom m7 = new Monom(1,2);							//m7 = 1*x^2
		Polynom p1=new Polynom();							//default constractor p1 = [[ ]] ... empty polynom
		Polynom p2=new Polynom("5 +x^2 -2*x^5");			//string constractor p2 = [[-2*x^5] [1*x^4] [5*x^0]]	
		Polynom p3=new Polynom(p2);							//copy constractor p3 = p2 = [[-2*x^5] [1*x^4] [-2*x^3] [10*x^0]]
		Polynom p4=new Polynom("3*x^4 -2*x^2");				//p4 = [[3*x^4] [-2*x^2]]
		
		p1.add(m7);											//adds monom m7 to the polynom p1 = [[1*x^2]]
		System.out.println("p1 after adding: "+p1);							
		
		p1.add(p2);											//adds polymon p2 to the polynom p1 ... new p1 = [[-2*x^5] [1*x^4] [1*x^2] [5*x^0]]
		System.out.println("p1 after adding: "+p1);	
		
		p1.substract(p2);									//substracts polymon p2 from the polynom p1 ... new p1 = [[1*x^2]]
		System.out.println("p1 after substracting: "+p1);
		
		p1.multiply(p2);									//multiplies p1 and p2 ... new p1 = [[1.0*X^4]]
		System.out.println(p1);
		
		Polynom p5=new Polynom("2*x^5-3*x^2");				//p5 = [[2*x^5] [-3*x^2]]
		Polynom p6=new Polynom("2*x^5-3*x^2");				//p6 = [[2*x^5] [-3*x^2]]
		Polynom p7=new Polynom("2*x^4-3*x^2");				//p7 = [[2*x^4] [-3*x^2]]
		Polynom p8=new Polynom();							//default p8 = [[ ]] ... empty polynom
		
		System.out.println(p5.equals(p6));					//returns true
		System.out.println(p5.equals(p7));					//returns false
		System.out.println(p5.isZero());					//returns false
		System.out.println(p8.isZero());					//returns true
		
		Polynom_able able=p7.copy();						//copy from Polymon to Polynom_able
		System.out.println(able);
		able.derivative();									//derivative
		
		System.out.println(able);
		System.out.println(p5.root(-1,15,4));      			//root
		System.out.println(p6.f(2));						//value of f(x) in a given 'x'
		System.out.println(p6.area(-1, 1, 0.00001));		//area
	}
}
