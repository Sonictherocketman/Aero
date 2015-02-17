package com.brianschrader.aero.tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.brianschrader.aero.Timestamp;

public class TimestampTest {

	/**
	 * Test fromStringWithPattern
	 * @result Pass
	 */
	@Test
	public void testFromStringWithPatternPass() {
		String s = "12/04/2014";
		String p = "MM/dd/yyyy";
		Timestamp t = Timestamp.fromStringWithFormat(s, p);
		assertNotNull(t);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		assertTrue(c.get(Calendar.MONTH) == Calendar.DECEMBER);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 4);
		assertTrue(c.get(Calendar.YEAR) == 2014);
	}

	/**
	 * Test fromStringWithPattern
	 * @result Fail
	 */
	@Test
	public void testFromStringWithPatternFail() {
		String s = "01/05/2012";
		String p = "MM/dd/yyyy";
		Timestamp t = Timestamp.fromStringWithFormat(s, p);
		assertNotNull(t);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		assertFalse(c.get(Calendar.MONTH) == Calendar.DECEMBER);
		assertFalse(c.get(Calendar.DAY_OF_MONTH) == 4);
		assertFalse(c.get(Calendar.YEAR) == 2014);
	}

	/**
	 * Test toMidnight
	 * @result Pass
	 */
	@Test 
	public void testToMidnightPass() {
		Timestamp t = Timestamp.fromStringWithFormat("12/12/2014", "MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		assertEquals(c.get(Calendar.HOUR), 0);
		assertEquals(c.get(Calendar.SECOND), 0);
		assertEquals(c.get(Calendar.MINUTE), 0);
	}

	/**
	 * Test toMidnight
	 * @result Fail
	 */
	@Test 
	public void testToMidnightFail() {
		Timestamp t = Timestamp.fromStringWithFormat("12/12/2014", "MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		assertNotEquals(c.get(Calendar.HOUR), 1);
		assertNotEquals(c.get(Calendar.SECOND), 1);
		assertNotEquals(c.get(Calendar.MINUTE), 1);	
	}
	
	/**
	 * Test add.
	 * @result Pass
	 */
	@Test 
	public void testAddPass() {
		Timestamp t1 = Timestamp.fromStringWithFormat("12/12/2014", "MM/dd/yyyy");
		Timestamp t2 = Timestamp.fromStringWithFormat("12/13/2014", "MM/dd/yyyy");
		
		t1.add(Calendar.DAY_OF_WEEK, 1);
		
		assertEquals(t1.getTime(), t2.getTime());
	}
	
	/**
	 * Test add.
	 * @result Fail
	 */
	@Test 
	public void testAddFail() {
		Timestamp t1 = Timestamp.fromStringWithFormat("12/12/2014", "MM/dd/yyyy");
		Timestamp t2 = Timestamp.fromStringWithFormat("12/23/2014", "MM/dd/yyyy");
		
		t1.add(Calendar.DAY_OF_WEEK, 1);
		
		assertNotEquals(t1.getTime(), t2.getTime());
	}
}
