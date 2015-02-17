package com.brianschrader.aero.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brianschrader.aero.Networking;

public class NetworkingTest {

	/**
	 * Tests doGet
	 * @result Pass
	 */
	@Test
	public void testDoGetPass() {
		try {
			assertNotNull(Networking.doGet("http://brianschrader.com"));
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Tests doGet
	 * @result Fail
	 */
	@Test
	public void testDoGetFail() {
		try {
			assertNull(Networking.doGet(""));
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Tests getJsonFromUrl
	 * @result Pass
	 */
	@Test
	public void testGetJsonFromUrlPass() {
		try {
			assertNotNull(Networking.getJSONFromUrl("http://ip.jsontest.com/"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Tests getJsonFromUrl
	 * @result Fail
	 */
	@Test
	public void testGetJsonFromUrlFail() {
		try {
			assertNull(Networking.getJSONFromUrl("http://brianschrader.com"));
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Tests getObjectFromUrl
	 * @result Pass
	 */
	@Test
	public void testGetObjectFromUrlPass() {
		try {
			TestJsonObject t = (TestJsonObject) Networking.getObjectFromUrl("http://ip.jsontest.com/", TestJsonObject.class);
			assertNotNull(t);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Tests getObjectFromUrl
	 * @result Fail
	 */
	@Test
	public void testGetObjectFromUrlFail() {
		try {
			assertNull(Networking.getObjectFromUrl("http://brianschrader.com", TestJsonObject.class));
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}