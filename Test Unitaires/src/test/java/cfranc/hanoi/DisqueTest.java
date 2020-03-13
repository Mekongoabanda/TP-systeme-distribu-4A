package cfranc.hanoi;

import static org.junit.Assert.*;

import org.junit.Test;

public class DisqueTest {
	
	Disque dSmall = new Disque(1);
	Disque dMedium = new Disque(2);
	Disque dTall = new Disque(3);

	@Test
	public void testCompareTo() {
		Disque petit = new Disque(1);
		Disque moyen = new Disque(2);
		Disque grand = new Disque(3);
		assertEquals(-1, petit.compareTo(moyen));
		assertEquals(-1, moyen.compareTo(grand));

		//TODO : à compléter
	}

	@Test
	public void compareTo_SmallTall_Negative(){
		int expected = -1;
		int actual = dSmall.compareTo(dTall);
		assertEquals(expected, actual);
	}

	@Test
	public void compareTo_MeduimSmall_Positive(){
		int expected = 1;
		int actual = dMedium.compareTo(dSmall);
		assertEquals(expected, actual);
	}

	@Test
	public void compareTo_TallSmall_Positive(){
		int expected = 1;
		int actual = dTall.compareTo(dSmall);
		assertEquals(expected, actual);
	}

	@Test
	public void compareTo_TallMedium_Positive(){
		int expected = 1;
		int actual = dTall.compareTo(dMedium);
		assertEquals(expected, actual);
	}

	@Test
	public void compareTo_EqualsDisk(){
		int expected = 0;
		Disque petit = new Disque(1);
		Disque moyen = new Disque(2);
		Disque grand = new Disque(3);
		int actual = dTall.compareTo(grand);
		int actual1 = dMedium.compareTo(moyen);
		int actual2 = dSmall.compareTo(petit);
		assertEquals(expected, actual);
		assertEquals(expected, actual1);
		assertEquals(expected, actual2);
	}
}