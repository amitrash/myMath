package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class JunitTest {
	//////////////////////Monom test//////////////////////
	//@Test
	void testMultiplyMonom() {
		Monom m1 = new Monom("x^2");
		Monom result = new Monom("x^3");
		result.multiply(m1);
		assertEquals(result.get_coefficient(), 1);
		assertEquals(result.get_power(), 5);
	}
	@Test
	void testConstructerMonom() {
		Monom m1 = new Monom();
		assertEquals(m1.get_coefficient(), 0);
		assertEquals(m1.get_power(), 1);
	}
	@Test
	void testStringConstructer() {
		Monom m1 = new Monom("-2x^3");
		assertEquals(m1.get_coefficient(), -2);
		assertEquals(m1.get_power(), 3);
	}
	@Test
	void testDerivativeMonom() {
		Monom m1 = new Monom("2*x^2");
		m1.derivative();
		assertEquals(m1.get_coefficient(), 4);
		assertEquals(m1.get_power(), 1);
	}
	@Test
	void testAdd() {
		Monom m1 = new Monom("x^2");
		m1.add(new Monom("2x^2"));
		assertEquals(m1.get_coefficient(), 3);
		assertEquals(m1.get_power(), 2);
	}
	//////////////////////Polynom test//////////////////////
	@Test
	void testString() {
		Polynom p1 = new Polynom("2x^2 +3x +5");
		assertTrue(p1.equals(new Polynom("2x^2 +3x +5")));
	}
	@Test
	void testPolynom() {
		Polynom p1 = new Polynom();
		assertTrue(p1.isZero());
	}
	@Test
	void testPolynomString() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1, 2));
		p1.add(new Monom(3, 1));
		p1.add(new Monom(7, 0));
		Polynom p2 = new Polynom("x^2 +3x +7");
		assertTrue(p1.equals(p2));
	}

	@Test
	void testAddPolynom_able() {
		Polynom p1 = new Polynom("2*x^2 +5");
		Polynom p2 = new Polynom("x^2 + 2*x + 2");
		p1.add(p2);
		Polynom p3 = new Polynom("3*x^2 +2*x +7");
		assertTrue(p1.equals(p3));
	}

	@Test
	void testAddMonom() {
		Polynom p1 = new Polynom("2*x^2 +5");
		Monom m1 = new Monom("3*x");
		p1.add(m1);
		assertTrue(p1.equals(new Polynom("2*x^2 +3*x +5")));
	}

	@Test
	void testSubstract() {
		Polynom p1 = new Polynom("2*x^2 +5");
		Polynom p2 = new Polynom("-2*x +5");
		p1.substract(p2);
		assertTrue(p1.equals(new Polynom("2*x^2 +2*x")));
	}

	@Test
	void testMultiply() {
		Polynom p1 = new Polynom("2*x^2 +5");
		Polynom p2 = new Polynom("2*x^2 +2");
		p1.multiply(p2);
		assertTrue(p1.equals(new Polynom("4*x^4 +14*x^2 +10")));
	}

	@Test
	void testEqualsPolynom_able() {
		Polynom p1 = new Polynom("2*x^2 +5*x");
		Polynom p2 = new Polynom("2*x^2 +5*x");
		boolean ans = p1.equals(p2);
		assertTrue(ans);
	}

	@Test
	void testIsZero() {
		Polynom p1 = new Polynom("2*x^2 +5*x");
		Polynom p2 = new Polynom("2*x^2 +5*x");
		p1.substract(p2);
		boolean ans = p1.isZero();
		assertTrue(ans);
	}

	@Test
	void testCopy() {
		Polynom p1 = new Polynom("4*x +5");
		Polynom p2 = (Polynom)p1.copy();
		assertTrue(p1.equals(p2));
	}

	@Test
	void testDerivative() {
		Polynom p1 = new Polynom("2*x^2 +5*x");
		Polynom p2 = new Polynom("4*x +5");
		p1.derivative();
		assertTrue(p1.equals(p2));
	}

	@Test
	void testRoot() {
		Polynom p1 = new Polynom("2*x^2 +5*x");
		assertTrue(p1.root(-2, 3, 0.1) < 0.1);
	}

	@Test
	void testF() {
		Polynom p1 = new Polynom("2*x^2 +5*x");
		double ans = p1.f(2);
		assertEquals(ans, 18);
	}

	@Test
	void testArea() {
		Polynom p1 = new Polynom("x");
		assertTrue(p1.area(0, 2, 0.1) >= 2*2/4);
	}
	
	@Test
	void testIteretor() {
		Polynom p1 = new Polynom("x");
		Monom m1 = new Monom("x");
		Iterator<Monom> iter = p1.iteretor();
		Monom m2 = iter.next();
		assertEquals(m1.get_coefficient(), m2.get_coefficient());
		assertEquals(m1.get_power(), m2.get_power());
	}
}
