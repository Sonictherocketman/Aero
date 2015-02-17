package com.brianschrader.aero.tests;

/**
 * A class that models the http://ip.jsontest.com/ response.
 * @author Brian Schrader
 *
 */
public class TestJsonObject extends Object {
	@SuppressWarnings("unused")
	private String ip;
	
	public TestJsonObject() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}