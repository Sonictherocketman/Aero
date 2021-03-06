package com.brianschrader.aero;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A subclass of java.sql.Timestamp that provides some common functions to
 * reduce boilerplate code.
 * 
 * @author Brian Schrader
 * 
 */
public class Timestamp extends java.sql.Timestamp {

	private static final long serialVersionUID = 1L;

	/**
	 * Just like java.sql.Timestamp.
	 * 
	 * @param time
	 */
	public Timestamp(long time) {
		super(time);
	}
	
	/**
	 * Make a timestamp of right now.
	 */
	public Timestamp() {
		this(new Date().getTime());
	}
	
	/**
	 * Create an aero Timestamp from a sql Timestamp.
	 * 
	 * @param timestamp
	 */
	public Timestamp(java.sql.Timestamp t) {
		this(t.getTime());
	}
	
	/**
	 * Create an Aero timestamp from a standard date object.
	 * 
	 * @param date
	 */
	public Timestamp(java.util.Date d) {
		this(d.getTime());
	}
	
	/**
	 * Creates a Timestamp from the given string using the given pattern. If the
	 * pattern does not match, or an exception was thrown, returns null.
	 * 
	 * @param dateString
	 * @param pattern
	 * 
	 */
	public static Timestamp fromStringWithFormat(String dateString, String pattern) {
		Timestamp t = null;
		try {
			t = new Timestamp(new SimpleDateFormat(pattern).parse(dateString).getTime());
		} catch (ParseException e) {
			System.out.printf("There was an error parsing string %c with pattern %c\n", dateString, pattern);
		}
		return t;
	}
	
	/**
	 * Converts to a string with the pattern provided. Use SimpleDateFormat patterns.
	 * @param pattern
	 * @return formattedString
	 */
	public String toStringWithFormat(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date(this.getTime()));
	}
	
	/**
	 * Converts to a string with the pattern provided. Use SimpleDateFormat patterns.
	 * @param t
	 * @param pattern
	 * @return formattedString
	 */
	public static String toStringWithFormat(Timestamp t, String pattern) {
		return new SimpleDateFormat(pattern).format(new Date(t.getTime()));
	}

	/**
	 * Converts to a string with the pattern provided. Use SimpleDateFormat patterns.
	 * @param t
	 * @param pattern
	 * @return formattedString
	 */
	public static String toStringWithFormat(java.sql.Timestamp t, String pattern) {
		return new SimpleDateFormat(pattern).format(new Date(t.getTime()));
	}

	/**
	 * Sets the time of day to midnight.
	 */
	public void toMidnight() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(this.getTime()));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		this.setTime(calendar.getTime().getTime());
	}

	/**
	 * Adds the number of the given field type to the timestamp. FieldType is
	 * according to the Calendar field types. Sample Usage:
	 * {@code ts.add(Calendar.DAY_OF_WEEK, 1);}
	 * 
	 * @param fieldType
	 *            int
	 * @param daysToAdd
	 *            int
	 */
	public Timestamp add(Integer fieldAmount, Integer daysToAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(this.getTime()));
		c.add(fieldAmount, daysToAdd);
		this.setTime(c.getTime().getTime());
		return this;
	}

}
