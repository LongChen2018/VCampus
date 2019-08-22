package tech.zxuuu.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONUtils {

	protected final static List<Class> BASIC_CLASSES = new ArrayList<>(Arrays.asList(
			String.class, int.class, Integer.class, boolean.class, Boolean.class, 
			double.class, Double.class, float.class, Float.class, long.class, Long.class
	));
	
	public final static boolean isBasicClass(Class<?> clazz) {
		System.out.println("BC contains " + clazz.getName() + "? = " + JSONUtils.BASIC_CLASSES.contains(clazz));
		return JSONUtils.BASIC_CLASSES.contains(clazz);
	}

}
