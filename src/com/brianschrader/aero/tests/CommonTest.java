package com.brianschrader.aero.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.brianschrader.aero.Common;

public class CommonTest {

	/**
	 * Tests a string object.
	 * @result Pass
	 */
	@Test
	public void testObjectIsDefinedStringPass() {
		String stringThatIsDefined = "I am defined. Yes I am!";
		assertTrue(Common.objectIsDefined(stringThatIsDefined));
	}

	/**
	 * Tests a string object.
	 * @result Fail
	 */
	@Test
	public void testObjectIsDefinedStringFail() {
		String stringThatIsDefined = "";
		assertFalse(Common.objectIsDefined(stringThatIsDefined));
	}

	/**
	 * Tests a list object.
	 * @result Pass
	 */
	@Test
	public void testObjectIsDefinedListPass() {
		List<String> listThatIsDefined = new ArrayList<String>();
		listThatIsDefined.add("Yay! I make this list not empty.");
		assertTrue(Common.objectIsDefined(listThatIsDefined));
	}

	/**
	 * Tests a list object.
	 * @result Fail
	 */
	@Test
	public void testObjectIsDefinedListFail() {
		List<String> listThatIsDefined = new ArrayList<String>();
		assertFalse(Common.objectIsDefined(listThatIsDefined));
	}
}
