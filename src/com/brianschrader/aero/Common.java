package com.brianschrader.aero;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of common use functions. File this under misc.
 * 
 * @author Brian Schrader
 * 
 */
public class Common {

	/**
	 * Tests if the given object is not null and not empty in one method! Supa
	 * Convinient!
	 * 
	 * @author Brian Schrader
	 * @since June 23, 20141
	 * @param object
	 * @return Returns true if object is NOT null.
	 * 
	 * <pre>
	 * {@code if (Common.testForNull(object)) {
	 *  	//Do Stuff Here 
	 *  }}
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	public static boolean objectIsDefined(Object object) {
		if (object != null) {
			if (object.getClass() == String.class) {
				if (!((String) object).isEmpty()) {
					return true;
				}
			} else if (object.getClass() == List.class) {
				if (!((List<Object>) object).isEmpty()) {
					return true;
				}
			} else if (object.getClass() == ArrayList.class) {
				if (!((List<Object>) object).isEmpty()) {
					return true;
				}
			}
		} else {
			return true;
		}
		return false;
	}
}
